/**
 * 
 */
package ca.ubc.ece.cpen221.mp3.tests;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Test.*;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * @author Henry Liu
 *
 */
public class ListGraphTest {
	static AdjacencyListGraph G = new AdjacencyListGraph();
	static Vertex a = new Vertex("a");
	static Vertex b = new Vertex("b");
	static Vertex c = new Vertex("c");
	static Vertex d = new Vertex("d");
	static Vertex e = new Vertex("e");
	
	@BeforeClass
	public static void setupGraph(){
		G.addVertex(a);
		G.addVertex(b);
		G.addVertex(c);
		G.addVertex(d);
		G.addVertex(e);
	}


	@Test
	public void testAddEdge() {
		
		G.addEdge(a, b);
		List<Vertex> testDownstream = new ArrayList<Vertex>();
		testDownstream.add(b);
		
	
		assertEquals(testDownstream, G.getDownstreamNeighbors(a));
		
	}
	
	@Test
	public void testAddEdge2() {
		
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

}
