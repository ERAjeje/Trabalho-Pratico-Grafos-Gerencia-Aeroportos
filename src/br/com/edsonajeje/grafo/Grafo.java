package br.com.edsonajeje.grafo;

import java.util.ArrayList;

/**
 * Estrutura do Grafo
 * Optei por trabalhar com parametros e funções estáticas para que eu pudesse manter o controle do número
 * de cidades inseridos e pudesse utilizar o grafo dentro das funções simulando o padrão singleton onde só
 * existe uma única instancia do objeto em toda execução do programa.
 */
public class Grafo {
    public static int amount = 0;
    private static float[][] graph;

    /**
     * Na classe aeroportos será instanciada a única ocorrencia do objeto grafo. Nesse momento o grafo é criado como
     * uma matriz 100 x 100 e a função inicializaGrafo é chamada para inserir 0 em todas as posições da matriz
     */
    public Grafo() {
        this.graph = new float[100][100];
        this.inicializaGrafo();
    }

    /**
     * Esta função estática retorna a quantidade de aeroportos já inseridos. A variável amount é inicializada com 0
     * e toda vez que um novo aeroporto é inserido, a função setAmount é chamada e incrementa a variável de controle
     * amount
     *
     * @return amount
     */
    public static int getAmount() {
        return amount;
    }

    public static void setAmount() {
        amount++;
    }

    private void inicializaGrafo() {
        for (int i=0; i<100; i++){
            for (int j=0; j<100; j++){
                this.graph[i][j] = 0;
            }
        }
    }

    /**
     * Esta função é chamada para realizar a inserção de um voo no grafo. A estrutura funciona de forma quadrada onde
     * cada numero inteiro entre 0 e amount está relacionado a uma cidade. Cada cidade está relacionada a uma linha e
     * uma coluna. Por exemplo, se inserir a cidade Ribeirão Preto no grafo vazio, esta será associada à linha 0 e
     * à coluna 0 do grafo.
     * No grafo o voo está relacionado a uma cidade de partida correspondente a uma linha da tabela e a uma cidade de
     * chegada, correspondente a uma coluna da tabela.
     * Por exemplo, se apos inserir a cidade Ribeirão Preto, que estará associada à linha 0 e coluna 0, for inserida a
     * cidade de São Paulo, que estará associada à linha 1 e coluna 1, e for registrado um voo partindo de Ribeirão
     * Preto e chegando em São Paulo, custando R$560,00. O grafo resultante da operação será:
     *
     * 0.0  560.0
     * 0.0  0.0
     *
     * Indica então que na linha 0 e coluna 1 o valor do voo é 560.
     *
     * @param partida
     * @param chegada
     * @param valor
     */
    public static void setGraph(int partida, int chegada, float valor) {
        graph[partida][chegada] = valor;
    }

    /**
     * Esta função retornará um valor float referente ao solicitado para a linha e coluna passadas por parâmetro como
     * partida e chegada. Por exemplo no grafo
     *
     * 0.0   560.0
     * 0.0   0.0
     *
     * o valor retornado para linha 1 e coluna 1 é 0.0 e para linha 0 e coluna 1 é 560.0
     *
     * @param partida
     * @param chegada
     * @return
     */
    public static float getGraph(int partida, int chegada) {
        return graph[partida][chegada];
    }

    /**
     * Esta função é utilizada pela classe aeroportos para retornar um array de inteiros contendo os indices de
     * coluna onde foram cadastrados voos para a cidade de partida informada. Ela percorre a linha e retorna o
     * indice onde o valor é diferente de 0.0
     *
     * Por exemplo, se o usuário quiser saber quais voos estão cadastrados saindo de Ribeirão Preto, esta função
     * retornará um array contendo os indices de coluna onde existem valores diferentes de 0.0
     *
     * @param partida
     * @return
     */
    public static int[] getDestino(int partida) {
        ArrayList<Integer> aux = new ArrayList<>();
        for (int i=0; i<amount; i++) {
            if(graph[partida][i] != 0) {
                aux.add(i);
            }
        }
        int[] result = new int[aux.size()];
        for (int i = 0; i < aux.size(); i++) {
            result[i] = aux.get(i);
        }
        return result;
    }

    /**
     * Esta função percorre a linha retornando um array contendo os valores armazenados na linha diferentes de 0.0
     *
     * @param partida
     * @return
     */
    public static float[] getCustosDestino(int partida) {
        ArrayList<Float> aux = new ArrayList<>();
        for (int i=0; i<amount; i++) {
            if(graph[partida][i] != 0.0) {
                aux.add(graph[partida][i]);
            }
        }
        float[] result = new float[aux.size()];
        for (int i = 0; i < aux.size(); i++) {
            result[i] = aux.get(i);
        }
        return result;
    }

    public static float[] getCustosOrigem(int chegada) {
        ArrayList<Float> aux = new ArrayList<>();
        for (int i=0; i<amount; i++) {
            if(graph[i][chegada] != 0.0) {
                aux.add(graph[i][chegada]);
            }
        }
        float[] result = new float[aux.size()];
        for (int i = 0; i < aux.size(); i++) {
            result[i] = aux.get(i);
        }
        return result;
    }

    /**
     * Retorna um array de inteiros contendo as cidades de origem para uma chegada informada
     * @param partida
     * @return
     */
    public static int[] getOrigem(int partida) {
        ArrayList<Integer> aux = new ArrayList<>();
        for (int i=0; i<amount; i++) {
            if(graph[i][partida] != 0.0) {
                aux.add(i);
            }
        }
        int[] result = new int[aux.size()];
        for (int i = 0; i < aux.size(); i++) {
            result[i] = aux.get(i);
        }
        return result;
    }
    // TODO: refazer
    public static void removeVertice(int vertice) {
        for(int i=vertice; i<99; i++) {
            graph[i] = graph[i+1];
        }
        for(int i=0; i<99; i++) {
            graph[i][vertice] = graph[i][vertice + 1];
        }
    }

    /**
     * Retorna a soma de todos os voos armazenados no grafo
     *
     * @return
     */
    public static float getSomatorio() {
        float aux = 0;
        for (int i=0; i<amount; i++) {
            for (int j=0; j<amount; j++) {
                aux += graph[i][j];
            }
        }
        return aux;
    }

    /**
     * Esta função printa o grafo no console. É utilizada para debugar o código
     */
    public static void printGraph() {
        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < amount; j++) {
                System.out.print(graph[i][j]+"\t");
            }
            System.out.println("");
        }
    }
}
