estado_inicial(e([
               v(p('Manuel'),   [1,2,3,4,5,6,7,8,9,10,11,12], _),
               v(p('Joaquim'),  [1,2,3,4,5,6,7,8,9,10,11,12], _),
               v(p('Gabriel'),  [1,2,3,4,5,6,7,8,9,10,11,12], _),
               v(p('Bruno'),    [1,2,3,4,5,6,7,8,9,10,11,12], _),
               v(p('Rui'),      [1,2,3,4,5,6,7,8,9,10,11,12], _),
               v(p('Tomás'),    [1,2,3,4,5,6,7,8,9,10,11,12], _)], 
              [v(p('Maria'),    [1,2,3,4,5,6,7,8,9,10,11,12], 8),
               v(p('Matilde'),  [1,2,3,4,5,6,7,8,9,10,11,12], 3),
               v(p('Sofia'),    [1,2,3,4,5,6,7,8,9,10,11,12], 11),
               v(p('Julio'),    [1,2,3,4,5,6,7,8,9,10,11,12], 12),
               v(p('Madalena'), [1,2,3,4,5,6,7,8,9,10,11,12], 7),
               v(p('Ana'),      [1,2,3,4,5,6,7,8,9,10,11,12], 4)])).
 
dif([]).
dif([X|R]) :- \+ member(X,R), dif(R).

ve_restricoes(E) :- esq(E); frente(E); lado(E); cabeceira(E); dir(E).

esq(e(_,[v(p(X),_,V)|R])) :- X = 'Manuel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Maria'),_,V1),R),
                                      posEsq(V,V1).

esq(e(_,[v(p(X),_,V)|R])) :- X = 'Bruno', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Ana'),_,V1),R),
                                      posEsq(V,V1).


%esq(e(_,[v(p(X),_,V)|R])) :- X \= 'Manuel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]).

posEsq(X,R) :- X = 2 , R = 1.
posEsq(X,R) :- X = 3 , R = 2.
posEsq(X,R) :- X = 4 , R = 3.
posEsq(X,R) :- X = 5 , R = 4.
posEsq(X,R) :- X = 6 , R = 5.
posEsq(X,R) :- X = 7 , R = 6.
posEsq(X,R) :- X = 8 , R = 7.
posEsq(X,R) :- X = 9 , R = 8.
posEsq(X,R) :- X = 10 , R = 9.
posEsq(X,R) :- X = 11 , R = 10.
posEsq(X,R) :- X = 12 , R = 11.
posEsq(X,R) :- X = 1 , R = 12.

dir(e(_,[v(p(X),_,V)|R])) :- X = 'Rui', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Sofia'),_,V1),R),
                                      posDir(V,V1).

dir(e(_,[v(p(X),_,V)|R])) :- X = 'Tomás', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Matilde'),_,V1),R),
                                      posDir(V,V1).

posDir(X,R) :- X = 12 , R = 1.
posDir(X,R) :- X = 1 , R = 2.
posDir(X,R) :- X = 2 , R = 3.
posDir(X,R) :- X = 3 , R = 4.
posDir(X,R) :- X = 4 , R = 5.
posDir(X,R) :- X = 5 , R = 6.
posDir(X,R) :- X = 6 , R = 7.
posDir(X,R) :- X = 7 , R = 8.
posDir(X,R) :- X = 8 , R = 9.
posDir(X,R) :- X = 9 , R = 10.
posDir(X,R) :- X = 10 , R = 11.
posDir(X,R) :- X = 11 , R = 12.


frente(e(_,[v(p(X),_,V)|R])) :- X = 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Maria'),_,V1),R),
                                          posFrente(V,V1).

%frente(e(_,[v(p(X),_,V)|R])) :- X \= 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]).

posFrente(X,R) :- X = 2, R = 12.
posFrente(X,R) :- X = 12, R = 2.
posFrente(X,R) :- X = 3, R = 11.
posFrente(X,R) :- X = 11, R = 3.
posFrente(X,R) :- X = 4, R = 10.
posFrente(X,R) :- X = 10, R = 4.
posFrente(X,R) :- X = 5, R = 9.
posFrente(X,R) :- X = 9, R = 5.
posFrente(X,R) :- X = 6, R = 8.
posFrente(X,R) :- X = 8, R = 6.                                                

lado(e(_,[v(p(X),_,V)|R])) :- X = 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Madalena'),_,V1),R),
                                          posLado(V,V1).

%lado(e(_,[v(p(X),_,V)|R])) :- X \= 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]).


posLado(X,R) :- (X = 2, R = 3); (X = 2, R = 1).
posLado(X,R) :- (X = 3, R = 4); (X = 3, R = 2).
posLado(X,R) :- (X = 4, R = 3); (X = 4, R = 5).
posLado(X,R) :- (X = 5, R = 4); (X = 5, R = 6).
posLado(X,R) :- (X = 6, R = 5); (X = 6, R = 7).
posLado(X,R) :- (X = 7, R = 6); (X = 7, R = 8).
posLado(X,R) :- (X = 8, R = 7); (X = 8, R = 9).
posLado(X,R) :- (X = 9, R = 8); (X = 9, R = 10).
posLado(X,R) :- (X = 10, R = 9); (X = 10, R = 11).
posLado(X,R) :- (X = 11, R = 10); (X = 11, R = 12).
posLado(X,R) :- (X = 12, R = 11); (X = 12, R = 1).
posLado(X,R) :- (X = 1, R = 12); (X = 1, R = 2).


cabeceira(e(_,[v(p(X),_,V)|R])) :- X = 'Gabriel', findall(V2, member(v(p(_),_,V2),R), L2),dif([V|L2]), posCabeceira(V).

%cabeceira(e(_,[v(p(X),_,V)|R])) :- X \= 'Gabriel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]).

posCabeceira(X) :- X = 1; X = 7. 

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