/*

Nota importante sobre o modo automático

Para o modo automático funcionar corretamente, é necessário que no ficheiro 2048.c, onde se encontram as funções baixo(),
cima(), direita() e esquerda(), o seguinte troço de código se encontre comentado (de maneira a não fazer parte das funções):

     if(count > 0){
       random_spot_num(grelha, sz, 1);

Esta ação impede que um novo número seja gerado na grelha. Ao contrário do que acontece no modo iterativo, no modo automático
não se pretende que um novo número seja gerado após executada uma das funções acima.

Também é de referir que se utilizou a função mostrar() de maneira a que seja possível acompanhar no ecrã o que acontece na grelha quando o modo auomático é
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
