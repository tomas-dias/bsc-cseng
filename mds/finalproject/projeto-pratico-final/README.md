# ***Relatório da Implementação do Trabalho Prático - Sistema de Registo de Presenças***

## **Introdução**

Foi proposto na cadeira de Metodologias e Desenvolvimento de Software a realização de um trabalho prático tendo em vista a especificação e implementação de um sistema automático de registo de presenças nas aulas. Com a especificação do projeto feita anteriormente, esta foi avaliada pelos docentes que sugeriram algumas correções. As etapas da implementação do sistema encontram-se descritas de seguida com as respetivas correções na especificação efetuadas.

---

## **Implementação**

A implementação do trabalho foi dividida nas seguintes etapas:

- Implementação dos testes e das respetivas classes especificadas no diagrama de classes (Utilizador, Aluno, Docente, Aula, Falta e Presenca).

- Implementação das classes responsáveis pela leitura dos ficheiros com a informação dos dados dos utilizadores (alunos e docentes) e aulas.

- Implementação da aplicação leitor de cartões.

- Implementação da aplicação para a gestão do sistema.

---

### Elaboração dos testes e implementação das classes definidas no diagrama de classes

Começou-se por implementar a classe Utilizador. Como especificada no diagrama de classes, esta classe é abstrata sendo que os testes dos métodos desta foram feitos nas classes de teste das classes herdadas Aluno e Docente. Os métodos da classe Utilizador testados e implementados foram os seguintes:

- **getNome()**: retorna o nome (String) do utilizador (aluno ou docente).

- **getCartao()**: retorna o número (String) do cartão do utilizador (aluno ou docente).

- **getEmail()**: gera o email do utilizador com base na sua informação (nome e número do cartão) tendo em conta se é aluno ou docente retornando o email (String).

- **setSenha()**: recebe como argumento uma String senha guardando-a no sistema.

- **setLogin()**: recebe como argumento uma String email e uma String senha e gera a String login.

- **getLogin()**: retorna o login (String) do utilizador (aluno ou docente).

Em comparação com o que inicialmente foi especificado no diagrama de classes, foi implementado o método **setLogin()** e não foi implementado o método **getSenha()** pois conclui-se que para o funcionameneto do sistema era desnecessário a existência deste método. Também foi alterado o nome do atributo **num** para **cartao**. Os restantes métodos vão ao encontro daquilo que inicialmente foi especificado.

Para classe Aluno, além dos métodos anteriormente referidos, foram testados e implementados os métodos seguintes:

- **getAluno()**: recebe como argumentos uma String nome e uma String cartao do aluno e retorna o objeto aluno se este encontra-se na lista dos alunos da disciplina. Caso contrário, retorna null.

- **getPresencas()**: retorna o número total de presenças (inteiro) do aluno.

- **getMeiasPresencas()**: retorna o número total de meias-presenças (inteiro) do aluno.

- **getFaltas()**: retorna o número total de faltas (inteiro) do aluno.

- **getPercentagem()**: retorna a percentagem de faltas (double) do aluno.

Em comparação com o que previsto no diagrama de classes, não foram implementados o atributo boolean **temFalta** nem o respetivo método **getTemFalta()**, pois foram considerados desnecessários e o método **justificaFalta()** foi implementado na classe **Falta** que será descrita mais à frente. Não existiu alterações nos restantes métodos.

Para a classe Docente, além dos métodos da classe Utilizador, foi apenas testado e implementado o seguinte método:

- **alteraEstado()**: recebe como argumento uma String estado que pode ter o valor **"J"** (para justificada) e **"NJ"** (para não justificada), e altera o estado para o valor contrário (**"NJ"** e **"J"**, respetivamente).

Não existem diferenças em relação ao especificado no diagrama de classes.

Para a classe Aula foram testados e implementados os seguintes métodos:

- **getAula()**: recebe como argumentos uma String data e uma String hora e retorna o objeto aula se esta se encontrar na lista das aulas da disciplina. Caso contrário, retorna null.

- **getData()**: retorna a data (String) da aula.

- **getHora()**: retorna a hora (String) da aula.

