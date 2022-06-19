import java.io.Serializable;

public class Docente extends Utilizador implements Serializable
{
    public String alteraEstado(String estado)
    {
        if(estado.equals("J")) {
            return "NJ";
        }
        else if(estado.equals("NJ")) {
            return "J";
        }
        else {
            throw new RuntimeException("Estado invalido.");
        }
    }
}
