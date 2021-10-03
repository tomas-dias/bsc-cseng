% Vocabulário

% Condições
liga(Comboio, Cidade1, Cidade2) /* significa que Comboio liga Cidade1 à Cidade2 */

% Fluentes
estaComboio(Obj,Comboio) /* significa que o Obj está no Comboio */
estaCidade(Obj, Cidade) /* significa que o Obj está na Cidade */
comboioCidade(Comboio,Cidade) /* significa que o Comboio está na Cidade */

% Ações
carregaObjeto(Obj, Comboio) /* carrega o Obj no Comboio */
descarregaObjeto(Obj, Comboio) /* descarrega o Obj do Comboio */
viaja(Comboio, Cidade) /* Comboio viaja para a Cidade */

estado_inicial([liga(c(1), cidade('Lisboa'), cidade('Porto')),
                liga(c(1), cidade('Porto'), cidade('Lisboa')), 
                liga(c(2), cidade('Lisboa'), cidade('Evora')),
                liga(c(2), cidade('Evora'), cidade('Lisboa')), 
                estaCidade(obj(1), cidade('Porto')),
                estaCidade(obj(2), cidade('Lisboa')),
                estaCidade(obj(3), cidade('Lisboa')),
                estaCidade(obj(4), cidade('Evora')),
                estaCidade(obj(5), cidade('Evora')),
                comboioCidade(c(1), cidade('Lisboa')),
                comboioCidade(c(2), cidade('Lisboa'))]).

estado_final([  estaCidade(obj(1), cidade('Evora')),
                estaCidade(obj(2), cidade('Porto')),
                estaCidade(obj(3), cidade('Evora')),
                estaCidade(obj(4), cidade('Porto')),
                estaCidade(obj(5), cidade('Lisboa'))]).

acao(carregaObjeto(obj(Obj), c(C)), 
    [estaCidade(obj(Obj), cidade(Cidade)), comboioCidade(c(C), cidade(Cidade))],
    [estaComboio(obj(Obj), c(C))], 
    [estaCidade(obj(Obj), cidade(Cidade))])
    :-  member(Obj, [1,2,3,4,5]), 
        member(C, [1,2]), 
        member(Cidade, ['Lisboa', 'Porto', 'Evora']).

acao(descarregaObjeto(obj(Obj), c(C)),
    [estaComboio(obj(Obj), c(C)), comboioCidade(c(C), cidade(Cidade))],
    [estaCidade(obj(Obj), cidade(Cidade))],
    [estaComboio(obj(Obj), c(C))])
    :-  member(Obj, [1,2,3,4,5]), 
        member(C, [1,2]), 
        member(Cidade, ['Lisboa', 'Porto', 'Evora']).

acao(viaja(c(C), cidade(Cidade))),
    [comboioCidade(c(C), cidade(Cidade2), liga(c(C), cidade(Cidade2), cidade(Cidade)))],
    [comboioCidade(c(C), cidade(Cidade))],
    [comboioCidade(c(C), cidade(Cidade2))])
    :-  member(C, [1]),
        member(Cidade, ['Lisboa', 'Porto']),
        member(Cidade2, ['Porto', 'Lisboa']),
        Cidade \= Cidade2.

acao(viaja(c(C), cidade(Cidade))),
    [comboioCidade(c(C), cidade(Cidade2), liga(c(C), cidade(Cidade2), cidade(Cidade))],
    [comboioCidade(c(C), cidade(Cidade))],
    [comboioCidade(c(C), cidade(Cidade2))])
    :-  member(C, [2]),
        member(Cidade, ['Lisboa', 'Evora']),
        member(Cidade2, ['Evora', 'Lisboa']),
        Cidade \= Cidade2.


/* Exercício 3 */

estado1([estaCidade(obj(1), cidade('Lisboa')),
         estaCidade(obj(2), cidade('Lisboa')),
         estaCidade(obj(3), cidade('Lisboa')),
         estaCidade(obj(4), cidade('Evora')),
         estaCidade(obj(5), cidade('Evora'))]])

