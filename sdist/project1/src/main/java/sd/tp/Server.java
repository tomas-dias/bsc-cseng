package sd.tp;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class Server
{
    public static void main(String[] args) throws IOException
    {
        LoadProperties lp = new LoadProperties();
        Properties appProps = lp.getProperties();

        try
        {
            /* Port relativo ao binder */
            int regPort = Integer.parseInt(appProps.getProperty("regPortServer"));

            /* criar um Objeto Remoto */
            SistemaVacinacao obj = new SistemaVacinacaoImpl();

            java.rmi.registry.LocateRegistry.createRegistry(regPort);

            /* Retorna a referencia do objeto remoto registado no serviço de nomes */
            Registry registry = LocateRegistry.getRegistry(regPort);

            /* atribui o nome no binder do servico ao objeto remoto criado */
            registry.rebind("SistemaVacinacao", obj);

            System.out.println("Objecto Remoto registado com sucesso no binder!");

            System.out.println("Servidor pronto");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}