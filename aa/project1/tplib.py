import numpy as np

class DecisionTreeREPrune:

    def __init__(self, atributos, criterion="erro", prune=False):

        self.atributos = atributos
        self.criterion = criterion
        self.prune = prune
        self.arvore = []

    def fit(self, x, y):

        #arvore = []
        constroi_arvore = []
        #num_exemplos = len(x)
        classes = np.unique(y)
        identificacao = 1
        #print("Numero Exemplos: ")
        #print(num_exemplos)
        #print("Atributos: ")
        #print(self.atributos)
        #print("Data: ")
        #print(x)
        #print("Clssses da data: ")
        #print(y)
        #print("Classes: ")
        #print(classes)

        particao = determina_melhor_particao(x, y, self.atributos, classes, self.criterion)

        raiz = particao[2]
        #print("Raiz")
        #print(raiz)
        raiz_ramos = np.unique(particao[0])
        #print("Ramos da raiz")
        #print(raiz_ramos)

        self.arvore.append(No(raiz, raiz_ramos))

        cresce_arvore(x, y, self.atributos, classes, particao, self.criterion, self.arvore)

        for a in self.arvore:
            if(a.no_pai != None):
                a.identificacao = identificacao
                identificacao += 1
                #print(a)
                constroi_arvore.append(a)

        print("\n")
        print("Árvore de Decisão resultante (utilizando o critério de impureza %s):" % self.criterion)
        print("\n")
        output_arvore(raiz, raiz_ramos, constroi_arvore)
        print("\n")

    def score(self, x, y):
        # print("Árvore")
        # for a in self.arvore:
        #     print(a)
        #print("Accuracy: ", calcula_exatidao(x, y, self.atributos, self.arvore))
        t = 0
        f = 0
        indice_y = 0
        for exemplo in x:
            classe = corre_arvore(self.arvore, exemplo, self.atributos)
            if(y[indice_y] == classe):
                t += 1
            else:
                f += 1
            indice_y += 1
        exat = t / (t + f)

        return exat

class Valor_Classe:

    def __init__(self, valor_classe):

        self.valor_classe = valor_classe
    
    count = 0

class No:

    def __init__(self, atributo, ramos, no_pai=None, ramo_pai=None, identificacao=0):

        self.atributo = atributo
        self.ramos = ramos
        self.no_pai = no_pai
        self.ramo_pai = ramo_pai
        self.identificacao = identificacao

    def __str__(self):

        return str(self.__class__) + ": " + str(self.__dict__)

class Folha:

    def __init__(self, classe, n_exemplos, no_pai, ramo_pai, identificacao=None):

        self.classe = classe
        self.n_exemplos = n_exemplos
        self.no_pai = no_pai
        self.ramo_pai = ramo_pai
        self.identificacao = identificacao
    
    def __str__(self):

        return str(self.__class__) + ": " + str(self.__dict__)

def calcula_impureza(coluna_atributo, coluna_classe, classes, criterio):

    valores_atributo, counts = np.unique(coluna_atributo, return_counts=True)
    #print(valores_atributo)
    #print(counts)
    #classes = np.unique(coluna_classe)
    #print("Array das classes: ")
    #print(classes)
    valores_atributo_classe = np.column_stack((coluna_atributo, coluna_classe))
    #print(valores_atributo_classe)
    
    divisoes = []

    for valor_atributo in valores_atributo:
        tmp = []
        for valor_atributo_classe in valores_atributo_classe:
            if valor_atributo_classe[0] == valor_atributo:
                tmp.append(valor_atributo_classe)
        divisoes.append(tmp)
    
    #print(divisoes)

    classes = classes.astype(Valor_Classe)
    #print(classes)

    indice_classes = 0

    while indice_classes < len(classes):
        classes[indice_classes] = Valor_Classe(classes[indice_classes])
        indice_classes += 1
    
    #print(classes)
     
    impureza = 0
    indice_counts = 0
    aux = 0
    minimo = 1

    for divisao in divisoes:
        for valor_divisao in divisao:
            for classe in classes:
                if valor_divisao[1] == classe.valor_classe:
                    classe.count += 1
                    break
        for classe in classes:
            #print(classe.count)
            #print(counts[indice_counts])
            if criterio == "gini":
                aux += (classe.count / counts[indice_counts]) ** 2
            elif criterio == "entropia":
                if classe.count / counts[indice_counts] == 0:
                    aux -= 0
                else:
                    aux -= (classe.count / counts[indice_counts]) * np.log2((classe.count / counts[indice_counts]))
                #print(aux)
            elif criterio == "erro":
                #print("Classe count: ")
                #print(classe.count)
                #print("Counts: ")
                #print(counts[indice_counts])
                aux = min(minimo, classe.count / counts[indice_counts])
                minimo = aux
                #print(aux)
        if criterio == "gini":
            aux = (1 - aux)

        #print("Aux da impureza: ")
        #print(aux)
        impureza += counts[indice_counts] / np.sum(counts) * aux
        #print(impureza)
        
        aux = 0
        minimo = 1
        
        for classe in classes:
            classe.count = 0
        
        indice_counts += 1
    
    return impureza

def determina_melhor_particao(x, y, atributos, classes, criterio):

    indice_x = 0
    indice_atributos = 0
    impureza_min = 1
    melhor_particao= []

    for coluna in x.T:
        #print("Calcular a impureza de %s: " % atributos[indice_atributos])
        impureza = calcula_impureza(coluna, y, classes, criterio)
        # print(indice_atributos)
        #print("Impureza de %s: " % atributos[indice_atributos])
        #print(impureza)
        #print("{:.2f}".format(impureza))
        # print("Tamanho atributos: ")
        # print(len(atributos))
        # print("Atributos: ")
        # print(atributos)
        # print("Tamanho data: ")
        # print(len(x.T))
        if impureza < impureza_min:
            impureza_min = impureza
            melhor_coluna = coluna
            # print(melhor_coluna)
            melhor_coluna_indice = indice_x
            atributo = atributos[indice_atributos]
        indice_x += 1
        indice_atributos += 1
    

    #print(melhor_coluna)
    melhor_particao.append(melhor_coluna)
    melhor_particao.append(melhor_coluna_indice)
    melhor_particao.append(atributo)

    return melhor_particao

