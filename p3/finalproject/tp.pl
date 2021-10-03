catena([], L, L).
catena([X|Xs], L, [X|Y]):- catena(Xs, L, Y).

nrev([], []).
nrev([X|A], B) :- nrev(A, AR), catena(AR, [X], B).

%----------------------------------------------------------------------------------------------------------------------

ambiguo(L, M, T1, T2) :- ambiguo(0, L, L, [], M, [], T1,[], T2), !.

%quando a lista está vazia e os elementos que estavam em X passam para M, os de Y para T1 e os de Z para T2
ambiguo(J,L1, [], M,M, T1, T1, T2, T2). 

%quando o código da primeira letra da lista é ambiguo, X passa a ter o código da letra atual, Y letra atual e Z as letras que formam o código atual passando depois para o elemento seguinte da lista
ambiguo(0,L1, [(L, C)|B],X, M,Y, T1,Z, T2) :- verifica_ambiguidade(L1, C, R),nrev(R, Sf), length(Sf, T), T\=0, J1 is 0 + 1, ambiguo(J1, L1, B,C, M, [L], T1, Sf, T2). 

%quando o código da primeira letra da lista não é ambiguo, passa para o elemento seguinte
ambiguo(0,L1, [(L, C)|B],X, M,Y, T1,Z, T2) :- verifica_ambiguidade(L1, C, R), nrev(R, Sf), length(Sf, 0), J1 is 0 + 1, ambiguo(J1, L1, B,X, M, Y, T1, Z, T2). 

%quando o tamanho do código é 1 passa para o elemento seguinte
ambiguo(J,L1, [(L, C)|B],X, M,Y, T1,Z, T2) :- J \= 0, length(C, 1), ambiguo(J, L1, B,X, M, Y, T1, Z,T2).

%quando o tamanho do código da letra atual é menor que o código da letra ambigua guardada e o código da letra atual é ambiguo, substitui o X, Y e Z pelo respetivo código, letra e as letras que formam esse código guardado,
%e passa para o elemento seguinte da lista 
ambiguo(J,L1, [(L, C)|B],X, M,Y, T1,Z, T2) :- J \= 0, length(C, Tc), Tc > 1, length(X, Tx), Tx \= 0, Tc < Tx, verifica_ambiguidade(L1, C, R),nrev(R, Sf), length(Sf, T), T\=0, ambiguo(J, L1, B,C, M, [L], T1, Sf, T2).

%quando ainda não encontrou nenhum código ambiguo e o código atual(C) é ambiguo,X passa a ter o código da letra atual, Y letra atual e Z as letras que formam o código atual, passando depois para o elemento seguinte da lista
ambiguo(J,L1, [(L, C)|B],X, M,Y, T1,Z, T2) :- J \= 0, length(C, Tc), Tc > 1, length(X, 0), verifica_ambiguidade(L1, C, R), nrev(R, Sf), length(Sf, T), T\=0, ambiguo(J, L1, B,C, M, [L], T1, Sf, T2).

%quando o tamanho do código da letra atual é menor que o código da letra ambigua guardada e o código da letra atual não é ambiguo, continua com o mesmo X, Y, Z, e passa para o elemento seguinte da lista
ambiguo(J,L1, [(L, C)|B],X, M,Y, T1,Z, T2) :- J \= 0, length(C, Tc), Tc > 1, length(X, Tx),Tx \= 0, Tc < Tx, verifica_ambiguidade(L1, C, R), nrev(R, Sf), length(Sf, 0), ambiguo(J, L1, B,X, M, Y, T1, Z, T2).

%quando o tamanho do código da letra atual é maior ou igual que o código da letra ambigua que está guardada, permanece o X, Y, Z e passa para o elemento seguinte da lista
ambiguo(J,L1, [(L, C)|B],X, M,Y, T1,Z, T2) :- J \= 0, length(C, Tc), Tc > 1, length(X, Tx), Tc >= Tx, ambiguo(J, L1, B,X, M, Y, T1, Z,T2).


