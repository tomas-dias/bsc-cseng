% a)

% estado inicial([pos do agente A, pos da caixa])
estado_inicial([(7,2),(6,2)]).

%estado final
estado_final([(_,_),(1,5)]).

ocupada((2,1)).
ocupada((1,3)).
ocupada((2,3)).
ocupada((4,4)).
ocupada((5,4)).
ocupada((6,4)).
ocupada((2,7)).
ocupada((1,1)).
ocupada((1,2)).
ocupada((2,2)).
ocupada((7,1)).
ocupada((7,7)).
ocupada((1,7)).

% op(estado_atual,operador,estado_seguinte,custo)

% movimento para baixo ou para cima
op([(X,Y),(XC,YC)],(0,YO),[(X1,Y1),(X2,Y2)],1) :-  member(YO, [6,5,4,3,2,1,0,-1,-2,-3,-4,-5,-6]),
                                                   YO \= 0,
                                                   X1 is X + 0,
                                                   Y1 is Y + YO,
                                                   lim(X1,Y1),
                                                   passaOcupada(X,Y,X1,Y1),
                                                   move(X,Y,X1,Y1,XC,YC,X2,Y2),
                                                   passaOcupadaCaixa(XC,YC,X2,Y2).

% movimento para a esquerda ou direita
op([(X,Y),(XC,YC)],(XO,0),[(X1,Y1),(X2,Y2)],1) :- member(XO,[6,5,4,3,2,1,0,-1,-2,-3,-4,-5,-6]),
                                                  XO \= 0,
                                                  X1 is X + XO,
                                                  Y1 is Y + 0,
                                                  lim(X1,Y1), 
                                                  passaOcupada(X,Y,X1,Y1), 
                                                  move(X,Y,X1,Y1,XC,YC,X2,Y2),
                                                  passaOcupadaCaixa(XC,YC,X2,Y2).

lim(A,B) :- A =< 7, A >= 1, B =< 7, B >= 1.

%caixa em cima e agente para na posição atual da caixa
move(X,Y,X1,Y,XC,YC,X2,Y2):- cruzaCaixa(X,Y,X1,Y,XC,YC),
                             X1 < X,
                             X2 is XC - 1, 
                             Y2 is YC + 0, 
                             lim(X2,Y2), !.

%caixa em baixo e agente para na posição atual da caixa
move(X,Y,X1,Y,XC,YC,X2,Y2):- cruzaCaixa(X,Y,X1,Y,XC,YC),
                             X1 > X,
                             X2 is XC + 1, 
                             Y2 is YC + 0, 
                             lim(X2,Y2), !.

%caixa em cima e agente passa pela caixa (msm coluna)
move(X,Y,X1,Y,XC,Y,X2,Y2) :- \+ cruzaCaixa(X,Y,X1,Y,XC,Y),
                              passaCaixa(X,Y, X1, Y, XC, Y),
                              X1 < X,
                              %write('caixa em cima'), nl,
                              X2 is X1 - 1, 
                              Y2 is Y + 0, 
                              lim(X2,Y2),!.

%caixa em baixo e agente passa pela caixa (msm coluna)
move(X,Y,X1,Y,XC,Y,X2,Y2) :- \+ cruzaCaixa(X,Y,X1,Y,XC,Y), 
                              passaCaixa(X,Y, X1, Y, XC, Y),
                              X1 > X,
                              %write('Caixa em baixo'),nl,
                              X2 is X1 + 1, 
                              Y2 is Y + 0, 
                              lim(X2,Y2), !.

%caixa está na coluna
move(X,Y,X1,Y,XC,Y,X2,Y2) :- \+ cruzaCaixa(X,Y,X1,Y,XC,Y), 
                              %write('caixa está na coluna'),nl,
                              \+ passaCaixa(X,Y, X1, Y, XC, Y),
                              X2 is XC + 0, 
                              Y2 is Y + 0, 
                              lim(X2,Y2), !.

%caixa não está na coluna
move(X,Y,X1,Y,XC,YC,X2,Y2) :- \+ cruzaCaixa(X,Y,X1,Y,XC,YC), 
                              %write('caixa n está na coluna'),nl,
                              \+ passaCaixa(X, Y, X1, Y, XC, Y),
                              X2 is XC, 
                              Y2 is YC, 
                              lim(X2,Y2), !.

%caixa à esquerda e agente para na posição da caixa
move(X,Y,X,Y1,XC,YC,X2,Y2):- cruzaCaixa(X,Y,X,Y1,XC,YC),
                             Y1 < Y,
                             X2 is XC + 0, 
                             Y2 is YC - 1, 
                             lim(X2,Y2), !.

