import java.io.Serializable;

public class Presenca implements Serializable
{
    public void contabilizaPresenca(Aluno aluno, Aula aula)
    {
        aluno.presencas++;
        aula.listaAlunosPresentes.add(aluno);
    }

    public void contabilizaMeiaPresenca(Aluno aluno, Aula aula)
    {
        aluno.meiasPresencas++;
        aula.listaAlunosPresentes.add(aluno);
    }
}
