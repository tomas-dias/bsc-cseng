#include "fila.c"
#include "escalonamento.h"


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
            else if(menor[j].t_inicio == menor[j+1].t_inicio){
                if(menor[j].cpu[0] > menor[j+1].cpu[0]){
                    aux = menor[j];
                    menor[j] = menor[j+1];
                    menor[j+1] = aux;
                }
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
        }
    }
}


// Funcao responsavel pelo output
void output(struct Queue* queue)
{
    if(queue->size == 0){
        printf("                    | ");
    }
    else{
        for(int i = 0; i < queue->size; i++){
            if(i == (queue->size) - 1){
                if(queue->size == 1){
                    int n = dequeue(queue);
                    printf(" %d                | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 2){
                    int n = dequeue(queue);
                    printf(" %d           | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 3){
                    int n = dequeue(queue);
                    printf(" %d      | ", n);
                    enqueue(queue, n);
                }
                else if(queue->size == 4){
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


// Implementa o algaritmo de escalonamento FCFS
void fcfs(processos p[], int n, struct Queue* run, struct Queue* blocked, struct Queue* ready)
{
    processos pr[n];
    int i, j;

    for(i = 0; i < n; i++){
        pr[i] = p[i];
        pr[i].ncpu = 0;
        pr[i].ninout = 0;
    }

    ordena_menor(pr, n);
    
    for(i = 0; i <= 60; i++){
        ordena_estado(pr, n, run, blocked, ready);
        for(j = 0; j < n; j++){
            if(i >= pr[j].t_inicio){
                if(i == pr[j].t_inicio){
                    enqueue(ready, pr[j].pid);
                    goto c;
                }
                else if(verifica(pr[j], run) == 1){
                    a:
                    pr[j].cpu[pr[j].ncpu]--;
                    if(pr[j].cpu[pr[j].ncpu] < 0){
                        pr[j].ncpu++;
                        dequeue(run);
                        if(pr[j].ncpu > 2 || pr[j].cpu[pr[j].ncpu] == 0){
                          // Processo finalizado
                          n = elimina(pr, n, pr[j].pid);
                          j = j-1;
                        }
                        else{
                             enqueue(blocked, pr[j].pid);
                             goto b;
                        }
                    }
                }
                else if(verifica(pr[j], blocked) == 1){
                    b:
                    pr[j].inout[pr[j].ninout]--;
                    if(pr[j].inout[pr[j].ninout] < 0){
                        pr[j].ninout++;
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

        printf("READY");
        output(ready);

        printf("RUN");
        output(run);

        printf("BLOCKED");
        output(blocked);

        printf("\n");
    }

}


// Implementa o algoritmo de escalonamento Round Robin
void rr(processos p[], int n, int q, struct Queue* run, struct Queue* blocked, struct Queue* ready)
{
    processos pr[n];
    int i, j;
    int quantum = q;

    for(i = 0; i < n; i++){
        pr[i] = p[i];
        pr[i].ncpu = 0;
        pr[i].ninout = 0;
    }
    
    ordena_menor(pr, n);

    for(i = 0; i <= 60; i++){
        ordena_estado(pr, n, run, blocked, ready);
        for(j = 0; j < n; j++){
            //t:
            if(i >= pr[j].t_inicio){
                if(i == pr[j].t_inicio && verifica(pr[j], ready) == 0){
                    enqueue(ready, pr[j].pid);
                    goto c;
                }
                else if(verifica(pr[j], run) == 1){
                    a:
                    q--;
                    if(q < 0){
                        q = quantum;
                        dequeue(run);
                        if(pr[j].cpu[pr[j].ncpu] == 0){
                            pr[j].ncpu++;
                            if(pr[j].ncpu > 2 || pr[j].cpu[pr[j].ncpu] == 0){
                                // Processo finalizado
                                n = elimina(pr, n, pr[j].pid);
                                j = j-1;
                            }
                            else{
                                enqueue(blocked, pr[j].pid);
                                goto b;
                            }
                        }
                        else if(pr[j+1].t_inicio == i){
                            enqueue(ready, pr[j+1].pid);
                            enqueue(ready, pr[j].pid);
                        }
                        else if((pr[j+1].inout[pr[j+1].ninout] == 0 && verifica(pr[j+1], blocked) == 1)){
                            if(pr[j+1].pid == front(blocked)){
                                dequeue(blocked);
                                enqueue(ready, pr[j+1].pid);
                                enqueue(ready, pr[j].pid);
                                pr[j+1].ninout++;
                            }
                            else{
                                coloca_posicao(pr[j+1], 1, blocked);
                                dequeue(blocked);
                                enqueue(ready, pr[j+1].pid);
                                enqueue(ready, pr[j].pid);
                                pr[j+1].ninout++;
                            }
                        }
                        else{
                            enqueue(ready, pr[j].pid);
                            goto c;
                        }
                    }
                    else{
                        pr[j].cpu[pr[j].ncpu]--;
                        if(pr[j].cpu[pr[j].ncpu] < 0){
                            q = quantum;
                            pr[j].ncpu++;
                            dequeue(run);
                            if(pr[j].ncpu > 2 || pr[j].cpu[pr[j].ncpu] == 0){
                                // Processo finalizado
                                n = elimina(pr, n, pr[j].pid);
                                j = j-1;
                            }
                            else{
                                enqueue(blocked, pr[j].pid);
                                goto b;
                            }
                        }
                    }
                }
                else if(verifica(pr[j], blocked) == 1){
                    b:
                    pr[j].inout[pr[j].ninout]--;
                    if(pr[j].inout[pr[j].ninout] < 0){
                        pr[j].ninout++;
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

        printf("READY");
        output(ready);

        printf("RUN");
        output(run);

        printf("BLOCKED");
        output(blocked);

        printf("\n");
    }
}