%caixa à direita e agente para na posição da caixa
move(X,Y,X,Y1,XC,YC,X2,Y2):- cruzaCaixa(X,Y,X,Y1,XC,YC),
                             Y1 > Y,
                             X2 is XC + 0, 
                             Y2 is YC + 1, 
                             lim(X2,Y2), !.

%caixa à esquerda e agente passa pela caixa (msm linha)
move(X,Y,X,Y1,X,YC,X2,Y2) :- \+ cruzaCaixa(X,Y,X,Y1,X,YC),
                              passaCaixa(X,Y, X, Y1, X, YC),
                              Y1 < Y,
                              %write('caixa à esquerda'), nl,
                              X2 is X + 0, 
                              Y2 is Y1 - 1, 
                              lim(X2,Y2), !.

%caixa à direita e agente passa pela caixa (msm linha)
move(X,Y,X,Y1,X,YC,X2,Y2) :- \+ cruzaCaixa(X,Y,X,Y1,X,YC), 
                              passaCaixa(X,Y, X, Y1, X, YC),
                              Y1 > Y,
                              %write('caixa à direita'), nl,
                              X2 is X + 0, 
                              Y2 is Y1 + 1, 
                              lim(X2,Y2), !.

%caixa está na linha
move(X,Y,X,Y1,X,YC,X2,Y2) :- \+ cruzaCaixa(X,Y,X,Y1,X,YC),
                              %write('caixa esta na linha'), nl,
                              \+ passaCaixa(X, Y, X, Y1, X, YC), 
                              X2 is X, 
                              Y2 is YC, 
                              lim(X2,Y2), !.

%caixa não está na linha
move(X,Y,X,Y1,XC,YC,X2,Y2) :- \+ cruzaCaixa(X,Y,X,Y1,XC,YC),
                              %write('caixa n esta na linha'), nl,
                              \+ passaCaixa(X, Y, X, Y1, X, YC),
                              X2 is XC, 
                              Y2 is YC, 
                              lim(X2,Y2), !.

%caixa na mesma coluna, true se posição seguinte do agente for a posição atual da caixa
cruzaCaixa(_,Y,X1,Y,XC,Y) :- X1 = XC.

%caixa na mesma linha, true se posição seguinte do agente for a posição atual da caixa
cruzaCaixa(X,_,X,Y1,X,YC) :- Y1 = YC.

passaOcupada(_,Y,X1,Y) :-   posSeguinteOcupada(X1,Y),
                            findall((A,Y), ocupada((A,Y)), L),
                            length(L, 0).

passaOcupada(X,Y,X1,Y) :-   posSeguinteOcupada(X1,Y), 
                            findall((A,Y), ocupada((A,Y)), L),
                            length(L, T),
                            T > 0,
                            verifX(L, X, X1), !.

passaOcupada(X,_,X,Y1) :-   posSeguinteOcupada(X,Y1), 
                            findall((X,A), ocupada((X,A)), L),
                            length(L, 0).

passaOcupada(X,Y,X,Y1) :-   posSeguinteOcupada(X,Y1), 
                            findall((X,A), 
                            ocupada((X,A)), L),  
                            length(L, T), 
                            T > 0, 
                            verifY(L, Y, Y1), !.

verifX([],_,_).
verifX([(X,_)|N], B, D):-   \+ (X < B, X > D ; X > B, X < D), 
                            verifX(N, B, D).

verifY([], _, _).
verifY([(_,Y)|N], B, D):-   \+ (Y < B, Y > D ; Y > B, Y < D), 
                            verifY(N, B, D).


%caixa na mesma coluna
passaCaixa(X,Y,X1,Y,XC, Y) :- (XC < X, XC > X1 ; XC > X, XC < X1).

%caixa na mesma linha
passaCaixa(X,Y,X,Y1, X, YC) :- (YC < Y, YC > Y1 ; YC > Y, YC < Y1).


posSeguinteOcupada(A,B) :- \+ member((A,B), [(2,1),(1,1),(1,2),(2,2),(1,3), (2,3), (4,4), (5,4), (6,4), (2,7)]).



passaOcupadaCaixa(_,Y,X1,Y) :-   posSeguinteOcupadaCaixa(X1,Y),
                            findall((A,Y), ocupada((A,Y)), L),
                            length(L, 0).

passaOcupadaCaixa(X,Y,X1,Y) :-   posSeguinteOcupadaCaixa(X1,Y), 
                            findall((A,Y), ocupada((A,Y)), L),
                            length(L, T),
                            T > 0,
                            verifX(L, X, X1), !.

passaOcupadaCaixa(X,_,X,Y1) :-   posSeguinteOcupadaCaixa(X,Y1), 
                            findall((X,A), ocupada((X,A)), L),
                            length(L, 0).