- **getDuracao()**: retorna a duracao (String) da aula.

- **getTipo()**: retorna o tipo (String) da aula.

Não existem diferenças em relação ao especificado no diagrama de classes.

Para a classe Falta foram testados e implementados os seguintes métodos:

- **getData()**: retorna a data (String) da aula que o aluno faltou.

- **getHora()**: retorna a hora (String) da aula que o aluno faltou.

- **getEstado()**: retorna o estado (String) da falta.

- **contabilizaFalta()**: recebe como argumentos um objeto aluno e um objeto aula e incrementa o atributo faltas do objeto aluno, cria um objeto falta em que os seus atributos data e hora ficam iguais aos atributos data e hora do objeto aula, o atributo estado fica com o valor "NJ", o objeto aluno é adicionado à lista os alunos que faltaram do objeto aula e o objeto falta é adicionado à lista de faltas do objeto aluno.

- **atualizaFalta()**: recebe como argumentos um objeto aluno, um objeto aula e um objeto docente e verifica se o aluno faltou à aula e em caso afirmativo o estado da falta é alterado com o auxílio do método **alteraEstado()** da classe Docente.

- **justificaFalta()**: recebe como argumentos um objeto aluno, um objeto aula e uma String justificacao e verifica se o aluno tem falta à aula definida. Em caso afirmativo, é atríbuido o conteúdo da String justificacao ao atributo justificacao do objeto falta.

Em comparação com o especifícado no diagrama de classes, foram adicionados os atributos **data** e **hora** e os respetivos métodos **getData()** e **getHora()**, o método **alteraEstado()** e o método **justificaFalta()** que no diagrama pertencia à classe Aluno. Esta mudança deveu-se ao facto de se tratar de um método que relaciona o objeto aluno ao objeto aula e por isso conclui-se que fazia mais sentido que o mesmo pertencesse à classe que dá nome à relação desses objetos (neste caso, Falta). Foram removidos os métodos **alteracaoConfirmada()** e **alteracaoInvalida()** por serem considerados inúteis. Os restantes métodos não sofreram alterações.

Por fim, para a classe Presenca foram testados e implementados os seguintes métodos:

- **contabilizaPresenca()**: recebe como argumentos um objeto aluno e um objeto aula e incrementa o atributo presencas do objeto aluno e adiciona o objeto aluno à lista de alunos presentes do objeto aula.

- **contabilizaMeiaPresenca()**: recebe como argumentos um objeto aluno e um objeto aula e incrementa o atributo meiaspresencas do objeto aluno e adiciona o objeto aluno à lista de alunos presentes do objeto aula.

Não existem diferenças em relação ao especificado no diagrama de classes.

---

### Implementação das classes responsáveis pela leitura dos ficheiros com os dados dos utilizadores e das aulas

Para a leitura dos ficheiros com os dados dos utilizadores (alunos e docentes) foi implementada a classe **LeitorDadosUtilizadores** que tem os seguintes métodos:

- **dados()**: utilizando a biblioteca Gson (para facilitar a conversão dos elementos do ficheiro Json para objetos), é lido o ficheiro e retorna um array com os utilizadores.

- **alunos()**: recebe como argumento um array de utilizadores e verifica se o atributo papel corresponde a "aluno". Em caso afirmativo, são criados objetos da classe Aluno com os atributos descritos no array e é retornado uma ArrayList de objetos da classe Aluno.

- **docentes()**: recebe como argumento um array de utilizadores e verifica se o atributo papel corresponde a "docente". Em caso afirmativo, são criados objetos da classe Docente com os atributos descritos no array e é retornado uma ArrayList de objetos da classe Docente.

Para a leitura dos ficheiros com os dados das aulas foi implementada a classe **LeitorDadosAulas** que tem o seguintes método:

- **aulas()**: utilizando a biblioteca Gson (para facilitar a conversão dos elementos do ficheiro Json para objetos), é lido o ficheiro e é retornado uma ArrayList com objetos da classe Aula.

---

### Implementação da aplicação do leitor de cartões

A implementação desta aplicação foi implementada diretamente no método **main()** e funciona da seguinte forma:

