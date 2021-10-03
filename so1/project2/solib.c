#include "fila.c"
#include "solib.h"


//
// ESCALONAMENTO
//


// Coloca o processo na posicao dada
void coloca_posicao(processos pr, int pos, struct Queue* queue)
{
  int aux = queue->size;
  int arr[aux];
  int i = 0;
  
    while(!isEmpty(queue)){
      arr[i] = dequeue(queue);
      i++;
    }
  
    for(i = aux; i >= pos; i--){
      arr[i] = arr[i - 1];
    }
  
    arr[pos - 1] = pr.pid;
  
    for(i = 0; i < aux; i++){
      enqueue(queue, arr[i]);
    }
}


// Elimina um processo do array de processos
int elimina(processos arr[], int n, int x) 
{ 
   int i; 
   for (i = 0; i < n; i++) 
      if (arr[i].pid == x) 
         break; 
  
   if (i < n) 
   { 
     n = n - 1; 
     for (int j = i; j < n; j++) 
        arr[j] = arr[j+1]; 
   } 
   return n; 
}


// Verifica se um processo se encontra na fila
int verifica(processos pr, struct Queue* queue)
{
    for(int i = 0; i < queue->size; i++){
        int n = dequeue(queue);
        if(pr.pid == n){
            enqueue(queue, n);
            int aux=i+1;
            for(int i = aux; i < queue->size; i++){
                n = dequeue(queue);
                enqueue(queue, n);
            }
            return 1;
        }
        else{
            enqueue(queue, n);
        }
    }
    return 0;
}


// Ordena os processos pelos menores tempos de chegada 
void ordena_menor(processos menor[], int n) 
{
	processos aux;
	int i,j;

	for(i = 1; i < n; i++){
		for(j = 0; j < n-i; j++){
			if(menor[j].t_inicio > menor[j+1].t_inicio){
				aux = menor[j];
				menor[j] = menor[j+1];
				menor[j+1] = aux;
			}
		}
    }
}


// Ordena os processos pelo estado (RUN -> BLOCKED -> READY)
void ordena_estado(processos estado[], int n, struct Queue* run, struct Queue* blocked, struct Queue* ready)
{
    processos aux;
    int i, j;

    for(i = 1; i < n; i++){
        for(j = 0; j < n-i; j++){
            if(verifica(estado[j+1], run) == 1){
                aux = estado[j];
                estado[j] = estado[j+1];
                estado[j+1] = aux;
            }
            else if(verifica(estado[j+1], blocked) == 1 && verifica(estado[j], ready) == 1){
                aux = estado[j];
                estado[j] = estado[j+1];
                estado[j+1] = aux;
            }
            else if(verifica(estado[j+1], blocked) == 1 && verifica(estado[j], blocked) == 1){
                if(estado[j+1].pid == front(blocked)){
                    aux = estado[j];
                    estado[j] = estado[j+1];
                    estado[j+1] = aux;
                }
            }
            else if(verifica(estado[j+1], ready) == 1 && verifica(estado[j], ready) == 1){
                if(estado[j+1].pid == front(ready)){
                    aux = estado[j];
                    estado[j] = estado[j+1];
                    estado[j+1] = aux;
                }
            }
        }
    }
}


//
// GESTÃO DE MEMÓRIA
//


// Calcula o espaço ocupado na memória
int calcula_espaco_ocupado(int mem_ocupada[], processos* p, int num_processos)
{
    int indice_processo;
    int indice_memoria = 0;
    int ocupacao = 0;
    int i = 0;
    int var = 0;

    for(indice_processo = 0; indice_processo < num_processos; indice_processo++){
        if(p[indice_processo].esta_na_mem == 1){
            while(i < p[indice_processo].ninst){
                mem_ocupada[indice_memoria] = p[indice_processo].instmem[i];
                mem_ocupada[indice_memoria+1] = p[indice_processo].parammem[i];
                ocupacao += 2;
                indice_memoria += 2;
                i++;
            }
            while(var < 10){
                mem_ocupada[indice_memoria] = p[indice_processo].variaveis[var];
                ocupacao++;
                indice_memoria++;
                var++;
            }
            i = 0;
            var = 0;
        }
    }

    return ocupacao;
}


// Verifica se existe espaço livre na memória para guardar o processo
int verifica_espaco_livre(int mem[], paginas pag[], int n, int mem_ocupada[], int ocupacao, int ninst)
{
    int indice_memoria;
    int indice_pagina = 0;
    int i = 0;
    int count = 0;
    int aux = 0;
    int espaco_necessario;
    int espaco_existente;

    while(indice_pagina < n){
        for(indice_memoria = pag[indice_pagina].indice_inicial; indice_memoria <= pag[indice_pagina].indice_final; indice_memoria++){
            for(i = 0; i < ocupacao; i++){
                if(indice_memoria == mem_ocupada[i]){
                    aux++;
                    break;
                }
            }
            if(aux != 0){
                break;
            }
        }
        if(aux == 0){
            count++;
        }
        aux = 0;
        indice_pagina++;
    }

    espaco_necessario = (ninst * 2) + 10;
    espaco_existente = count * 10;

    if(espaco_necessario <= espaco_existente){
        return 1;
    }
    else{
        return 0;
    }
}