%----------------------------------------------------------------------------------------------------------------------

contido(C, M, R) :- contido(C, M, [], R).

%quando código(C) é igual ao possivel código ambiguo(M), return da lista vazia 
contido([], [], R, []).

%quando código(C) é maior que o possivel código ambiguo(M), return da lista vazia
contido(C, [], R, []).

%quando código(C) é menor que o possivel código ambiguo(M), return da lista R 
contido([], M, R, R).   

%quando o elemento atual do código(C) é diferente do elemento atual do possivel código ambiguo(M), return da lista R vazia
contido([F|S], [M1|M2], X, R) :- F \= M1, contido([], M1, [], R).

%quando o elemento atual do código(C) é igual ao elemento atual do possivel código ambiguo(M), passa para o elemento seguinte e guarda o elemento numa lista
contido([M1|S], [M1|M2], X, R) :- contido(S, M2, [M1|X], R). 

%----------------------------------------------------------------------------------------------------------------------

verifica_ambiguidade(L, M, T2):- L1 = L, verifica_ambiguidade(L1, L, [], M, [], T2), !.

%quando a lista está vazia e a lista Y tbm está vazia, return da lista de letras em T2 
verifica_ambiguidade(L1,[], [], M, T2, T2).

%quando a lista está vazia, já atualizou o valor de M(tamanho de Y > 0) e tem uma letra na lista X faz com que seja devolvido a lista vazia.
verifica_ambiguidade(L1, [], Y, M, X, T2) :- length(Y, T), T>0, length(X, 1), verifica_ambiguidade(L1, [], [], M, [], T2).

%quando o valor do possivel código ambiguo(M) está atualizado e é igual ao código da letra atual, adiciona a letra atual à lista X 
verifica_ambiguidade(L1,[(L, Y)|N],Y, M, X, T2)  :- verifica_ambiguidade(L1, [],[], M, [L|X], T2).

%quando o código atual é igual ao possivel código passa para o elemento seguinte da lista
verifica_ambiguidade(L1,[(L, M)|N],Y, M, X, T2)  :- verifica_ambiguidade(L1,N,Y, M, X, T2).

%quando o código atual(C) é diferente do possivel código ambiguo(M) e C não está contido em M, passa para o elemento seguinte da lista 
verifica_ambiguidade(L1,[(L, C)|N], Y, M, X, T2) :- Y\= C,  C \= M, contido(C, M, R), R = [], verifica_ambiguidade(L1,N,Y, M, X, T2).

%quando o código atual(C) é diferente do possivel código ambiguo(M) e C está contido em M, atualiza o valor de M e guarda a letra atual na lista X
verifica_ambiguidade(L1,[(L, C)|N], Y, M, X, T2) :- Y\= C, C \= M, contido(C, M, R), R \= [],atualiza(C, M, M1),nrev(M1, M12), verifica_ambiguidade(L1,L1,M12, M12, [L|X], T2).

%----------------------------------------------------------------------------------------------------------------------

atualiza(C, M, R):- atualiza(C, M, [], R).

%quando código(C) é igual ao possivel código ambiguo(M), return da lista vazia
atualiza([], [], R, R).

%quando o possivel código ambiguo(M) tem mais um elemento que o código(C), guarda esse elemento numa lista.
atualiza([], [Lm], X, R)       :- atualiza([], [], [Lm|X], R).

%quando o possivel código ambiguo(M) tem mais do que um elemento que o código (C) vai guardando esses elementos numa lista.
atualiza([], [M1|M2], X, R)    :- atualiza([], M2, [M1|X], R).

%quando o elemento atual do código(C) é igual ao elemento atual do possivel código ambiguo(M) passa para o elemento seguinte com a lista vazia.
atualiza([M1|S], [M1|M2], X, R):- atualiza(S, M2, X, R).
%----------------------------------------------------------------------------------------------------------------------