passaOcupadaCaixa(X,Y,X,Y1) :-   posSeguinteOcupadaCaixa(X,Y1), 
                            findall((X,A), 
                            ocupada((X,A)), L),  
                            length(L, T), 
                            T > 0, 
                            verifY(L, Y, Y1), !.

posSeguinteOcupadaCaixa(A,B) :- \+ member((A,B), [(2,1),(1,1),(1,2),(2,2),(1,3), (2,3), (4,4), (5,4), (6,4), (2,7), (7,1), (7,7), (1,7)]).

%-----------------------------------------------------------------------------------------------------------------------
% b)

%estado_inicial(Estado)
%estado_final(Estado)

%representacao dos operadores
%op(Eact,OP,Eseg,Custo)

%representacao dos nós
%no(Estado,no_pai,OperadorCusto,Profundidade)
:- dynamic(fechado/1).
:- dynamic(nos/1).
:- dynamic(maxNL/1).

maxNL(0).
nos(0).

inc:- retract(nos(N)), N1 is N+1, asserta(nos(N1)).

actmax(N):- maxNL(N1), N1 >= N,!.
actmax(N):- retract(maxNL(_N1)), asserta(maxNL(N)).

pesquisa(Problema,Alg):-
    consult(Problema),
    estado_inicial(S0),
    pesquisa(Alg,[no(S0,[],[],0,0)],Solucao),
    escreve_seq_solucao(Solucao),
    retract(nos(Ns)),retract(maxNL(NL)),
    asserta(nos(0)),asserta(maxNL(0)),
    write(nos(visitados(Ns),lista(NL))).


pesquisa(largura,Ln,Sol):- pesquisa_largura(Ln,Sol),!.
pesquisa(profundidade,Ln,Sol):- pesquisa_profundidade(Ln,Sol),!.
pesquisa(it,Ln,Sol):- pesquisa_it(Ln,Sol,1), !.

%pesquisa_largura([],_):- !,fail.
pesquisa_largura([no(E,Pai,Op,C,P)|_],no(E,Pai,Op,C,P)):- estado_final(E), inc.

pesquisa_largura([E|R],Sol) :-  inc,
                                asserta(fechado(E)), 
                                expande(E,Lseg), 
                                %esc(E),
                                E=no(Ei,_Pai,_Op,_C,_P),
                                assertz(fechado(Ei)),
                                insere_fim(Lseg,R,Resto),
                                length(Resto, N),
                                actmax(N),
                                pesquisa_largura(Resto,Sol).

pesquisa_profundidade([no(E,Pai,Op,C,P)|_],no(E,Pai,Op,C,P)):- estado_final(E), inc.

pesquisa_profundidade([E|R],Sol) :-  inc,
                                asserta(fechado(E)), 
                                expande(E,Lseg), 
                                %esc(E),
                                E=no(Ei,_Pai,_Op,_C,_P),
                                assertz(fechado(Ei)),
                                insere_fim(R,Lseg,Resto),
                                length(Resto, N),
                                actmax(N),
                                pesquisa_profundidade(Resto,Sol).

pesquisa_it(Ln,Sol,P):- pesquisa_pLim(Ln,Sol,P).
pesquisa_it(Ln,Sol,P):- P1 is P+1, pesquisa_it(Ln,Sol,P1).

pesquisa_pLim([no(E,Pai,Op,C,P)|_],no(E,Pai,Op,C,P),_):- estado_final(E), inc.

pesquisa_pLim([E|R],Sol,Pl):-   inc, expandePl(E,Lseg,Pl), %esc(E),
                                insere_fim(R,Lseg,Resto),
                                length(Resto, N),
                                actmax(N), 
                                pesquisa_pLim(Resto,Sol,Pl).



expande(no(E,Pai,Op,C,P),L):- findall(no(En,no(E,Pai,Op,C,P),Opn,Cnn,P1),
                                        (op(E,Opn,En,Cn),P1 is P+1, Cnn is Cn+C, \+ fechado(En)),
                                        L).

expandePl(no(_,_,_,_,P),[],Pl):- Pl =< P, ! .
expandePl(no(E,Pai,Op,C,P),L,_):- findall(no(En,no(E,Pai,Op,C,P),Opn,Cnn,P1),
                                    (op(E,Opn,En,Cn),P1 is P+1, Cnn is Cn+C),
                                    L).

insere_fim([],L,L).
insere_fim(L,[],L).
insere_fim(R,[A|S],[A|L]):- insere_fim(R,S,L).

escreve_seq_solucao(no(E,Pai,Op,Custo,Prof)):-  write(custo(Custo)),nl,
                                                write(profundidade(Prof)),nl,
                                                escreve_seq_accoes(no(E,Pai,Op,_,_)).


