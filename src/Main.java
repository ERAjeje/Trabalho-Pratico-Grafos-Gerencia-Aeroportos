import javax.swing.*;

/**
 * Eu não fiz a separação em todas as classes em arquivos separados para não gerar um numero muito grande de
 * arquivos... Acabei optando por separar o grafo das outras classes e as telas que precisam de classes especificas para
 * funcionar, como no caso dos actionPerformed que deveriam ser colocados em arquivos separados para cada listner ou
 * a classe VooTableModel que foi criada dentro da classe da tela onde seria utilizada.
 *
 * Estou enviando um arquivo compilado para você executar caso a execução pelo IDE não de certo.
 * Tive problemas de compatibilidade entre a IDE que uso (intellij idea) e o netbeans e/ou eclipse.
 *
 */
public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                tela tela = new tela();
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
            }
        });
    }
}
