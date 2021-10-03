%Estado inicial
estado_inicial(e([[l,l,x],
                  [l,l,o],
                  [o,x,x],
                  [x,o,o]], x)).


%Terminais
terminal(E):- terminal(E,_).

%quando existem três peças iguais numa linha
terminal(e([L1,L2,L3,L4],_),X):- igual(L1,X); igual(L2,X); igual(L3,X); igual(L4,X).

%quando exitem três peças iguais na primeira coluna
terminal(e([[C1,_,_],[C1,_,_],[C1,_,_],[_,_,_]],_),C1):- C1 \= l.
terminal(e([[_,_,_],[C1,_,_],[C1,_,_],[C1,_,_]],_),C1):- C1 \= l.

%quando existem três peças iguais na segunda coluna
terminal(e([[_,C2,_],[_,C2,_],[_,C2,_],[_,_,_]],_),C2):- C2 \= l.
terminal(e([[_,_,_],[_,C2,_],[_,C2,_],[_,C2,_]],_),C2):- C2 \= l.

%quando existem três peças iguais na terceira coluna
terminal(e([[_,_,C3],[_,_,C3],[_,_,C3],[_,_,_]],_),C3):- C3 \= l.
terminal(e([[_,_,_],[_,_,C3],[_,_,C3],[_,_,C3]],_),C3):- C3 \= l.

%quando existem três peças iguais nas diagonais
terminal(e([[D1,_,_],[_,D1,_],[_,_,D1],[_,_,_]],_),D1):- D1 \= l.
terminal(e([[_,_,_],[D1,_,_],[_,D1,_],[_,_,D1]],_),D1):- D1 \= l.

terminal(e([[_,_,D2],[_,D2,_],[D2,_,_],[_,_,_]],_),D2):- D2 \= l.
terminal(e([[_,_,_],[_,_,D2],[_,D2,_],[D2,_,_]],_),D2):- D2 \= l.

%quando o tabuleiro está cheio (há empate)
terminal(e([L1,L2,L3,L4],_),e):- \+ (member(l,L1); member(l,L2); member(l,L3); member(l,L4)).

igual([A,A,A],A):- A=x;A=o.



%Função de utilidade
valor(E,V,_):- terminal(E,X), (X=x,V=1;X=o,V= -1;X=e, V=0).


%Operadores de transição
op1(e(E,X), joga(X,C), e(S,O)):- inv(X,O), jogaColuna(E, X, C, S).



jogaColuna([L1,L2,L3,[l,C2,C3]], X, 1, [L1,L2,L3,[X,C2,C3]]):- C2\=l, C3\=l.
jogaColuna([L1,L2,L3,[C1,l,C3]], X, 2, [L1,L2,L3,[C1,X,C3]]):- C3\=l.
jogaColuna([L1,L2,L3,[C1,C2,l]], X, 3, [L1,L2,L3,[C1,C2,X]]).
jogaColuna([L1,L2,[l,C2,C3],L4], X, 1, [L1,L2,[X,C2,C3],L4]):- C2\=l, C3\=l.
jogaColuna([L1,L2,[C1,l,C3],L4], X, 2, [L1,L2,[C1,X,C3],L4]):- C3\=l.
jogaColuna([L1,L2,[C1,C2,l],L4], X, 3, [L1,L2,[C1,C2,X],L4]).
jogaColuna([L1,[l,C2,C3],L3,L4], X, 1, [L1,[X,C2,C3],L3,L4]):- C2\=l, C3\=l.
jogaColuna([L1,[C1,l,C3],L3,L4], X, 2, [L1,[C1,X,C3],L3,L4]):- C3\=l.
jogaColuna([L1,[C1,C2,l],L3,L4], X, 3, [L1,[C1,C2,X],L3,L4]).
jogaColuna([[l,C2,C3],L2,L3,L4], X, 1, [[X,C2,C3],L2,L3,L4]):- C2\=l, C3\=l.
jogaColuna([[C1,l,C3],L2,L3,L4], X, 2, [[C1,X,C3],L2,L3,L4]):- C3\=l.
jogaColuna([[C1,C2,l],L2,L3,L4], X, 3, [[C1,C2,X],L2,L3,L4]).


inv(x,o).
inv(o,x).


joga3linha:- estado_inicial(E), agente(E).

agente(E):- terminal(E), valor(E,V,_), write(vitoria(V)), nl.
agente(Ei):- minimax_decidir(Ei,Op),
	         write(jogo(Op)),
	         op1(Ei,Op,Es),
	         write(Es),nl,
		     adversario(Es).

	  
adversario(E):- terminal(E), valor(E,V,_), write(vitoria(V)), nl.

adversario(Es):- 
		         write('coluna - '), read(I),
	             op1(Es,joga(_,I),Ess), write(Ess),nl,
	             agente(Ess).