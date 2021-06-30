package br.com.edsonajeje.aeroportos;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Classe para popular a tabela com os voos recebidos do grafo
 */
public class VooTableModel extends AbstractTableModel {

    private final String[] COLUMNS = {"Origem","Destino","Valor"};
    private List<Voo> voos;

    public VooTableModel(List<Voo> voos) {
        this.voos = voos;
    }

    @Override
    public int getRowCount() {
        return voos.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch(columnIndex) {
            case 0 -> voos.get(rowIndex).getPartida();
            case 1 -> voos.get(rowIndex).getChegada();
            case 2 -> voos.get(rowIndex).getValor();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
    /**
     * Esta era a função que estava estourando o exception... Mas fiz o teste sem ela e funcionou normal... Então acho
     * que devo ter implementado errado ou não ter entendido direito a documentação
     */
//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        if(getValueAt(0, columnIndex) != null) {
//            return getValueAt(0, columnIndex).getClass();
//        } else {
//            return Object.class;
//        }
//    }
}