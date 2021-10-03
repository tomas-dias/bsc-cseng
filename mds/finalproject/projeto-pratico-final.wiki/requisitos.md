# ***Requisitos***

## **Requisitos de utilizador**

1\. "O utilizador deve conseguir registar a presença numa aula através da leitura do seu cartão."

2\. "O utilizador deve conseguir autenticar-se através dos dados do SIIUE."

3\. "O utilizador, no caso de se tratar de um docente, deve conseguir aceder à informação atualizada relacionada com o registo de presenças e faltas de todos os alunos inscritos à disciplina."

4\. "O utilizador, no caso de se tratar de um docente, deve conseguir aceder à informação atualizada relacionada com o registo de presenças e faltas de cada aula."

5\. "O utilizador, no caso de se tratar de um aluno, deve conseguir aceder à informação atualizada relacionada com o registo de presenças e faltas do aluno."

6\. "O utilizador, no caso de se tratar de um docente, pode alterar o estado de uma falta de um aluno"

7\. "O utilizador, no caso de se tratar de um aluno, pode adicionar uma justificação a uma falta."

## **Requisitos de sistema**

1.1. "Na hora da aula, o docente e os alunos passam os seus cartões no leitor de cartões, sendo contabilizada a sua presença nessa aula."

1.2. "Quando o docente não passa o seu cartão ao fim de 50 minutos desde a hora prevista de início da aula, considera-se que não houve aula e, consequentemente, não são contabilizadas as faltas."

1.3. "Se um aluno passa o seu cartão depois de decorrida 1h sobre a hora prevista de início da aula, apenas será considerada meia presença."

1.4. "Após decorrido o tempo de duração da aula, se aluno não tiver passado o seu cartão, deve ser contabilizado como falta injustificada."

---

2.1. "O sistema deve importar a lista de alunos inscritos à disciplina a partir do SIIUE."

2.2. "O sistema deve importar a lista de docentes que lecionam a disciplina a partir do SIIUE."

2.3. "Deve ser permitida a entrada no sistema através do email e da palavra-passe da conta do SIIUE do utilizador."

---

3.1. "O sistema deve apresentar a lista de todos os alunos inscritos com o respetivo nome completo e número de aluno; número de presenças, meias presenças, faltas justificadas e faltas injustificadas; e a percentagem de faltas."

---

4.1. "O sistema deve possuir uma configuração persistente, onde estão definidas as aulas (data, hora, duração e tipo)."

4.2. "O sistema deve apresentar a lista dos alunos presentes ou com meia presença com o nome completo e número de aluno de cada um; e a lista dos alunos que faltaram com o nome completo e número de aluno de cada um, com a informação sobre se a falta se encontra justificada ou injustificada, e no caso de se encontrar justificada, é mostrada uma anotação com a justificação do aluno."

---

5.1. "O sistema apresentar a contabilização das presenças e faltas de cada aluno como o número de presenças, meias presenças, faltas, percentagem de faltas, informação se o aluno esteve presente ou faltou a uma aula e se neste último caso, se a falta foi justificada ou injustificada."

---

6.1. "O docente pode alterar o estado de faltas como justificadas ou injustificadas, em qualquer momento."

6.2. "Sempre que um aluno atinja 25% de faltas, o sistema deve enviar um email ao aluno e outro aos docentes a informar sobre esta situação."

6.3. "Sempre que um aluno atinja 50% de faltas, o sistema deve enviar um email ao aluno, aos docentes e ao director de curso a informar sobre esta situação."

6.4. "O sistema deve avaliar as condições acima, a cada hora de aula."

---

7.1. "No caso de o utilizador se tratar de um aluno, este pode adicionar uma justificação a uma determinada falta com um prazo de 1 semana desde da aula em que faltou."

---

"Toda a informação do sistema deve estar guardada numa base de dados."

"O sistema deve ser atualizado automaticamente quando verificada qualquer alteração."

"O sistema deve gerar um relatório no final do semestre com toda a informação relacionada com a contabilização de faltas e presenças que inclui a listagem de alunos com o número de presenças e respetiva percentagem, um gráfico com as presenças por aula ao longo do tempo, uma lista dos alunos com entre 25% e 50% de faltas e uma lista dos alunos com mais de 50% de faltas."
