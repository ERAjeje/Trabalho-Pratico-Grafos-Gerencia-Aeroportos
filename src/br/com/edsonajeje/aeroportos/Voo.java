package br.com.edsonajeje.aeroportos;

/**
 * Classe responsável por fazer por popular as linhas da tabela. Ela é utilizada como modelo para o JTable
 */
public class Voo {
    private String partida;
    private String chegada;
    private float valor;

    public Voo() {
    }

    public Voo(String partida, String chegada, float valor) {
        this.partida = partida;
        this.chegada = chegada;
        this.valor = valor;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
