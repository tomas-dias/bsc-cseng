#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include "escalonamento.c"
#define NUM_PROCESSOS 4
#define QUANTUM 3


// Le o ficheiro de input e chama as funcoes com os algoritmos de escalonamento implementados
int main(void)
{
    int opcao;
    char input[50];

    printf("\n");
    printf(" SIMULADOR DE ESCALONAMENTO\n");
    a:
    printf("\n");
    printf(" Insira uma das seguintes opcoes: \n");
    printf("\n");
    printf(" Insira 1 - FCFS\n");
    printf(" Insira 2 - RR\n");
    printf(" Insira 3 - Sair\n");
    printf("\n");
    printf(" Opcao: ");
    scanf("%d", &opcao);
    printf("\n");
    if(opcao != 1 && opcao != 2 && opcao != 3){
        while(opcao != 1 && opcao != 2 && opcao != 3){
            printf(" Opcao invalida\n");
            printf("\n");
            printf(" Insira uma das seguintes opcoes: \n");
            printf("\n");
            printf(" Insira 1 - FCFS\n");
            printf(" Insira 2 - RR\n");
            printf(" Insira 3 - Sair\n");
            printf("\n");
            printf(" Opcao: ");
            scanf("%d", &opcao);
            printf("\n");
        }
        goto b;
    }
    b:
    if(opcao == 3){
        printf(" Trabalho realizado por: Tomas Dias");
        printf("\n");
        printf("\n");
        return 0;
    }
    else{
        printf(" Insira input (.txt): ");
        scanf("%s", input);
        printf("\n");
        printf("\n");
        
        //

        int i = 0; 
        int nchar = 100;
        int j = 0;
        int a = 6;
        int b = 8;
        char aux[3], linha[NUM_PROCESSOS][nchar];
        processos p[NUM_PROCESSOS];
        
        FILE *fp = fopen(input, "r");
        
        while(fgets(linha[i], nchar, fp)){
            
            linha[i][strlen(linha[i]) - 1] = '\0';
            
            strncpy(aux, linha[i], 3);
            p[i].pid = atoi(aux);
            //sscanf(aux, "%d", &p[i].pid);
            //printf("%d", p[i].pid);
            //printf("\n");
            
            memset(aux, 0, sizeof(aux));
            
            strncpy(aux, linha[i]+4, 1);
            p[i].t_inicio = atoi(aux);
            //sscanf(aux, "%d", &p[i].t_inicio);
            //printf("%d", p[i].t_inicio);
            //printf("\n");
            
            memset(aux, 0, sizeof(aux));
            
            for(j = 0; j < 3; j++){
                strncpy(aux, linha[i]+a, 2);
                p[i].cpu[j] = atoi(aux);
                //sscanf(aux, "%d", &p[i].cpu[j]);
                //printf("%d", p[i].cpu[j]);
                memset(aux, 0, sizeof(aux));
                a+=4;
            }
            a = 6;
            //printf("\n");
            
            for(j = 0; j < 2; j++){
                strncpy(aux, linha[i]+b, 2);
                p[i].inout[j] = atoi(aux);
                //sscanf(aux, "%d", &p[i].inout[j]);
                //printf("%d", p[i].inout[j]);
                memset(aux, 0, sizeof(aux));
                b+=4;
            }
            b = 8;
            //printf("\n");
            i++;
        }
        
        fclose(fp);
        
        //
        
        struct Queue* run = createQueue(1);
        struct Queue* blocked = createQueue(4);
        struct Queue* ready = createQueue(4);
        
        //
        
        if(opcao == 1){
            fcfs(p, NUM_PROCESSOS, run, blocked, ready);
            goto a;
        }
        else if(opcao == 2){
            rr(p, NUM_PROCESSOS, QUANTUM, run, blocked, ready);
            goto a;
        }
    }
}