// Guarda o processo na memória
processos guarda_memoria(int mem[], paginas pag[], int n, int mem_ocupada[], int ocupacao, processos pr)
{
    int indice_memoria;
    int indice_mem_ocupada;
    int indice_pagina = 0;
    int i = 0;
    int j = 0; 
    int aux = 0;

    while(indice_pagina < n){
        for(indice_memoria = pag[indice_pagina].indice_inicial; indice_memoria <= pag[indice_pagina].indice_final; indice_memoria++){
            for(indice_mem_ocupada = 0; indice_mem_ocupada < ocupacao; indice_mem_ocupada++){
                if(indice_memoria == mem_ocupada[indice_mem_ocupada]){
                    aux++;
                    break;
                }
            }
            if(aux != 0){
                break;
            }
        }
        if(aux == 0){
            indice_memoria = pag[indice_pagina].indice_inicial;
            while(indice_memoria <= pag[indice_pagina].indice_final && i < pr.ninst){
                mem[indice_memoria] = pr.instrucoes[i];
                pr.instmem[i] = indice_memoria;
                mem[indice_memoria + 1] = pr.parametros[i];
                pr.parammem[i] = indice_memoria + 1;
                indice_memoria += 2;
                i++;
            }
            if(i == pr.ninst){
                while(indice_memoria <= pag[indice_pagina].indice_final && j < 10){
                    mem[indice_memoria] = 0;
                    pr.variaveis[j] = indice_memoria;
                    indice_memoria++;
                    j++;
                }
            }
        }
        aux = 0;
        indice_pagina++;
    }

    pr.esta_na_mem = 1;

    return pr;
}


// Executa uma instrução do processo
processos executa_instrucao(processos pr, int mem[], int inst, int var, int indice_var, struct Queue* blocked, struct Queue* stdout)
{
    int indice_instrucao;

    if(inst == 0){
        if(indice_var < 0 || indice_var > 9){
            printf(" > erro de segmentacao do processo %d ", pr.pid);
            printf("\n");
            pr.ninstmem++;
            return pr;
        }
        else{
            mem[var] = 0;
            pr.ninstmem++;
            return pr;
        }
    }
    else if(inst == 1){
        if(indice_var < 0 || indice_var > 9){
            printf(" > erro de segmentacao do processo %d ", pr.pid);
            printf("\n");
            pr.ninstmem++;
            return pr;
        }
        else{
            mem[var]++;
            pr.ninstmem++;
            return pr;
        }
    }
    else if(inst == 2){
        if(indice_var < 0 || indice_var > 9){
            printf(" > erro de segmentacao do processo %d ", pr.pid);
            printf("\n");
            pr.ninstmem++;
            return pr;
        }
        else{
            mem[var]--;
            pr.ninstmem++;
            return pr;
        }
    }
    else if(inst == 4){
        indice_instrucao = pr.ninstmem + mem[pr.parammem[pr.ninstmem]];
        if(indice_instrucao >= pr.ninst){
            printf(" > erro de segmentacao do processo %d ", pr.pid);
            printf("\n");
            pr.ninstmem++;
            return pr;
        }
        else{
            pr.ninstmem = indice_instrucao;
            return pr;
        }
    }
    else if(inst == 5){
        indice_instrucao = pr.ninstmem - mem[pr.parammem[pr.ninstmem]];
        if(indice_instrucao < 0){
            printf(" > erro de segmentacao do processo %d ", pr.pid);
            printf("\n");
            pr.ninstmem++;
            return pr;
        }
        else{
            pr.ninstmem = indice_instrucao;
            return pr;
        }
    }
    else if(inst == 7){
        if(indice_var < 0 || indice_var > 9){
            printf(" > erro de segmentacao do processo %d ", pr.pid);
            printf("\n");
            pr.ninstmem++;
            return pr;
        }
        else{
            if(mem[var] == 0){
                indice_instrucao = pr.ninstmem + 2;
                if(indice_instrucao >= pr.ninst){
                    printf(" > erro de segmentacao do processo %d ", pr.pid);
                    printf("\n");
                    pr.ninstmem++;
                    return pr;
                }
                else{
                    pr.ninstmem = indice_instrucao;
                    return pr;
                }
            }
        }
    }
    else if(inst == 8){
        if(indice_var < 0 || indice_var > 9){
            printf(" > erro de segmentacao do processo %d ", pr.pid);
            printf("\n");
            pr.ninstmem++;
            return pr;
        }
        else{
            enqueue(stdout, mem[var]);
            pr.ninstmem++;
            return pr;
        } 
    }
}


