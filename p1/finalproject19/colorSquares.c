#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include "colorSquares.h"

int marcar(int tabuleiro[][20], int sz, int x, int y){
	int valor=tabuleiro[x][y];
	int num_quadrados=0;
	tabuleiro[x][y]=0;
	if(tabuleiro[x][y+1]== valor && y<sz){
		num_quadrados+=marcar(tabuleiro, sz, x, y+1);
	}
	if(tabuleiro[x][y-1]==valor && y>0){
		num_quadrados+=marcar(tabuleiro, sz, x, y-1);
	}
	if(tabuleiro[x+1][y]== valor && x<sz){
		num_quadrados+=marcar(tabuleiro, sz, x+1, y);
	}
	if(tabuleiro[x-1][y]== valor && x>0){
		num_quadrados+=marcar(tabuleiro, sz, x-1, y);
	}
	return num_quadrados+1;
}

int pontuacao(int num_quadrados){
	return (num_quadrados*(num_quadrados+1))/2;
}

void gravidade(int tabuleiro[][20], int sz){ //i=coluna j=linha
	int i,posv, j;
	for(i=0; i<sz; i++){
		for(j=0;j<sz-1; j++){
			if(tabuleiro[i][j]==0){
				posv=j;
				for(j++;j<sz;j++){
					if(tabuleiro[i][j]!=0){
						tabuleiro[i][posv++]= tabuleiro[i][j];
						tabuleiro[i][j]=0;
					}
				}
			}
		}
	}
}

void coluna(int tabuleiro[][20], int sz){ //i=coluna j=linha
	int i, colv, j;
	for(i=0; i<sz-1; i++){
		if(tabuleiro[i][0]== 0){
			colv=i;
			for(i++;i<sz;i++){
				if(tabuleiro[i][0]!= 0){
					for(j=0;j<sz;j++){
						tabuleiro[colv][j]=tabuleiro[i][j];
						tabuleiro[i][j]=0;
					}
					colv++;
				}
			}
		}
	}
}

int jogada(int tabuleiro[][20], int sz,int x, int y){
	int pontos;
	pontos= pontuacao(marcar(tabuleiro, sz, x, y));
	gravidade(tabuleiro, sz);
	coluna(tabuleiro, sz);
	return pontos;
}

void mostrar (int tabuleiro [20][20], int sz){

    for (int y=sz-1; y>=0; y--){
        for (int x=0; x<sz; x++){

            if (tabuleiro[x][y]==0){
                printf(" - ");
            }
            else{
                printf(" %d ", tabuleiro[x][y]);
            }
         }
    printf("\n");
  }
}