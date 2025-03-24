import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

abstract class Pessoa {
    protected String nome;
    protected int idade;
    protected String cpf;
    protected String senha;
    protected String email;
    protected boolean autenticado = false;
    Scanner ent = new Scanner(System.in);

    
    public boolean fazerLogin(String email, String senha, String tabela) {
        try (Connection conexao = ConexaoDB.conectar()) {
            String sql = "SELECT * FROM " + tabela + " WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.autenticado = true;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void cadastrarPessoa() {
        System.out.print("NOME:");
        this.nome = ent.nextLine();
        System.out.print("IDADE:");
        this.idade = ent.nextInt();
        ent.nextLine();
        System.out.print("CPF:");
        this.cpf = ent.nextLine();
        System.out.print("EMAIL:");
        this.email = ent.nextLine();
        System.out.print("SENHA:");
        this.senha = ent.nextLine();
    }

    public void editarPessoa() {
        System.out.print("NOME: ");
        setNome(ent.nextLine());
        System.out.print("IDADE: ");
        setIdade(ent.nextInt());
        ent.nextLine();
        System.out.print("CPF: ");
        setCpf(ent.nextLine());
        System.out.print("EMAIL: ");
        setEmail(ent.nextLine());
        System.out.print("SENHA: ");
        setSenha(ent.nextLine());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Pessoa() {
    }

    public Pessoa(String nome, int idade, String cpf, String email, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }
}