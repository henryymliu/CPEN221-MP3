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
	public static Set<List<Vertex>> DepthFirstSearch(Graph graph) {
		HashMap<Vertex, Boolean> discoveredVertices = new LinkedHashMap<Vertex, Boolean>();
		List<Vertex> vertices = new ArrayList<Vertex>(graph.getVertices());
		for(Vertex v: vertices){
			discoveredVertices.put(v, false);
		}
		Set<List<Vertex>> paths = new HashSet<List<Vertex>>();
		Stack<Vertex> vertexStack = new Stack<Vertex>();
		

		while (!vertexStack.isEmpty()) {
			Vertex n = vertexStack.pop();
			if (!discoveredVertices.get(n)) {
				//discoveredVertices.put(n, true);
				discoveredVertices.put(n, true);
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
		return new HashSet<List<Vertex>>(paths);
	}

}
