import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

public class LivroController {
    private LivroDAO livroDao;

    public LivroController(Connection conn) {
        this.livroDao = new LivroDAO(conn);
    }

    public void cadastrarLivro(Livro livro) {
        try {
            livroDao.salvar(livro);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public void listarLivros() {
        try {
            List<Livro> livros = livroDao.buscarLivros();
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro cadastrado.");
            } else {
                for (Livro livro : livros) {
                    System.out.println(livro);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    public void listarLivrosForaDeEstoque() {
        try {
            List<Livro> livros = livroDao.buscarLivros();
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro cadastrado.");
            } else {
                System.out.println("\nLIVROS FORA DE ESTOQUE:");
                boolean encontrou = false;
                for (Livro l : livros) {
                    if (l.getQuantidadeDisponivel() == 0) {
                        System.out.println("TÍTULO: " + l.getTitulo());
                        encontrou = true;
                    }
                }
                if (!encontrou) {
                    System.out.println("Nenhum livro está fora de estoque.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    public void atualizarLivro(Livro livro) {
        try {
            livroDao.editar(livro);
            System.out.println("Livro atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void excluirLivro(String titulo) {
        try {
            livroDao.excluir(titulo);
            System.out.println("Livro excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
    }

    public Livro buscarLivro(String titulo) {
        try {
            return livroDao.buscar(titulo);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o livro: " + e.getMessage());
            return null;
        }
    }

    public Livro localizacaoDoLivro(String titulo) {
        try {
            return livroDao.localizacao(titulo);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar a localização do livro: " + e.getMessage());
            return null;
        }
    }
}