% step: s1
/* Pré-Condição */ [comboioCidade(c(1), cidade('Lisboa')), liga(c(1),cidade('Lisboa'),cidade('Porto'))]
/* Ação */ viaja(c(1), cidade('Porto'))
/* Efeito */ [comboioCidade(c(1), cidade('Porto'))]

%-------------------------------------

% step: s2
/* Pré-Condição */ [comboioCidade(c(1), cidade('Porto')), estaCidade(obj(1), cidade('Porto'))]
/* Ação */ carregaObjeto(obj(1), c(1))
/* Efeito */ [estaComboio(obj(1), c(1))]

%-------------------------------------

% step: s3
/* Pré-Condição */ [comboioCidade(c(1), cidade('Porto')), liga(c(1),cidade('Porto'),cidade('Lisboa'))]
/* Ação */ viaja(c(1), cidade('Lisboa'))
/* Efeito */ [comboioCidade(c(1), cidade('Lisboa'))]

%-------------------------------------

% step: s4
/* Pré-Condição */ [estaComboio(obj(1), c(1)), comboioCidade(c(1), cidade('Lisboa'))]
/* Ação */ descarregaObjeto(obj(1), c(1))
/* Efeito */ [estaCidade(obj(1), cidade('Lisboa'))]

%-------------------------------------

/* ordem */ 
estado_inicial<s1<s2<s3<s4<estado1

/* links */ 
[
 (estado_inicial, comboioCidade(c(1), cidade('Lisboa')), s1),
 (estado_inicial,  liga(c(1),cidade('Lisboa'),cidade('Porto')), s1),
 (estado_inicial, estaCidade(obj(1), cidade('Porto')), s2),
 (estado_inicial, liga(c(1),cidade('Porto'),cidade('Lisboa')), s3)
 (s1, comboioCidade(c(1), cidade('Porto')), s2),
 (s1, comboioCidade(c(1), cidade('Porto')), s3),  
 (s2, estaComboio(obj(1), c(1)), s4), 
 (s3, comboioCidade(c(1), cidade('Lisboa')), s4), 
 (s4, estaCidade(obj(1), cidade('Lisboa')), estado1)
]


/* Exercício 4 */

% step: s1
/* Pré-Condição */ [comboioCidade(c(2), cidade('Lisboa')), estaCidade(obj(3), cidade('Lisboa'))]
/* Ação */ carregaObjeto(obj(3), c(2))
/* Efeito */ [estaComboio(obj(3), c(2))]

%--------------------------------------

% step: s2
/* Pré-Condição */ [comboioCidade(c(2), cidade('Lisboa')), liga(c(2), cidade('Lisboa'), cidade('Evora'))]
/* Ação */ viaja(c(2), cidade('Evora'))
/* Efeito */ [comboioCidade(c(2), cidade('Evora'))]

%--------------------------------------

% step: s3
/* Pré-Condição */ [estaComboio(obj(3), c(2)), comboioCidade(c(2), cidade('Evora'))]
/* Ação */ descarregaObjeto(obj(3), c(2))
/* Efeito */ [estaCidade(obj(3), cidade('Evora'))]

%--------------------------------------

% step: s4
/* Pré-Condição */ [comboioCidade(c(2), cidade('Evora')), estaCidade(obj(4), cidade('Evora'))]
/* Ação */ carregaObjeto(obj(4), c(2))
/* Efeito */ [estaComboio(obj(4), c(2))]

%--------------------------------------

% step: s5
/* Pré-Condição */ [comboioCidade(c(2), cidade('Evora')), estaCidade(obj(5), cidade('Evora'))]
/* Ação */ carregaObjeto(obj(5), c(2))
/* Efeito */ [estaComboio(obj(5), c(2))]

%--------------------------------------

