#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include "solib.c"
#define QUANTUM 3
#define MEMORIA 200


int main()
{
    char input[10];
    int indice_linha = 0;
    int i;
    int a = 4;
    int b = 0;
    int nchar = 100;
    int count = 0;
    int num_processos;
    int pid;
    char ini[] = "INI";
    char c;
    char aux[4];

    
    printf(" Insira input (.txt): ");
    scanf("%s", input);
    printf("\n");
    

    FILE *fp = fopen(input, "r");

    // Calcula o número de linhas do ficheiro
    for(c = getc(fp); c != EOF; c = getc(fp)){
        if(c == '\n'){
            count = count + 1; 
        }
    }

    // Reset do pointer do ficheiro
    rewind(fp);

    // Calcula o número de processos existentes no ficheiro
    char *pos;
    char str[nchar];
    int index;

    num_processos = 0;

    while ((fgets(str, nchar, fp)) != NULL)
    {
        index = 0;

        while ((pos = strstr(str + index, ini)) != NULL)
        {
            index = (pos - str) + 1;

            num_processos++;
        }
    }

    rewind(fp);

    char linha[count][nchar];
    processos p[num_processos];

    for(i = 0; i < num_processos; i++){
        pid = i + 1;
        p[i].pid = pid;
        p[i].ninst = 0;
    }

    i = 0;

    while(fgets(linha[indice_linha], nchar, fp)){

        strncpy(aux, linha[indice_linha], 3);
        
        if(strcmp(aux, "INI") == 0){
            if(b == 0){
                memset(aux, 0, sizeof(aux));
                strncpy(aux, linha[indice_linha]+a, 2);
                p[i].t_inicio = atoi(aux);
                memset(aux, 0, sizeof(aux));
                b++;
            }
            else{
                i++;
                memset(aux, 0, sizeof(aux));
                strncpy(aux, linha[indice_linha]+a, 2);
                p[i].t_inicio = atoi(aux);
                memset(aux, 0, sizeof(aux));
            }
        }
        else {
            p[i].instrucoes[p[i].ninst] = codifica_instrucao(aux);
            memset(aux, 0, sizeof(aux));
            strncpy(aux, linha[indice_linha]+a, 2);
            p[i].parametros[p[i].ninst] = atoi(aux);
            memset(aux, 0, sizeof(aux));
            p[i].ninst++;
        }

        indice_linha++;
    }
    
    /* Teste 
    for(i = 0; i < num_processos; i++){
        printf(" %d ", p[i].pid);
        printf("\n");
        printf(" %d ", p[i].t_inicio);
        printf("\n");
        int ninst = p[i].ninst;
        for(p[i].ninst = 0; p[i].ninst < ninst; p[i].ninst++){
            printf(" %d ", p[i].instrucoes[p[i].ninst]);
            printf("\n");
            printf(" %d ", p[i].parametros[p[i].ninst]);
            printf("\n");
        }
        printf("\n");
    }
    */

    fclose(fp);

    struct Queue* run = createQueue(1);
    struct Queue* blocked = createQueue(8);
    struct Queue* ready = createQueue(8);
    struct Queue* stdout = createQueue(1);

    so_pag(p, num_processos, QUANTUM, MEMORIA, run, blocked, ready, stdout);
    
    return 0;
}

