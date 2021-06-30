package br.com.edsonajeje.aeroportos;
import br.com.edsonajeje.grafo.Grafo;

import java.util.ArrayList;

/**
 * A classe aeroportos, apesar de não ser estática, também é unica na execução do programa. Ela é passada como parametro
 * entre as telas pois é utilizada para armazenar os nomes dos aeroportos cadastrados. Para a classe grafo é passada
 * somente o indice correspondente ao nome do aeroporto. No momento que um objeto dessa classe é instanciado, no construtor
 * é inicializado o array de strings com 100 posições para armazenar o nome dos aeroportos e é instanciado o único
 * objeto Grafo disponível para a execução do programa.
 *
 */
public class Aeroportos {
    private static String[] aeroporto;
    private Grafo grafo;

    /**
     * Contrutor
     */
    public Aeroportos() {
        this.aeroporto = new String[100];
        this.grafo = new Grafo();
    }

    /**
     * Esta função recebe como parametro uma string contendo o nome do aeroporto. Para tornar mais fácil a verificação
     * se essa string já existe no array, optei por utilizar um laço for e inserir em um objeto ArrayList. Se este
     * aeroporto já existir na lista a função retorna false e a tela dispara uma mensagem de erro informando que o
     * aeroporto já está cadastrado, caso contrário ela insere a string no array e libera mais uma linha e coluna no
     * grafo incrementando a variável amount
     *
     * @param aeroporto
     * @return
     */
    public boolean insereAeroporto(String aeroporto) {
        ArrayList<String> aux = new ArrayList<>();
        for(int i=0; i<Grafo.getAmount(); i++) aux.add(this.aeroporto[i]);
        if(!aux.contains(aeroporto)) {
            this.aeroporto[Grafo.getAmount()] = aeroporto;
            Grafo.setAmount();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Esta função retorna um inteiro correspondente à posição da string no array ou -1 caso não exista
     * Como esta função é chamada atrelada a um combobox que contem o nome dos aeroportos cadastrados, a opção de
     * retornar -1 existe para tratar erros, mesmo que esses possam não ocorrer pela forma como esta string é recebida
     * pela função.
     *
     * @param aeroporto
     * @return
     */
    public int getAeroportoIndex(String aeroporto) {
        int p = -1;
        for(int i=0; i<Grafo.amount; i++){
            if(this.aeroporto[i].contentEquals(aeroporto)) {
                p = i;
                break;
            }
        }
        return p;
    }

    /**
     * A unção insere voo recebe duas strings, aeroporto de partida e aeroporto de chegada, e um float com o valor do
     * trajeto/voo. Utiliza a função getAeroportoIndex para informar ao grafo qual linha e coluna o valor deve ser
     * inserido. Para garantir a inexistencia de erros, essa inserção só ocorre caso os indices recebidos sejam diferentes
     * de -1 tanto para o paramentro partida quanto para chegada.
     * Esta função retorna true para sucesso na inserção e false para falha
     *
     * @param partida
     * @param chegada
     * @param valor
     * @return
     */
    public boolean insereVoo(String partida, String chegada, float valor) {
        int p , c;
        p = getAeroportoIndex(partida);
        c = getAeroportoIndex(chegada);
        if (p != -1 && c != -1) {
            Grafo.setGraph(p, c, valor);
            return true;
        }
        return false;
    }

    /**
     * Esta função é chamada nas telas para popular os combobox com o nome dos aeroportos cadastrados
     * Ela retorna um array de strings e recebe um array de inteiros. É utilizada na tela para popular a tabela com
     * a listagem dos voos para uma partida ou chegada informada
     *
     * @param index
     * @return
     */
    public String[] getNomeAeroportos(int[] index) {
        String[] result = new String[index.length];
        for (int i=0; i<index.length; i++) {
            result[i] = aeroporto[index[i]];
        }
        return result;
    }

    /**
     * Retorna uma string referente ao aeroporto armazenado em um indice do array
     *
     * @param index
     * @return
     */
    private String getAeroporto(int index) {
        return this.aeroporto[index];
    }

    /**
     * Retorna um array de strings contendo todos os aeroportos cadastrados.
     *
     * @return
     */
    public String[] getAeroportos() {
        String[] result = new String[Grafo.getAmount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = getAeroporto(i);
        }
        return result;
    }

    /**
     * getVoosPartida e getVoosChegada são utilizadas para preencher a tabela com os voos cadastrados para uma
     * partida ou chegada informada. Ambas retornam um array de objetos Voo. Esta classe tem um aeroporto de partida, um
     * aeroporto de chegada e o custo do voo entre elas.
     *
     * A diferença entre elas é que para o getVoosPartida tem a partida fixa e varia a chegada e getVoosChegada tem
     * a chegada fixa e varia as partidas.
     *
     * @param partida
     * @param chegadas
     * @param custos
     * @return
     */
    private Voo[] getVoosPartida(String partida, String[] chegadas, float[] custos) {
        Voo[] voos = new Voo[chegadas.length];
        for (int i=0; i<voos.length; i++) {
            voos[i] = new Voo();
            voos[i].setPartida(partida);
            voos[i].setChegada(chegadas[i]);
            voos[i].setValor(custos[i]);
        }
        return voos;
    }

    private Voo[] getVoosChegada(String partida, String[] chegadas, float[] custos) {
        Voo[] voos = new Voo[chegadas.length];
        for (int i=0; i<voos.length; i++) {
            voos[i] = new Voo();
            voos[i].setPartida(chegadas[i]);
            voos[i].setChegada(partida);
            voos[i].setValor(custos[i]);
        }
        return voos;
    }

    /**
     * Dada uma partida retorna uma lista de voos com os destinos para essa partida e os custos dos voos.
     *
     * @param partida
     * @return
     */
    public Voo[] listaAeroportosDestino(String partida) {
        int[] index;
        index = Grafo.getDestino(getAeroportoIndex(partida));
        String[] chegadas = getNomeAeroportos(index);
        float[] custo = Grafo.getCustosDestino(getAeroportoIndex(partida));
        return getVoosPartida(partida, chegadas, custo);
    }

    /**
     * Dada uma chegada retorna uma lista de voos com as partidas para essa chegada e os custos dos voos.
     *
     * @param chegada
     * @return
     */
    public Voo[] listaAeroportosOrigem(String chegada) {
        int[] aux;
        aux = Grafo.getOrigem(getAeroportoIndex(chegada));
        String[] origem = getNomeAeroportos(aux);
        float[] custo = Grafo.getCustosOrigem(getAeroportoIndex(chegada));
        return getVoosChegada(chegada, origem, custo);
    }

    public String[] getAeroportosDestinos(String partida) {
        int[] index;
        index = Grafo.getDestino(getAeroportoIndex(partida));
        return getNomeAeroportos(index);
    }

    /**
     * Função utilizada para atualizar o valor de um voo já cadastrado.
     *
     * @param partida
     * @param chegada
     * @param valor
     * @return
     */
    public String atualizaVoo(String partida, String chegada, float valor) {
        int p , c;
        p = getAeroportoIndex(partida);
        c = getAeroportoIndex(chegada);
        if (p == -1) return partida;
        if (c == -1) return chegada;
        float aux = Grafo.getGraph(p, c);
        Grafo.setGraph(p, c, valor);
        return "";
    }

    /**
     * Remove um aeroporto cadastrado e a linha correspondente a ele no grafo. Caso existam outros voos com partida
     * ou chegada para o aeroporto removido, esses voos serão excluidos altomaticamente
     *
     * @param aeroporto
     * @return
     */
    public boolean removeAeroporto(String aeroporto) {
        int p = getAeroportoIndex(aeroporto);
        if (p != -1) {
            Grafo.removeVertice(p);
            for (int i = p; i<=Grafo.amount-1; i++) {
                this.aeroporto[i] = this.aeroporto[i+1];
            }
            Grafo.amount -= 1;
            return true;
        }
        return false;
    }

    /**
     * Remove um voo específico entre uma partida e uma chegada. Simplesmente insere 0.0 no valor referente à
     * linha e a coluna referentes ao voo.
     *
     * @param partida
     * @param chegada
     * @return
     */
    public boolean removeVoo(String partida, String chegada) {
        int p = getAeroportoIndex(partida);
        int c = getAeroportoIndex(chegada);
        if (p != -1 && c != -1) {
            Grafo.setGraph(p, c, 0);
            return true;
        }
        return false;
    }

    /**
     * Retorna um float referente ao somatório de todos os custos de voos cadastrados no grafo
     *
     * @return
     */
    public float calculaCustoTotal() {
        return Grafo.getSomatorio();
    }

    public void printAeroportos() {
        for (int i = 0; i<Grafo.amount; i++) {
            System.out.println(aeroporto[i]);
        }
    }

}
