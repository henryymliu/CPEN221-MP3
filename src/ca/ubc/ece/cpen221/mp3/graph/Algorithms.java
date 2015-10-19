package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import java.util.*;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 * 
	 * Please see the README for the machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and other
	 * methods should be implemented here.
	 * 
	 * You should write the specs for this and all other methods.
	 * 
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method and others
		return 0;
	}
	
	/**
	 * Traverses graph using depth-first search algorithm, returning
	 * list of vertices in order traversed
	 * @param graph graph of vertices with their edges
	 * @param v vertex in graph to start traversing on
	 * @return List of vertices in the order traversed
	 */
	public static List<Vertex> DepthFirstSearch(Graph graph, Vertex v) {
		//HashMap<Vertex, Boolean> discoveredVertices = new LinkedHashMap<Vertex, Boolean>();
		List<Vertex> traversedVertices = new LinkedList<Vertex>();
		Stack<Vertex> vertexStack = new Stack<Vertex>();
		vertexStack.push(v);

		while (!vertexStack.isEmpty()) {
			Vertex n = vertexStack.pop();
			if (!traversedVertices.contains(n)) {
				//discoveredVertices.put(n, true);
				traversedVertices.add(n);
				for(Vertex neighbour: graph.getDownstreamNeighbors(n)){
					vertexStack.push(neighbour);
					
				}
			}
		}
		/*
		for(Vertex discovered: discoveredVertices.keySet()){
			if(discoveredVertices.get(discovered)){
				traversedVertices.add(discovered);
			}
		}
		*/
		return new LinkedList<Vertex>(traversedVertices);
	}

}
