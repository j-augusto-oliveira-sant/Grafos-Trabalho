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
        filaPrioridade.add(origem);

        while (!filaPrioridade.isEmpty()) {
            int verticeAtual = filaPrioridade.poll();

            for (Aresta aresta : adjacencias.get(verticeAtual)) {
                int verticeAdjacente = aresta.destino;
                int novaDistancia = distancias[verticeAtual] + aresta.peso;

                if (novaDistancia < distancias[verticeAdjacente]) {
                    distancias[verticeAdjacente] = novaDistancia;
                    filaPrioridade.add(verticeAdjacente);
                }
            }
        }

        return distancias;
    }
}