#include <stdio.h>
#include <stdlib.h>
#include "colorSquares.c"

int main (void){

    int sz, x, y, tabuleiro[20][20];
    srand(time(0));
    int errosize = 1;
    int pont = 0;

    printf ("\n");
    printf("COLOR SQUARES \n \n");

    printf ("Funcionamento do jogo\n Um tabuleiro vertical e preenchido com quadrados de cores diferentes e o jogador pode remover grupos com a mesma cor.\n Nota: Um grupo consiste num conjunto de quadrados com lados partilhados. \n");
    printf (" Apos cada movimento, o tabuleiro e atualizado de acordo com as seguintes regras: \n");
    printf (" 1. gravidade: Os quadrados acima da  area vazia caem devido a gravidade. \n");
    printf (" 2. coluna: Quando toda a coluna esta vazia, esta colapsa movendo os blocos da direita para a esquerda de modo a fechar a separacao.\n \n");

    while (errosize==1){

        printf ("Indique o tamanho do tabuleiro (Um numero entre 1 e 20) - ");
        scanf("%d", &sz);
        if(sz>0 && sz<21){
            errosize=0;
        }
        else{

            printf("\n");
        }
    }



    for (y=0; y<sz; y++){
        for (x=0; x<sz; x++){

            tabuleiro[x][y] = (rand()%(4)) +1;
        }
    }

    printf ("\n");
    printf ("Considere as coordenadas (0,0) como o canto inferior esquerdo do tabuleiro \n \n");

    while (tabuleiro[0][0] != 0){

        int errozero = 1;
        mostrar (tabuleiro, sz);

        while (errozero == 1){

            printf ("\n");
            printf("Indique um valor para (x,y) (ex: 1,0) -  ");
            scanf("%d,%d",&x,&y);


            if (tabuleiro[x][y]==0 || x >= sz || y >= sz){
                printf ("\n");
                printf("Ocorreu um erro. Verifique se os valores de x e y se encontram entre 0 e %d e se correspondem a um numero do tabuleiro diferernte de '-' \n", sz-1);
            }
            else{
                errozero = 0;
            }
        }

        int marcar(int tabuleiro[20][20], int sz, int x, int y);
        void gravidade (int tabuleiro[20][20], int sz);
        void coluna (int tabuleiro[20][20], int sz);
        int pontuacao(int numquadrados);
        int jogada(int tabuleiro[20][20], int sz, int x, int y);

        pont = pont + jogada (tabuleiro, sz, x, y);
        printf("\n");
    }

    mostrar(tabuleiro, sz);
    printf("\n");
    printf("Pontuacao Final: %d \n", pont);
    printf("\n");
    printf("Trabalho Pratico de Programacao I desenvolvido por Rui Roque e Tomas Dias");
    printf("\n");

    return 0;
}