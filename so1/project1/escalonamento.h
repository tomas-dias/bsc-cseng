#ifndef GUARDA_escalonamento_H
#define GUARDA_escalonamento_H

// Estrutura de um processo
typedef struct processo
{
	int pid;
	int t_inicio;
	int cpu[3];
    int inout[2];
    int ncpu;
    int ninout;

}processos;

void coloca_posicao(processos pr, int pos, struct Queue* queue);
int elimina(processos arr[], int n, int x);
int verifica(processos pr, struct Queue* queue);
void ordena_menor(processos menor[], int n);
void ordena_estado(processos estado[], int n, struct Queue* run, struct Queue* blocked, struct Queue* ready);
void output(struct Queue* queue);
void fcfs(processos p[], int n, struct Queue* run, struct Queue* blocked, struct Queue* ready);
void rr(processos p[], int n, int q, struct Queue* run, struct Queue* blocked, struct Queue* ready);

#endif