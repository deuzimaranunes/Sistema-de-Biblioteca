import java.util.Scanner;

public class MenuView {
    private final AlunoView aluno;
    private final FuncionarioView funcionario;
    private final LivroView livro;
    private final EmprestimoView emprestimo;
    private final Scanner scanner = new Scanner(System.in);

    public MenuView(AlunoView aluno, FuncionarioView funcionario, LivroView livro, EmprestimoView emprestimo) {
        this.aluno = aluno;
        this.funcionario = funcionario;
        this.livro = livro;
        this.emprestimo = emprestimo;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n Menu da Biblioteca:");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("3. Gerenciar Livros");
            System.out.println("4. Gerenciar Empréstimos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    aluno.exibirMenu();
                    break;
                case 2:
                    funcionario.menuFuncionario();
                    break;
                case 3:
                    livro.exibirMenu();
                    break;
                case 4: 
                    emprestimo.exibirMenu();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }
}