import java.util.Scanner;

public class FuncionarioView extends Pessoa {
    private Scanner scanner = new Scanner(System.in);
    private FuncionarioController controller;

    public FuncionarioView(FuncionarioController controller) {
        this.controller = controller;
    }

    public FuncionarioView() {
    }

    public void menuFuncionario() {
        boolean executando;
        do {
            System.out.println("\n==== Menu Funcionário ====");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Editar Funcionário");
            System.out.println("3. Buscar Funcionário");
            System.out.println("4. Listar Funcionários");
            System.out.println("5. Remover Funcionário");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            executando = true;

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    editarFuncionario();
                    break;
                case 3:
                    buscarFuncionario();
                    break;
                case 4:
                    controller.listarFuncionarios();
                    break;
                case 5:
                    removerFuncionario();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (executando);
    }

    private void cadastrarFuncionario() {
        super.cadastrarPessoa();
        System.out.println("E administrador? ");
        boolean administrador = scanner.nextBoolean();

        Funcionario funcionario = new Funcionario(nome, idade, cpf, email, senha, administrador);
        controller.cadastrarFuncionario(funcionario);
    }

    private void editarFuncionario() {
        super.editarPessoa();
        System.out.println("E administrador? ");
        boolean administrador = scanner.nextBoolean();

        Funcionario funcionario = new Funcionario(nome, idade, cpf, email, senha, administrador);
        controller.atualizarFuncionario(funcionario);
    }

    private void removerFuncionario() {
        System.out.println("CPF do funcionario a excluir: ");
        String cpf = scanner.nextLine();
        controller.excluirFuncionario(cpf);
    }

    private void buscarFuncionario() {
        System.out.println("CPF do funcionario a buscar: ");
        String cpf = scanner.nextLine();

        Funcionario funcionario = controller.buscarFuncionario(cpf);
        if (funcionario != null) {
            System.out.println(funcionario);
        } else {
            System.out.println("Funcionario não encontrado.");
        }
    }
}
