package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Before;
import org.junit.BeforeClass;
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
	
	@Before
	public void setupGraph(){
		G = new AdjacencyListGraph();
		G.addVertex(a);
		G.addVertex(b);
		G.addVertex(c);
		G.addVertex(d);
		G.addVertex(e);
		G.addEdge(a, b);
		G.addEdge(a, c);
		G.addEdge(a, e);
		G.addEdge(e, a);
		G.addEdge(e, d);
		G.addEdge(d, a);
	}
	

	@Test
	public void test() {
		//assertEquals(new HashSet<List<Vertex>>(), Algorithms.DepthFirstSearch(G));
		assertEquals(new HashSet<List<Vertex>>(), Algorithms.breadthFirstSearch(G));
	}

}
