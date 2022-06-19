# **MDS Final Project Directions (in Portuguese)**

## **Sistema automático de registo de presenças nas aulas**

### **Descrição do sistema**

Pretende-se desenvolver um sistema automático de registo de presenças nas aulas de MDS. O sistema deve permitir registar as presenças dos alunos e dos professores automaticamente, através da leitura do cartão de aluno ou docente, respectivamente.

Os requisitos do sistema estão descritos abaixo.

- O sistema deve possuir uma configuração persistente, onde estão definidas as aulas (data, hora, duração e tipo);

- O sistema deve importar a lista de alunos inscritos à disciplina a partir do SIIUE;

- Na hora da aula, o docente e os alunos passam os seus cartões no leitor de cartões, sendo contabilizada a sua presença nessa aula;

- Quando o docente não passa o seu cartão, considera-se que não houve aula e, consequentemente, não são contabilizadas as faltas;

- Sempre que um aluno atinja 25% de faltas, o sistema deve enviar um email ao aluno e outro aos docentes a informar sobre esta situação;

- Sempre que um aluno atinja 50% de faltas, o sistema deve enviar um email ao aluno, aos docentes e ao director de curso a informar sobre esta situação;

- Se um aluno passa o seu cartão depois de decorrida 1h sobre a hora de início da aula, apenas será considerada meia presença;

- A cada hora de aula, o sistema deve avaliar as condições acima, enviando automaticamente os emails necessários;

- A qualquer momento, o docente deve poder alterar o estado de faltas como justificadas ou injustificadas;

- No final do semestre é gerado um relatório com a seguinte informação:

  - Listagem de alunos com o número de presenças e respectiva percentagem;

  - Gráfico de presenças por aula, ao longo do tempo;

  - Lista de alunos com entre 25% e 50% de faltas;

  - Lista de alunos com mais de 50% de faltas;

---

### **Implementação**

O trabalho prático da disciplina terá como base um sistema de registo automático de presenças e será realizado em duas partes: (1) especificação e (2) implementação. A especificação do sistema será feita sobre a totalidade do sistema, enquanto que a implementação será feita apenas sobre parte do sistema, de acordo com as especificações a serem divulgadas.

A descrição do sistema apresenta alguns aspectos pouco claros, mal definidos ou em aberto, pelo que a interpretação do enunciado e as decisões tomadas para resolver estes problemas fazem parte da avaliação do trabalho e têm de ser descritas no relatório de implementação.

Toda a gestão do projecto deve ser feita no GitLab. Para tal, os alunos devem criar um projecto específico para o trabalho. Este projecto deve ter como membros todos os elementos do grupo, bem como os docentes, com papel de “maintainer”.

A actividade de todos os membros do projecto ficará registada no GitLab, com informação do utilizador e data da acção. Esta informação será usada na avaliação do trabalho. Assim, é recomendado que todos os elementos do grupo participem activamente no projecto, sob pena de serem penalizados em relação aos outros elementos que participam mais activamente.

Tentativas de fraude serão penalizadas com a atribuição de nota zero a todos os elementos do grupo em questão.
