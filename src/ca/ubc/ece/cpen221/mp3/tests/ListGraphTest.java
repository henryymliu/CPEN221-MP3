package ca.ubc.ece.cpen221.mp3.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class ListGraphTest {
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
    }

    @Test
    public void testAddEdge() {
        G.addEdge(a, b);
        G.addEdge(a, c);
        G.addEdge(a, e);
        G.addEdge(e, a);
        G.addEdge(e, d);
        G.addEdge(d, a);
        List<Vertex> testDownstreamA = new ArrayList<Vertex>();
        testDownstreamA.add(b);
        testDownstreamA.add(c);
        testDownstreamA.add(e);

        assertEquals(testDownstreamA, G.getDownstreamNeighbors(a));

    }

    @Test
    public void testUpstream() {
        G.addEdge(a, b);
        G.addEdge(a, c);
        G.addEdge(a, e);
        G.addEdge(e, a);
        G.addEdge(e, d);
        G.addEdge(d, a);
        List<Vertex> testUpstreamA = new ArrayList<Vertex>();
        testUpstreamA.add(d);
        testUpstreamA.add(e);

        assertEquals(testUpstreamA, G.getUpstreamNeighbors(a));

    }
    
    @Test
    public void testEdgeExists() {
        G.addEdge(a, b);
        G.addEdge(a, c);
        G.addEdge(a, e);
        G.addEdge(e, a);
        G.addEdge(e, d);
        G.addEdge(d, a);
        
        assertTrue(G.edgeExists(a, b));
        assertFalse(G.edgeExists(a, d));
    }

    public void testGetVertices() {
        List<Vertex> testGetVertices = new ArrayList<Vertex>(Arrays.asList(a,b,c,d,e,f));
        assertEquals(testGetVertices,G.getVertices());
        
        AdjacencyMatrixGraph empty;
        assertEquals(0,empty.getVertices());
    }
}