// Função que implementa a instrução fork
int frk(int mem[], paginas pag[], int num_paginas, int mem_ocupada[], int ocupacao, processos pr, processos* p, int n, int var, struct Queue* ready)
{
    processos pnovo;
    int i = 0;
    
    pnovo.pid = n + 1;
    pnovo.t_inicio = pr.t_inicio;
    mem[var] = pnovo.pid;
    pnovo.ninst = pr.ninst;
    pnovo.ninstmem = pr.ninstmem + 1;
    while(i < pnovo.ninst){
        pnovo.instrucoes[i] = pr.instrucoes[i];
        pnovo.parametros[i] = pr.parametros[i];
        i++;
    }
    pnovo = guarda_memoria(mem, pag, num_paginas, mem_ocupada, ocupacao, pnovo);
    n += 1;
    p = realloc(p, n * sizeof(processos));

    p[n-1] = pnovo;
    enqueue(ready, p[n-1].pid);

    return n;

}


//
// SIMULADOR SISTEMA OPERATIVO
//


// Implementa o simulador do Sistema Operativo com escalonamento RR e gestão de memória paginada
void so_pag(processos p[], int n, int q, int memoria, struct Queue* run, struct Queue* blocked, struct Queue* ready, struct Queue* stdout)
{
    int mem[memoria];
    int num_paginas = memoria / 10;
    paginas pag[num_paginas];
    int indice_pagina = 0;
    processos* pr = (processos*)malloc(n * sizeof(processos));
    int i, j, var, indice_memoria;
    int quantum = q;
    int mem_ocupada[200];
    int ocupacao;

    for(indice_memoria = 0; indice_memoria < memoria; indice_memoria++){
        mem[indice_memoria] = -100;
    }

    indice_memoria = 0;

    for(i = 0; i < n; i++){
        pr[i] = p[i];
        pr[i].ninstmem = 0;
        pr[i].ninout = 5;
        pr[i].esta_na_mem = 0;
    }

    for(i = 0; i < num_paginas; i++){
        pag[i].indice_inicial = indice_pagina;
        indice_pagina += 9;
        pag[i].indice_final = indice_pagina;
        indice_pagina += 1;
    }

    ordena_menor(pr, n);

    printf(" T   | "); printf("        stdout          | "); printf("         READY          | "); printf("          RUN           | "); printf("         BLOCKED        |");
    printf("\n");

    for(i = 0; i <= 60; i++){
        ordena_estado(pr, n, run, blocked, ready);
        for(j = 0; j < n; j++){
            if(i >= pr[j].t_inicio){
                if(i == pr[j].t_inicio && verifica(pr[j], ready) == 0){
                    ocupacao = calcula_espaco_ocupado(mem_ocupada, pr, n);
                    if(verifica_espaco_livre(mem, pag, num_paginas, mem_ocupada, ocupacao, pr[j].ninst) == 1){
                        pr[j] = guarda_memoria(mem, pag, num_paginas, mem_ocupada, ocupacao, pr[j]);
                        enqueue(ready, pr[j].pid);
                        goto c;
                    }
                    else{
                        n = elimina(pr, n, pr[j].pid);
                        printf(" > impossivel criar processo ");
                        printf("\n");
                    }
                }
                else if(verifica(pr[j], run) == 1){
                    a:
                    q--;
                    if(q < 0){
                        q = quantum;
                        dequeue(run);
                        ocupacao = calcula_espaco_ocupado(mem_ocupada, pr, n);
                        if(pr[j+1].t_inicio == i && verifica_espaco_livre(mem, pag, num_paginas, mem_ocupada, ocupacao, pr[j+1].ninst) == 1){
                            enqueue(ready, pr[j+1].pid);
                            enqueue(ready, pr[j].pid);
                        }
                        else if((pr[j+1].ninout == 0 && verifica(pr[j+1], blocked) == 1)){
                            if(pr[j+1].pid == front(blocked)){
                                dequeue(blocked);
                                enqueue(ready, pr[j+1].pid);
                                enqueue(ready, pr[j].pid);
                                pr[j+1].ninout = 5;
                            }
                            else{
                                coloca_posicao(pr[j+1], 1, blocked);
                                dequeue(blocked);
                                enqueue(ready, pr[j+1].pid);
                                enqueue(ready, pr[j].pid);
                                pr[j+1].ninout = 5;
                            }
                        }
                        else{
                            enqueue(ready, pr[j].pid);
                            goto c;
                        }
                    }
                    else{
                        if(pr[j].ninstmem >= pr[j].ninst || mem[pr[j].instmem[pr[j].ninstmem]] == 9){
                            q = quantum;
                            dequeue(run);
                            printf(" > processo %d acabou ", pr[j].pid);
                            printf("\n");
                            pr[j].esta_na_mem = 0;
                            n = elimina(pr, n, pr[j].pid);
                            if(n == 0){
                                printf("\n");
                                exit(0);
                            }
                            else{
                                j = j-1;
                            }
                        }
                        else if(mem[pr[j].instmem[pr[j].ninstmem]] == 6){
                            q = quantum;
                            pr[j].ninstmem++;
                            dequeue(run);
                            enqueue(blocked, pr[j].pid);
                            goto b;
                        }
                        else if(mem[pr[j].instmem[pr[j].ninstmem]] == 3){
                            ocupacao = calcula_espaco_ocupado(mem_ocupada, pr, n);
                            if(verifica_espaco_livre(mem, pag, num_paginas, mem_ocupada, ocupacao, pr[j].ninst) == 1){
                                n = frk(mem, pag, num_paginas, mem_ocupada, ocupacao, pr[j], pr, n, pr[j].variaveis[mem[pr[j].parammem[pr[j].ninstmem]]], ready);
                            }
                            else{
                                printf(" > fork sem sucesso");
                                printf("\n");
                            }
                            pr[j].ninstmem++;
                        }
                        else{
                            pr[j] = executa_instrucao(pr[j], mem, mem[pr[j].instmem[pr[j].ninstmem]], pr[j].variaveis[mem[pr[j].parammem[pr[j].ninstmem]]], mem[pr[j].parammem[pr[j].ninstmem]], blocked, stdout);
                        }
                    }
                }
                else if(verifica(pr[j], blocked) == 1){
                    b:
                    pr[j].ninout--;
                    if(pr[j].ninout < 0){
                        pr[j].ninout = 5;
                        if(pr[j].pid == front(blocked)){
                            dequeue(blocked);
                            enqueue(ready, pr[j].pid);
                            goto c;  
                        }
                        else{
                            coloca_posicao(pr[j], 1, blocked);
                            dequeue(blocked);
                            enqueue(ready, pr[j].pid);
                            goto c;
                        }    
                    }
                }
                else if(verifica(pr[j], ready) == 1){
                    c:
                    if(pr[j].pid == front(ready) && (!isFull(run))){
                        dequeue(ready);
                        enqueue(run, pr[j].pid);
                        goto a;
                    }
                }
            }
        }

        if(i > 9 && i <= 99){
            printf(" %d  | ", i);
        }
        else if(i > 99){
            printf(" %d | ", i);
        }
        else{
            printf(" %d   | ", i);
        }

        output(stdout);
        dequeue(stdout);

        output(ready);

        output(run);

        output(blocked);

        printf("\n");
        
    }
}


