estado_inicial(e([
               v(p('Manuel'),  [1,2,3,4], _),
               v(p('Joaquim'), [1,2,3,4], _),
               v(p('Gabriel'), [1,2,3,4], _)], 
              [v(p('Maria'),   [1,2,3,4], 4)])).

dif([]).
dif([X|R]) :- \+ member(X,R), dif(R).

ve_restricoes(E) :- esq(E); frente(E); lado(E); cabeceira(E).

esq(e(_,[v(p(X),_,V)|R])) :- X = 'Manuel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Maria'),_,V1),R),
                                      posEsq(V,V1).


posEsq(X,R) :- X = 2 , R = 1.
posEsq(X,R) :- X = 3 , R = 2.
posEsq(X,R) :- X = 4 , R = 3.
posEsq(X,R) :- X = 1 , R = 4.


frente(e(_,[v(p(X),_,V)|R])) :- X = 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Maria'),_,V1),R),
                                          posFrente(V,V1).


posFrente(X,R) :- X = 2, R = 4.
posFrente(X,R) :- X = 4, R = 2.
                                                

lado(e(_,[v(p(X),_,V)|R])) :- X = 'Joaquim', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), member(v(p('Gabriel'),_,V1),R),
                                          posLado(V,V1).


posLado(X,R) :- (X = 2, R = 3); (X = 2, R = 1).
posLado(X,R) :- (X = 3, R = 4); (X = 3, R = 2).
posLado(X,R) :- (X = 4, R = 3); (X = 4, R = 1).
posLado(X,R) :- (X = 1, R = 4); (X = 1, R = 2).


cabeceira(e(_,[v(p(X),_,V)|R])) :- X = 'Gabriel', findall(V2, member(v(p(_),_,V2),R), L2), dif([V|L2]), posCabeceira(V).


posCabeceira(X) :- X = 1; X = 3. 

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