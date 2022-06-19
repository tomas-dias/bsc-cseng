import java.io.Serializable;

public class Falta implements Serializable
{
    String data, hora, estado, justificacao;

    public String getData()
    {
        return data;
    }

    public String getHora()
    {
        return hora;
    }

    public String getEstado()
    {
        return estado;
    }

    public void contabilizaFalta(Aluno aluno, Aula aula)
    {
        Falta falta = new Falta();
        aluno.faltas++;
        aula.listaAlunosFaltaram.add(aluno);
        falta.data = aula.data;
        falta.hora = aula.hora;
        falta.estado = "NJ";
        aluno.listaFaltas.add(falta);
    }

    public void atualizaFalta(Aluno aluno, Aula aula, Docente docente) throws RuntimeException
    {
        for(Falta obj : aluno.listaFaltas) {
            if(aula.data.equals(obj.data) && aula.hora.equals(obj.hora)) {
                obj.estado = docente.alteraEstado(obj.estado);
                return;
            }
        }
        throw new RuntimeException("O aluno nao tem falta na aula introduzida");
    }

    public void justificaFalta(Aluno aluno, Aula aula, String justificacao) {
        for(Falta obj : aluno.listaFaltas) {
            if(aula.data.equals(obj.data) && aula.hora.equals(obj.hora)) {
                obj.justificacao = justificacao;
            }
        }
    }
}
