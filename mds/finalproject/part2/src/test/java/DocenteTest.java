import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;

public class DocenteTest
{
    Docente docente;

    @BeforeEach
    public void setUp()
    {
        docente = new Docente();
    }

    @AfterEach
    public void down()
    {
        docente = null;
    }

    @Test
    public void testAlteraEstadoQuandoEstadoEJReturnsEstadoComoNJ()
    {
        assertEquals("NJ", docente.alteraEstado("J"), "Estado da falta e J(Justificada) então ao chamar a alteraEstado deve alterar o estado da falta para NJ(nao Justificada)");
    }

    @Test
    public void testAlteraEstadoQuandoEstadoENJRetornaEstadoComoJ()
    {
        assertEquals("J", docente.alteraEstado("NJ"), "Estado da falta e NJ(nao justificada) então ao chamar a alteraEstado deve alterar o estado da falta para J(Justificada)");
    }

    @Test
    public void testAlteraEstadoQuandoEstadoEQualquerCoisaRetornaEstadoComoNJ()
    {
        assertThrows(RuntimeException.class, () -> docente.alteraEstado("ajsdhasdkjkalsf"), "Estado da falta nao e J nem NJ então ao chamar a alteraEstado deve lancar uma excecao com a msg de Estado Invalido");
    }

    @Test
    public void testGetNomeRetornaStringNome()
    {
        docente.nome = "Leonie Leonard";

        assertEquals("Leonie Leonard", docente.getNome(), "Nome do docente e Leonie Leonard entao deve retornar Leonie Leonard");
    }

    @Test
    public void testGetCartaoRetornaNumeroDoDocente()
    {
        docente.cartao = "100";

        assertEquals("100", docente.getCartao(), "Numero do docente e 100 entao deve retornar 100");
    }

    @Test
    public void testGetEmailSePapelForDocenteRetornaEmailDoDocente()
    {
        docente.nome = "Leonie Leonard";
        docente.papel = "docente";

        assertEquals("LL@uevora.pt", docente.getEmail());
    }

    @Test
    public void testGetEmailSePapelNaoForDocenteNemAlunoRetornaNaoEDaUniversidade()
    {
        docente.nome = "Leonie Leonard";
        docente.papel = "Professor";

        assertNull(docente.getEmail());
    }

    @Test
    public void testSetSenha()
    {
        docente.setSenha("asdfH");

        assertEquals("asdfH", docente.senha);
    }

    @Test
    public void testSetLoginSeEmailForODoDocenteESenhaCorretaLoginENormal()
    {
        docente.senha = "asd";
        docente.email = "ll@uevora.pt";
        docente.setLogin("ll@uevora.pt", "asd");

        assertEquals("Username: ll@uevora.pt Password: asd", docente.login);
    }

    @Test
    public void testSetLoginSeEmailNaoForDaUniversidadeLoginEIgualAEmailInvalido()
    {
        docente.senha = "asd";
        docente.setLogin("ll@gmail.com", "asd");

        assertEquals("Email Invalido!", docente.login, "Email n e da universidade de évora então login deve ficar com a string  Email Invalido!");
    }

    @Test
    public void testSetLoginSeEmailForODoDocenteESenhaIncorretaLoginEIgualASenhaInvalida()
    {
        docente.senha = "asd";
        docente.setLogin("ll@uevora.pt", "asdh");

        assertEquals("Senha Invalida!",docente.login);
    }

    @Test
    public void testSetLoginSenhaNaoCorrespondeAoEmailLoginFicaComAStringSenhaInvalida()
    {
        docente.senha = "asd";
        docente.email = "LL@uevora.pt";
        docente.setLogin("LR@alunos.uevora.pt", "asd");

        assertEquals("Senha Invalida!", docente.login);
    }

    @Test
    public void testGetLoginRetornaLogin()
    {
        docente.login = "Username: LL@uevora.pt Password: asd";

        assertEquals("Username: LL@uevora.pt Password: asd", docente.getLogin(), "Email do docente e ll@uevora.pt entao deve retornar ll@uevora.pt");
    }

    @Test
    public void testGetLoginSeEmailInvalidoLancaExcecao()
    {
        docente.login = "Email Invalido!";

        assertThrows(RuntimeException.class, () -> docente.getLogin());
    }

    @Test
    public void testGetLoginSeSenhaInvalidaLancaExcecao()
    {
        docente.login = "Senha Invalida!";

        assertThrows(RuntimeException.class, () -> docente.getLogin());
    }
}
