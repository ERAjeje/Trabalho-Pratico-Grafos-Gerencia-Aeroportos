# Trabalho Prático de Teoria dos Grafos - Gerência de Aeroportos

Trabalho apresentado como exigência da disciplina de Teoria dos Grafos, ministrada pelo Professor Me. Hugo Resende, para obtenção de nota parcial.

Este trabalho é a implementação o de um programa que possibilite o
gerenciamento de aeroportos e voos (com custos associados) entre eles. De modo a realizar essa tarefa de forma eficiente,
deverá ser utilizado um dígrafo ponderado, representado por uma matriz de pesos.

## Recursos do Programa

O programa deverá fornecer recursos que realizem as ações das seguintes funções:

* **inicializaGrafo()**: atribui o valor 0 (zero) às posições da matriz de pesos; 
Essa função deverá ser executada de forma automática, ou seja, não é necessário oferecer essa opção ao
usuário; 
    * Considere o número máximo de aeroportos em 100.
    * As estrutura inicial deverá ser estática, sendo liberadas posições de memória conforme demanda.
    
* **insereAeroporto()**: reserva uma linha e uma coluna na matriz de pesos para representar o aeroporto inserido;
    * O aeroporto deverá ser inserido pelo nome;
    * O programa deverá verificar se o aeroporto já está inserido e, caso positivo, retornar uma mensagem de
erro.
      
* **insereVoo()**: insere um voo de um aeroporto para outro (com o custo associado);
    * Os aeroportos deverão estar previamente inseridos no grafo;
        * Caso contrário, retornar uma mensagem de erro.
    * Deverão ser listados os nomes dos aeroportos para que o usuário se guie na escolha;
    * Caso o voo já esteja inserido, deve-se oferecer a opção de atualizar o custo entre os aeroportos
selecionados.
      
* **listaAeroportosDestino()**: após o usuário escolher um aeroporto, lista todos os aeroportos que possuem voos
de destino cadastrados, inclusive com os custos totais para cada um deles;

* **listaAeroportosOrigem()**: após o usuário escolher um aeroporto, lista todos os aeroportos que possuem voos
de origem cadastrados, inclusive com os custos totais para cada um deles;
  
* **atualizaVoo()**: ocorre similarmente à inserção de voos, porém voo deverá estar previamente inserido.
  * caso o voo não esteja inserido deve-se oferecer essa opção ao usuário;
  * caso algum dos aeroportos, ou os dois, não esteja(m) inseridos, deverá ser oferecida essa opção ao
usuário.
    
* **removeAeroporto()**: com base na informação de um nome de aeroporto, realiza a deleção de um aeroporto e
de todos os voos de origem/destino vinculadas ao tal;
  * Caso o aeroporto não exista, emitir uma mensagem de erro.
  
* **removeVoo()**: após a escolha de dois aeroportos, realiza a remoção de uma rota aérea;
  * Caso pelo menos um dos aeroportos não exista, emitir uma mensagem de erro.
  
* **calculaCustoTotal()**: retorna o somatório de todos os custos dos voos cadastrados.