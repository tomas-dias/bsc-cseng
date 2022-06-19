import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LeitorCartoes implements Serializable
{
    public static void main(String[] args)
    {
        ArrayList<Aluno> alunos = LeitorDadosUtilizadores.alunos(LeitorDadosUtilizadores.dados()); //ArrayList que contém em cada posição do array um objeto aluno
        ArrayList<Docente> docentes = LeitorDadosUtilizadores.docentes(LeitorDadosUtilizadores.dados()); //ArrayList que contém em cada posição do array um objeto docente
        ArrayList<Aula> aulas = LeitorDadosAulas.aulas(); //ArrayList que contém em cada posição do array um objeto aula

        Presenca presenca = new Presenca(); //Objeto da classe Presenca
        Falta falta = new Falta(); //Objeto da classe Falta
        int aux = 0, profPassouCartao = 0;
        /*
            aux = Variável utilizada para verificar se o aluno está na lista de alunos presentes à aula,
            se for diferente de zero o aluno já está na lista de alunos presentes à aula;

            profPassouCartao = Variável que como o próprio nome indica
                               serve para verificar se o docente passou ou não o cartão.
         */

        if(aulas != null) {
            /*
                ciclo para percorrer todas as aula que existem
            */
            for(Aula aula : aulas) {
                long inicio = System.currentTimeMillis(); //Variável que contém o tempo em milisegundos do momento em que se inicia o ciclo das aulas
                long fim = inicio + 60000; //Variável que contém o tempo em milisegundos em que a aula termina

                System.out.println("------------------------------");
                System.out.println("|  Aula - " + aula.data + "         |");
                System.out.println("------------------------------");
                /*
                    ciclo para percorrer o tempo de aula
                 */
                while(System.currentTimeMillis() < fim) {

                    if((System.currentTimeMillis() - inicio) / 1000 < 10) {
                        System.out.print("" + (System.currentTimeMillis() - inicio) / 1000 + "  Insira o cartao: ");
                    }
                    else {
                        System.out.print("" + (System.currentTimeMillis() - inicio) / 1000 + " Insira o cartao: ");
                    }

                    Scanner scanner = new Scanner(System.in);
                    String cartao = scanner.nextLine();
                    /*
                        ciclo para verificar se o docente passou o cartão antes de ocorridos 25 segundos de aula
                     */
                    for(Docente docente: docentes) {
                        if(cartao.equals(docente.cartao) && (int) ((System.currentTimeMillis() - inicio)/1000) < 25) {
                            profPassouCartao= 1;
                            break;
                        }
                    }

                    if(profPassouCartao == 0 && (int) ((System.currentTimeMillis() - inicio)/1000) >= 25) {
                        System.out.println("A aula de " + aula.data + " nao se realizou.");
                        break;
                    }
                    else if(profPassouCartao == 1) {
                        /*
                            ciclo para percorrer todos os alunos inscritos
                         */
                        for(Aluno aluno : alunos) {
                            if((int) ((System.currentTimeMillis() - inicio) / 1000) < 30) {
                                if(cartao.equals(aluno.cartao)) {
                                    /*
                                        ciclo para percorrer lista de alunos presentes
                                     */
                                    for(Aluno obj : aula.listaAlunosPresentes) {
                                        if(obj.cartao.equals(cartao)) {
                                            aux++;
                                        }
                                    }
                                    if(aux == 0) {
                                        presenca.contabilizaPresenca(aluno, aula); // é adicionado à lista de presentes na aula
                                        System.out.print("" + aluno.nome + " tem presenca," + " tempo de entrada: ");
                                        System.out.println((int) ((System.currentTimeMillis() - inicio) / 1000) + "s");
                                    }
                                    else {
                                        System.out.println("O aluno ja passou o cartao");
                                        aux = 0;
                                    }
                                }
                            }
                            else if((int) ((System.currentTimeMillis() - inicio) / 1000) < 60) {
                                if(cartao.equals(aluno.cartao)) {
                                    for(Aluno obj : aula.listaAlunosPresentes) {
                                        if(obj.cartao.equals(cartao)) {
                                            aux++;
                                        }
                                    }
                                    if(aux == 0) {
                                        presenca.contabilizaMeiaPresenca(aluno, aula); // é adicionado à lista de presentes na aula
                                        System.out.println("" + aluno.nome + " tem meia-presenca");
                                        System.out.println((int) ((System.currentTimeMillis() - inicio) / 1000));
                                    }
                                    else {
                                        System.out.println("O aluno ja passou o cartao");
                                        aux = 0;
                                    }
                                }
                            }
                            else {
                                if(cartao.equals(aluno.cartao)) {
                                    for(Aluno obj : aula.listaAlunosPresentes) {
                                        if(obj.cartao.equals(cartao)) {
                                            aux++;
                                        }
                                    }
                                    if(aux == 0) {
                                        falta.contabilizaFalta(aluno, aula); // é adicionado à lista de alunos que faltaram à aula
                                        System.out.println("" + aluno.nome + " tem falta");
                                        System.out.println((int) ((System.currentTimeMillis() - inicio) / 1000));
                                    }
                                    else {
                                        System.out.println("O aluno ja passou o cartao");
                                        aux = 0;
                                    }
                                }
                            }
                        }
                    }
                }
                if(profPassouCartao == 1) {
                    for(Aluno aluno : alunos) {
                        aux = 0;
                        for(Aluno obj : aula.listaAlunosPresentes) {
                            if(aluno == obj) {
                                aux++;
                                break;
                            }
                        }
                        if(aula.listaAlunosFaltaram.size() == 0 && aux == 0){
                            falta.contabilizaFalta(aluno, aula); // é adicionado à lista de alunos que faltaram à aula
                        }
                        else if(aux == 0 && aluno != aula.listaAlunosFaltaram.get(0)) {
                            falta.contabilizaFalta(aluno, aula); // é adicionado à lista de alunos que faltaram à aula
                        }
                    }
                    aux = 0;
                    profPassouCartao = 0;
                }
            }
        }
        try {
            FileOutputStream f1 = new FileOutputStream(new File("dados.txt"));
            FileOutputStream f2 = new FileOutputStream(new File("aulas.txt"));
            ObjectOutputStream o1 = new ObjectOutputStream(f1);
            ObjectOutputStream o2 = new ObjectOutputStream(f2);

            o1.writeObject(alunos); // é escrito no ficheiro dados.txt o array de objetos alunos atualizado
            o2.writeObject(aulas);  // é escrito no ficheiro aulas.txt o array de objetos aulas atualizado

            o1.close();
            f2.close();
            o2.close();
            f2.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}