def cresce_arvore(x, y, atributos, classes, melhor_particao, criterio, arvore):

    melhor_coluna = melhor_particao[0]
    melhor_coluna_indice = melhor_particao[1]
    #print("O indice da melhor coluna é: ")
    #print(melhor_coluna_indice)
    atributo = melhor_particao[2]
    valores_atributo = np.unique(melhor_coluna)

    for valor_atributo in valores_atributo:
        exemplos = []
        y_new = []
        #print("Valor atributo atual: ")
        #print(valor_atributo)
        
        indice_x = 0
        for linha in x:
            #print(linha)
            if linha[melhor_coluna_indice] == valor_atributo:
                exemplos.append(linha)
                y_new.append(y[indice_x])
            indice_x += 1
        
        exemplos = np.array(exemplos)
        #print("Exemplos: ")
        #print(exemplos)
        y_new = np.array(y_new)
        #print("Classes dos exemplos: ")
        #print(y_new)

        #print(exemplos.T)
        coluna = exemplos.T[melhor_coluna_indice]
        #print(coluna)
        impureza = calcula_impureza(coluna, y_new, classes, criterio)
        if impureza == 0:
            #print("Atributo - Folha: ")
            #print(atributo)
            #print(valor_atributo)
            arvore.append(Folha(y_new[0], len(exemplos), atributo, valor_atributo))
            # print("Arvore: ")
            # for a in arvore:
            #     print(a)
            # print("\n")
        else:
            exemplos = np.delete(exemplos, melhor_coluna_indice, axis=1)
            #print("Atributos antes de eliminação: ")
            #print(atributos)
            atributos = np.delete(atributos, melhor_coluna_indice)
            #print("Exemplos atualizados: ")
            #print(exemplos)
            #print("Classes: ")
            #print(classes)
            #print("Atributos atualizados: ")
            #print(atributos)
            particao = determina_melhor_particao(exemplos, y_new, atributos, classes, criterio)
            #print("Melhor Particao: ")
            #print(particao[2])
            arvore.append(No(particao[2], np.unique(particao[0]), atributo, valor_atributo))
            # print("Arvore: ")
            # for a in arvore:
            #     print(a)
            # print("\n")
            cresce_arvore(exemplos, y_new, atributos, classes, particao, criterio, arvore)

def output_ramo(atributo, ramo, arvore, espaco="  "):

    for a in arvore:
        if a.no_pai == atributo and a.ramo_pai == ramo:
            no = a
            break
    
    if type(no) == Folha:
        print(espaco + "--> { %s : %d } (folha proveniente do ramo: %s)" % (no.classe, no.n_exemplos, no.ramo_pai))
        arvore.remove(no)
    else:
        print(espaco + "--> %s (nó proveniente do ramo: %s):" % (no.atributo, no.ramo_pai))
        for no_ramo in no.ramos:
            print(espaco + "--> %s (ramo de: %s):" % (no_ramo, no.atributo))
            output_ramo(no.atributo, no_ramo, arvore)

def output_arvore(raiz, raiz_ramos, arvore):
    
    print(raiz)
    for raiz_ramo in raiz_ramos:
        print("  --> %s (ramo de: %s - RAIZ):" % (raiz_ramo, raiz))
        output_ramo(raiz, raiz_ramo, arvore)

def corre_arvore(arvore, exemplo, atributos):

    passou = 0
    i = 0 

    for no in arvore:
        if(no.no_pai == None):
            filhos = no.ramos
            atributo_no = no.atributo
            numero = no.identificacao
            break
    for filho in filhos:
        if(passou == 0):
            for atributo in exemplo:
                if(atributo == filho and atributos[i] == atributo_no):
                    passou = 1
                    break
                i+=1
                if(i == len(exemplo)):
                        i = 0
                        break
        if(passou == 1):
            break
    
    classe = corre_subarvore(arvore, exemplo, atributos, atributo, atributo_no, numero)
    return classe

def corre_subarvore(arvore, exemplo, atributos, atributo, pai, numero):
    passou = 0
    i = 0
    folha = 0
    for no in arvore:
        if(no.ramo_pai == atributo and no.no_pai == pai):
            if (type(no) == Folha and no.identificacao > numero):
                classe = no.classe
                folha = 1
                break
            elif(no.identificacao > numero):
                filhos = no.ramos
                atributo_no = no.atributo
                numero = no.identificacao
                break
    
    if(folha == 0):
        for filho in filhos:
            if(passou == 0):
                for atributo in exemplo:
                    if(atributo == filho and atributos[i] == atributo_no):
                        passou = 1
                        break
                    i+=1
                    if(i == len(exemplo)):
                        i = 0
                        break
            if(passou == 1):
                break
        classe = corre_subarvore(arvore, exemplo, atributos, atributo, atributo_no, numero)

    return classe

# def calcula_exatidao(xdata, ydata, atributos, arvore):
#     t = 0
#     f = 0
#     j = 0
#     for x in xdata:
#         classe = corre_arvore(arvore, x, atributos)
#         if(ydata[j] == classe):
#             t += 1
#         else:
#             f += 1
#         j += 1
#     exat = t / (t + f) 
#     return exat