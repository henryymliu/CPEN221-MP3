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
    AdjacencyListGraph G1;
    AdjacencyMatrixGraph M1;
    AdjacencyListGraph G2;
    AdjacencyMatrixGraph M2;
    AdjacencyListGraph G3;
    AdjacencyMatrixGraph M3;
    AdjacencyListGraph G4;
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
        
        G3 = new AdjacencyListGraph();
        G3.addVertex(a);
        G3.addVertex(b);
        G3.addVertex(c);
        G3.addVertex(d);
        G3.addVertex(e);
        G3.addVertex(f);
        G3.addVertex(g);
        G3.addVertex(h);
        G3.addVertex(i);
        G3.addVertex(j);
        
        M3 = new AdjacencyMatrixGraph();
        M3.addVertex(a);
        M3.addVertex(b);
        M3.addVertex(c);
        M3.addVertex(d);
        M3.addVertex(e);
        M3.addVertex(f);
        M3.addVertex(g);
        M3.addVertex(h);
        M3.addVertex(i);
        M3.addVertex(j);
        
        G4 = new AdjacencyListGraph();
        
        M4 = new AdjacencyMatrixGraph();
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
        HashSet<List<Vertex>> listBreadthFirstSearch2 = new HashSet<List<Vertex>>();
        HashSet<List<Vertex>> listBreadthFirstSearch3 = new HashSet<List<Vertex>>();
        List<Vertex> arrayA2 = Arrays.asList(a,c,f,e,h,i);
        List<Vertex> arrayB2 = Arrays.asList(b,c,e);
        List<Vertex> arrayC2 = Arrays.asList(c,e);
        List<Vertex> arrayD2 = Arrays.asList(d,c,e);
        List<Vertex> arrayE2 = Arrays.asList(e);
        List<Vertex> arrayF2 = Arrays.asList(f,h,i);
        List<Vertex> arrayG2 = Arrays.asList(g,j);
        List<Vertex> arrayH2 = Arrays.asList(h);
        List<Vertex> arrayI2 = Arrays.asList(i);
        List<Vertex> arrayJ2 = Arrays.asList(j);
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
        
        List<Vertex> arrayA3 = Arrays.asList(a);
        List<Vertex> arrayB3 = Arrays.asList(b);
        List<Vertex> arrayC3 = Arrays.asList(c);
        List<Vertex> arrayD3 = Arrays.asList(d);
        List<Vertex> arrayE3 = Arrays.asList(e);
        List<Vertex> arrayF3 = Arrays.asList(f);
        List<Vertex> arrayG3 = Arrays.asList(g);
        List<Vertex> arrayH3 = Arrays.asList(h);
        List<Vertex> arrayI3 = Arrays.asList(i);
        List<Vertex> arrayJ3 = Arrays.asList(j);
        listBreadthFirstSearch3.add(arrayA3);
        listBreadthFirstSearch3.add(arrayB3);
        listBreadthFirstSearch3.add(arrayC3);
        listBreadthFirstSearch3.add(arrayD3);
        listBreadthFirstSearch3.add(arrayE3);
        listBreadthFirstSearch3.add(arrayF3);
        listBreadthFirstSearch3.add(arrayG3);
        listBreadthFirstSearch3.add(arrayH3);
        listBreadthFirstSearch3.add(arrayI3);
        listBreadthFirstSearch3.add(arrayJ3);
        
        assertEquals(listBreadthFirstSearch2, Algorithms.breadthFirstSearch(G2));
        assertEquals(listBreadthFirstSearch2, Algorithms.breadthFirstSearch(M2));
        
        assertEquals(listBreadthFirstSearch3, Algorithms.breadthFirstSearch(G3));
        assertEquals(listBreadthFirstSearch3, Algorithms.breadthFirstSearch(M3));
        
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(G4));
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(M4));
        
        
        assertEquals(Algorithms.breadthFirstSearch(G2), Algorithms.breadthFirstSearch(M2));
    }

    @Test
    public void testDepthFirstSearch() {
        HashSet<List<Vertex>> listDepthFirstSearch2 = new HashSet<List<Vertex>>();
        HashSet<List<Vertex>> listDepthFirstSearch3 = new HashSet<List<Vertex>>();
        List<Vertex> arrayA2 = Arrays.asList(a,f,i,h,c,e);
        List<Vertex> arrayB2 = Arrays.asList(b,c,e);
        List<Vertex> arrayC2 = Arrays.asList(c,e);
        List<Vertex> arrayD2 = Arrays.asList(d,c,e);
        List<Vertex> arrayE2 = Arrays.asList(e);
        List<Vertex> arrayF2 = Arrays.asList(f,i,h);
        List<Vertex> arrayG2 = Arrays.asList(g,j);
        List<Vertex> arrayH2 = Arrays.asList(h);
        List<Vertex> arrayI2 = Arrays.asList(i);
        List<Vertex> arrayJ2 = Arrays.asList(j);    
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
        
        List<Vertex> arrayA3 = Arrays.asList(a);
        List<Vertex> arrayB3 = Arrays.asList(b);
        List<Vertex> arrayC3 = Arrays.asList(c);
        List<Vertex> arrayD3 = Arrays.asList(d);
        List<Vertex> arrayE3 = Arrays.asList(e);
        List<Vertex> arrayF3 = Arrays.asList(f);
        List<Vertex> arrayG3 = Arrays.asList(g);
        List<Vertex> arrayH3 = Arrays.asList(h);
        List<Vertex> arrayI3 = Arrays.asList(i);
        List<Vertex> arrayJ3 = Arrays.asList(j);    
        listDepthFirstSearch3.add(arrayA3);
        listDepthFirstSearch3.add(arrayB3);
        listDepthFirstSearch3.add(arrayC3);
        listDepthFirstSearch3.add(arrayD3);
        listDepthFirstSearch3.add(arrayE3);
        listDepthFirstSearch3.add(arrayF3);
        listDepthFirstSearch3.add(arrayG3);
        listDepthFirstSearch3.add(arrayH3);
        listDepthFirstSearch3.add(arrayI3);
        listDepthFirstSearch3.add(arrayJ3);
        
        assertEquals(listDepthFirstSearch2, Algorithms.depthFirstSearch(G2));
        assertEquals(listDepthFirstSearch2, Algorithms.depthFirstSearch(M2));
        
        assertEquals(listDepthFirstSearch3, Algorithms.depthFirstSearch(G3));
        assertEquals(listDepthFirstSearch3, Algorithms.depthFirstSearch(M3));
        
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(G4));
        assertEquals(new HashSet<List<Vertex>>(), Algorithms.depthFirstSearch(M4));
        
        
        assertEquals(Algorithms.depthFirstSearch(G2), Algorithms.depthFirstSearch(M2));
    }

    @Test
    public void testCommonUpstreamVertices() {
        
        assertEquals(new ArrayList<Vertex>(Arrays.asList(f)), Algorithms.commonUpstreamVertices(G2, h, i));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(G2, b, c));

        
        assertEquals(new ArrayList<Vertex>(Arrays.asList(f)), Algorithms.commonUpstreamVertices(M2, h, i));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(M2, b, c));
        
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(M4, a, b));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonUpstreamVertices(G4, a, b));
    }
    
    public void testCommonDownstreamVertices() {
        
        assertEquals(new ArrayList<Vertex>(Arrays.asList(a)), Algorithms.commonDownstreamVertices(G2, b, c));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(G2, h, i));
        
        
        assertEquals(new ArrayList<Vertex>(Arrays.asList(a)), Algorithms.commonDownstreamVertices(M2, b, c));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(M2, h, i));
        
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(M4, a, b));
        assertEquals(new ArrayList<Vertex>(), Algorithms.commonDownstreamVertices(G4, a, b));
    }

}
