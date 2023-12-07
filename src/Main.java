public class Main {
    public static void main(String[] args) {
        ListaAdjacencia grafo = new ListaAdjacencia(4,false);
        grafo.adicionarAresta(0,1);
        grafo.adicionarAresta(0,2);
        grafo.adicionarAresta(2,1);
        grafo.adicionarAresta(2,3);
        grafo.mostrar();
        System.out.println("==========");
        grafo.removerVertice(2);
        grafo.mostrar();

//        e) Verificar se um grafo é euleriano, semieuleriano ou não euleriano.
//        f) Verificar se um grafo é Hamiltoniano, semi-hamiltoniano ou não hamiltoniano.
    //        a. Para o algoritmo que verifica se o grafo é hamiltoniano descrever qual a
    //        estratégia utilizada para solucionar o problema.
    }
}