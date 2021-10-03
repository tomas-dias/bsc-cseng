############################################################################################################
.data

	image_gray_input: .asciiz "./lena512noisy80k.gray"      
	image_gray_output_mean: .asciiz "./lena_gray.gray"            
	image_gray_output_median: .asciiz "./lena_median.gray" 
	input_buffer: .space 262144
	mean_buffer: .space 262144
	median_buffer: .space 262144
	output_buffer: .space 262144
	input_buffer_size: .word 262144
	output_buffer_size: .word 262144
	
	list: .space 9
	
.text	
############################################################################################################





######################################################
# main - Esta funcao e a responsavel por chamar as outras 
######################################################
main:
     
     la $a0, image_gray_input # Ficheiro a ser lido
     la $a1, input_buffer
     la $a2, input_buffer_size
     jal read_gray_image
     nop
     
     move $a0, $s1
     la $a1, output_buffer
     la $a2, output_buffer_size
     
     jal mean_filter
     nop
     
     #jal median_filter
     #nop
     
     jal write_gray_image_mean
     nop
     
     j fim
     nop
     
######################################################
# read_gray_image - Esta funcao abre o ficheiro e le a imagem para a memoria 
#
# Argumentos:
# a0 - nome do ficheiro a ser lido
# 
#
#
# Retorna:
# v0 - endereco de memoria onde esta a imagem (buffer com a imagem lida)
######################################################
read_gray_image:
                move $s0, $a0
                move $s1, $a1
                move $s2, $a2
                
                move $a0, $s0
                li $a1, 0             
                li $a2, 0             # Read only
                li $v0, 13            # Open
                syscall               # v0 = file descriptor
                
                
                # Ler o ficheiro
                move $a0, $v0         # File descriptor
                move $a1, $s1
                move $a2, $s2
                li $v0, 14 
                syscall
                
                # Fechar o ficheiro
                li $v0, 16
                syscall
                
                # Valor a ser retornado
                move $v0, $s1
                jr $ra
                nop

######################################################
# mean_filter -  Esta funcaoo calcula a convolucao de uma imagem A com uma mascara (matriz 3 x 3) e coloca o resultado numa matriz B
#
# Argumentos:
# a0 - buffer com a matriz A (imagem que vai servir de input)
# a1 - buffer com a matriz B (imagem com o filtro/mascara aplicada)
# a2 - dimensoes da imagem correspondente ao output
# 
# Retorna:
# v0 - buffer da imagem que vai servir de output (imagem sem ruido)
######################################################
mean_filter:
            li $t3, 260102								# Contador para o loop
            
            loop:
                 beq $t3, $zero, fim_do_loop						# Se este chegar a 0, sai do loop
                 nop
                 
                 lbu $t1, 513($a0) # byte escolhido
                 lbu $t2, 0($a1) # byte a ser escrito
                 
                 lbu $s0, -513($a0)   #load de x - 513    ou seja M(1,1)
                 addu $t2, $t2, $s0
                 		
                 lbu $s1, -512($a0)	# M(1,2)
                 addu $t2, $t2, $s1
                 
                 lbu $s2, -511($a0)	#M(1,3)
                 addu $t2, $t2, $s2
                 
                 lbu $s3, -1($a0)      #M(2,1)
                 addu $t2, $t2, $s3
                 
                 lbu $s4, 1($a0)     #M(2, 3)
                 addu $t2, $t2, $s4
                 
                 lbu $s5, 511($a0)     #M(3,1)
                 addu $t2, $t2, $s5
                 
                 lbu $s6, 512($a0)     #M(3,2)
                 addu $t2, $t2, $s6
                 
                 lbu $s7, 513($a0)        #M(3,3)
                 addu $t2, $t2, $s7
                 
                 div $t8, $t2, 9        # meter em t8, o byte escolhido somado com todos os da sua vizinhança por 9
                 sb $t8, 0($a1)       #store esse byte na posição do byte a ser escrito escolhido inicialmente
                 
                 #Incrementar enderecos para ler e escrever
                 addi $a0, $a0, 1
                 addi $a1, $a1, 1                #é como percorrer um array
                 addi $t3, $t3, -1
                 
                 move $v0, $a1             #Confirmar que os bytes stored vão para v0
                 j loop
                 nop
              
            fim_do_loop:
                        jr $ra
                        nop
                
