import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AulaTest
{
    Aula aula;

    @BeforeEach
    public void setUp()
    {
        aula = new Aula();
    }

    @AfterEach
    public void down()
    {
        aula = null;
    }

    @Test
    public void testGetAulaRetornaObjetoAulaNotNullSeAulaEstaNaListaAulas()
    {
        aula.data = "2020-06-01";
        aula.hora = "10:00";

        assertNotNull(aula.getAula(aula.data, aula.hora));
    }

    @Test
    public void testGetAulaRetornaNullSeOAulaNaoEstaNaLista()
    {
        aula.data = "2020-06-17";
        aula.hora = "15:00";

        assertNull(aula.getAula(aula.data, aula.hora));
    }

    @Test
    public void testGetDataRetornaStringDataDaAula()
    {
        aula.data = "2020-06-17";

        assertEquals("2020-06-17", aula.getData());
    }

    @Test
    public void testGetHoraRetornaStringHoraDaAula()
    {
        aula.hora = "15:00";

        assertEquals("15:00", aula.getHora());
    }

    @Test
    public void testGetDuracaRetornaInteiroDuracaoDaAula()
    {
        aula.duracao = 120; // em minutos

        assertEquals(120, aula.getDuracao());
    }

    @Test
    public void testGetTipoRetornaStringTipoDaAula()
    {
        aula.tipo = "Pratica";

        assertEquals("Pratica", aula.getTipo());
    }

    @Test
    public void testGetListaAlunosPresentesRetornaArrayListDeObjetosAlunoDosAlunosQueEstiveramPresentes()
    {
        ArrayList<Aluno> teste = new ArrayList<>();
        Aluno a1 = new Aluno(); a1.nome = "Joao";
        Aluno a2 = new Aluno(); a2.nome = "Manuel";
        Aluno a3 = new Aluno(); a3.nome = "Maria";

        teste.add(a1);
        teste.add(a2);
        teste.add(a3);

        Presenca p = new Presenca();
        p.contabilizaPresenca(a1, aula);
        p.contabilizaPresenca(a2, aula);
        p.contabilizaPresenca(a3, aula);

        assertEquals(teste, aula.listaAlunosPresentes);
    }

    @Test
    public void testGetListaAlunosFaltaramRetornaArrayListDeObjetosAlunoDosAlunosQueFaltaram()
    {
        ArrayList<Aluno> teste = new ArrayList<>();
        Aluno a1 = new Aluno(); a1.nome = "Joao";
        Aluno a2 = new Aluno(); a2.nome = "Manuel";
        Aluno a3 = new Aluno(); a3.nome = "Maria";

        teste.add(a1);
        teste.add(a2);
        teste.add(a3);

        Falta f = new Falta();
        f.contabilizaFalta(a1, aula);
        f.contabilizaFalta(a2, aula);
        f.contabilizaFalta(a3, aula);

        assertEquals(teste, aula.listaAlunosFaltaram);
    }
}
