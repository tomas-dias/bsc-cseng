estado_inicial(e([
               v(p('Manuel'),   [1,2,3,4,5,6,7,8], _),
               v(p('Joaquim'),  [1,2,3,4,5,6,7,8], _),
               v(p('Gabriel'),  [1,2,3,4,5,6,7,8], _)], 
              [v(p('Maria'),    [1,2,3,4,5,6,7,8], 8),
               v(p('Matilde'),  [1,2,3,4,5,6,7,8], 3),
               v(p('Julio'),    [1,2,3,4,5,6,7,8], 6),
               v(p('Madalena'), [1,2,3,4,5,6,7,8], 7),
               v(p('Ana'),      [1,2,3,4,5,6,7,8], 4)])).


dif([]).
dif([X|R]) :- \+ member(X,R), dif(R).

ve_restricoes(E) :- esq(E); frente(E); lado(E); cabeceira(E).

esq(e(_,[v(p(X),_,V)|R])) :- X = 'Manuel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Maria'),_,V1),R),
                                      posEsq(V,V1).

%esq(e(_,[v(p(X),_,V)|R])) :- X \= 'Manuel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]).

posEsq(X,R) :- X = 2 , R = 1.
posEsq(X,R) :- X = 3 , R = 2.
posEsq(X,R) :- X = 4 , R = 3.
posEsq(X,R) :- X = 5 , R = 4.
posEsq(X,R) :- X = 6 , R = 5.
posEsq(X,R) :- X = 7 , R = 6.
posEsq(X,R) :- X = 8 , R = 7.
posEsq(X,R) :- X = 1 , R = 8.


frente(e(_,[v(p(X),_,V)|R])) :- X = 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Maria'),_,V1),R),
                                          posFrente(V,V1).

%frente(e(_,[v(p(X),_,V)|R])) :- X \= 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]).

posFrente(X,R) :- X = 2, R = 8.
posFrente(X,R) :- X = 8, R = 2.
posFrente(X,R) :- X = 3, R = 7.
posFrente(X,R) :- X = 7, R = 3.
posFrente(X,R) :- X = 4, R = 6.
posFrente(X,R) :- X = 6, R = 4.                                                

lado(e(_,[v(p(X),_,V)|R])) :- X = 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Matilde'),_,V1),R),
                                          posLado(V,V1).

%lado(e(_,[v(p(X),_,V)|R])) :- X \= 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]).


posLado(X,R) :- (X = 2, R = 3); (X = 2, R = 1).
posLado(X,R) :- (X = 3, R = 4); (X = 3, R = 2).
posLado(X,R) :- (X = 4, R = 3); (X = 4, R = 5).
posLado(X,R) :- (X = 5, R = 4); (X = 5, R = 6).
posLado(X,R) :- (X = 6, R = 5); (X = 6, R = 7).
posLado(X,R) :- (X = 7, R = 6); (X = 7, R = 8).
posLado(X,R) :- (X = 8, R = 7); (X = 8, R = 1).
posLado(X,R) :- (X = 1, R = 8); (X = 1, R = 2).


cabeceira(e(_,[v(p(X),_,V)|R])) :- X = 'Gabriel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), posCabeceira(V).

%cabeceira(e(_,[v(p(X),_,V)|R])) :- X \= 'Gabriel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]).

posCabeceira(X) :- X = 5; X = 1. 

esc(L):-  sort(L,L1),
          esc_a(L1),
          nl.

esc_a(L):-  nl,
            esc_l(L).


esc_l([H|T]):- H = v(P,_,X), 
               write(P = X),
               nl,
               esc_l(T).                       


forCheck(e(Lni,[v(N,D,V)|Li]), e(Lnii,[v(N,D,V)|Li])):-  corta(V,Lni,Lnii).

  corta(_,[],[]).
  corta(V,[v(N,D,_)|Li], [v(N,D1,_)|Lii]):- delete(D,V,D1), corta(V,Li,Lii).