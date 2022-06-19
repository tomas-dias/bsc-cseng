import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest
{
    Aluno aluno;

    @BeforeEach
    public void setUp()
    {
        aluno = new Aluno();
    }

    @AfterEach
    public void down()
    {
        aluno = null;
    }

    //@Disabled
    @Test
    public void testGetNomeRetornaStringNomeAluno()
    {
        aluno.nome = "Haris Seferovic";

        assertEquals("Haris Seferovic", aluno.getNome());
    }

    //@Disabled
    @Test
    public void testGetCartaoRetornaStringNumeroCartaoAluno()
    {
        aluno.cartao = "300";

        assertEquals("300", aluno.getCartao());
    }

    //@Disabled
    @Test
    public void testGetAlunoRetornaObjetoAlunoNotNullSeAlunoEstaNaListaAlunos()
    {
        aluno.nome = "Janice Mccray";
        aluno.cartao = "008";

        assertNotNull(aluno.getAluno(aluno.nome, aluno.cartao));
    }

    //@Disabled
    @Test
    public void testGetAlunoRetornaNullQuandoAlunoNaoEstaNaListaAlunos()
    {
        assertNull(aluno.getAluno(aluno.nome, aluno.cartao), "O aluno não existe!");
    }

    //@Disabled
    @Test
    public void testGetPresencasRetornaInteiroNumeroPresencas()
    {
        aluno.presencas = 10;

        assertEquals(10, aluno.getPresencas());
    }

    //@Disabled
    @Test
    public void testGetMeiasPresencasRetornaInteiroNumeroMeiasPresencas()
    {
        aluno.meiasPresencas = 3;

        assertEquals(3, aluno.getMeiasPresencas());
    }

    //@Disabled
    @Test
    public void testGetFaltasRetornaInteiroNumeroFaltas()
    {
        aluno.faltas = 23;

        assertEquals(23, aluno.getFaltas());
    }

    //@Disabled
    @Test
    public void testGetPercentagemRetornaDoublePercentagemFaltas()
    {
        aluno.percentagem = 63.4;

        assertEquals(63.4, aluno.getPercentagem());
    }

    //@Disabled
    @Test
    public void testSetSenhaEmQueSenhaFicaComAStringInserida()
    {
        aluno.setSenha("asd");

        assertEquals("asd", aluno.senha, "Aluno definiu que a senha era asd entao senha deve ficar com a string asd");
    }

    //@Disabled
    @Test
    public void testGetEmailPapelIgualAAlunoRetornaEmailDoAluno()
    {
        aluno.papel  = "aluno";
        aluno.cartao = "003";

        assertEquals("l003@alunos.uevora.pt", aluno.getEmail());
    }

    //@Disabled
    @Test
    public void testGetEmailPapelInvalidoRetornaNull()
    {
        aluno.papel = "Estudante";

        assertNull(aluno.getEmail());
    }

    //@Disabled
    @Test
    public void testSetLoginEmailValidoESenhaValidaLoginFicaComAStringCorreta()
    {
        aluno.senha = "asd";
        aluno.email = "l003@alunos.uevora.pt";
        aluno.setLogin("l003@alunos.uevora.pt", "asd");

        assertEquals("Username: l003@alunos.uevora.pt Password: asd", aluno.login);
    }

    //@Disabled
    @Test
    public void testSetLoginEmailInvalidoLoginFicaComAStringEmailInvalido()
    {
        aluno.email = "l003@gmail.com";
        aluno.setLogin("l003@gmail.com", "asd");

        assertEquals("Email Invalido!", aluno.login);
    }

    //@Disabled
    @Test
    public void testSetLoginSenhaInvalidaLoginFicaComAStringSenhaInvalida()
    {
        aluno.senha = "asd";
        aluno.email = "l003@alunos.uevora.pt";
        aluno.setLogin("l003@alunos.uevora.pt", "asdH");

        assertEquals("Senha Invalida!", aluno.login);
    }

    //@Disabled
    @Test
    public void testSetLoginSenhaNaoCorrespondeAoEmailLoginFicaComAStringSenhaInvalida()
    {
        aluno.senha = "asd";
        aluno.email = "l004@alunos.uevora.pt";
        aluno.setLogin("l003@alunos.uevora.pt", "asd");

        assertEquals("Senha Invalida!", aluno.login);
    }

    //@Disabled
    @Test
    public void testGetLoginEmQueLoginEIgualAEmailInvalidoDeveLancarUmaExcecao()
    {
        aluno.login = "Email Invalido!";

        assertThrows(RuntimeException.class, () -> aluno.getLogin());
    }

    //@Disabled
    @Test
    public void testGetLoginEmQueLoginEIgualASenhaInvalidaDeveLancarUmaExcecao()
    {
        aluno.login = "Senha Invalida!";

        assertThrows(RuntimeException.class, () -> aluno.getLogin());
    }

    //@Disabled
    @Test
    public void testGetLoginEmQueLoginNaoEIgualASenhaInvalidaNemAEmailInvalidaRetornaLogin()
    {
        aluno.login = "Username: l004@alunos.uevora.pt Password: asd";

        assertEquals("Username: l004@alunos.uevora.pt Password: asd", aluno.getLogin());
    }
}
