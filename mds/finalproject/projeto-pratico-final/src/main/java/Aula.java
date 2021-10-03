import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Aula implements Serializable
{
    int duracao;
    String data, hora, tipo;
    ArrayList<Aluno> listaAlunosPresentes = new ArrayList<>();
    ArrayList<Aluno> listaAlunosFaltaram = new ArrayList<>();

    public Aula getAula(String data, String hora)
    {
        for(Aula aula : Objects.requireNonNull(LeitorDadosAulas.aulas())) {
            if(aula.data.equals(data) && aula.hora.equals(hora)){
                return aula;
            }
        }
        return null;
    }

    public String getData()
    {
        return data;
    }

    public String getHora()
    {
        return hora;
    }

    public int getDuracao()
    {
        return duracao;
    }

    public String getTipo()
    {
        return tipo;
    }
}
