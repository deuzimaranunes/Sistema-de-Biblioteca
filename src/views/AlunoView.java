import java.util.Scanner;

public class AlunoView extends Pessoa {
    private Scanner scanner = new Scanner(System.in);
    private AlunoController controller;

    public AlunoView(AlunoController controller) {
        this.controller = controller;
    }

    public AlunoView() {
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("\n--- Menu Alunos ---");
            System.out.println("1. Fazer login");
            System.out.println("2. Cadastrar aluno");
            System.out.println("3. Listar alunos");
            System.out.println("4. Atualizar aluno");
            System.out.println("5. Excluir aluno");
            System.out.println("6. Buscar aluno");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    cadastrarAluno();
                    break;
                case 3:
                    controller.listarAlunos();
                    break;
                case 4:
                    atualizarAluno();
                    break;
                case 5:
                    excluirAluno();
                    break;
                case 6:
                    buscarAluno();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarAluno() {
        super.cadastrarPessoa();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        Aluno aluno = new Aluno(nome, idade, cpf, email, senha, matricula, curso);
        controller.cadastrarAluno(aluno);
    }

    private void atualizarAluno() {
        super.editarPessoa();
        System.out.print("Nova matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Novo curso: ");
        String curso = scanner.nextLine();

        Aluno aluno = new Aluno(nome, idade, cpf, email, senha, matricula, curso);
        controller.atualizarAluno(aluno);
    }

    private void excluirAluno() {
        System.out.print("CPF do aluno a excluir: ");
        String cpf = scanner.nextLine();
        controller.excluirAluno(cpf);
    }

    private void buscarAluno() {
        System.out.print("CPF do aluno a buscar: ");
        String cpf = scanner.nextLine();
        Aluno aluno = controller.buscarAluno(cpf);
        if (aluno != null) {
            System.out.println(aluno);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private void fazerLogin() {
        Aluno aluno = new Aluno();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        if (aluno.fazerLogin(email, senha, "aluno")) {
            System.out.println("Login realizado com sucesso! ");
            EmprestimoView emprestimo = new EmprestimoView();
            emprestimo.exibirMenu();
        } else {
            System.out.println("Email ou senha invalidos. ");
            System.out.println("Digite 1 para tentar novamente ou digite 0 para voltar ao menu anterior.");
            int opcao = scanner.nextInt();
            if (opcao == 1) {
                fazerLogin();
            } else if (opcao == 0) {
                System.out.println("Voltando ao menu anterior...");
                exibirMenu();
            }

        }
    }
}
