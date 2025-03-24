public class Aluno extends Pessoa {
    private String matricula;
    private String curso;

    public Aluno(String nome, int idade, String cpf, String email, String senha, String matricula, String curso) {
        super(nome, idade, cpf, email, senha);
        this.matricula = matricula;
        this.curso = curso;
    }
    
    public Aluno() { }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    @Override
    public String toString() {
        return "\nAluno - Nome: " + nome + "\nIdade: " + idade + "\nCPF: " + cpf + 
               "\nEmail: " + email + "\nMatricula: " + matricula + "\nCurso: " + curso;
    }
}
