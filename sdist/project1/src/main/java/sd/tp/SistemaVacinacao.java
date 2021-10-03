package sd.tp;

import java.sql.Date;
import java.util.ArrayList;

public interface SistemaVacinacao extends java.rmi.Remote
{
    ArrayList<String> consultaCentros() throws java.rmi.RemoteException;
    ArrayList<Integer> consultaInscricoes(String centro, String nome) throws java.rmi.RemoteException;
    ArrayList<Integer> consultaVacinados(String vacina) throws java.rmi.RemoteException;
    boolean verificaCentro(String centro) throws java.rmi.RemoteException;
    boolean verificaInscrito(String nome, int codigo) throws java.rmi.RemoteException;
    boolean verificaVacina(String vacina) throws java.rmi.RemoteException;
    boolean verificaVacinado(String nome, int codigo) throws java.rmi.RemoteException;
    int inscricao(String nome, String genero, int idade, String centro) throws java.rmi.RemoteException;
    void removeDaFila(String nome, int codigo, Date data, String vacina) throws java.rmi.RemoteException;
    void atualizaEfeitos(String nome, int codigo) throws java.rmi.RemoteException;
}