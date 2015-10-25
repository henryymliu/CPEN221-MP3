package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AlgorithmTest {
    AdjacencyListGraph G1;
    AdjacencyMatrixGraph M1;
    AdjacencyListGraph G2;
    AdjacencyMatrixGraph M2;

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
        G1 = new AdjacencyListGraph();
        G1.addVertex(a);
        G1.addVertex(b);
        G1.addVertex(c);
        G1.addVertex(d);
        G1.addVertex(e);
        G1.addVertex(f);
        G1.addVertex(g);
        G1.addVertex(h);
        G1.addVertex(i);
        G1.addVertex(j);
        G1.addEdge(a, a);
        G1.addEdge(a, b);
        G1.addEdge(b, c);
        G1.addEdge(c, e);
        G1.addEdge(c, f);
        G1.addEdge(d, i);
        G1.addEdge(e, c);
        G1.addEdge(i, j);
        G1.addEdge(j, e);
        G1.addEdge(j, a);

        M1 = new AdjacencyMatrixGraph();
        M1.addVertex(a);
        M1.addVertex(b);
        M1.addVertex(c);
        M1.addVertex(d);
        M1.addVertex(e);
        M1.addVertex(f);
        M1.addVertex(g);
        M1.addVertex(h);
        M1.addVertex(i);
        M1.addVertex(j);
        M1.addEdge(a, a);
        M1.addEdge(a, b);
        M1.addEdge(b, c);
        M1.addEdge(c, e);
        M1.addEdge(c, f);
        M1.addEdge(d, i);
        M1.addEdge(e, c);
        M1.addEdge(i, j);
        M1.addEdge(j, e);
        M1.addEdge(j, a);
        
        G2 = new AdjacencyListGraph();
        G2.addVertex(a);
        G2.addVertex(b);
        G2.addVertex(c);
        G2.addVertex(d);
        G2.addVertex(e);
        G2.addVertex(f);
        G2.addVertex(g);
        G2.addVertex(h);
        G2.addVertex(i);
        G2.addVertex(j);
        G2.addEdge(a, c);
        G2.addEdge(a, f);
        G2.addEdge(b, c);
        G2.addEdge(c, e);
        G2.addEdge(d, c);
        G2.addEdge(f, h);
        G2.addEdge(f, i);
        G2.addEdge(g, j);
        
        M2 = new AdjacencyMatrixGraph();
        M2.addVertex(a);
        M2.addVertex(b);
        M2.addVertex(c);
        M2.addVertex(d);
        M2.addVertex(e);
        M2.addVertex(f);
        M2.addVertex(g);
        M2.addVertex(h);
        M2.addVertex(i);
        M2.addVertex(j);
        M2.addEdge(a, c);
        M2.addEdge(a, f);
        M2.addEdge(b, c);
        M2.addEdge(c, e);
        M2.addEdge(d, c);
        M2.addEdge(f, h);
        M2.addEdge(f, i);
        M2.addEdge(g, j);
    }

    @Test
    public void testShortestDistance() {

        // for AdjacencyListGraph

        assertEquals((Algorithms.shortestDistance(G1, a, e)), 3);
        assertEquals((Algorithms.shortestDistance(G1, a, f)), 3);
        assertEquals((Algorithms.shortestDistance(G1, a, a)), 1);
        assertEquals((Algorithms.shortestDistance(G1, d, j)), 2);
        assertEquals((Algorithms.shortestDistance(G1, a, j)), -1);
        assertEquals((Algorithms.shortestDistance(G1, e, d)), -1);
        assertEquals((Algorithms.shortestDistance(G1, j, j)), -1);

        // for AdjacencyMatrixGraph

        assertEquals((Algorithms.shortestDistance(M1, a, e)), 3);
        assertEquals((Algorithms.shortestDistance(M1, a, f)), 3);
        assertEquals((Algorithms.shortestDistance(M1, a, a)), 1);
        assertEquals((Algorithms.shortestDistance(M1, d, j)), 2);
        assertEquals((Algorithms.shortestDistance(M1, a, j)), -1);
        assertEquals((Algorithms.shortestDistance(M1, e, d)), -1);
        assertEquals((Algorithms.shortestDistance(M1, j, j)), -1);
        
    }

    @Test
    public void testBreadthFirstSearch() {
        HashSet<List<Vertex>> listBreadthFirstSearch = new HashSet<List<Vertex>>();
        List<Vertex> arrayA = new ArrayList<Vertex>();
        
        // assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(G));

        
        //assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(M));
        
        assertEquals(Algorithms.breadthFirstSearch(G2), Algorithms.breadthFirstSearch(M2));
    }

    @Test
    public void testDepthFirstSearch() {

        // assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(G));

        
        // assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(M));
        
        assertEquals(Algorithms.depthFirstSearch(G2), Algorithms.depthFirstSearch(M2));
    }

    @Test
    public void testCommonUpstreamVertices() {
        
        assertEquals(new ArrayList<Vertex>(Arrays.asList(f)), Algorithms.commonUpstreamVertices(G2, h, i));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(G2, b, c));

        
        assertEquals(new ArrayList<Vertex>(Arrays.asList(f)), Algorithms.commonUpstreamVertices(M2, h, i));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(M2, b, c));
        
    }
    
    public void testCommonDownstreamVertices() {
        
        assertEquals(new ArrayList<Vertex>(Arrays.asList(a)), Algorithms.commonDownstreamVertices(G2, b, c));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(G2, h, i));
        
        
        assertEquals(new ArrayList<Vertex>(Arrays.asList(a)), Algorithms.commonDownstreamVertices(M2, b, c));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(M2, h, i));
    }

}
