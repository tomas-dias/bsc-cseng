/*

Nota importante sobre o modo iterativo

Importante verificar se no ficheiro 2048.c, onde se encontram as fun��es baixo(), cima(), direita() e esquerda(),
o seguinte tro�o de c�digo N�O se encontra comentado:

     if(count > 0){
        random_spot_num(grelha, sz, 1);

O tro�o de c�digo acima permite gerar um novo n�mero na grelha quando uma das fun�es acima acaba de ser executada.

*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "2048.h"
#include "2048.c"

int main(void)
{
    int sz, x, y, **grelha;

    srand(time(0));

    int sizeerror = 1;
    while(sizeerror==1){

        printf("Indique o tamanho da grelha (Um numero entre 1 e 10) - ");
        scanf("%d", &sz);
        if(sz>0 && sz<11){

            sizeerror=0;
        }
        else{

            printf("Valor invalido.");
            printf("\n");
        }
    }


    grelha = malloc(sizeof(int) * sz);
    for(x = 0; x < sz; x++){
        grelha[x] = malloc(sizeof(int) * sz);
        for(y = 0; y < sz; y++){
            grelha[x][y] = 0;
        }
    }
    random_spot_num(grelha, sz, 2);

    printf("\n");

    jogada(grelha, sz);

    return 0;
}

