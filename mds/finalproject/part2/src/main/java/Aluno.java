import java.io.Serializable;
import java.util.ArrayList;

public class Aluno extends Utilizador implements Serializable
{
    int presencas, meiasPresencas, faltas;
    double percentagem;
    ArrayList<Falta> listaFaltas = new ArrayList<>();;

    public Aluno getAluno(String nome, String cartao)
    {
        for(Aluno aluno : LeitorDadosUtilizadores.alunos(LeitorDadosUtilizadores.dados())) {
            if(aluno.nome.equals(nome) && aluno.cartao.equals(cartao)) {
                return aluno;
            }
        }
        return null;
    }

    public int getPresencas()
    {
        return presencas;
    }

    public int getMeiasPresencas()
    {
        return meiasPresencas;
    }

    public int getFaltas()
    {
        return faltas;
    }

    public double getPercentagem()
    {
        return percentagem;
    }
}
