package ca.ubc.ece.cpen221.mp3.tests;
import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixGraphTest {
	AdjacencyListGraph G;
	Vertex a = new Vertex("a");
	Vertex b = new Vertex("b");
	Vertex c = new Vertex("c");
	Vertex d = new Vertex("d");
	Vertex e = new Vertex("e");
	
	@Before
	public void setupGraph(){
		G = new AdjacencyListGraph();
		G.addVertex(a);
		G.addVertex(b);
		G.addVertex(c);
		G.addVertex(d);
		G.addVertex(e);
	}
	@Test
	public void test() {
		
		G.addEdge(a, b);
		List<Vertex> testDownstream = new ArrayList<Vertex>();
		testDownstream.add(b);
	
		assertEquals(testDownstream, G.getDownstreamNeighbors(a));
	}
	@Test
	public void testAddEdge2() {
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

}
