public class Funcionario extends Pessoa {
    private boolean administrador;
   
    public Funcionario() {}
    
    public Funcionario(String nome, int idade, String cpf, String email, String senha, boolean administrador) {
        super(nome, idade, cpf, email, senha);
        this.administrador = administrador;
    }

    public boolean getAdministrador(){
        return administrador;
    }

    public void setAdministrador(boolean administrador){
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "\nFuncionario - Nome: " + nome + "\nIdade: " + idade + "\nCPF: " + cpf
                + "\nEmail: " + email + "\nAdministrador? " + administrador;
    } 
}
