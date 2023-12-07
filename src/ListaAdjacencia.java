import java.util.ArrayList;
import java.util.List;

class Aresta{
    int origem;
    int destino;
    int peso;

    public Aresta(int origem, int destino, int peso){
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }
}

public class ListaAdjacencia {
    private int nVertices;
    private List<List<Aresta>> adjacencias;
    private boolean direcionado;

    public ListaAdjacencia(int nVertices, boolean direcionado){
       this.nVertices = nVertices;
       this.direcionado = direcionado;
       this.adjacencias = new ArrayList<>(nVertices);
       for (int i=0;i<nVertices;i++){
           adjacencias.add(new ArrayList<>());
       }
    }

    public void adicionarAresta(int u, int v, int peso){
        Aresta aresta = new Aresta(u,v,peso);
        adjacencias.get(u).add(aresta);
        if (!direcionado) {
            Aresta arestaInvertida = new Aresta(v, u, peso);
            adjacencias.get(v).add(arestaInvertida);
        }
    }

    public void adicionarAresta(int u, int v){
        adicionarAresta(u,v,1);
    }

    public void mostrar(){
        for (int i = 0; i < nVertices; i++) {
            System.out.println("Vertice "+i+": ");
            for (Aresta aresta : adjacencias.get(i)){
                System.out.println("("+aresta.destino+","+"Peso:"+aresta.peso+")");
            }
            System.out.println();
        }
    }

    private void dfs(int vertice, boolean[] visitado) {
        // busca em profundidade
        visitado[vertice] = true;
        for (Aresta aresta : adjacencias.get(vertice)) {
            int destino = aresta.destino;
            if (!visitado[destino]) {
                dfs(destino, visitado);
            }
        }
    }

    public int[] calcularDijkstra(int origem, int destino) {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(adjacencias);
        return dijkstraAlgorithm.dijkstra(origem, destino);
    }

    public void mostrarDijkstra(int origem, int destino){
        int[] dijkstra = calcularDijkstra(origem, destino);
        System.out.println(origem+" --> "+destino);
        for (int i =0;i<dijkstra.length;i++){
            System.out.println(i+": "+dijkstra[i]);
        }
    }

    public int distanciaDijkstra(int origem, int destino){
       int[] resultadoDijkstra = this.calcularDijkstra(origem,destino);
       return resultadoDijkstra[destino];
    }

    public boolean isConexo() {
        boolean[] visitado = new boolean[nVertices];
        dfs(0, visitado);
        for (boolean v : visitado) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    public boolean isCompleto() {
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                if (i != j && !isAdjacente(i, j)) {
                    // Há pelo menos um par de vértices não adjacentes
                    return false;
                }
            }
        }
        // Todos os pares de vértices são adjacentes, o grafo é completo
        return true;
    }

    public void removerAresta(int u, int v){
        List<Aresta> arestasU =adjacencias.get(u);
        for (Aresta aresta : arestasU){
            if (aresta.destino == v){
                arestasU.remove(aresta);
                break;
            }
        }
        if (!direcionado){
            List<Aresta> arestasV = adjacencias.get(v);
            for (Aresta aresta : arestasV){
                if (aresta.destino == u){
                    arestasV.remove(aresta);
                    break;
                }
            }
        }
    }

    public void removerVertice(int vertice) {
        // Passo 1: Remover todas as arestas incidentes no vértice
        List<Aresta> arestasDoVertice = adjacencias.get(vertice);
        for (Aresta aresta : arestasDoVertice) {
            int verticeAdjacente = aresta.destino;
            List<Aresta> arestasAdjacente = adjacencias.get(verticeAdjacente);
            // remover a aresta no vértice adjacente que aponta de volta para o vértice que está sendo removido
            for (int i = 0; i < arestasAdjacente.size(); i++) {
                if (arestasAdjacente.get(i).destino == vertice) {
                    arestasAdjacente.remove(i);
                    i--;  // Ajustar o índice após a remoção
                }
            }
        }
        // Passo 2: Remover o próprio vértice da lista de adjacências
        adjacencias.remove(vertice);
        // Atualizar o número de vértices
        nVertices--;
        for (List<Aresta> arestas : adjacencias) {
            for (Aresta aresta : arestas) {
                if (aresta.destino > vertice) {
                    aresta.destino--;
                }
            }
        }
    }

    public boolean isHamiltoniano() {
        if (!isConexo()) {
            return false; // Se não for conexo, não pode ser Hamiltoniano
        }
        for (int i = 0; i < nVertices; i++) {
            int grau = adjacencias.get(i).size();
            if (grau < nVertices / 2) {
                return false; // Não atende ao Teorema de Dirac
            }
        }
        for (int i = 0; i < nVertices; i++) {
            for (int j = i + 1; j < nVertices; j++) {
                if (!isAdjacente(i, j) && (adjacencias.get(i).size() + adjacencias.get(j).size()) < nVertices) {
                    return false; // Não atende ao Teorema de Ore
                }
            }
        }
        return true;
    }

    public boolean isEuleriano() {
        if (!isConexo()) {
            return false; // Se não for conexo, não pode ser Euleriano
        }
        for (int i = 0; i < nVertices; i++) {
            if (adjacencias.get(i).size() % 2 != 0) {
                return false; // Grau ímpar, não é Euleriano
            }
        }
        return true;
    }


    public boolean isAdjacente(int u,int v){
        List<Aresta> arestasU =adjacencias.get(u);
        for (Aresta aresta : arestasU){
            if (aresta.destino == v){
                return true;
            }
        }
        return false;
    }

    /**
    * Verifica se é adjacente e se tem o peso passado.
    */
    public boolean isAdjacenteComPeso(int u, int v, int peso) {
        List<Aresta> arestasU = adjacencias.get(u);
        for (Aresta aresta : arestasU) {
            if (aresta.destino == v && aresta.peso == peso) {
                return true;
            }
        }
        return false;
    }

    public void mostrarDoVertice(int vertice){
        System.out.println("Vertice: "+vertice);
        for (Aresta aresta : adjacencias.get(vertice)){
            System.out.println("("+aresta.destino+", Peso: "+aresta.peso+")");
        }
    }


}
