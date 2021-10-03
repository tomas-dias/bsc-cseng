import java.io.Serializable;

public abstract class Utilizador implements Serializable
{
    String nome, cartao, email, senha, login, papel;

    public String getNome()
    {
        return nome;
    }

    public String getCartao()
    {
        return cartao;
    }

    public String getEmail()
    {
        if(papel.equals("docente")) {
            int espaco = nome.indexOf(" ");
            String i_nome1 = nome.substring(0, 1);
            String i_nome2 = nome.substring(espaco+1, espaco+2);
            email = i_nome1 + i_nome2 + "@uevora.pt";
            return email;
        }
        else if(papel.equals("aluno")) {
            email = "l" + cartao + "@alunos.uevora.pt";
            return email;
        }
        else {
            return null;
        }
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getLogin()
    {
        if(login.equals("Email Invalido!") || login.equals("Senha Invalida!")) {
            throw new RuntimeException("Login Invalido!");
        }
        else {
            return login;
        }
    }

    public void setLogin(String email, String senha)
    {
        int index = email.indexOf('@');
        String uevora = email.substring(index);
        if(!"@uevora.pt".equals(uevora) && !"@alunos.uevora.pt".equals(uevora)) {
            login =  "Email Invalido!";
        }
        else if(!senha.equals(this.senha) || !email.equals(this.email)) {
            login =  "Senha Invalida!";
        }
        else {
            login = "Username: " + email + " Password: " + senha;
        }
    }
}
