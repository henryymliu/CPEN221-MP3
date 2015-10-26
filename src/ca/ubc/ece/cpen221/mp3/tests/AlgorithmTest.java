package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AlgorithmTest {
    AdjacencyListGraph L1;
    AdjacencyMatrixGraph M1;
    AdjacencyListGraph L2;
    AdjacencyMatrixGraph M2;
    AdjacencyListGraph L3;
    AdjacencyMatrixGraph M3;
    AdjacencyListGraph L4;
    AdjacencyMatrixGraph M4;

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
    Vertex k = new Vertex("k");

    /*
     * Initializes graphs that will be used in the test. L1 and M1 will be used
     * for testing ShortestDistance() L2, L3, L4, M2, M3, M4 will be used to
     * test BFS, DFS, Upstream, and Downstream methods
     */
    @Before
    public void setupGraph() {
        L1 = new AdjacencyListGraph();
        L1.addVertex(a);
        L1.addVertex(b);
        L1.addVertex(c);
        L1.addVertex(d);
        L1.addVertex(e);
        L1.addVertex(f);
        L1.addVertex(g);
        L1.addVertex(h);
        L1.addVertex(i);
        L1.addVertex(j);
        L1.addEdge(a, a);
        L1.addEdge(a, b);
        L1.addEdge(b, c);
        L1.addEdge(c, e);
        L1.addEdge(c, f);
        L1.addEdge(d, i);
        L1.addEdge(e, c);
        L1.addEdge(i, j);
        L1.addEdge(j, e);
        L1.addEdge(j, a);

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

        L2 = new AdjacencyListGraph();
        L2.addVertex(a);
        L2.addVertex(b);
        L2.addVertex(c);
        L2.addVertex(d);
        L2.addVertex(e);
        L2.addVertex(f);
        L2.addVertex(g);
        L2.addVertex(h);
        L2.addVertex(i);
        L2.addVertex(j);
        L2.addVertex(k);
        L2.addEdge(a, a);
        L2.addEdge(a, b);
        L2.addEdge(a, c);
        L2.addEdge(c, d);
        L2.addEdge(c, f);
        L2.addEdge(c, i);
        L2.addEdge(d, b);
        L2.addEdge(d, i);
        L2.addEdge(e, e);
        L2.addEdge(f, a);
        L2.addEdge(h, g);
        L2.addEdge(j, a);

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
        M2.addVertex(k);
        M2.addEdge(a, a);
        M2.addEdge(a, b);
        M2.addEdge(a, c);
        M2.addEdge(c, d);
        M2.addEdge(c, f);
        M2.addEdge(c, i);
        M2.addEdge(d, b);
        M2.addEdge(d, i);
        M2.addEdge(e, e);
        M2.addEdge(f, a);
        M2.addEdge(h, g);
        M2.addEdge(j, a);

        // list and matrix with no edges
        L3 = new AdjacencyListGraph();
        L3.addVertex(a);
        L3.addVertex(b);
        L3.addVertex(c);
        L3.addVertex(d);

        M3 = new AdjacencyMatrixGraph();
        M3.addVertex(a);
        M3.addVertex(b);
        M3.addVertex(c);
        M3.addVertex(d);

        // empty list and matrix
        L4 = new AdjacencyListGraph();

        M4 = new AdjacencyMatrixGraph();
    }

    /*
     * Tests shortestDistance method
     */
    @Test
    public void testShortestDistance() {

        // for AdjacencyListGraph
        assertEquals((Algorithms.shortestDistance(L1, a, e)), 3);
        assertEquals((Algorithms.shortestDistance(L1, a, f)), 3);
        assertEquals((Algorithms.shortestDistance(L1, a, a)), 1);
        assertEquals((Algorithms.shortestDistance(L1, d, j)), 2);
        assertEquals((Algorithms.shortestDistance(L1, a, j)), -1);
        assertEquals((Algorithms.shortestDistance(L1, e, d)), -1);
        assertEquals((Algorithms.shortestDistance(L1, j, j)), -1);

        // for AdjacencyMatrixGraph
        assertEquals((Algorithms.shortestDistance(M1, a, e)), 3);
        assertEquals((Algorithms.shortestDistance(M1, a, f)), 3);
        assertEquals((Algorithms.shortestDistance(M1, a, a)), 1);
        assertEquals((Algorithms.shortestDistance(M1, d, j)), 2);
        assertEquals((Algorithms.shortestDistance(M1, a, j)), -1);
        assertEquals((Algorithms.shortestDistance(M1, e, d)), -1);
        assertEquals((Algorithms.shortestDistance(M1, j, j)), -1);
    }

    /*
     * Tests breadthFirstSearch method
     */
    @Test
    public void testBreadthFirstSearch() {
        // initialize HashSet of lists for testing
        HashSet<List<Vertex>> listBreadthFirstSearch2 = new HashSet<List<Vertex>>();
        HashSet<List<Vertex>> listBreadthFirstSearch3 = new HashSet<List<Vertex>>();

        List<Vertex> arrayA2 = Arrays.asList(a, b, c, d, f, i);
        List<Vertex> arrayB2 = Arrays.asList(b);
        List<Vertex> arrayC2 = Arrays.asList(c, d, f, i, b, a);
        List<Vertex> arrayD2 = Arrays.asList(d, b, i);
        List<Vertex> arrayE2 = Arrays.asList(e);
        List<Vertex> arrayF2 = Arrays.asList(f, a, b, c, d, i);
        List<Vertex> arrayG2 = Arrays.asList(g);
        List<Vertex> arrayH2 = Arrays.asList(h, g);
        List<Vertex> arrayI2 = Arrays.asList(i);
        List<Vertex> arrayJ2 = Arrays.asList(j, a, b, c, d, f, i);
        List<Vertex> arrayK2 = Arrays.asList(k);
        listBreadthFirstSearch2.add(arrayA2);
        listBreadthFirstSearch2.add(arrayB2);
        listBreadthFirstSearch2.add(arrayC2);
        listBreadthFirstSearch2.add(arrayD2);
        listBreadthFirstSearch2.add(arrayE2);
        listBreadthFirstSearch2.add(arrayF2);
        listBreadthFirstSearch2.add(arrayG2);
        listBreadthFirstSearch2.add(arrayH2);
        listBreadthFirstSearch2.add(arrayI2);
        listBreadthFirstSearch2.add(arrayJ2);
        listBreadthFirstSearch2.add(arrayK2);

        List<Vertex> arrayA3 = Arrays.asList(a);
        List<Vertex> arrayB3 = Arrays.asList(b);
        List<Vertex> arrayC3 = Arrays.asList(c);
        List<Vertex> arrayD3 = Arrays.asList(d);
        listBreadthFirstSearch3.add(arrayA3);
        listBreadthFirstSearch3.add(arrayB3);
        listBreadthFirstSearch3.add(arrayC3);
        listBreadthFirstSearch3.add(arrayD3);

        // test using L2 and M2 (not empty and containing edges)
        assertEquals(listBreadthFirstSearch2, Algorithms.breadthFirstSearch(L2));
        assertEquals(listBreadthFirstSearch2, Algorithms.breadthFirstSearch(M2));

        // test using L3 and M3 (not empty and not containing edges)
        assertEquals(listBreadthFirstSearch3, Algorithms.breadthFirstSearch(L3));
        assertEquals(listBreadthFirstSearch3, Algorithms.breadthFirstSearch(M3));

        // test using L4 and M4 (empty)
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(L4));
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(M4));

        // test for consistency between list and graph implementations
        assertEquals(Algorithms.breadthFirstSearch(L2), Algorithms.breadthFirstSearch(M2));
    }

    /*
     * Tests depthFirstSearch method
     */
    @Test
    public void testDepthFirstSearch() {
        // initialize HashSet of lists for testing
        HashSet<List<Vertex>> listDepthFirstSearch2 = new HashSet<List<Vertex>>();
        HashSet<List<Vertex>> listDepthFirstSearch3 = new HashSet<List<Vertex>>();

        List<Vertex> arrayA2 = Arrays.asList(a, c, i, f, d, b);
        List<Vertex> arrayB2 = Arrays.asList(b);
        List<Vertex> arrayC2 = Arrays.asList(c, i, f, a, b, d);
        List<Vertex> arrayD2 = Arrays.asList(d, i, b);
        List<Vertex> arrayE2 = Arrays.asList(e);
        List<Vertex> arrayF2 = Arrays.asList(f, a, c, i, d, b);
        List<Vertex> arrayG2 = Arrays.asList(g);
        List<Vertex> arrayH2 = Arrays.asList(h, g);
        List<Vertex> arrayI2 = Arrays.asList(i);
        List<Vertex> arrayJ2 = Arrays.asList(j, a, c, i, f, d, b);
        List<Vertex> arrayK2 = Arrays.asList(k);
        listDepthFirstSearch2.add(arrayA2);
        listDepthFirstSearch2.add(arrayB2);
        listDepthFirstSearch2.add(arrayC2);
        listDepthFirstSearch2.add(arrayD2);
        listDepthFirstSearch2.add(arrayE2);
        listDepthFirstSearch2.add(arrayF2);
        listDepthFirstSearch2.add(arrayG2);
        listDepthFirstSearch2.add(arrayH2);
        listDepthFirstSearch2.add(arrayI2);
        listDepthFirstSearch2.add(arrayJ2);
        listDepthFirstSearch2.add(arrayK2);

        List<Vertex> arrayA3 = Arrays.asList(a);
        List<Vertex> arrayB3 = Arrays.asList(b);
        List<Vertex> arrayC3 = Arrays.asList(c);
        List<Vertex> arrayD3 = Arrays.asList(d);
        listDepthFirstSearch3.add(arrayA3);
        listDepthFirstSearch3.add(arrayB3);
        listDepthFirstSearch3.add(arrayC3);
        listDepthFirstSearch3.add(arrayD3);

        // test using L2 and M2 (not empty and containing edges)
        assertEquals(listDepthFirstSearch2, Algorithms.depthFirstSearch(L2));
        assertEquals(listDepthFirstSearch2, Algorithms.depthFirstSearch(M2));

        // test using L3 and M3 (not empty and not containing edges)
        assertEquals(listDepthFirstSearch3, Algorithms.depthFirstSearch(L3));
        assertEquals(listDepthFirstSearch3, Algorithms.depthFirstSearch(M3));

        // test using L4 and M4 (empty)
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(L4));
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(M4));

        // test for consistency between list and graph implementations
        assertEquals(Algorithms.depthFirstSearch(L2), Algorithms.depthFirstSearch(M2));
    }

    /*
     * Tests commonUpstreamVertices method
     */
    @Test
    public void testCommonUpstreamVertices() {

        // test using L2 and M2 (not empty and containing edges)
        assertEquals(new ArrayList<Vertex>(Arrays.asList(a)), Algorithms.commonUpstreamVertices(L2, b, c));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(L2, c, d));

        // test using L3 and M3 (not empty and not containing edges)
        assertEquals(new ArrayList<Vertex>(Arrays.asList(a)), Algorithms.commonUpstreamVertices(M2, b, c));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(M2, c, d));

        // test using L4 and M4 (empty)
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(M4, a, b));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(L4, a, b));
    }

    /*
     * Tests commonDownstreamVertices method
     */
    @Test
    public void testCommonDownstreamVertices() {

        // test using L2 and M2 (not empty and containing edges)
        assertEquals(new ArrayList<Vertex>(Arrays.asList(b)), Algorithms.commonDownstreamVertices(L2, a, d));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(L2, a, b));

        // test using L3 and M3 (not empty and not containing edges)
        assertEquals(new ArrayList<Vertex>(Arrays.asList(b)), Algorithms.commonDownstreamVertices(M2, a, d));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(M2, a, b));

        // test using L4 and M4 (empty)
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(M4, a, b));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(L4, a, b));
    }

}
