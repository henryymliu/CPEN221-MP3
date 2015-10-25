package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AlgorithmTest {
    AdjacencyListGraph G;
    AdjacencyMatrixGraph M;

    Vertex a = new Vertex("a");
    Vertex b = new Vertex("b");
    Vertex c = new Vertex("c");
    Vertex d = new Vertex("d");
    Vertex e = new Vertex("e");
    Vertex f = new Vertex("f");
    Vertex g = new Vertex("g");
    Vertex h = new Vertex("h");
    Vertex i = new Vertex("i");
    Vertex j = new Vertex("j");

    @Before
    public void setupGraph() {
        G = new AdjacencyListGraph();
        G.addVertex(a);
        G.addVertex(b);
        G.addVertex(c);
        G.addVertex(d);
        G.addVertex(e);
        G.addVertex(f);
        G.addVertex(g);
        G.addVertex(h);
        G.addVertex(i);
        G.addVertex(j);

        M = new AdjacencyMatrixGraph();
        M.addVertex(a);
        M.addVertex(b);
        M.addVertex(c);
        M.addVertex(d);
        M.addVertex(e);
        M.addVertex(f);
        M.addVertex(g);
        M.addVertex(h);
        M.addVertex(i);
        M.addVertex(j);

        /*
         * G.addEdge(b, c); G.addEdge(c, a);
         */
    }

    @Test
    public void testShortestDistance() {

        // for AdjacencyListGraph
        G.addEdge(a, b);
        G.addEdge(b, c);
        G.addEdge(c, d);
        G.addEdge(d, e);
        G.addEdge(e, f);
        G.addEdge(f, g);
        G.addEdge(g, h);
        G.addEdge(h, i);
        G.addEdge(i, j);
        G.addEdge(j, a);

        G.addEdge(a, f);
        G.addEdge(d, e);
        G.addEdge(a, a);

        assertEquals((Algorithms.shortestDistance(G, a, e)), 4);

        assertEquals((Algorithms.shortestDistance(G, a, f)), 1);
        assertEquals((Algorithms.shortestDistance(G, a, j)), 5);
        assertEquals((Algorithms.shortestDistance(G, a, a)), 1);
        assertEquals((Algorithms.shortestDistance(G, e, e)), 10);

        // for AdjacencyMatrixGraph
        M.addEdge(a, b);
        M.addEdge(b, c);
        M.addEdge(c, d);
        M.addEdge(d, e);
        M.addEdge(e, f);
        M.addEdge(f, g);
        M.addEdge(g, h);
        M.addEdge(h, i);
        M.addEdge(i, j);
        M.addEdge(j, a);

        M.addEdge(a, f);
        M.addEdge(d, e);
        M.addEdge(a, a);

        assertEquals((Algorithms.shortestDistance(M, a, e)), 4);

        assertEquals((Algorithms.shortestDistance(M, a, f)), 1);
        assertEquals((Algorithms.shortestDistance(M, a, j)), 5);
        assertEquals((Algorithms.shortestDistance(M, a, a)), 1);
        assertEquals((Algorithms.shortestDistance(M, e, e)), 10);

    }

    @Test
    public void testBreadthFirstSearch() {
        G.addEdge(a, b);
        G.addEdge(b, d);
        G.addEdge(d, g);
        G.addEdge(g, c);
        G.addEdge(d, f);
        G.addEdge(f, a);
        G.addEdge(e, h);
        G.addEdge(h, i);
        G.addEdge(i, h);
        G.addEdge(h, j);

        // assertEquals(new HashSet<List<Vertex>>(),
        // Algorithms.DepthFirstSearch(G));
        // assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(G));

        M.addEdge(a, b);
        M.addEdge(b, d);
        M.addEdge(d, g);
        M.addEdge(g, c);
        M.addEdge(d, f);
        M.addEdge(f, a);
        M.addEdge(e, h);
        M.addEdge(h, i);
        M.addEdge(i, h);
        M.addEdge(h, j);
        // assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(M));
    }

    @Test
    public void testDepthFirstSearch() {
        G.addEdge(a, b);
        G.addEdge(b, d);
        G.addEdge(d, g);
        G.addEdge(g, c);
        G.addEdge(d, f);
        G.addEdge(f, a);
        G.addEdge(e, h);
        G.addEdge(h, i);
        G.addEdge(i, h);
        G.addEdge(h, j);

        // assertEquals(new HashSet<List<Vertex>>(),
        // Algorithms.DepthFirstSearch(G));
        // assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(G));
        
        M.addEdge(a, b);
        M.addEdge(b, d);
        M.addEdge(d, g);
        M.addEdge(g, c);
        M.addEdge(d, f);
        M.addEdge(f, a);
        M.addEdge(e, h);
        M.addEdge(h, i);
        M.addEdge(i, h);
        M.addEdge(h, j);
        // assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(M));
    }

    @Test
    public void testCommonUpstreamVertices() {
        G.addEdge(a, b);
        G.addEdge(b, d);
        G.addEdge(d, g);
        G.addEdge(g, c);
        G.addEdge(d, f);
        G.addEdge(f, a);
        G.addEdge(e, h);
        G.addEdge(h, i);
        G.addEdge(i, h);
        G.addEdge(h, j);
        
        //assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(G, f, g));
        
        M.addEdge(a, b);
        M.addEdge(b, d);
        M.addEdge(d, g);
        M.addEdge(g, c);
        M.addEdge(d, f);
        M.addEdge(f, a);
        M.addEdge(e, h);
        M.addEdge(h, i);
        M.addEdge(i, h);
        M.addEdge(h, j);
        
        //assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(M, f, g));
    }
    
    public void testCommonDownstreamVertices() {
        G.addEdge(a, b);
        G.addEdge(b, d);
        G.addEdge(d, g);
        G.addEdge(g, c);
        G.addEdge(d, f);
        G.addEdge(f, a);
        G.addEdge(e, h);
        G.addEdge(h, i);
        G.addEdge(i, h);
        G.addEdge(h, j);
        
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(G, f, g));
        
        M.addEdge(a, b);
        M.addEdge(b, d);
        M.addEdge(d, g);
        M.addEdge(g, c);
        M.addEdge(d, f);
        M.addEdge(f, a);
        M.addEdge(e, h);
        M.addEdge(h, i);
        M.addEdge(i, h);
        M.addEdge(h, j);
        
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(M, f, g));
    }

}
