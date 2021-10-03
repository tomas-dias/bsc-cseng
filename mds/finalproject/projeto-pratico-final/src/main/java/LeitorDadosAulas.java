import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class LeitorDadosAulas
{
    public static ArrayList<Aula> aulas()
    {
        Gson gson = new Gson();
        Aula[] arr;
        ArrayList<Aula> aulas = new ArrayList<>();

        try(Reader reader = new FileReader("aulas.json")) {
            // Convert JSON File to Java Object
            arr = gson.fromJson(reader, Aula[].class);

        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }

        for(Aula obj : arr) {
            Aula aula = new Aula();
            aula.data = obj.data;
            aula.hora = obj.hora;
            aulas.add(aula);
        }

        return aulas;
    }
}