//
// AUXILIARES DE INPUT E OUTPUT
//


// Codifica uma instrução
int codifica_instrucao(char *instrucao)
{
    if(strcmp(instrucao, "ZER") == 0){
        return 0;
    }
    else if(strcmp(instrucao, "INC") == 0){
        return 1;
    }
    else if(strcmp(instrucao, "DEC") == 0){
        return 2;
    }
    else if(strcmp(instrucao, "FRK") == 0){
        return 3;
    }
    else if(strcmp(instrucao, "JFW") == 0){
        return 4;
    }
    else if(strcmp(instrucao, "JBK") == 0){
        return 5;
    }
    else if(strcmp(instrucao, "DSK") == 0){
        return 6;
    }
    else if(strcmp(instrucao, "JIZ") == 0){
        return 7;
    }
    else if(strcmp(instrucao, "PRT") == 0){
        return 8;
    }
    else{
        return 9;
    }  
}


// Funcao responsavel pelo output
void output(struct Queue* queue)
{
    if(queue->size == 0){
        printf("                        | ");
    }
    else{
        for(int i = 0; i < queue->size; i++){
            if(i == (queue->size) - 1){
                if(queue->size == 1){
                    int n = dequeue(queue);
                    printf(" %d                      | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 2){
                    int n = dequeue(queue);
                    printf(" %d                   | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 3){
                    int n = dequeue(queue);
                    printf(" %d                | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 4){
                    int n = dequeue(queue);
                    printf(" %d             | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 5){
                    int n = dequeue(queue);
                    printf(" %d          | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 6){
                    int n = dequeue(queue);
                    printf(" %d       | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 7){
                    int n = dequeue(queue);
                    printf(" %d    | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 8){
                    int n = dequeue(queue);
                    printf(" %d | ", n);
                    enqueue(queue, n);
                }
            }
            else{
                int n = dequeue(queue);
                printf(" %d ", n);
                enqueue(queue, n);
            }
        }
    }
}