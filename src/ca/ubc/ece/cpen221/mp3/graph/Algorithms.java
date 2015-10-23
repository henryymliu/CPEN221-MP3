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
        HashSet<Vertex> visitedSet = new HashSet<Vertex>();
        Queue<Vertex> currentQueue = new LinkedList<Vertex>();
        Queue<Vertex> nextQueue = new LinkedList<Vertex>();
        Vertex nextVertex;
        int depth = 0;

        for (Vertex eachVertex : graph.getDownstreamNeighbors(a)) {
            nextQueue.add(eachVertex);
            visitedSet.add(eachVertex);
            System.out.println(eachVertex);
        }

        while (!nextQueue.isEmpty()) {
            depth++;
            currentQueue = new LinkedList<Vertex>(nextQueue);
            while (!nextQueue.isEmpty()) {
                nextQueue.remove();
            }
            while (!currentQueue.isEmpty()) {
                nextVertex = currentQueue.poll();
                
                System.out.println(nextVertex);
                if (nextVertex.equals(b)) {
                    System.out.println("1");
                    return depth;
                }
                nextQueue.addAll(graph.getDownstreamNeighbors(nextVertex));
                if (!visitedSet.contains(nextVertex)) {
                    visitedSet.add(nextVertex);
                    if (nextVertex.equals(b)) {
                        System.out.println("5");
                        return depth;
                    }
                }
                
            }
            currentQueue = nextQueue;
            System.out.println("6");
        }
        return -1;
    }

    public static HashSet<List<Vertex>> breadthFirstSearch(Graph graph) {
        List<Vertex> allVertices = graph.getVertices();
        Set<List<Vertex>> paths = new HashSet<List<Vertex>>();

        for (Vertex nextV : allVertices) {
            Queue<Vertex> vertexQueue = new LinkedList<Vertex>();
            ArrayList<Vertex> edgeVertex = new ArrayList<Vertex>();

            Vertex currentVertex = nextV;
            vertexQueue.add(currentVertex);
            edgeVertex.add(currentVertex);

            while (!vertexQueue.isEmpty()) {
                for (int i = 0; i < allVertices.size(); i++) {
                    if (graph.edgeExists(currentVertex, allVertices.get(i))) {
                        if (!edgeVertex.contains(allVertices.get(i))) {
                            vertexQueue.add(allVertices.get(i));
                            edgeVertex.add(allVertices.get(i));
                        }
                    }
                }
                vertexQueue.remove();
                currentVertex = vertexQueue.peek();
            }
            paths.add(edgeVertex);

        }
        return new HashSet<List<Vertex>>(paths);
    }

    /**
     * Traverses graph using depth-first search algorithm, returning set of
     * lists of vertices in order traversed, with set containing all possible
     * traversals
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
        // List<Vertex> traversedPath = new LinkedList<Vertex>();
        Stack<Vertex> vertexStack = new Stack<Vertex>();

        for (Vertex nextV : vertices) {
            /*
             * reset map of discovered vertices and create new path
             */
            for (Vertex v : vertices) {
                discoveredVertices.put(v, false);
            }
            List<Vertex> traversedPath = new LinkedList<Vertex>();

            vertexStack.push(nextV); // starting value for new path

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

    public static List<Vertex> upstreamVertices(Graph graph, Vertex a, Vertex b) {
        List<Vertex> allVertices = graph.getVertices();
        List<Vertex> vertexAUpstream = graph.getUpstreamNeighbors(a);
        List<Vertex> vertexBUpstream = graph.getUpstreamNeighbors(b);
        ArrayList<Vertex> edgeVertex = new ArrayList<Vertex>();

        for (int i = 0; i < allVertices.size(); i++) {
            for (int k = 0; k < allVertices.size(); k++) {
                if (vertexAUpstream.get(i) == vertexBUpstream.get(k)) {
                    edgeVertex.add(vertexAUpstream.get(i));
                }
            }
        }

        return edgeVertex;
    }

    public static List<Vertex> downstreamVertices(Graph graph, Vertex a, Vertex b) {
        List<Vertex> allVertices = graph.getVertices();
        List<Vertex> vertexADownstream = graph.getDownstreamNeighbors(a);
        List<Vertex> vertexBDownstream = graph.getDownstreamNeighbors(b);
        ArrayList<Vertex> edgeVertex = new ArrayList<Vertex>();

        for (int i = 0; i < allVertices.size(); i++) {
            for (int k = 0; k < allVertices.size(); k++) {
                if (vertexADownstream.get(i) == vertexBDownstream.get(k)) {
                    edgeVertex.add(vertexADownstream.get(i));
                }
            }
        }

        return edgeVertex;
    }

}
