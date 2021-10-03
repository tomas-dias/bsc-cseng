
estado_inicial(e(0,0,3,2)).


%estado_inicial(e(0,0,0,0)).

maximo(7).

%Terminal - estado final que neste é caso quando n existe paus em nenhuma das filas 
terminal(e(0,0,0,0)).

%Função de utilidade - valores positivos para o max e valores negativos para o min (função que dá valor ao estado terminal)
valor(E,-1,P):- terminal(E),  R is P mod 2, R=1.
valor(E,1,P):- terminal(E),  R is P mod 2, R=0.


%Operação que retira N paus da 1ª fila
op1(e(N1,N2,N3,N4),ret(1,N),e(N11,N2,N3,N4)):-  numero(1,N),
                                                N11 is N1 - N, N11>= 0.

%Operação que retira N paus da 2ª fila
op1(e(N2,N1,N3,N4),ret(2,N),e(N2,N11,N3,N4)):-  numero(1,N),
                                                N11 is N1 - N, N11>= 0.

%Operação que retira N paus da 3ª fila
op1(e(N1,N2,N3,N4),ret(3,N),e(N1,N2,N33,N4)):-  numero(1,N),
                                                N33 is N3 - N, N33>= 0.

%Operação que retira N paus da 4ª fila
op1(e(N1,N2,N3,N4),ret(4,N),e(N1,N2,N3,N44)):-  numero(1,N),
                                                N44 is N4 - N, N44>= 0.


%Verifica se pode retirar N paus da fila inserida
 numero(N,N).
 numero(L, N1):-maximo(M), L<M, L1 is L+1, numero(L1,N1).


jogaNim:- estado_inicial(E), agente(E).

agente(E):- terminal(E), valor(E,V,_), write(vitoria(V)), nl.
agente(Ei):- minimax_decidir(Ei,Op),
	         write(jogo(Op)),
	         op1(Ei,Op,Es),
	         write(Es),nl,
		     adversario(Es).

	  
adversario(E):- terminal(E), valor(E,V,_), write(vitoria(V)), nl.

adversario(Es):- 
		         write('coluna - '), read(I),
                 write('npaus - '), read(J),
	             op1(Es,ret(I,J),Ess), write(Ess),nl,
	             agente(Ess).