escreve_seq_accoes([]).
escreve_seq_accoes(no(E,Pai,Op,_,_)):-  escreve_seq_accoes(Pai),
                                        write(e(Op,E)),nl.

esc(A):- write(A), nl.

%----------------------------------------------------------------------------------------------------------------------------
% c)

% Resposta no ficheiro PDF

%---------------------------------------------------------------------------------------------------------
% d)
%heurística para estimar distância h(Estado da caixa,Valor)

h([(_,_),(X1,Y1)], Val):- estado_final([(_,_),(Xf,Yf)]), mod(Vi,Xf,X1), mod(Vj,Yf,Y1), Val is (Vi+Vj) div 1.

%h([(_,_),(X1,Y1)],0).
  
mod(Vj,X,Y) :- X<Y,!, Vj is Y-X.
mod(Vj,X,Y) :- Vj is X-Y.

max(Vi,Vi,Vj):-Vi>Vj,!.
max(Vj,_,Vj).

%--------------------------------------------------------------------------------------------------------
% e)

%estado_inicial(Estado)
%estado_final(Estado)

%representação dos operadores
%op(Eact,OP,Eseg,Custo)

%representação dos nós
%no(Estado,no_pai,Operador,Custo,H+C,Profundidade)

pesquisaI(Problema,Alg):-
    consult(Problema),
    estado_inicial(S0),
    pesquisaI(Alg,[no(S0,[],[],0,1,0)],Solucao),
    escreve_seq_solucaoPI(Solucao),
    retract(nos(Ns)),retract(maxNL(NL)),
    asserta(nos(0)),asserta(maxNL(0)),
    write(nos(visitados(Ns),lista(NL))).

pesquisaI(a,E,S):- pesquisa_a(E,S), !.
pesquisaI(g,E,S):- pesquisa_g(E,S), !.

%pesquisa_a([],_):- !,fail.
pesquisa_a([no(E,Pai,Op,C,HC,P)|_],no(E,Pai,Op,C,HC,P)):- estado_final(E),inc.

pesquisa_a([E|R],Sol):- inc,
                        asserta(fechado(E)), 
                        expandePI(E,Lseg), 
                        %esc(E),
                        insere_ord(Lseg,R,Resto),
                        length(Resto,N),
                        actmax(N),
                        pesquisa_a(Resto,Sol).

%pesquisa_g([],_):- !,fail.
pesquisa_g([no(E,Pai,Op,C,HC,P)|_],no(E,Pai,Op,C,HC,P)):- estado_final(E).

pesquisa_g([E|R],Sol):- inc,  asserta(fechado(E)),  expande_g(E,Lseg), %esc(E),
                              insere_ord(Lseg,R,Resto),length(Resto,N), actmax(N),
                              pesquisa_g(Resto,Sol).

expandePI(no(E,Pai,Op,C,HC,P),L):-  findall(no(En,no(E,Pai,Op,C,HC,P),Opn,Cnn,HCnn,P1),
                                              (op(E,Opn,En,Cn), \+ fechado(no(En,_,_,_,_,_)),
                                               P1 is P+1, Cnn is Cn+C, h(En,H), HCnn is Cnn+H), L).

expande_g(no(E,Pai,Op,C,HC,P),L):- findall(no(En,no(E,Pai,Op,C,HC,P),Opn,Cnn,H,P1),
					   (op(E,Opn,En,Cn),
                                            \+ fechado(no(En,_,_,_,_,_)),
					    P1 is P+1, Cnn is Cn+C, h(En,H)), L).

insere_ord([],L,L).
insere_ord([A|L],L1,L2):- insereE_ord(A,L1,L3), insere_ord(L,L3,L2).

insereE_ord(A,[],[A]).
insereE_ord(A,[A1|L],[A,A1|L]):- menor_no(A,A1),!.
insereE_ord(A,[A1|L], [A1|R]):- insereE_ord(A,L,R).

menor_no(no(_,_,_,_,N,_), no(_,_,_,_,N1,_)):- N < N1.

escreve_seq_solucaoPI(no(E,Pai,Op,Custo,_HC,Prof))  :-  write(custo(Custo)),nl,
                                                        write(profundidade(Prof)),nl,
                                                        escreve_seq_accoesPI(no(E,Pai,Op,_,_,_)).


escreve_seq_accoesPI([]).
escreve_seq_accoesPI(no(E,Pai,Op,_,_,_)):-  escreve_seq_accoesPI(Pai),
                                            write(e(Op,E)),nl.

%--------------------------------------------------------------------------------------------------------
% f)

% Resposta no ficheiro PDF