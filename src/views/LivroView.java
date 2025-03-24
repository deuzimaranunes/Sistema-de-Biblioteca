import java.util.Scanner;

public class LivroView {
    private Scanner sc = new Scanner(System.in);
    private LivroController controller;

    public LivroView(LivroController controller) {
        this.controller = controller;
    }
    public LivroView() {}

    public void exibirMenu() {
        boolean executando;
        do {
            System.out.println("\n==== Menu Livro ====");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Editar Livro");
            System.out.println("4. Buscar Livro");
            System.out.println("5. Listar Livros");
            System.out.println("6. Listar Livros Fora de Estoque");
            System.out.println("7. Localização do Livro");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();
            executando = true;

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    removerLivro();
                    break;
                case 3:
                    editarLivro();
                    break;
                case 4:
                    buscarLivro();
                    break;
                case 5:
                    controller.listarLivros();
                    break;
                case 6:
                    controller.listarLivrosForaDeEstoque();
                    break;
                case 7:
                    localizacaoDoLivro();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (executando);
    }

    public void cadastrarLivro() {
        System.out.print("TÍTULO: ");
        String titulo = sc.nextLine();
        System.out.print("AUTOR: ");
        String autor = sc.nextLine();
        System.out.print("ANO DA EDIÇÃO: ");
        int ano = sc.nextInt();
        sc.nextLine();
        System.out.print("QUANTIDADE DISPONÍVEL: ");
        int quantidadeDisponivel = sc.nextInt();
        sc.nextLine();
        System.out.print("CORREDOR: ");
        String corredor = sc.nextLine();
        System.out.print("PRATELEIRA: ");
        String prateleira = sc.nextLine();

        Livro livro = new Livro(titulo, autor, ano, quantidadeDisponivel, corredor, prateleira);
        controller.cadastrarLivro(livro);
    }

    public void editarLivro() {
        System.out.println("Digite o título do livro a ser editado: ");
        String titulo = sc.nextLine();

        System.out.println("Autor: ");
        String autor = sc.nextLine();
        System.out.println("Ano da Edição: ");
        int ano = sc.nextInt();
        System.out.println("Quantidade Disponível: ");
        int quantidadeDisponivel = sc.nextInt();
        sc.nextLine();
        System.out.println("Corredor: ");
        String corredor = sc.nextLine();
        System.out.println("Prateleira: ");
        String prateleira = sc.nextLine();
        System.out.println("Livro editado com sucesso!");

        Livro livro = new Livro(titulo, autor, ano, quantidadeDisponivel, corredor, prateleira);
        controller.atualizarLivro(livro);
    }

    public void removerLivro() {
        System.out.println("Digite o título do livro a ser removido: ");
        String titulo = sc.nextLine();
        controller.excluirLivro(titulo);
    }

    public void buscarLivro() {
        System.out.println("Digite o título do livro para buscar: ");
        String titulo = sc.nextLine();
        Livro livro = controller.buscarLivro(titulo);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public String localizacaoDoLivro() {
        System.out.println("Digite o título do livro para localizar: ");
        String titulo = sc.nextLine();
        Livro livro = controller.localizacaoDoLivro(titulo);
        if (livro != null) {
            System.out.println(livro);
        }
        return ("Livro não encontrado!");
    }
}
