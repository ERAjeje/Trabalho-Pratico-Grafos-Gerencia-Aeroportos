import br.com.edsonajeje.aeroportos.Aeroportos;
import br.com.edsonajeje.grafo.Grafo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Tela inicial que será chamada na entrada do programa
 */
public class tela extends JFrame implements ActionListener {
    private JButton btnInsereAeroporto;
    private JButton btnInsereVoo;
    private JPanel rootPanel;
    private JButton btnBusca;
    private JButton btnTotal;
    private JButton btnRemoveAeroporto;
    private JButton btnRemoveVoo;

    /**
     * Instancia única do objeto aeroportos que será passada como parametro entre as telas
     */
    Aeroportos aeroportos = new Aeroportos();

    /**
     * Construtor da tela
     */
    public tela() {

//         Caso queira começar com alguns aeroportos e voos preenchidos, basta descomentar essas linhas e
//         rodar o projeto novamente.

         aeroportos.insereAeroporto("Ribeirão Preto");
         aeroportos.insereAeroporto("São Paulo");
         aeroportos.insereAeroporto("Campinas");
         aeroportos.insereVoo("Ribeirão Preto", "São Paulo", 590);
         aeroportos.insereVoo("Ribeirão Preto", "Campinas", 550);
         aeroportos.insereVoo("Campinas", "Ribeirão Preto", 590);



        /**
         * Estas linhas de código adiciona um listener para "ouvir" os cliques nos botões
         */
        btnInsereAeroporto.addActionListener(this);
        btnInsereVoo.addActionListener(this);
        btnBusca.addActionListener(this);
        btnTotal.addActionListener(this);
        btnRemoveAeroporto.addActionListener(this);
        btnRemoveVoo.addActionListener(this);

        /**
         * Inicialização do painel principal e configurações de tamanho de tela, titulo da tela e função de fechar
         * no close da tela.
         */
        add(rootPanel);
        setTitle("Gerencia de Aeroportos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Ações que serão realizadas pelos botões
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * Botão para inserir um aeroporto. Ao clicar no botão será exibido um input para digitar o nome da cidade.
         * Para garantir que não seja inserido valores nulos existe uma verificação if.
         * Caso a inserção retorne false, significa que o aeroporto digitado já está cadastrado, então é exibido uma
         * mensagem de erro informando que não é possivel inserir um aeroporto existente.
         */
        if (e.getSource() == btnInsereAeroporto) {
            String aeroporto = JOptionPane.showInputDialog("Digite o nome do Aeroporto.");
            if (aeroporto != null) {
                if(!aeroportos.insereAeroporto(aeroporto)) {
                    JOptionPane.showMessageDialog(this, "Erro! Aeroporto já inserido anteriormente.");
                }
            }

            /**
             * Botão para inserir voo. Ao clicar, exibe um combobox com os aeroportos cadastrados para selecionar o
             * aeroporto de partida, depois acontece o mesmo para o de chegada e um input para inserir o valor do
             * voo. Acontece um tratamento para normalização de floats para evitar presença de virgulas.
             * É realizado um teste para garantir que todos os paramentros existem e é inserido o voo no grafo.
             */
        } else if (e.getSource() == btnInsereVoo) {
            String[] list = aeroportos.getAeroportos();
            JComboBox jComboBox = new JComboBox(list);
            int inputP = JOptionPane.showConfirmDialog(this, jComboBox, "Digite o aeroporto de partida.", JOptionPane.DEFAULT_OPTION);
            String partida = String.valueOf(jComboBox.getSelectedItem());
            int inputC = JOptionPane.showConfirmDialog(this, jComboBox, "Digite o aeroporto de chegada.", JOptionPane.DEFAULT_OPTION);
            String chegada = String.valueOf(jComboBox.getSelectedItem());
            String value = JOptionPane.showInputDialog(this,"Digite o valor da passagem.");
            if(inputP == 0 && inputC == 0 && value != null) {
                float price = Float.valueOf(value.replace(',','.'));
                aeroportos.insereVoo(partida, chegada, price);
            }

            /**
             * Este botão chama a tela onde os voos serão listados tanto para as partidas escolhidas quanto para as
             * chegadas escolhidas.
             *
             */
        } else if(e.getSource() == btnBusca) {
            Busca busca = new Busca(aeroportos);
            busca.setLocationRelativeTo(null);
            busca.setVisible(true);
            this.setVisible(false);
        } else if(e.getSource() == btnTotal) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String message = "O somatório de todos os custos dos voos é R$"+decimalFormat.format(aeroportos.calculaCustoTotal());
            JOptionPane.showMessageDialog(this, message);
        } else if(e.getSource() == btnRemoveAeroporto) {

            /**
             * Para remoção de aeroportos é impossível ocorrer erros como erros de digitação ou aeroporto inexistente
             * pois os aeroportos existentes são listados em um combobox e assim suprime a necessidade de apresentar
             * mensagem de erro.
             */
            String[] listaAeroportos = aeroportos.getAeroportos();
            JComboBox<String> cmbOrigem = new JComboBox<>(listaAeroportos);
            cmbOrigem.insertItemAt("Selecione o Aeroporto", 0);
            cmbOrigem.setSelectedIndex(0);
            int inputP = JOptionPane.showConfirmDialog(this, cmbOrigem, "Remover Aeroporto", JOptionPane.DEFAULT_OPTION);
            String partida = String.valueOf(cmbOrigem.getSelectedItem());
            if(inputP == 0) {
                aeroportos.removeAeroporto(partida);
            }
        } else if(e.getSource() == btnRemoveVoo) {
            /**
             * Como acontece o preenchimento dos combobox utilizando os aeroportos cadastrados, não será possível a
             * ocorrencia de um erro causado pela escoha de aeroportos não cadastrados ou combinação de aeroportos
             * que não existam.
             * O metodo getAeroportos() retorna um String[]. Para realizar a remoção do aeroporto de partida da lista,
             * pois não é possível um voo partindo e chegando no mesmo aeroporto (para este modelo implementado),
             * esta lista é transformada em um ArrayList<String>.
             */
            String[] list = aeroportos.getAeroportos();
            JComboBox cmbPartida = new JComboBox(list);
            cmbPartida.insertItemAt("Selecione o Aeroporto de Partida", 0);
            cmbPartida.setSelectedIndex(0);
            int inputP = JOptionPane.showConfirmDialog(this, cmbPartida, "Remover Voo", JOptionPane.DEFAULT_OPTION);
            String partida = String.valueOf(cmbPartida.getSelectedItem());

            String[] newList = aeroportos.getAeroportosDestinos(partida);
            JComboBox cmbChegada = new JComboBox(newList);
            cmbChegada.insertItemAt("Selecione o Aeroporto de Chegada", 0);
            cmbChegada.setSelectedIndex(0);
            int inputC = JOptionPane.showConfirmDialog(this, cmbChegada, "Remover Voo partindo de "+partida, JOptionPane.DEFAULT_OPTION);
            String chegada = String.valueOf(cmbChegada.getSelectedItem());
            if(inputP == 0 && inputC == 0) {
                aeroportos.removeVoo(partida, chegada);
            }
        }
    }
}
