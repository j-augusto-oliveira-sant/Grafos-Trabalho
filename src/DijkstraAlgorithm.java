import java.util.*;
class DijkstraAlgorithm {
    private final List<List<Aresta>> adjacencias;

    public DijkstraAlgorithm(List<List<Aresta>> adjacencias) {
        this.adjacencias = adjacencias;
    }

    public int[] dijkstra(int origem, int destino) {
        int[] distancias = new int[adjacencias.size()];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        PriorityQueue<Integer> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(vertex -> distancias[vertex]));
        distancias[origem] = 0;
        int[] verticesAntecessores = new int[adjacencias.size()];
        filaPrioridade.add(origem);
        while (!filaPrioridade.isEmpty()) {
            int verticeAtual = filaPrioridade.poll();

            for (Aresta aresta : adjacencias.get(verticeAtual)) {
                int verticeAdjacente = aresta.destino;
                int novaDistancia = distancias[verticeAtual] + aresta.peso;

                if (novaDistancia < distancias[verticeAdjacente]) {
                    distancias[verticeAdjacente] = novaDistancia;
                    verticesAntecessores[verticeAdjacente] = verticeAtual;
                    filaPrioridade.add(verticeAdjacente);
                }
            }
//            imprimirIteracao(verticeAtual,distancias, verticesAntecessores);
//            imprimirTabela(verticeAtual,distancias, verticesAntecessores);
        }
        return distancias;
    }
    private void imprimirIteracao(int verticeAtual, int[] distancias, int[] verticesAntecessores) {
        System.out.println("Iteração:");
        for (int i = 0; i < distancias.length; i++) {
            if (distancias[i] != 2147483647) {
                System.out.printf("Vertice: %d, Caminho mais curto: %d, Vertice Antecessor: %d%n", i, distancias[i], verticesAntecessores[i]);
            } else {
                System.out.printf("Vertice: %d, Caminho mais curto: infinito, Vertice Antecessor: %d%n", i, verticesAntecessores[i]);
            }
        }
        System.out.println();
    }
    private void imprimirTabela(int verticeAtual, int[] distancias, int[] verticesAntecessores) {
        System.out.println("\\hline");
        for (int i = 0; i < distancias.length; i++) {
            if (distancias[i] != 2147483647) {
                System.out.printf("& %d & %d & %d\\\\\n", i, distancias[i], verticesAntecessores[i]);
            } else {
                System.out.printf("& %d & $\\infty$ & %d\\\\\n", i, verticesAntecessores[i]);
            }
        }
    }
}