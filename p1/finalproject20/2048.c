#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "2048.h"

int baixo(int **grelha, int sz){
    int count = 0;
    int num_pecascomb = 0;
    for(int y=0; y<sz; y++){
        for(int x=sz-1; x>=0; x--){
            if(grelha[x][y] != 0){
               if(x+1<=sz-1){
                 int aux=x+1;
                 while(aux<=sz-1){
                    if(grelha[aux][y] != 0){
                        if(grelha[aux][y] == grelha[x][y]){
                            grelha[aux][y] += grelha[x][y];
                            grelha[x][y] = 0;
                            num_pecascomb = num_pecascomb + 1;
                            count++;
                            break;

                        }
                        else if(aux-1 != x){
                            grelha[aux-1][y] = grelha[x][y];
                            grelha[x][y] = 0;
                            count++;
                            break;

                        }
                        else{
                            break;
                        }

                    }
                    if(aux == sz-1){
                       grelha[aux][y] = grelha[x][y];
                       grelha[x][y] = 0;
                       count++;
                    }
                    aux++;
                }

               }

            }

        }

    }
    if(count > 0){
       random_spot_num(grelha, sz, 1);
    }
    return num_pecascomb;
}

int cima(int **grelha, int sz){
    int count = 0;
    int num_pecascomb = 0;
    for(int y=0; y<sz; y++){
        for(int x=0; x<sz; x++){
            if(grelha[x][y] != 0){
               if(x-1>=0){
                 int aux=x-1;
                 while(aux>=0){
                    if(grelha[aux][y] != 0){
                        if(grelha[aux][y] == grelha[x][y]){
                            grelha[aux][y] += grelha[x][y];
                            grelha[x][y] = 0;
                            num_pecascomb = num_pecascomb + 1;
                            count++;
                            break;

                        }
                        else if(aux+1 != x){
                            grelha[aux+1][y] = grelha[x][y];
                            grelha[x][y] = 0;
                            count++;
                            break;

                        }
                        else{
                            break;
                        }

                    }
                    if(aux == 0){
                       grelha[aux][y] = grelha[x][y];
                       grelha[x][y] = 0;
                       count++;
                    }
                    aux--;
                }

               }

            }

        }

    }
    if(count > 0){
       random_spot_num(grelha, sz, 1);
    }
    return num_pecascomb;
}

int direita(int **grelha, int sz){
    int count = 0;
    int num_pecascomb = 0;
    for(int x=0; x<sz; x++){
        for(int y=sz-1; y>=0; y--){
            if(grelha[x][y] != 0){
               if(y+1<=sz-1){
                 int aux=y+1;
                 while(aux>=0){
                    if(grelha[x][aux] != 0){
                        if(grelha[x][aux] == grelha[x][y]){
                            grelha[x][aux] += grelha[x][y];
                            grelha[x][y] = 0;
                            num_pecascomb = num_pecascomb + 1;
                            count++;
                            break;

                        }
                        else if(aux-1 != y){
                            grelha[x][aux-1] = grelha[x][y];
                            grelha[x][y] = 0;
                            count++;
                            break;

                        }
                        else{
                            break;
                        }

                    }
                    if(aux == 0){
                       grelha[x][aux] = grelha[x][y];
                       grelha[x][y] = 0;
                       count++;
                    }
                    aux++;
                }

               }

            }

        }

    }
    if(count > 0){
       random_spot_num(grelha, sz, 1);
    }
    return num_pecascomb;
}

int esquerda(int **grelha, int sz){
    int count = 0;
    int num_pecascomb = 0;
    for(int x=0; x<sz; x++){
        for(int y=0; y<sz; y++){
            if(grelha[x][y] != 0){
               if(y-1>=0){
                 int aux=y-1;
                 while(aux>=0){
                    if(grelha[x][aux] != 0){
                        if(grelha[x][aux] == grelha[x][y]){
                            grelha[x][aux] += grelha[x][y];
                            grelha[x][y] = 0;
                            num_pecascomb = num_pecascomb + 1;
                            count++;
                            break;

                        }
                        else if(aux+1 != y){
                            grelha[x][aux+1] = grelha[x][y];
                            grelha[x][y] = 0;
                            count++;
                            break;

                        }
                        else{
                            break;
                        }

                    }
                    if(aux == 0){
                       grelha[x][aux] = grelha[x][y];
                       grelha[x][y] = 0;
                       count++;
                    }
                    aux--;
                }

               }

            }

        }

    }
    if(count > 0){
       random_spot_num(grelha, sz, 1);
    }
    return num_pecascomb;
}

void jogada(int **grelha, int sz){
            char input_direcao[2];
            int num_pecascomb_baixo = 0;
            int num_pecascomb_cima = 0;
            int num_pecascomb_direita = 0;
            int num_pecascomb_esquerda = 0;
            int num_total_pecascomb = 0;
            while(1){
                 mostrar (grelha, sz);
                 printf ("\n");


                 printf("Indique uma letra correspondente ao sentido que pretende(Letra B para baixo, Letra C para cima, Letra E para esquerda e Letra D para direita) -  ");
                 scanf("%s", input_direcao);

                 printf("\n");
                 if(strcmp(input_direcao, "B") == 0 || strcmp(input_direcao, "b") == 0){
                    num_pecascomb_baixo += baixo(grelha, sz);
                 }
                 else if(strcmp(input_direcao, "C") == 0 || strcmp(input_direcao, "c") == 0){
                    num_pecascomb_cima += cima(grelha, sz);
                 }
                 else if(strcmp(input_direcao, "D") == 0 || strcmp(input_direcao, "d") == 0){
                    num_pecascomb_direita += direita(grelha, sz);
                 }
                 else if(strcmp(input_direcao, "E") == 0 || strcmp(input_direcao, "e") == 0){
                    num_pecascomb_esquerda += esquerda(grelha, sz);
                 }
                 else if(strcmp(input_direcao, "F") == 0 || strcmp(input_direcao, "f") == 0){
                    num_total_pecascomb = num_pecascomb_baixo + num_pecascomb_cima + num_pecascomb_direita + num_pecascomb_esquerda;
                    printf(" pecas combinadas: %d ", num_total_pecascomb);
                    printf("\n");
                    printf("\n");
                    printf("Obrigado por jogar.");
                    printf("\n");
                    printf("\n");
                    printf("Trabalho realizado por Tomas Dias e Miguel Correia");
                    printf("\n");
                    exit(0);
                 }
                 else {
                    printf("Valor Invalido.");
                    printf("\n");
                 }
            }
}

void mostrar(int **grelha, int sz){
     printf("\n");
     for(int x=0 ; x<sz ; x++){
         for(int y=0 ; y<sz ; y++){
             if(grelha[x][y]!=0){
                if(y!=sz-1){
                   printf("| %d ", grelha[x][y]);

                }
                else{
                   printf("| %d |", grelha[x][y]);
                }
             }
             else{
                if(y!=sz-1){
                   printf("| - ", grelha[x][y]);
                }
                else{
                   printf("| - |", grelha[x][y]);
                }
             }
         }
         printf("\n");
     }
     printf("\n");
}

void random_spot_num(int **grelha, int sz, int count){
    int value = 0;
    while(value < count){
        int coluna = (rand() % (sz));
        int linha = (rand() % (sz));
        int numero = ((rand()%(2))+1)*2;
        if(grelha[linha][coluna] == 0){
            grelha[linha][coluna] = numero;
            value++;
        }
    }
}