######################################################
#
#
#
#
#
#
######################################################

median_filter:

	# Parecido ao da media, mas desta vez aplicamos um algoritmo de ordenação
	
	li $t3, 260102
	la $t8, list     #guardar valores num array, percorrê-los e aplicar um algoritmo de ordenacao
	
            loopmedian:
            
                 beq $t3, $zero, fim_do_loopmedian
                 nop
                 
                 lbu $t1, 513($a0) # byte escolhido
                 
                 lbu $t2, 0($a1)
                 sb $t2, 0($t8)
                 
                 lbu $s0, -513($a0)
                 sb $s0, 1($t8)
                 
                 lbu $s1, -512($a0)
                 sb $s1, 2($t8)
                 
                 lbu $s2, -511($a0)
                 sb $s2, 3($t8)
                 
                 lbu $s3, -1($a0)
                 sb $s3, 4($t8)
                 
                 lbu $s4, 1($a0)
                 sb $s4, 5($t8)
                 
                 lbu $s5, 511($a0)
                 sb $s5, 6($t8)
                 
                 lbu $s6, 512($a0)
                 sb $s6, 7($t8)
                 
                 lbu $s7, 513($a0)
                 sb $s7, 8($t8)
                 
                 #valores guardados, aplicar algoritmo
                 
                 algoritmo:
                 	
                 	lbu $t4, 0($t8)
                 	lbu $t9, 1($t8)
                 
                 	slt $t7, $t4, $t9
                 
                	beq $t7, 1, continua    #se o t7 for igual a 1, nao se mudam
                 	nop
                 	
                 	sb $t9, 0($t8)   #trocar posições
                 	sb $t4, 1($t8)
                 	
                 	continua:
                 	
                 		addi $t8, $t8, 1   #incrementar posição do array
                 	
                 		beq $t8, $zero, fimalgoritmo    #se chegar ao fim, acaba o algoritmo
                 		nop
                 	
                 		j algoritmo
                 		nop
                 
                 fimalgoritmo:
                 	
                 	lb $t7, 5($t8)
                 	
                 	sb $t7, 0($a1)
                 	
                 	addi $a0, $a0, 1
                 	addi $a1, $a1, 1
                 	addi $t3, $t3, -1
                 	
                 	move $v0, $a1
                 	
                 	j loopmedian
                 	nop
                 	
fim_do_loopmedian:
	jr $ra
	nop
                 
                 
                 




######################################################
# write_gray_image - Esta funcao cria o ficheiro que sera o output da imagem gray original (imagem sem ruido)
#
# Argumentos:
# a0 - nome do ficheiro a ser criado
# a1 - buffer com a imagem gray original
# a2 - comprimento do buffer com a imagem gray original
# 
# Retorna:
# v0 - endereco de memoria onde esta a imagem (buffer com a imagem escrita)
######################################################
write_gray_image_mean:
                 # Abrir o ficheiro para escrita
                 la   $a0, image_gray_output_mean     # Nome do ficheiro que servir� de output
                 li   $a1, 1
                 li   $a2, 0
                 li   $v0, 13                    
                 syscall                                            
                 move $s2, $v0                   
                 
                 # Escrever no ficheiro
                 li   $v0, 15                    # System call for write to file
                 move $a0, $s2                   # File descriptor
                 la $a1, output_buffer           # Address of buffer from which to write
                 li $a2, 262144                  # Hardcoded buffer length
                 syscall                         # Write to file
                 
                 # Fechar o ficheiro
                 li $v0, 16                    # System call for close file
                 move $a0, $s2                # File descriptor to close
                 syscall                         # Close file
                 
                 
                 
                 
                 
fim:
                li $v0, 10
                syscall

     
     
