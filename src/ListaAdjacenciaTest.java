import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListaAdjacenciaTest {

    @Test
    void testAdicionarAresta() {
        // Arrange
        ListaAdjacencia grafo = new ListaAdjacencia(5, false);

        // Act
        grafo.adicionarAresta(0, 1, 2);

        // Assert
        assertTrue(grafo.isAdjacente(0, 1));
    }

    @Test
    void testRemoverAresta() {
        // Arrange
        ListaAdjacencia grafo = new ListaAdjacencia(5, false);
        grafo.adicionarAresta(0, 1, 2);

        // Act
        grafo.removerAresta(0, 1);

        // Assert
        assertFalse(grafo.isAdjacente(0, 1));
    }

    @Test
    void testRemoverVertice() {
        ListaAdjacencia grafo = new ListaAdjacencia(5, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(0, 2, 3);
        grafo.removerVertice(1);
        // o 2 virou o novo 1 mas o peso dele era 3 e nao 2
        assertTrue(grafo.isAdjacenteComPeso(0, 1, 3));
        assertFalse(grafo.isAdjacenteComPeso(0, 1, 2));
    }

    @Test
    void testIsConexo() {
        // Arrange
        ListaAdjacencia grafo = new ListaAdjacencia(3, false);
        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(1, 2);

        // Act & Assert
        assertTrue(grafo.isConexo());

        ListaAdjacencia grafoNaoConexo = new ListaAdjacencia(5, false);
        grafoNaoConexo.adicionarAresta(0, 1);
        grafoNaoConexo.adicionarAresta(1, 2);
        grafoNaoConexo.adicionarAresta(3, 4);

        // Act & Assert
        assertFalse(grafoNaoConexo.isConexo());
    }

    @Test
    void testIsCompleto() {
        // Arrange
        ListaAdjacencia grafo = new ListaAdjacencia(3, false);
        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(1, 2);

        // Act & Assert
        assertTrue(grafo.isCompleto());
    }

    @Test
    void testIsCompleto2(){
        ListaAdjacencia grafoNaoCompleto = new ListaAdjacencia(3,false);
        grafoNaoCompleto.adicionarAresta(0,1);
        grafoNaoCompleto.adicionarAresta(1,2);
        assertFalse(grafoNaoCompleto.isCompleto());
    }

    @Test
    void testIsHamiltoniano(){
        ListaAdjacencia grafoHamiltoniano = new ListaAdjacencia(5, false);
        grafoHamiltoniano.adicionarAresta(0,2);
        grafoHamiltoniano.adicionarAresta(0,1);
        grafoHamiltoniano.adicionarAresta(2,1);
        grafoHamiltoniano.adicionarAresta(2,3);
        grafoHamiltoniano.adicionarAresta(2,4);
        grafoHamiltoniano.adicionarAresta(4,3);
        grafoHamiltoniano.adicionarAresta(4,1);
        grafoHamiltoniano.adicionarAresta(1,3);
        assertEquals(1, grafoHamiltoniano.isHamiltoniano());
    }

    @Test
    void testIsHamiltoniano2(){
        ListaAdjacencia grafoNaoHamiltoniano = new ListaAdjacencia(6, false);
        grafoNaoHamiltoniano.adicionarAresta(0,3);
        grafoNaoHamiltoniano.adicionarAresta(3,2);
        grafoNaoHamiltoniano.adicionarAresta(3,1);
        grafoNaoHamiltoniano.adicionarAresta(1,2);
        grafoNaoHamiltoniano.adicionarAresta(3,4);
        grafoNaoHamiltoniano.adicionarAresta(3,5);
        grafoNaoHamiltoniano.adicionarAresta(5,4);
        assertEquals(0, grafoNaoHamiltoniano.isHamiltoniano());
    }

    @Test
    public void testIsEuleriano() {
        // Grafo Euleriano (todos os vértices têm grau par)
        ListaAdjacencia grafoEuleriano = new ListaAdjacencia(5, false);
        grafoEuleriano.adicionarAresta(0, 1);
        grafoEuleriano.adicionarAresta(1, 2);
        grafoEuleriano.adicionarAresta(2, 3);
        grafoEuleriano.adicionarAresta(3, 4);
        grafoEuleriano.adicionarAresta(4, 0);

        assertEquals(1,grafoEuleriano.isEuleriano());

        // Grafo Não Euleriano (um vértice tem grau ímpar)
        ListaAdjacencia grafoSemiEuleriano = new ListaAdjacencia(5, false);
        grafoSemiEuleriano.adicionarAresta(0, 1);
        grafoSemiEuleriano.adicionarAresta(4, 0);
        grafoSemiEuleriano.adicionarAresta(2, 4);
        grafoSemiEuleriano.adicionarAresta(4, 3);
        grafoSemiEuleriano.adicionarAresta(1, 3);
        grafoSemiEuleriano.adicionarAresta(1, 2);

        assertEquals(2,grafoSemiEuleriano.isEuleriano());


    }

    @Test
    void testNonEuleriano(){
        // Não Eulerinao
        ListaAdjacencia grafoNaoEuleriano = new ListaAdjacencia(5, false);
        grafoNaoEuleriano.adicionarAresta(0, 1);
        grafoNaoEuleriano.adicionarAresta(4, 0);
        grafoNaoEuleriano.adicionarAresta(2, 4);
        grafoNaoEuleriano.adicionarAresta(4, 3);
        grafoNaoEuleriano.adicionarAresta(1, 3);
        grafoNaoEuleriano.adicionarAresta(1, 2);
        grafoNaoEuleriano.adicionarAresta(3, 2);
        assertEquals(0, grafoNaoEuleriano.isEuleriano());
    }

    @Test
    public void testDijkstra(){
        ListaAdjacencia grafo = new ListaAdjacencia(5, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(0, 2, 4);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(1, 3, 7);
        grafo.adicionarAresta(2, 4, 3);
        grafo.adicionarAresta(3, 4, 5);

        int origem = 0;
        int destino = 4;
        int[] resultadoEsperado = {0, 2, 3, 9, 6};
        int[] resultadoDijkstra = grafo.calcularDijkstra(origem, destino);
        assertArrayEquals(resultadoEsperado, resultadoDijkstra);
    }

    @Test
    public void testCaminhoMinimoA(){
        ListaAdjacencia grafo = new ListaAdjacencia(5,true);
        grafo.adicionarAresta(0,1,1);
        grafo.adicionarAresta(0,4,10);
        grafo.adicionarAresta(0,3,3);
        grafo.adicionarAresta(1,2,5);
        grafo.adicionarAresta(2,4,1);
        grafo.adicionarAresta(3,2,2);
        grafo.adicionarAresta(3,4,6);

        int[] resultadoEsperado = {0,1,5,3,6};
        int[] resultadoDijkstra = grafo.calcularDijkstra(0, 4);

        assertArrayEquals(resultadoEsperado, resultadoDijkstra);
    }

    @Test
    public void testCaminhoMinimoB(){
        ListaAdjacencia grafo = new ListaAdjacencia(7,true);
        grafo.adicionarAresta(1,2,15);
        grafo.adicionarAresta(1,3,9);
        grafo.adicionarAresta(2,4,2);
        grafo.adicionarAresta(3,2,4);
        grafo.adicionarAresta(3,4,3);
        grafo.adicionarAresta(3,5,16);
        grafo.adicionarAresta(4,5,6);
        grafo.adicionarAresta(4,6,21);
        grafo.adicionarAresta(5,6,7);

        //1
        int[] resultado = grafo.calcularDijkstra(1,6);
        assertEquals(25,resultado[6]);
        //2
        assertEquals(12,resultado[4]);
    }

    @Test
    public void testCaminhoMinimoC(){
        ListaAdjacencia grafo = new ListaAdjacencia(18,false);
//        0. Viana do Castelo
//        1. Braga
//        2. Porto
//        3. Vila Real
//        4. Bragança
//        5. Viseu
//        6. Guarda
//        7. Aveiro
//        8. Castelo Branco
//        9. Portalegre
//        10. Évora
//        11. Beja
//        12. Faro
//        13. Setúbal
//        14. Lisbon (Lisboa)
//        15. Santarém
//        16. Leiria
//        17. Coimbra
        grafo.adicionarAresta(0, 1, 50);   // Viana -> Braga 50
        grafo.adicionarAresta(0, 2, 80);   // Viana -> Porto 80
        grafo.adicionarAresta(1, 3, 100);  // Braga -> Vila 100
        grafo.adicionarAresta(1, 2, 50);   // Braga -> Porto 50
        grafo.adicionarAresta(2, 3, 120);  // Porto -> Vila 120
        grafo.adicionarAresta(2, 7, 70);   // Porto -> Aveiro 70
        grafo.adicionarAresta(7, 5, 100);  // Aveiro -> Viseu 100
        grafo.adicionarAresta(7, 17, 80);   // Aveiro -> Coimbra 80
        grafo.adicionarAresta(17, 6, 160);  // Coimbra -> Guarda 160
        grafo.adicionarAresta(17, 8, 160);  // Coimbra -> Castelo Branco 160
        grafo.adicionarAresta(17, 16, 70);  // Coimbra -> Leiria 70
        grafo.adicionarAresta(16, 14, 130); // Leiria -> Lisboa 130
        grafo.adicionarAresta(15, 14, 70); // Santarém -> Lisboa 70
        grafo.adicionarAresta(14, 13, 50); // Lisboa -> Setúbal 50
        grafo.adicionarAresta(14, 10, 150); // Lisboa -> Évora 150
        grafo.adicionarAresta(13, 11, 135); // Setúbal -> Beja 135
        grafo.adicionarAresta(13, 12, 260); // Setúbal -> Faro 260
        grafo.adicionarAresta(12, 11, 170); // Faro -> Beja 170
        grafo.adicionarAresta(11, 10, 80); // Beja -> Évora 80
        grafo.adicionarAresta(10, 9, 100); // Évora -> Portalegre 100
        grafo.adicionarAresta(9, 15, 150); // Portalegre -> Santarém 150
        grafo.adicionarAresta(10, 15, 120); // Évora -> Santarém 120
        grafo.adicionarAresta(9, 8, 80); // Portalegre -> Castelo Branco 80
        grafo.adicionarAresta(8, 17, 160); // Castelo Branco -> Coimbra 160
        grafo.adicionarAresta(8, 6, 100); // Castelo Branco -> Guarda 100
        grafo.adicionarAresta(6, 5, 80); // Guarda -> Viseu 80
        grafo.adicionarAresta(5, 17, 80); // Viseu -> Coimbra 80
        grafo.adicionarAresta(17, 7, 80); // Coimbra -> Aveiro 80
        grafo.adicionarAresta(6, 4, 200); // Guarda -> Bragança 200
        grafo.adicionarAresta(6, 3, 150); // Guarda -> Vila Real 150
        grafo.adicionarAresta(3, 4, 140); // Vila Real -> Bragança 140
        grafo.adicionarAresta(3, 1, 100); // Vila Real -> Braga 100
        grafo.adicionarAresta(3, 2, 120); // Vila Real -> Porto 120

        assertEquals(400,grafo.distanciaDijkstra(14,1));
    }
}