package sd.tp;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SistemaVacinacaoImpl extends UnicastRemoteObject implements SistemaVacinacao, java.io.Serializable
{
    LoadProperties lp = new LoadProperties();
    Properties appProps = lp.getProperties();

    PostgresConnector pc = new PostgresConnector(appProps.getProperty("pgHost"), appProps.getProperty("pgDB"), appProps.getProperty("pgUser"), appProps.getProperty("pgPwd"));

    public SistemaVacinacaoImpl() throws IOException
    {
        super();
    }

    public ArrayList<String> consultaCentros() throws java.rmi.RemoteException
    {
        ArrayList<String> centros = new ArrayList<>();

        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            ResultSet rs = stmt.executeQuery("select * from centro;");

            while(rs.next())
            {
                String nome = rs.getString("nomecentro");
                String localizacao = rs.getString("localizacao");
                String centro = String.format("%11s | %7s", nome, localizacao);
                centros.add(centro);
            }

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();

        return centros;
    }

    public ArrayList<Integer> consultaInscricoes(String centro, String nome) throws java.rmi.RemoteException
    {
        ArrayList<Integer> inscricoes = new ArrayList<>();
        int count = 0;
        int inscrito = 0;

        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            String query = "select count(*) as filaEspera from inscricao where nomecentro = '" + centro + "' ;";
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next())
            {
                inscricoes.add(rs.getInt("filaEspera"));
            }

            String query2 = "select * from inscricao where nomecentro = '" + centro + "';";
            rs = stmt.executeQuery(query2);

            while(rs.next())
            {
                count++;
                if(nome.equals(rs.getString("nome")))
                {
                    inscrito = 1;
                    inscricoes.add(count);
                    break;
                }
            }

            if(inscrito == 0)
            {
                inscricoes.add(0);
            }

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();

        return inscricoes;
    }

    public ArrayList<Integer> consultaVacinados(String vacina) throws java.rmi.RemoteException
    {
        ArrayList<Integer> vacinados = new ArrayList<>();

        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            int count;

            Statement stmt = pc.getStatement();
            String query = "select count(*) as totalvacinados from vacinados where vacina = '" + vacina + "' ;";
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next())
            {
                count = rs.getInt("totalvacinados");
                vacinados.add(count);
            }

            String query2 = "select count(*) as totalefeitos from vacinados where vacina = '" + vacina + "' and efeitos = TRUE;";
            rs = stmt.executeQuery(query2);

            if(rs.next())
            {
                count = rs.getInt("totalefeitos");
                vacinados.add(count);
            }

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();

        return vacinados;
    }

    public boolean verificaCentro(String centro) throws java.rmi.RemoteException
    {
        boolean bool = false;

        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            ResultSet rs = stmt.executeQuery("select * from centro;");

            while(rs.next())
            {
                if(rs.getString("nomecentro").equals(centro))
                {
                    bool = true;
                    break;
                }
            }

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();

        return bool;
    }

    public boolean verificaInscrito(String nome, int codigo) throws  java.rmi.RemoteException
    {
        boolean bool = false;

        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            ResultSet rs = stmt.executeQuery("select * from inscricao;");

            while(rs.next())
            {
                if(rs.getString("nome").equals(nome) && codigo == rs.getInt("id"))
                {
                    bool = true;
                    break;
                }
            }

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();

        return bool;
    }

    public boolean verificaVacina(String vacina) throws java.rmi.RemoteException
    {
        boolean bool = false;

        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            ResultSet rs = stmt.executeQuery("select * from vacinas;");

            while(rs.next())
            {
                if(rs.getString("nomevacina").equals(vacina))
                {
                    bool = true;
                    break;
                }
            }

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();

        return bool;
    }

    public boolean verificaVacinado(String nome, int codigo) throws java.rmi.RemoteException
    {
        boolean bool = false;

        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            ResultSet rs = stmt.executeQuery("select * from vacinados;");

            while(rs.next())
            {
                if(rs.getString("nome").equals(nome) && codigo == rs.getInt("id"))
                {
                    bool = true;
                    break;
                }
            }

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();

        return bool;
    }

    public int inscricao(String nome, String genero, int idade, String centro)
    {
        int id = -1;

        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            String update = "insert into inscricao(nome, genero, idade, nomecentro) values ('" + nome + "' , '" + genero + "' , " + idade + " , '" + centro + "');";
            stmt.executeUpdate(update);

            ResultSet rs = stmt.executeQuery("select id from inscricao where nome = '" + nome + "';");

            while(rs.next())
            {
                id = rs.getInt("id");
            }

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();

        return id;
    }

    public void removeDaFila(String nome, int codigo, Date data, String vacina) throws java.rmi.RemoteException
    {
        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            ResultSet rs = stmt.executeQuery("select * from inscricao;");

            while(rs.next())
            {
                if(rs.getString("nome").equals(nome) && codigo == rs.getInt("id"))
                {
                    String genero = rs.getString("genero");
                    int idade = rs.getInt("idade");
                    String nomecentro = rs.getString("nomecentro");

                    String insert = "insert into vacinados values (" + codigo + ", '" + nome + "', '" + genero + "', " + idade + ", '" + data + "', '" + vacina + "', FALSE, '" + nomecentro + "');";
                    stmt.executeUpdate(insert);

                    break;
                }
            }

            String delete = "delete from inscricao where nome = '" + nome + "' and id = '" + codigo + "';";
            stmt.executeUpdate(delete);

            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();
    }

    public void atualizaEfeitos(String nome, int codigo) throws java.rmi.RemoteException
    {
        try
        {
            pc.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Statement stmt = pc.getStatement();
            String sql = "update vacinados set efeitos = TRUE where nome = '" + nome + "' and id = " + codigo + ";";

            stmt.executeUpdate(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        pc.disconnect();
    }
}