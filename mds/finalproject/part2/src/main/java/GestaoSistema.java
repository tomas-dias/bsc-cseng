import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GestaoSistema
{
    static ArrayList<Aluno> alunos = importa_dados_alunos();
    static ArrayList<Aula> aulas = importa_dados_aulas();
    static ArrayList<Aluno> alunos_faltas_25 = new ArrayList<>();
    static ArrayList<Aluno> alunos_faltas_50 = new ArrayList<>();
    static ArrayList<Docente> docentes = LeitorDadosUtilizadores.docentes(LeitorDadosUtilizadores.dados());
    static String item;
    static int aux = 0;
    static Falta falta = new Falta();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        String cartao;
        int mudarUtilizador = 1;

        while(mudarUtilizador == 1) {

            System.out.print("Insira o número do cartão( Insira 0 se pretender sair da aplicação ): ");
            cartao = scanner.nextLine();

            if(cartao.equals("0")) {
                mudarUtilizador = 0;
            }
            else {
                for(Docente docente : docentes) {
                    if(cartao.equals(docente.cartao)) {
                        mudarUtilizador = menuDocente(docentes.get(0));
                    }
                    else {
                        for(Aluno aluno : alunos) {
                            if(cartao.equals(aluno.cartao)) {
                                mudarUtilizador = menuAluno(aluno);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static int menuDocente(Docente docente)
    {
        String alterar_estado;

        while(true) {
            /*
                Menu
            */
            System.out.println("-------------------------------------------------------");
            System.out.printf("|       Menu Do Docente  %20s         |\n", docente.nome);
            System.out.println("-------------------------------------------------------");
            System.out.println("| Insira 0    - Sair da aplicação                     |");
            System.out.println("| Insira 1    - Importar dados dos utilizadores       |");
            System.out.println("| Insira 2    - Alterar estado                        |");
            System.out.println("| Insira 3    - Mostrar Relatório de faltas           |");
            System.out.println("| Insira 4    - Consultar faltas por aluno            |");
            System.out.println("| Insira 5    - Consultar presenças e faltas por aula |");
            System.out.println("| Insira 6    - Mudar de Utilizador                   |");
            System.out.println("-------------------------------------------------------");
            /*
                Inserção do item que deseja ver
            */
            item = scanner.nextLine();
            /*
                caso item seja 1
            */
            switch (item) {
                case "1":

                    System.out.println("--------------------------------");
                    System.out.println("|         Utilizadores:        |");
                    System.out.println("--------------------------------\n");
                    /*
                        se a arrayList dos alunos não tiver vazia percorre essa mesma arraylist
                    */
                    if (alunos != null) {
                        for (Aluno aluno : alunos) {
                            System.out.printf("| Nome: %20s   |\n", aluno.nome);
                            System.out.printf("| Cartão: %4s                 |\n", aluno.cartao);
                            System.out.printf("| Email: %s |\n\n", aluno.getEmail());
                        }
                    }
                    /*
                        se a arrayList dos docentes não tiver vazia percorre essa mesma arraylist
                    */
                    if(docentes != null){
                        for (Docente d: docentes){
                            System.out.printf("| Nome: %20s   |\n", d.nome);
                            System.out.printf("| Cartão: %4s                 |\n", d.cartao);
                            System.out.printf("| Email: %s          |\n\n", d.getEmail());
                        }
                    }
                    break;
                /*
                    caso item seja 2
                */
                case "2": {
                    System.out.println("Introduza um cartão de aluno: ");
                    String cartao = scanner.nextLine();
                    for (Aluno aluno : alunos) {
                        if (cartao.equals(aluno.cartao)) {
                            System.out.print("Introduza a data da aula da falta que deseja alterar o estado: ");
                            alterar_estado = scanner.nextLine();
                            for (Aula aula : aulas) {
                                if (aula.data.equals(alterar_estado)) {
                                    falta.atualizaFalta(aluno, aula, docente);
                                    System.out.println("Estado de falta alterado com sucesso");
                                    aux++;
                                    break;
                                }
                            }
                            if (aux == 0) {
                                System.out.println("O aluno não tem falta na aula selecionada");
                            } else {
                                aux = 0;
                                break;
                            }
                        }
                    }
                    break;
                }
                /*
                    caso item seja 3
                */
                case "3":
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("|                                                                    RELATORIO PRESENCAS E FALTAS                                                                          |");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for (Aluno aluno : alunos) {
                        /*
                            Função que calcula a percentagem do aluno
                         */
                        calcula_percentagem(aluno);

                        if (aluno.percentagem >= 25.0 && aluno.percentagem <= 50.0) {
                            alunos_faltas_25.add(aluno);
                        } else if (aluno.percentagem > 50) {
                            alunos_faltas_50.add(aluno);
                        }

                        if (aluno.percentagem >= 10 && (100 - aluno.percentagem) >= 10) {
                            System.out.printf("| Nome: %20s | Cartao: %s | Num_Presencas: %d | Num_Meia_Presencas: %d | Número de Faltas: %d | Percentagem_Presencas: %.1f%% | Percentagem de Faltas: %.1f%%  |\n", aluno.nome, aluno.cartao, aluno.presencas, aluno.meiasPresencas, aluno.faltas, 100 - aluno.percentagem, aluno.percentagem);
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        } else if (aluno.percentagem == 100) {
                            System.out.printf("| Nome: %20s | Cartao: %s | Num_Presencas: %d | Num_Meia_Presencas: %d | Número de Faltas: %d | Percentagem_Presencas: %.1f%%  | Percentagem de Faltas: %.1f%% |\n", aluno.nome, aluno.cartao, aluno.presencas, aluno.meiasPresencas, aluno.faltas, 100 - aluno.percentagem, aluno.percentagem);
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        } else {
                            System.out.printf("| Nome: %20s | Cartao: %s | Num_Presencas: %d | Num_Meia_Presencas: %d | Número de Faltas: %d | Percentagem_Presencas: %.1f%%| Percentagem de Faltas: %.1f%%   |\n", aluno.nome, aluno.cartao, aluno.presencas, aluno.meiasPresencas, aluno.faltas, 100 - aluno.percentagem, aluno.percentagem);
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        }

                    }
                    /*
                        Apresentar a lista de alunos com entre 25% e 50% de faltas
                    */
                    System.out.println("\n-------------------------------------------------");
                    System.out.println("| Lista de alunos com entre 25% e 50% de faltas |");
                    System.out.println("-------------------------------------------------");

                    for (Aluno aluno : alunos_faltas_25) {
                        System.out.printf("|          %20s                 |\n", aluno.nome);
                    }

                    System.out.println("-------------------------------------------------");
                    /*
                        Apresentar a lista de alunos com mais de 50% de faltas
                    */
                    System.out.println("\n---------------------------------------------");
                    System.out.println("| Lista de alunos com mais de 50% de faltas |");
                    System.out.println("---------------------------------------------");

                    for (Aluno aluno : alunos_faltas_50) {
                        System.out.printf("|      %20s                 |\n", aluno.nome);
                    }

                    System.out.println("---------------------------------------------\n");
                    break;
                /*
                    caso item seja 4
                */
                case "4": {
                    System.out.println("Introduza um cartão de aluno: ");
                    String cartao = scanner.nextLine();
                    for (Aluno aluno : alunos) {
                        if (cartao.equals(aluno.cartao)) {
                            System.out.println("FALTAS - " + aluno.nome);
                            for (Falta falta : aluno.listaFaltas) {
                                System.out.print("Data em que faltou: " + falta.data);
                                System.out.print(" Hora em que faltou: " + falta.hora);
                                System.out.print(" Estado da falta: " + falta.estado);
                                if(falta.justificacao != null) {
                                    System.out.println(" justificação: " + falta.justificacao);
                                }else{
                                    System.out.println(" justificação: ");
                                }
                                aux ++;
                            }
                            if (aux == 0) {
                                System.out.println("O aluno não tem faltas.");
                            }
                            System.out.print("\n");
                            break;
                        }
                    }
                    aux = 0;
                    break;
                }
                /*
                    caso item seja 5
                */
                case "5": {
                    System.out.print("Introduza a data da aula: ");
                    String aul = scanner.nextLine();
                    for (Aula aula : aulas) {
                        if (aula.data.equals(aul) && (aula.listaAlunosFaltaram.size() != 0 || aula.listaAlunosPresentes.size() != 0)) {
                            if(aula.listaAlunosPresentes.size() != 0) {
                                System.out.println("------------------------------");
                                System.out.println("| Lista de alunos presentes: |");
                                System.out.println("------------------------------");
                                for (Aluno obj : aula.listaAlunosPresentes) {
                                    System.out.println(obj.nome);
                                }
                            }else{
                                System.out.print("\n\nOs alunos faltaram todos \n\n");
                            }
                            if(aula.listaAlunosFaltaram.size() != 0) {
                                System.out.println("-------------------------------");
                                System.out.println("|Lista de alunos que faltaram:|");
                                System.out.println("-------------------------------");
                                for (Aluno obj : aula.listaAlunosFaltaram) {
                                    System.out.println(obj.nome);
                                }
                            }else{
                                System.out.print("\n\nOs alunos tiveram todos presentes \n\n");
                            }
                            aux = 1;
                        }
                    }
                    if(aux == 0){
                        System.out.println("A aula de " + aul + " não se realizou\n");
                    }else{
                        System.out.println();
                    }
                    aux = 0;
                    break;
                }
                /*
                    caso item seja 6 dá return 1
                */
                case "6":
                    return 1;
                /*
                    caso item seja 0 dá return 0
                */
                case "0":
                    return  0;
                /*
                    Caso nenhum destes dá print Opção Inválida
                */
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static int menuAluno(Aluno aluno)
    {
        String daf, haf, justificacao; // variaveis que guardam os inputs da data e hora da aula e justificação

        while(true) {
            /*
                Menu
            */
            System.out.println("-------------------------------------------------");
            System.out.printf("|       Menu Do Aluno  %20s     |\n", aluno.nome);
            System.out.println("-------------------------------------------------");
            System.out.println("| Insira 0    - Sair da aplicação               |");
            System.out.println("| Insira 1    - Justificar Falta                |");
            System.out.println("| Insira 2    - Mostrar Relatório de faltas     |");
            System.out.println("| Insira 3    - Consultar faltas do aluno       |");
            System.out.println("| Insira 4    - Mudar de utilizador             |");
            System.out.println("-------------------------------------------------");
            /*
                Inserção do item que deseja ver
            */
            item = scanner.nextLine();
            /*
                caso item seja 1
            */
            switch (item) {
                case "1":
                    System.out.print("Insira a data da aula que pretende justificar a falta: ");
                    daf = scanner.nextLine();
                    System.out.print("Insira a hora da aula que pretende justificar a falta: ");
                    haf = scanner.nextLine();
                    for (Falta falta : aluno.listaFaltas) {
                        if (daf.equals(falta.data) && haf.equals(falta.hora)) {
                            System.out.println("Insira a justificacao: ");
                            justificacao = scanner.nextLine();
                            for (Aula aula : aulas) {
                                if(aula.data.equals(falta.data) && aula.hora.equals(falta.hora)) {
                                    falta.justificaFalta(aluno, aula, justificacao);
                                    System.out.println("Justificação enviada com sucesso");
                                    aux = 1;
                                    break;
                                }
                            }
                            if(aux == 1){
                                break;
                            }
                        }
                    }
                    if (aux == 0) {
                        System.out.println("Não tem falta na aula selecionada.");
                    }
                    aux = 0;
                    break;
                /*
                    caso item seja 2
                */
                case "2":
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("|                                                                    RELATORIO PRESENCAS E FALTAS                                                                          |");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    calcula_percentagem(aluno);

                    if (aluno.percentagem >= 10 && (100 - aluno.percentagem) >= 10) {
                        System.out.printf("| Nome: %20s | Cartao: %s | Num_Presencas: %d | Num_Meia_Presencas: %d | Número de Faltas: %d | Percentagem_Presencas: %.1f%% | Percentagem de Faltas: %.1f%%  |\n", aluno.nome, aluno.cartao, aluno.presencas, aluno.meiasPresencas, aluno.faltas, 100 - aluno.percentagem, aluno.percentagem);
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    } else if (aluno.percentagem == 100) {
                        System.out.printf("| Nome: %20s | Cartao: %s | Num_Presencas: %d | Num_Meia_Presencas: %d | Número de Faltas: %d | Percentagem_Presencas: %.1f%%  | Percentagem de Faltas: %.1f%% |\n", aluno.nome, aluno.cartao, aluno.presencas, aluno.meiasPresencas, aluno.faltas, 100 - aluno.percentagem, aluno.percentagem);
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    } else {
                        System.out.printf("| Nome: %20s | Cartao: %s | Num_Presencas: %d | Num_Meia_Presencas: %d | Número de Faltas: %d | Percentagem_Presencas: %.1f%%| Percentagem de Faltas: %.1f%%   |\n", aluno.nome, aluno.cartao, aluno.presencas, aluno.meiasPresencas, aluno.faltas, 100 - aluno.percentagem, aluno.percentagem);
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }
                    break;
                /*
                    caso item seja 3
                */
                case "3":
                    System.out.println("FALTAS - " + aluno.nome);
                    for (Falta falta : aluno.listaFaltas) {
                        System.out.print("Data em que faltou: " + falta.data);
                        System.out.print(" Hora em que faltou: " + falta.hora);
                        System.out.print(" Estado da falta: " + falta.estado);
                        if(falta.justificacao != null) {
                            System.out.println(" justificação: " + falta.justificacao);
                        }else{
                            System.out.println(" justificação: ");
                        }
                        aux++;
                    }
                    if (aux == 0) {
                        System.out.println("O aluno não tem faltas.\n");
                    } else {
                        System.out.println();
                        aux = 0;
                    }
                    break;
                /*
                    caso item seja 4 dá return 1
                 */
                case "4":
                    return  1;
                /*
                    caso item seja 0 dá return 0
                 */
                case "0":
                    return  0;
                /*
                    Caso nenhum destes dá print Opção Inválida
                 */
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static ArrayList<Aluno> importa_dados_alunos()
    {
        try {

            FileInputStream fi1 = new FileInputStream(new File("dados.txt"));
            ObjectInputStream oi1 = new ObjectInputStream(fi1);

            return (ArrayList<Aluno>) oi1.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ArrayList<Aula> importa_dados_aulas()
    {
        try {

            FileInputStream fi2 = new FileInputStream(new File("aulas.txt"));
            ObjectInputStream oi2 = new ObjectInputStream(fi2);

            return (ArrayList<Aula>) oi2.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void calcula_percentagem(Aluno aluno)
    {
        double count = 0.0;
        /*
            correr a arrayList aulas em que cada linha corresponde a um objeto (aula) da classe Aula
            de forma a obter o número de aulas lecionadas
        */
        for(Aula aula : aulas) {
            /*
                Se a lista de alunos presentes na aula não estiver vazia
                ou a lista de alunos que faltaram não estiver vazia
                vai iterando a variável count
                para ajudar a obter a percentagem de faltas
            */
            if(aula.listaAlunosPresentes.size() != 0 || aula.listaAlunosFaltaram.size() != 0) {
                count++;
            }
        }
        /*
            Calculo da percentagem de faltas do aluno
        */
        aluno.percentagem = (aluno.faltas / count) * 100;
    }
}
