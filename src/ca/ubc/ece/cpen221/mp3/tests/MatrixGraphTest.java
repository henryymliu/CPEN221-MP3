package ca.ubc.ece.cpen221.mp3.tests;
import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MatrixGraphTest {
	AdjacencyMatrixGraph G = new AdjacencyMatrixGraph();
	Vertex a = new Vertex("a");
	Vertex b = new Vertex("b");
	Vertex c = new Vertex("c");
	Vertex d = new Vertex("d");
	Vertex e = new Vertex("e");
	@Test
	public void test() {
		G.addVertex(a);
		G.addVertex(b);
		G.addVertex(c);
		G.addVertex(d);
		G.addVertex(e);
		
		//G.addEdge(a, b);
		List<Vertex> testDownstream = new ArrayList<Vertex>();
		testDownstream.add(b);
	
		//assertEquals(testDownstream, G.getDownstreamNeighbors(a));
	}

}