**1.** Em primeiro lugar é verificado se existem ou não aulas ( if(aulas != null) ).

**2.** Em seguida é percorrido um ciclo enquanto a arrayList aulas tivesse aulas em que cada posição da arraylist era guardada um objeto aula.

**3.** Depois é iniciado um ciclo que termina quando o tempo atual for maior ou igual ao tempo em que a aula termina neste caso quando são ocorridos um minuto de aula e onde é pedido durante esse tempo para serem inseridos os números dos cartões.

**4.** É verificado se nos primeiros 25 segundos é introduzido o cartão do docente para confirmar se houve ou não aula.

**5.** Em caso de o docente não ter passado o cartão aparece uma mensagem a dizer que a aula não se realizou e sai do ciclo do tempo de aula, passando logo para a aula seguinte.

**6.** Em caso de o docente ter passado o cartão antes de ocorridos 25 segundos de aula, é percorrido a arraylist alunos em que cada posião da arraylist é guardada um objeto aluno e é verificado se o aluno passou antes de ocorridos 30 segundos de aula, se o aluno passou antes de ocorridos 60 segundos de aula ou se não passou nos 60 segundos de aula.

**7.** No caso de o aluno inserir o número do cartão antes de ocorridos 30 segundos de aula, é verificado se o número do cartão inserido corresponde ao de algum aluno, se for é percorrido um ciclo que verifica se o número do cartão inserido já está na lista de alunos presentes à aula, se ainda não tiver contabiliza-se a presença do aluno monstrando uma mensagem a dizer que o aluno tem presença na aula e é adicionado à lista de alunos presentes à aula, se o aluno já tivesse na lista de alunos presentes à aula apareceria uma mensagem a dizer que o aluno já tinha passado o cartão.

**8.** No caso de o aluno inserir o número do cartão depois de ocorridos 30 segundos de aula, é verificado se o número do cartão inserido corresponde ao de algum aluno, se for é percorrido um ciclo que verifica se o número do cartão inserido já está na lista de alunos presentes à aula, se ainda não tiver contabiliza-se como meia presença do aluno monstrando uma mensagem a dizer que o aluno teve meia presença na aula e é adicionado à lista de alunos presentes à aula, se o aluno já tivesse na lista de alunos presentes à aula apareceria uma mensagem a dizer que o aluno já tinha passado o cartão.

**9.** Se não acontecer nenhum destes casos, vai ser verificado se o número do cartão inserido corresponde ao de algum aluno que esteja na lista de alunos presentes na aula, se tiver aparece a mensagem a dizer que o aluno já passou o cartão senão é contabilizada falta ao aluno.

**10.** Assim que a aula é terminada e tenha havido aula, é percorrida a arraylist alunos e verifica para cada aluno inscrito se esteve ou não presente na aula se a lista de alunos que faltaram tiver vazia e o aluno não tiver na lista de presentes na aula é inserido na lista de alunos que faltaram, senão se o aluno não tiver na lista de alunos presentes e não inseriu o número depois de 60 segundos de aula é também inserido na lista de alunos que faltaram.

**11.** No fim de terem sido percorridas todas as aulas, é escrito para o ficheiro dados.txt o array de objetos alunos atualizado e para o ficheiro aulas.txt o array de objetos aulas atualizado. 

---

### Implementação da aplicação para a gestão do sistema

Na implementação da aplicação para a gestão do sistema foram implementadas os seguintes métodos:

- **importa_dados_alunos()**: faz a leitura do ficheiro com a informação atualizada (após a execução do leitor de cartões) dos alunos e retorna uma ArrayList de objetos alunos.

- **importa_dados_aulas()**: faz a leitura do ficheiro com a informação atualizada (após a execução do leitor de cartões) das aulas e retorna uma ArrayList de objetos aulas.

- **calcula_percentagem()**: recebe como argumento um objeto aluno e calcula a percentagem de faltas do mesmo em relação ao número de aulas realizadas guardando o resultado no atributo percentagem do objeto aluno.

