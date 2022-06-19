import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FaltaTest
{
    Falta falta;

    @BeforeEach
    public void setUp()
    {
        falta = new Falta();
    }

    @AfterEach
    public void down()
    {
        falta = null;
    }

    //@Disabled
    @Test
    public void testGetDataRetornaDataDaAulaQueAlunoFaltou()
    {
        falta.data = "2020-06-17";

        assertEquals("2020-06-17", falta.getData());
    }

    //@Disabled
    @Test
    public void testGetHoraRetornaHoraDaAulaQueAlunoFaltou()
    {
        falta.hora = "11:00";

        assertEquals("11:00", falta.getHora());
    }

    //@Disabled
    @Test
    public void testGetEstadoRetornaEstadoDaAulaQueAlunoFaltou()
    {
        falta.estado = "J";

        assertEquals("J", falta.getEstado());
    }

    //@Disabled
    @Test
    public void testContabilizaFaltaIncrementaAtributoFaltasAlunoEAdicionaObjetoAlunoAoAtributoListaAlunosFaltaramAulaEAdicionaObjetoFaltaAoAtributoListaFaltasAluno()
    {
        Aluno a1 = new Aluno(); a1.nome = "Joao";
        Aula al = new Aula();
        al.data = "2020-06-17";
        al.hora = "11:00";
        ArrayList<Aluno> teste1 = new ArrayList<>(); teste1.add(a1);

        falta.contabilizaFalta(a1, al);

        assertEquals(1, a1.faltas);
        assertEquals(teste1, al.listaAlunosFaltaram);
        assertNotNull(a1.listaFaltas.get(0));
    }

    @Test
    public void testAtualizaFaltaSeAlunotemFaltaAUmaAulaAlteraOEstadoDaFaltaDeNaoJustificadaParaJustificada() throws RuntimeException
    {
        Docente d = new Docente();
        Aluno a = new Aluno();
        Aula al = new Aula();
        al.data = "2020-06-17";
        al.hora = "11:00";
        falta.hora = "11:00";
        falta.data = "2020-06-17";
        falta.estado = "NJ";
        a.listaFaltas.add(falta);
        al.listaAlunosFaltaram.add(a);

        falta.atualizaFalta(a, al, d);

        assertEquals("J", a.listaFaltas.get(0).estado);
    }

    @Test
    public void testAtualizaFaltaSeAlunotemFaltaAUmaAulaAlteraOEstadoDaFaltaDeJustificadaParaNaoJustificada() throws RuntimeException
    {
        Docente d = new Docente();
        Aluno a = new Aluno();
        Aula al = new Aula();
        al.data = "2020-06-17";
        al.hora = "11:00";
        falta.data = "2020-06-17";
        falta.hora = "11:00";
        falta.estado = "J";
        a.listaFaltas.add(falta);
        al.listaAlunosFaltaram.add(a);

        falta.atualizaFalta(a, al, d);

        assertEquals("NJ", a.listaFaltas.get(0).estado);
    }

    @Test
    public void testAtualizaFaltaLancaExcecaoSeAlunoNaotemFaltaNaAulaSelecionada() throws RuntimeException
    {
        Docente d = new Docente();
        Aluno a = new Aluno();
        Aula al = new Aula();

        assertThrows(RuntimeException.class, () -> falta.atualizaFalta(a, al, d));
    }

    @Test
    public void testJustificaFaltaGuardaInputNoAtributoJustificacaoDoObjetoFalta()
    {
        Aluno a = new Aluno();
        Aula al = new Aula(); al.data = "2020-06-01"; al.hora = "11:00";
        falta.data = "2020-06-01"; falta.hora = "11:00";
        String justificacao = "Doença";

        a.listaFaltas.add(falta);

        falta.justificaFalta(a, al, justificacao);

        assertEquals("Doença", falta.justificacao);
    }
}
