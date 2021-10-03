/*

Nota importante sobre o modo autom�tico

Para o modo autom�tico funcionar corretamente, � necess�rio que no ficheiro 2048.c, onde se encontram as fun��es baixo(),
cima(), direita() e esquerda(), o seguinte tro�o de c�digo se encontre comentado (de maneira a n�o fazer parte das fun��es):

     if(count > 0){
       random_spot_num(grelha, sz, 1);

Esta a��o impede que um novo n�mero seja gerado na grelha. Ao contr�rio do que acontece no modo iterativo, no modo autom�tico
n�o se pretende que um novo n�mero seja gerado ap�s executada uma das fun��es acima.

Tamb�m � de referir que se utilizou a fun��o mostrar() de maneira a que seja poss�vel acompanhar no ecr� o que acontece na grelha quando o modo auom�tico �
executado.

*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "2048.h"
#include "2048.c"

int main()
{
  char fNome[50], linha_mov[5], c_sz[3], linha_gre[13];
  int **grelha, x, y, sz, elemento;
  printf("Qual e o nome do ficheiro de texto? Nao se esqueca de colocar a extensao (.txt) no final: ");
  scanf("%s", fNome);
  FILE *fPointer= fopen(fNome, "r");
  fgets(c_sz, 3, fPointer);
  sscanf(c_sz, "%d", &sz);//sscanf transforma strings em int.

  grelha = malloc(sizeof(int) * sz);
  for(x = 0; x < sz; x++){
        grelha[x] = malloc(sizeof(int) * sz);
        fgets(linha_gre, 13, fPointer);
        for(y = 0; y < sz; y++){
            elemento= linha_gre[y] - '0';
            grelha[x][y]= elemento;
        }
  }

  mostrar(grelha, sz); // mostra a grelha inicial
  printf("\n");
  fgets(linha_mov, 6, fPointer);
  fclose(fPointer);

  int num_pecascomb_baixo = 0;
  int num_pecascomb_cima = 0;
  int num_pecascomb_direita = 0;
  int num_pecascomb_esquerda = 0;
  int num_total_pecascomb = 0;

  for(int i=0; linha_mov[i] != '\0'; i++){
      if(linha_mov[i] == 'B'){
         num_pecascomb_baixo += baixo(grelha, sz);
         mostrar(grelha, sz); // mostra a grelha atualizada
         printf("\n");
      }
      else if(linha_mov[i] == 'D'){
         num_pecascomb_direita += direita(grelha, sz);
         mostrar(grelha, sz); // mostra a grelha atualizada
         printf("\n");
      }
      else if(linha_mov[i] == 'E'){
         num_pecascomb_esquerda += esquerda(grelha, sz);
         mostrar(grelha, sz); // mostra a grelha atualizada
         printf("\n");
      }
      else if(linha_mov[i] == 'C'){
         num_pecascomb_cima += cima(grelha, sz);
         mostrar(grelha, sz); // mostra a grelha atualizada
         printf("\n");
      }
  }
  num_total_pecascomb = num_pecascomb_baixo + num_pecascomb_cima + num_pecascomb_direita + num_pecascomb_esquerda;
  printf("pecas combinadas: %d", num_total_pecascomb);
  printf("\n");

  return 0;

}
