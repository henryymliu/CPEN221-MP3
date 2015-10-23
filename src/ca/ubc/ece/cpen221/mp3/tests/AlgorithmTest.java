package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AlgorithmTest {
    AdjacencyListGraph G;

    Vertex a = new Vertex("a");
    Vertex b = new Vertex("b");
    Vertex c = new Vertex("c");
    Vertex d = new Vertex("d");
    Vertex e = new Vertex("e");
    Vertex f = new Vertex("f");

    @Before
    public void setupGraph() {
        G = new AdjacencyListGraph();
        G.addVertex(a);
        G.addVertex(b);
        G.addVertex(c);
        G.addVertex(d);
        G.addVertex(e);
        G.addVertex(f);

        /*
         * G.addEdge(b, c); G.addEdge(c, a);
         */
    }

    @Test
    public void testShortestDistance() {

        G.addEdge(a, b);
        G.addEdge(b, c);
        G.addEdge(c, d);
        G.addEdge(d, e);
        G.addEdge(a, f);
        G.addEdge(e, f);

        assertEquals((Algorithms.shortestDistance(G, a, f)), 1);
        assertEquals((Algorithms.shortestDistance(G, a, c)), 2);
        assertEquals((Algorithms.shortestDistance(G, a, e)), 4);
    }

    @Test
    public void testBFS() {
        G.addEdge(a, b);

        G.addEdge(a, c);
        G.addEdge(a, e);
        G.addEdge(e, a);
        G.addEdge(e, d);
        G.addEdge(d, a);

        // assertEquals(new HashSet<List<Vertex>>(),
        // Algorithms.DepthFirstSearch(G));
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(G));
    }

}
