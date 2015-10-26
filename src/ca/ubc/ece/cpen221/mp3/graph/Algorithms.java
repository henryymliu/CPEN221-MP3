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
	 * Returns shortest traversal distance between vertices a and b.
	 * @requires a and b exist in graph
	 * @param graph graph that has already been generated
	 * @param a starting vertex
	 * @param b vertex to find connection to from a
	 * @return shortest distance traversed from a to b, -1 if path not found
	 */
    public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
        HashSet<Vertex> visitedSet = new HashSet<Vertex>();
        Queue<Vertex> currentQueue = new LinkedList<Vertex>();
        Queue<Vertex> nextQueue = new LinkedList<Vertex>();
        Vertex nextVertex;
        int depth = 1;

        for (Vertex eachVertex : graph.getDownstreamNeighbors(a)) {
            nextQueue.add(eachVertex);
            //System.out.println(eachVertex);
        }

        while (!nextQueue.isEmpty()) {
            
            currentQueue = new LinkedList<Vertex>(nextQueue);
            nextQueue = new LinkedList<Vertex>();
            
            while (!currentQueue.isEmpty()) {
                nextVertex = currentQueue.poll();
                
                //System.out.println(nextVertex);
                if (nextVertex.equals(b)) {
                    //System.out.println(depth);
                    return depth;
                }
                
                //if vertex not traversed, marked as visited, and add its downstream neighbours
                if (!visitedSet.contains(nextVertex)) {
                    visitedSet.add(nextVertex);
                    nextQueue.addAll(graph.getDownstreamNeighbors(nextVertex));
                }
                
            }
          
            depth++;
           // System.out.println(depth);
        }
        return -1;
    }
    
    /**
     * Performs BFS starting from every vertex in an unweighted directed graph
     * 
     * @param graph graph that already has vertices with edges
     * @return set of all BFS traversals beginning at each vertex
     */
    public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
        List<Vertex> allVertices = graph.getVertices();
        Set<List<Vertex>> paths = new HashSet<List<Vertex>>();
        
        //iterate through all vertices
        for (Vertex nextV : allVertices) {
            Queue<Vertex> vertexQueue = new LinkedList<Vertex>();
            ArrayList<Vertex> traversedVertices = new ArrayList<Vertex>();

            //enqueue first vertex
            Vertex currentVertex = nextV;
            vertexQueue.add(currentVertex);

            while (!vertexQueue.isEmpty()) {
            	currentVertex = vertexQueue.poll();
            	
            	//if vertex has not been traversed yet, add it to traversed, 
            	//and enqueue its downstream vertices
            	if(!traversedVertices.contains(currentVertex)){
            		traversedVertices.add(currentVertex);
            		vertexQueue.addAll(graph.getDownstreamNeighbors(currentVertex));
            	}
              
            }
            paths.add(traversedVertices);

        }
        return new HashSet<List<Vertex>>(paths);
    }

    /**
     * Traverses graph using depth-first search algorithm, returning set of
     * lists of vertices in order traversed, with set containing all possible
     * traversals starting from each vertex
     * 
     * @param graph
     *            graph of vertices with their edges
     * @param v
     *            vertex in graph to start traversing on
     * @return List of vertices in the order traversed
     */
    public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
       
        List<Vertex> vertices = new ArrayList<Vertex>(graph.getVertices());
        Set<List<Vertex>> paths = new HashSet<List<Vertex>>();
        Stack<Vertex> vertexStack = new Stack<Vertex>();

        for (Vertex nextV : vertices) {
            
            List<Vertex> traversedPath = new LinkedList<Vertex>();

            vertexStack.push(nextV); // starting value for new path

            /**
             * this is the actual DFS algorithm (non-recursive)
             */
            while (!vertexStack.isEmpty()) {

                Vertex n = vertexStack.pop();

                if (!traversedPath.contains(n)) {
                 
                    traversedPath.add(n);
                    vertexStack.addAll(graph.getDownstreamNeighbors(n));
                   
                }
            }

            paths.add(traversedPath);

        }

        return new HashSet<List<Vertex>>(paths);
    }

    /**
     * Returns common upstream vertices of vertices a and b.
     * 
     * @param graph that has been initialized with vertices and edges
     * @param a vertex in graph
     * @param b vertex in graph
     * @return list of vertices that are shared upstream by a and b
     */
    public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
 
        List<Vertex> vertexAUpstream = graph.getUpstreamNeighbors(a);
        List<Vertex> vertexBUpstream = graph.getUpstreamNeighbors(b);
        ArrayList<Vertex> edgeVertex = new ArrayList<Vertex>();

        for (Vertex i: vertexAUpstream) {
            for (Vertex k: vertexBUpstream) {
                if (i.equals(k)) {
                    edgeVertex.add(i);
                }
            }
        }

        return edgeVertex;
    }
    
    /**
     * Returns common downstream vertices of vertices a and b.
     * 
     * @param graph that has been initialized with vertices and edges
     * @param a vertex in graph
     * @param b vertex in graph
     * @return list of vertices that are shared downstream by a and b
     */
    public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {

        List<Vertex> vertexADownstream = graph.getDownstreamNeighbors(a);
        List<Vertex> vertexBDownstream = graph.getDownstreamNeighbors(b);
        
        ArrayList<Vertex> edgeVertex = new ArrayList<Vertex>();

        for (Vertex i: vertexADownstream) {
            for (Vertex k: vertexBDownstream) {
                if (i.equals(k)) {
                    edgeVertex.add(i);
                }
            }
        }

        return edgeVertex;
    }

}
