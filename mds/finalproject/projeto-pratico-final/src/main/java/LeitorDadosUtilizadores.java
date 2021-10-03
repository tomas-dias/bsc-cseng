import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class LeitorDadosUtilizadores
{
    public static Aluno[] dados()
    {
        Gson gson = new Gson();
        Aluno[] utilizadores;

        try(Reader reader = new FileReader("dados.json")) {
            // Convert JSON File to Java Object
            utilizadores = gson.fromJson(reader, Aluno[].class);

        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        return utilizadores;
    }

    public static ArrayList<Aluno> alunos(Aluno[] utilizadores)
    {
        ArrayList<Aluno> alunos = new ArrayList<>();

        if(utilizadores != null) {
            for(Utilizador utilizador : utilizadores) {
                if(utilizador.papel.equals("aluno")) {
                    Aluno aluno = new Aluno();
                    aluno.nome = utilizador.nome;
                    aluno.cartao = utilizador.cartao;
                    aluno.papel = utilizador.papel;
                    alunos.add(aluno);
                }
            }
        }
        return alunos;
    }

    public static ArrayList<Docente> docentes(Aluno[] utilizadores)
    {
        ArrayList<Docente> docentes = new ArrayList<>();

        if(utilizadores != null) {
            for(Utilizador utilizador : utilizadores) {
                if(utilizador.papel.equals("docente")) {
                    Docente docente = new Docente();
                    docente.nome = utilizador.nome;
                    docente.cartao = utilizador.cartao;
                    docente.papel = utilizador.papel;
                    docentes.add(docente);
                }
            }
        }
        return docentes;
    }
}
