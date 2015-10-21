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
	 * Traverses graph using depth-first search algorithm, returning set of lists of
	 * vertices in order traversed, with set containing all possible traversals
	 * 
	 * @param graph
	 *            graph of vertices with their edges
	 * @param v
	 *            vertex in graph to start traversing on
	 * @return List of vertices in the order traversed
	 */
	public static Set<List<Vertex>> DepthFirstSearch(Graph graph) {
		HashMap<Vertex, Boolean> discoveredVertices = new LinkedHashMap<Vertex, Boolean>();
		List<Vertex> vertices = new ArrayList<Vertex>(graph.getVertices());
		for (Vertex v : vertices) {
			discoveredVertices.put(v, false);
		}
		
		Set<List<Vertex>> paths = new HashSet<List<Vertex>>();
		//List<Vertex> traversedPath = new LinkedList<Vertex>();
		Stack<Vertex> vertexStack = new Stack<Vertex>();

		for (Vertex nextV : vertices) {
			/*
			 * reset map of discovered vertices and create new path
			 */
			for (Vertex v : vertices) {
				discoveredVertices.put(v, false);
			}
			List<Vertex> traversedPath = new LinkedList<Vertex>();
			
			
			vertexStack.push(nextV); //starting value for new path
			
			/**
			 * this is the actual DFS algorithm (non-recursive)
			 */
			while (!vertexStack.isEmpty()) {
				
				Vertex n = vertexStack.pop();
				
				if (!discoveredVertices.get(n)) {
					// discoveredVertices.put(n, true);
					discoveredVertices.put(n, true);
					traversedPath.add(n);
					for (Vertex neighbour : graph.getDownstreamNeighbors(n)) {
						vertexStack.push(neighbour);

					}
				}
			}
			
			paths.add(traversedPath);
			
		}
	
		return new HashSet<List<Vertex>>(paths);
	}

}
