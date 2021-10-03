#ifndef GUARDA_solib_H
#define GUARDA_solib_H

// Estrutura de um processo
typedef struct processo
{
    int pid;
    int t_inicio;
    int instrucoes[20];
    int parametros[20];
    int ninst;
    int variaveis[10];
    int instmem[20];
    int parammem[20];
    int ninstmem;
    int ninout;
    int esta_na_mem;

}processos;

typedef struct pagina
{
    int indice_inicial;
    int indice_final;

}paginas;

void coloca_posicao(processos pr, int pos, struct Queue* queue);
int elimina(processos arr[], int n, int x);
int verifica(processos pr, struct Queue* queue);
void ordena_menor(processos menor[], int n);
void ordena_estado(processos estado[], int n, struct Queue* run, struct Queue* blocked, struct Queue* ready);
int codifica_instrucao(char *instrucao);
int calcula_espaco_ocupado(int mem_ocupada[], processos* p, int num_processos);
int verifica_espaco_livre(int mem[], paginas pag[], int n, int mem_ocupada[], int ocupacao, int ninst);
processos guarda_memoria(int mem[], paginas pag[], int n, int mem_ocupada[], int ocupacao, processos pr);
processos executa_instrucao(processos pr, int mem[], int inst, int var, int indice_var, struct Queue* blocked, struct Queue* stdout);
int frk(int mem[], paginas pag[], int num_paginas, int mem_ocupada[], int ocupacao, processos pr, processos* p, int n, int var, struct Queue* ready);
void output(struct Queue* queue);
void so_pag(processos p[], int n, int q, int memoria, struct Queue* run, struct Queue* blocked, struct Queue* ready, struct Queue* stdout);

#endif