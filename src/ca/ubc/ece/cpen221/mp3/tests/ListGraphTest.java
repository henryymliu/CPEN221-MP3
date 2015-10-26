package ca.ubc.ece.cpen221.mp3.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class ListGraphTest {
    AdjacencyListGraph G;
    Vertex a = new Vertex("a");
    Vertex b = new Vertex("b");
    Vertex c = new Vertex("c");
    Vertex d = new Vertex("d");
    Vertex e = new Vertex("e");
    Vertex f = new Vertex("f");

    /*
     * Initialize graph that will be used in the test and tests addVertex and
     * addEdge methods
     */
    @Before
    public void setupGraph() {
        G = new AdjacencyListGraph();
        // tests addVertex
        G.addVertex(a);
        G.addVertex(b);
        G.addVertex(c);
        G.addVertex(d);
        G.addVertex(e);
        G.addVertex(f);
        // tests addEdge
        G.addEdge(a, b);
        G.addEdge(a, c);
        G.addEdge(a, e);
        G.addEdge(e, a);
        G.addEdge(e, d);
        G.addEdge(d, a);
    }

    /*
     * Tests getDownstreamNeighbors method
     */
    @Test
    public void testDownstream() {
        List<Vertex> testDownstreamA = new ArrayList<Vertex>();
        testDownstreamA.add(b);
        testDownstreamA.add(c);
        testDownstreamA.add(e);

        assertEquals(testDownstreamA, G.getDownstreamNeighbors(a));
    }

    /*
     * Tests getUpstreamNeighbors method
     */
    @Test
    public void testUpstream() {
        List<Vertex> testUpstreamA = new ArrayList<Vertex>();
        testUpstreamA.add(d);
        testUpstreamA.add(e);

        assertEquals(testUpstreamA, G.getUpstreamNeighbors(a));
    }

    /*
     * Tests edgeExists method
     */
    @Test
    public void testEdgeExists() {

        assertTrue(G.edgeExists(a, b));
        assertFalse(G.edgeExists(a, d));
    }

    /*
     * Tests getVertices method
     */
    @Test
    public void testGetVertices() {
        List<Vertex> testGetVertices = new ArrayList<Vertex>(Arrays.asList(a, b, c, d, e, f));
        assertEquals(testGetVertices, G.getVertices());

        Graph empty = new AdjacencyListGraph();
        assertEquals(new ArrayList<Vertex>(), empty.getVertices());
    }
}
