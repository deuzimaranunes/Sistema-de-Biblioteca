import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EmprestimoView {
    private Scanner scanner = new Scanner(System.in);
    private EmprestimoController controller;

    public EmprestimoView(EmprestimoController controller) {
        this.controller = controller;
    }

    public EmprestimoView() {
    }

    public void exibirMenu() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n==== Menu de Emprestimos ====");
            System.out.println("1. Cadastrar Emprestimo");
            System.out.println("2. Listar Emprestimos");
            System.out.println("3. Buscar Emprestimo por titulo");
            System.out.println("4. Devolver Livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarEmprestimo();
                    break;
                case 2:
                    controller.listarEmprestimos();
                    break;
                case 3:
                    buscarEmprestimoPorTitulo();
                    break;
                case 4:
                    devolverLivro();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void cadastrarEmprestimo() {
        System.out.println("\nCadastrar novo empréstimo:");

        System.out.print("Titulo do livro do empréstimo: ");
        String titulo = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Matricula do aluno: ");
        String matricula = scanner.nextLine();
        Aluno aluno = new Aluno();
        aluno.setMatricula(matricula);

        Livro livro = controller.buscarLivroPorTitulo(titulo);
        if (livro == null) {
            System.out.println("Livro não encontrado no sistema.");
            return;
        }

        Date dataEmprestimo = null;
        Date dataDevolucao = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);

            System.out.print("Data de Empréstimo (DD/MM/AAAA): ");
            java.util.Date parsedEmprestimo = dateFormat.parse(scanner.nextLine());
            dataEmprestimo = new Date(parsedEmprestimo.getTime());

            System.out.print("Data de Devolução (DD/MM/AAAA): ");
            java.util.Date parsedDevolucao = dateFormat.parse(scanner.nextLine());
            dataDevolucao = new Date(parsedDevolucao.getTime());

        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use DD/MM/AAAA.");
            return;
        }

        boolean sucesso = controller.criarEmprestimo(dataEmprestimo, dataDevolucao, aluno, livro);
        if (sucesso) {
            System.out.println("Empréstimo cadastrado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar empréstimo.");
        }
    }

    public void buscarEmprestimoPorTitulo() {
        System.out.print("Digite o titulo do livro do empréstimo: ");
        String titulo = scanner.nextLine();
        scanner.nextLine();

        Emprestimo emprestimo = controller.buscarEmprestimo(titulo);
        if (emprestimo != null) {
            mostrarEmprestimo(emprestimo);
        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }

    public void devolverLivro() {
        System.out.print("Digite o título do empréstimo para devolução: ");
        String titulo = scanner.nextLine();
        boolean sucesso = controller.devolver(titulo);
        
        if (sucesso) {
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Erro ao devolver livro. Verifique o título.");
        }
    }
    

    public void mostrarEmprestimo(Emprestimo emprestimo) {
        System.out.println("\nDetalhes do Empréstimo:");
        System.out.println("Aluno: " + emprestimo.getAluno().getNome());
        System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
        System.out.println("Data de Empréstimo: " + emprestimo.getDataEmprestimo());
        System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());
        System.out.println("------------------------------------------------------");
    }
}