- **menuAluno()**: este método recebe como argumento um objeto aluno e é responsável por apresentar a estrutura do menu do aluno e pelo o funcionamento das funcionalidades associadas ao aluno. Para a funcionalidade **"Justificar falta"** é pedido um input com a data e a hora da aula que aluno pretende justificar e é verificado se estes atributos são idênticos aos atributos com o mesmo nome de alguma falta na lista de faltas do aluno. Se se verifica, é pedido um novo input com a justificação e é executado o método **justificaFalta()** da classe Falta, e é apresentada uma mensagem no ecrã que a justificação foi feita com sucesso. No caso de a condição acima não se verificar, é apresentada uma mensagem no ecrã que o aluno não tem falta à aula selecionada. Na funcionalidade **"Mostrar Relatório de Faltas"** é executado o método **calcula_percentagem()** que recebe como argumento um objeto aluno, e é apresentado no ecrã o nome do aluno, o cartão do aluno, o número de presenças, meias-presenças e faltas do aluno e as percentagens de presenças e faltas do aluno. Na funcionalidade **"Consultar faltas do aluno"** é apresentado no ecrã a lista de faltas do aluno com a data e a hora da aula que o aluno faltou, o estado da falta e a justificação. No caso de não existirem faltas, é apresentado no ecrã uma mensagem que aluno não tem faltas. A funcionalidade **"Mudar de utilizador"** permite que seja introduzido um novo input de cartão de utilizador.

- **menuDocente()**: este método recebe como argumento o objeto docente e é responsável por apresentar a estrutura do menu do docente e pelo o funcionamento das funcionalidades associadas ao docente. Para a funcionalidade **"Importar dados utilizadores"** é verificado se a arrayList dos alunos não está vazia e se não estiver percorre essa mesma arrayList apresentando no ecrã o nome, cartão e email de cada aluno, e depois é verificado o mesmo para para a arrayList dos docentes e é apresentado os mesmos dados. Para a funcionalidade **"Alterar estado"** é pedido que seja introduzido o número do cartão do aluno a quem pretende alterar o estado de falta percorrendo a arrayList dos alunos verificando se o número introduzido pertence a algum aluno se pertencer é pedido em qual a aula da falta que deseja alterar verificando se a mesma pertence a alguma aula da arrayList se for altera o estado da falta se não lança uma exceção a dizer que o aluno não tem falta na aula introduzida. Para a funcionalidade **"Mostrar Relatório de faltas"** é percorrido a arrayList dos alunos onde para cada aluno é calculado a percentagem de falta, monstrando o nome, o cartao, o número de presenças,faltas e meias presenças e as respetivas percentagens no ecrã, é verificado também se a percentagem de faltas está entre 25% e 50% ou maior que 50% se for entre 25% e 50% o aluno é adicionado à lista de alunos com entre 25% e 50% de faltas sendo apresentado no ecrã os nomes desses alunos senão é adicionado à lista de alunos com mais de 50% de faltas apresentado também os nomes dos alunos com mais de 50% de faltas. Para a funcionalidade **"Consultar faltas por aluno"** é pedido que seja introduzido o número do cartão do aluno a quem pretende ver as aulas em que faltou apresentando no ecrã as datas das aulas, assim como as hora, os estados das faltas (J - justificada NJ - não justificada) e as justificações caso não tenha qualquer falta é apresentado no ecrã que o aluno não tem faltas. Para a funcionalidade **"Consultar presenças e faltas por aula"** é pedido que seja introduzido a data da aula que pretende ver a lista de alunos que faltaram e que estiveram presentes, caso as lista de alunos presentes da data da aula inserida não esteja vazia ou a dos alunos que faltaram não esteja vazia apresenta as listas senão a aula inserida não se realizou. A funcionalidade **"Mudar de utilizador"** permite que seja introduzido um novo input de cartão de utilizador.

- **main()**: método responsável por executar os métodos anteriormente descritos. É inicialmente pedido um input de um cartao de utilizador e no caso de se tratar de um docente executa o **menuDocente()**, e no caso de se tratar de um aluno executa o **menuAluno()**.

Em comparação com o que foi específicado nos **use cases** do projeto, todas as funcionalidades foram implementadas. 

---

*Trabalho realizado por:*

- Rui Roque nº 42720
- Tomás Dias nº 42784