import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PresencaTest
{
    Presenca presenca;

    @BeforeEach
    public void setUp()
    {
        presenca = new Presenca();
    }

    @AfterEach
    public void down()
    {
        presenca = null;
    }

    @Test
    public void testContabilizaPresencaIncrementaAtributoPresencasObjetoAlunoEAdicionaObjetoAlunoAListaAlunosPresentes()
    {
        Aluno a = new Aluno();
        Aula al = new Aula();
        ArrayList<Aluno> teste = new ArrayList<>();
        teste.add(a);

        presenca.contabilizaPresenca(a, al);

        assertEquals(1, a.presencas);
        assertEquals(teste, al.listaAlunosPresentes);
    }

    @Test
    public void testContabilizaMeiaPresencaIncrementaAtributoPresencasObjetoAlunoEAdicionaObjetoAlunoAListaAlunosPresentes()
    {
        Aluno a = new Aluno();
        Aula al = new Aula();
        ArrayList<Aluno> teste = new ArrayList<>();
        teste.add(a);

        presenca.contabilizaMeiaPresenca(a, al);

        assertEquals(1, a.meiasPresencas);
        assertEquals(teste, al.listaAlunosPresentes);
    }
}