% step: s6
/* Pré-Condição */ [comboioCidade(c(2), cidade('Evora')), liga(c(2), cidade('Evora'), cidade('Lisboa'))]
/* Ação */ viaja(c(2), cidade('Lisboa'))
/* Efeito */ [comboioCidade(c(2), cidade('Lisboa'))]

%--------------------------------------

% step: s7
/* Pré-Condição */ [estaComboio(obj(5), c(2)), comboioCidade(c(2), cidade('Lisboa'))]
/* Ação */ descarregaObjeto(obj(5), c(2))
/* Efeito */ [estaCidade(obj(5), cidade('Lisboa'))]

%--------------------------------------
% step: s8
/* Pré-Condição */ [estaComboio(obj(4), c(2)), comboioCidade(c(2), cidade('Lisboa'))]
/* Ação */ descarregaObjeto(obj(4), c(2))
/* Efeito */ [estaCidade(obj(4), cidade('Lisboa'))]

%--------------------------------------

% step: s9
/* Pré-Condição */ [comboioCidade(c(1), cidade('Lisboa')), estaCidade(obj(2), cidade('Lisboa'))]
/* Ação */ carregaObjeto(obj(2), c(1))
/* Efeito */ [estaComboio(obj(2), c(1))]

%--------------------------------------

% step: s10
/* Pré-Condição */ [comboioCidade(c(1), cidade('Lisboa')), estaCidade(obj(4), cidade('Lisboa'))]
/* Ação */ carregaObjeto(obj(4), c(1))
/* Efeito */ [estaComboio(obj(4), c(1))]

%--------------------------------------

% step: s11
/* Pré-Condição */ [comboioCidade(c(1), cidade('Lisboa')), liga(c(1), cidade('Lisboa'), cidade('Porto'))]
/* Ação */ viaja(c(1), cidade('Porto'))
/* Efeito */ [comboioCidade(c(1), cidade('Porto'))]

%--------------------------------------

% step: s12
/* Pré-Condição */ [estaComboio(obj(2), c(1)), comboioCidade(c(1), cidade('Porto'))]
/* Ação */ descarregaObjeto(obj(2), c(1))
/* Efeito */ [estaCidade(obj(2), cidade('Porto'))]

%--------------------------------------

