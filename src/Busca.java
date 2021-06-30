import br.com.edsonajeje.aeroportos.Aeroportos;
import br.com.edsonajeje.aeroportos.Voo;
import br.com.edsonajeje.aeroportos.VooTableModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Esta tela exibitá os voos cadastrados selecionados por partida ou por chegada. Ela possui uma variavel da classe
 * Aeroporto que será recebida por parametro na criação da tela.
 *
 */
public class Busca extends JFrame implements ActionListener {
    private JComboBox cmbOrigem;
    private JComboBox cmbDestino;
    private JSplitPane rootPanel;
    private JTable table1;
    private Aeroportos aeroportos;

    /**
     * Construtor recebendo aeroporto como paramentro e configurando a tela, titulo e função de close
     *
     * @param aeroportos
     */
    public Busca(Aeroportos aeroportos) {
        this.aeroportos = aeroportos;
        setOrigens();
        setDestinos();
        add(rootPanel);
        setTitle("Busca de Voos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * setDestinos e setOrigens são usados para popular os combobox com os aeroportos cadastrados no sistema.
     * Utiliza a função getAeroportos do objeto aeroportos para receber uma lista de strings.
     *
     */
    private void setDestinos() {
        cmbDestino.removeAllItems();
        String[] listaAeroportos = aeroportos.getAeroportos();
        cmbDestino.addItem("Aeroportos de Destino");
        for (String item :
                listaAeroportos) {
            cmbDestino.addItem(item);
        }
        cmbDestino.addActionListener(this);
    }

    private void setOrigens() {
        cmbOrigem.removeAllItems();
        String[] listaAeroportos = aeroportos.getAeroportos();
        cmbOrigem.addItem("Aeroportos de Origem");
        for (String item :
                listaAeroportos) {
            cmbOrigem.addItem(item);
        }
        cmbOrigem.addActionListener(this);
    }

    /**
     * Configuração das ações disparadas ao selecionar um valor nos combobox
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * Quando seleciona um valor em um dos combobox ele primeiro retira o listner do outro combobox e limpa caso
         * haja alguma seleção anterior. Em seguida utiliza o valor selecionado para buscar uma lista dos voos.
         * Caso seja selecionado um valor no combobox de aeroporto de partida será chamada a função do objeto
         * aeroportos listaAeroportosDestino listando os voos com partida do aeroporto selecionado. Esse array é
         * utilizado para criar um objeto VooTableModel que será utilizado para popular a tabela com os voos retornados.
         *
         * Caso seja selecionado um valor no combobox de aeroporto de destino ocorrerá o mesmo, porém listando os voos
         * que tem destino o aeroporto selecionado e a tabela será populada com esses valores.
         */
        if(e.getSource() == cmbOrigem ) {
            cmbDestino.removeActionListener(this);
            setDestinos();
            String partida = String.valueOf(cmbOrigem.getSelectedItem());
            if(partida != "Aeroportos de Origem") {
                Voo[] voos = aeroportos.listaAeroportosDestino(partida);
                VooTableModel vooTableModel = new VooTableModel(List.of(voos));
                table1.setModel(vooTableModel);
                table1.setAutoCreateRowSorter(true);
            }
        } else {
            cmbOrigem.removeActionListener(this);
            setOrigens();
            String chegada = String.valueOf(cmbDestino.getSelectedItem());
            if(chegada != "Aeroportos de Destino") {
                Voo[] voos = aeroportos.listaAeroportosOrigem(chegada);
                VooTableModel vooTableModel = new VooTableModel(List.of(voos));
                table1.setModel(vooTableModel);
                table1.setAutoCreateRowSorter(true);
            }
        }
    }

}
