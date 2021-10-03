package sd.tp;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Client
{
    public Client()
    {
        super();
    }

    public static void getCentros(SistemaVacinacao obj) throws RemoteException
    {
        ArrayList<String> centros = obj.consultaCentros();

        System.out.println("\n\n-------------------------");
        System.out.println("|      Centro |   Local |");

        if(centros.isEmpty())
            System.out.println("Não existem centros disponíveis");
        else
        {
            for (String centro : centros)
            {
                System.out.println("-------------------------");
                System.out.printf("| %19s |\n", centro);
            }

            System.out.println("-------------------------\n");
        }
    }

    public static void getCentroInscricoes(SistemaVacinacao obj) throws RemoteException
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\nIntroduza o centro de vacinação ou \"menu\" para voltar para o menu: ");
        String centro_insc = scanner.next();

        if(obj.verificaCentro(centro_insc))
        {
            System.out.print("\nIntroduza o seu nome: ");
            String nome = scanner.next();

            ArrayList<Integer> inscricoes = obj.consultaInscricoes(centro_insc, nome );

            System.out.printf("\nEm fila de espera no %s : %d\n\n", centro_insc, inscricoes.get(0));

            if(inscricoes.get(1) == 0)
                System.out.printf("%s não está inscrito(a) no %s\n\n", nome, centro_insc);
            else
            {
                System.out.printf("Posição de %s na fila de espera no %s : %d\n", nome, centro_insc, inscricoes.get(1));
            }
        }
        else if(centro_insc.equals("menu"))
            System.out.println();
        else
        {
            System.out.println("\n----------------------------------------");
            System.out.println("| ERRO - Centro de vacinação inválido. |");
            System.out.println("----------------------------------------");

            getCentroInscricoes(obj);
        }
    }

    public static void inscrever(SistemaVacinacao obj) throws RemoteException
    {
        int codigo;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.print("\nIntroduza o seu nome: ");
        String nome = scanner.next();

        System.out.print("Introduza o seu género: ");
        String genero = scanner.next();

        System.out.print("Introduza a sua idade: ");
        int idade = scanner.nextInt();

        System.out.print("Introduza centro de vacinação: ");
        String centro = scanner.next();

        while(!obj.verificaCentro(centro) && !centro.equals("menu"))
        {
                System.out.println("ERRO - Centro de vacinação inválido.");
                System.out.print("Introduza centro de vacinação ou \"menu\" para voltar para o menu: ");
                centro = scanner.next();
        }

        if(obj.verificaCentro(centro))
        {
            codigo = obj.inscricao(nome, genero, idade, centro);

            if(codigo == -1)
                System.err.println("Ocorreu um erro no processamento do código.");
            else
                System.out.println("Código de vacinação: " + codigo + "\n");
        }
    }

    public static void setVacinado(SistemaVacinacao obj) throws RemoteException
    {
        int codigo;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.print("\nIntroduza o nome: ");
        String nome = scanner.next();

        System.out.print("Introduza o código de vacinação: ");

        try
        {
            codigo = scanner.nextInt();
        }
        catch (Exception e)
        {
            System.out.println("Valor inserido não é válido!");
            setVacinado(obj);
            return;
        }

        if(obj.verificaInscrito(nome, codigo))
        {
            Date data;

            System.out.print("Introduza a data em que foi vacinado(a) (ex: ano-mes-dia): ");

            try
            {
                data = Date.valueOf(scanner.next());
            }
            catch (Exception e)
            {
                System.out.println("Data inserida não é válida. Formato de input: ano-mes-dia.");
                setVacinado(obj);
                return;
            }

            System.out.print("Introduza o tipo de vacina: ");
            String vacina = scanner.next();
            System.out.println();

            obj.removeDaFila(nome, codigo, data, vacina);
        }
        else
        {
            System.out.println("\n---------------------------------------------------------------------");
            System.out.println("| ERRO - Cidadão não está inscrito ou código inserido não é válido  |");
            System.out.println("---------------------------------------------------------------------");

            System.out.print("Se desejar voltar ao menu introduza \"menu\": ");
            String menu = scanner.next();

            if(!menu.equals("menu"))
                setVacinado(obj);
        }
    }

    public static void setEfeitos(SistemaVacinacao obj) throws RemoteException
    {
        int codigo;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.print("\nIntroduza o nome: ");
        String nome = scanner.next();

        System.out.print("Introduza o código de vacinação: ");

        try
        {
            codigo = scanner.nextInt();
        }
        catch (Exception e)
        {
            System.out.println("Valor inserido não é válido!");
            setVacinado(obj);
            return;
        }

        if(obj.verificaVacinado(nome, codigo))
        {
            obj.atualizaEfeitos(nome, codigo);
            System.out.println();
        }
        else
        {
            System.out.println("\n---------------------------------------------------------------------");
            System.out.println("| ERRO - Cidadão não está registado ou código inserido não é válido  |");
            System.out.println("---------------------------------------------------------------------");

            System.out.print("Se desejar voltar ao menu introduza \"menu\": ");
            String menu = scanner.next();

            if(!menu.equals("menu"))
                setEfeitos(obj);
        }
    }

    public static void getVacinados(SistemaVacinacao obj) throws RemoteException
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Introduza o tipo de vacina: ");
        String vacina = scanner.next();

        if(obj.verificaVacina(vacina))
        {
            ArrayList<Integer> vacinados = obj.consultaVacinados(vacina);

            System.out.printf("\nNúmero total de vacinados com a vacina %s: %d\n", vacina, vacinados.get(0));
            System.out.printf("Número de casos com efeitos secundários com a vacina %s: %d\n\n", vacina, vacinados.get(1));
        }
        else
        {
            System.out.println("\n---------------------------------------------------------------------");
            System.out.println("| ERRO - Vacina introduzida não existe                              |");
            System.out.println("---------------------------------------------------------------------");

            System.out.print("Se desejar voltar ao menu introduza \"menu\": ");
            String menu = scanner.next();

            if(!menu.equals("menu"))
                getVacinados(obj);
        }
    }

    public static void main(String[] args) throws IOException
    {
        LoadProperties lp = new LoadProperties();
        Properties appProps = lp.getProperties();

        String regHost = appProps.getProperty("regHostClient");
        String regPort = appProps.getProperty("regPortClient");

        try
        {
            SistemaVacinacao obj = (SistemaVacinacao) java.rmi.Naming.lookup("rmi://" + regHost +":"+regPort +"/SistemaVacinacao");

            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            int opcao = -1;

            while(true)
            {
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("| SISTEMA DE ACOMPANHAMENTO DE VACINAÇÃO - MENU                                      |");
                System.out.println("|                                                                                    |");
                System.out.println("| [1] Consulta de centros de vacinação                                               |");
                System.out.println("| [2] Consulta do comprimento da fila de espera num centro                           |");
                System.out.println("| [3] Inscrição para vacinação num centro de vacinação                               |");
                System.out.println("| [4] Registar a realização da vacinação                                             |");
                System.out.println("| [5] Reportar existência de efeitos secundários                                     |");
                System.out.println("| [6] Consulta do nº de cidadãos vacinados  e nº de cidadãos com efeitos secundários |");
                System.out.println("| [7] Sair                                                                           |");
                System.out.println("--------------------------------------------------------------------------------------");

                System.out.println();
                System.out.print("Introduza uma opcao: ");

                try
                {
                    opcao = scanner.nextInt();
                }
                catch (Exception e)
                {
                    System.out.println("Valor inserido não é um número...\n");
                    main(args);
                }

                switch (opcao)
                {
                    case 1 -> getCentros(obj);
                    case 2 -> getCentroInscricoes(obj);
                    case 3 -> inscrever(obj);
                    case 4 -> setVacinado(obj);
                    case 5 -> setEfeitos(obj);
                    case 6 -> getVacinados(obj);
                    case 7 -> {
                        System.out.println();
                        System.exit(0);
                    }
                    default -> System.out.println("ERRO - Introduza uma opção válida!");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}