% step: s13
/* Pré-Condição */ [estaComboio(obj(4), c(1)), comboioCidade(c(1), cidade('Porto')]
/* Ação */ descarregaObjeto(obj(4), c(1))
/* Efeito */ [estaCidade(obj(4), cidade('Porto'))]

%--------------------------------------

% step: s14
/* Pré-Condição */ [comboioCidade(c(1), cidade('Porto')), estaCidade(obj(1), cidade('Porto'))]
/* Ação */ carregaObjeto(obj(1), c(1))
/* Efeito */ [estaComboio(obj(1), c(1))]

%--------------------------------------

% step: s15
/* Pré-Condição */  [comboioCidade(c(1), cidade('Porto')), liga(c(1), cidade('Porto'), cidade('Lisboa'))]
/* Ação */ viaja(c(1), cidade('Lisboa'))
/* Efeito */ [comboioCidade(c(1), cidade('Lisboa'))]

%--------------------------------------

% step: s16
/* Pré-Condição */ [estaComboio(obj(1), c(1)), comboioCidade(c(1), cidade('Lisboa')]
/* Ação */ descarregaObjeto(obj(1), c(1))
/* Efeito */ [estaCidade(obj(1), cidade('Lisboa'))]

%--------------------------------------

% step: s17
/* Pré-Condição */ [comboioCidade(c(2), cidade('Lisboa')), estaCidade(obj(1), cidade('Lisboa'))]
/* Ação */ carregaObjeto(obj(1), c(2))
/* Efeito */ [estaComboio(obj(1), c(2))]

%--------------------------------------

% step: s18
/* Pré-Condição */ [comboioCidade(c(2), cidade('Lisboa')), liga(c(2), cidade('Lisboa'), cidade('Evora'))]
/* Ação */ viaja(c(2), cidade('Evora'))
/* Efeito */ [comboioCidade(c(2), cidade('Evora'))]

%--------------------------------------

% step: s19
/* Pré-Condição */ [estaComboio(obj(1), c(2)), comboioCidade(c(2), cidade('Evora')]
/* Ação */ descarregaObjeto(obj(1), c(2))
/* Efeito */ [estaCidade(obj(1), cidade('Evora'))]

%--------------------------------------

/* ordem */
estado_inicial<s1<s2<s3<s4<s5<s6<s7<s8<s9<s10<s11<s12<s13<s14<s15<s16<s17<s18<s19<estado_final

/* links */
[
 (estado_inicial, comboioCidade(c(2), cidade('Lisboa')), s1),
 (estado_inicial, estaCidade(obj(3), cidade('Lisboa')), s1),
 (estado_inicial, comboioCidade(c(2), cidade('Lisboa')), s2),
 (estado_inicial, liga(c(2), cidade('Lisboa'), cidade('Evora')), s2),
 (estado_inicial, estaCidade(obj(4), cidade('Evora')), s4),
 (estado_inicial, estaCidade(obj(5), cidade('Evora')), s5),
 (estado_inicial, liga(c(2), cidade('Evora'), cidade('Lisboa')), s6),
 (estado_inicial, comboioCidade(c(1), cidade('Lisboa')), s9),
 (estado_inicial, estaCidade(obj(2), cidade('Lisboa')), s9),
 (estado_inicial, comboioCidade(c(1), cidade('Lisboa')), s10),
 (estado_inicial, comboioCidade(c(1), cidade('Lisboa')), s11),
 (estado_inicial, liga(c(1), cidade('Lisboa'), cidade('Porto')), s11),
 (estado_inicial, estaCidade(obj(1), cidade('Porto')), s14),
 (estado_inicial, liga(c(1), cidade('Porto'), cidade('Lisboa')), s15),
 (estado_inicial, liga(c(2), cidade('Lisboa'), cidade('Evora')), s18),
 (s1, estaComboio(obj(3), c(2)), s3), 
 (s2, comboioCidade(c(2), cidade('Evora')), s3), 
 (s2, comboioCidade(c(2), cidade('Evora')), s4),
 (s2, comboioCidade(c(2), cidade('Evora')), s5),
 (s2, comboioCidade(c(2), cidade('Evora')), s6),  
 (s3, estaCidade(obj(3), cidade('Evora')), estado_final),
 (s4, estaComboio(obj(4), c(2)), s8),
 (s5, estaComboio(obj(5), c(2)), s7),
 (s6, comboioCidade(c(2), cidade('Lisboa')), s7),
 (s6, comboioCidade(c(2), cidade('Lisboa')), s8),
 (s6, comboioCidade(c(2), cidade('Lisboa')), s17),
 (s6, comboioCidade(c(2), cidade('Lisboa')), s18),
 (s7, estaCidade(obj(5), cidade('Lisboa')), estado_final),
 (s8, estaCidade(obj(4), cidade('Lisboa')), s10),
 (s9, estaComboio(obj(2), c(1)), s12),
 (s10, estaComboio(obj(4), c(1)), s13),
 (s11, comboioCidade(c(1), cidade('Porto')), s12),
 (s11, comboioCidade(c(1), cidade('Porto')), s13),
 (s11, comboioCidade(c(1), cidade('Porto')), s14),
 (s11, comboioCidade(c(1), cidade('Porto')), s15),
 (s12, estaCidade(obj(2), cidade('Porto')), estado_final),
 (s13, estaCidade(obj(4), cidade('Porto')), estado_final),
 (s14, estaComboio(obj(1), c(1)), s16),
 (s15, comboioCidade(c(1), cidade('Lisboa')), s16),
 (s16, estaCidade(obj(1), cidade('Lisboa')), s17),
 (s17, estaComboio(obj(1), c(2)), s19),
 (s18, comboioCidade(c(2), cidade('Evora')), s19),
 (s19, estaCidade(obj(1), cidade('Evora')), estado_final)
]