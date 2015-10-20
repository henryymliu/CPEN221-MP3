package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
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

    public static List<Vertex> breadthFirstSearch(Graph graph, Vertex v) {
        List<Vertex> allVertices = graph.getVertices();
        Queue<Vertex> vertexQueue = new LinkedList<Vertex>();
        ArrayList<Vertex> edgeVertex = new ArrayList<Vertex>();

        Vertex currentVertex = v;
        vertexQueue.add(currentVertex);
        edgeVertex.add(currentVertex);

        while (!vertexQueue.isEmpty()) {
            for (int i = 0; i < allVertices.size(); i++) {
                if (graph.edgeExists(currentVertex, allVertices.get(i))) {
                    if (!edgeVertex.contains(allVertices.get(i))){
                    vertexQueue.add(allVertices.get(i));
                    edgeVertex.add(allVertices.get(i));
                    }
                }
            }
            vertexQueue.remove();
            currentVertex = vertexQueue.peek();
        }
        return edgeVertex;
    }
    
    public static List<Vertex> upstreamVertices(Graph graph, Vertex a, Vertex b){
        List<Vertex> allVertices = graph.getVertices();
        List<Vertex> vertexAUpstream = graph.getUpstreamNeighbors(a);
        List<Vertex> vertexBUpstream = graph.getUpstreamNeighbors(b);
        ArrayList<Vertex> edgeVertex = new ArrayList<Vertex>();
        
        for (int i = 0; i < allVertices.size(); i++){
            for (int k = 0; k < allVertices.size(); k++){
                if (vertexAUpstream.get(i) == vertexBUpstream.get(k)){
                    edgeVertex.add(vertexAUpstream.get(i));
                }
            }
        }
        
        return edgeVertex;
    }
    
    public static List<Vertex> downstreamVertices(Graph graph, Vertex a, Vertex b){
        List<Vertex> allVertices = graph.getVertices();
        List<Vertex> vertexADownstream = graph.getDownstreamNeighbors(a);
        List<Vertex> vertexBDownstream = graph.getDownstreamNeighbors(b);
        ArrayList<Vertex> edgeVertex = new ArrayList<Vertex>();
        
        for (int i = 0; i < allVertices.size(); i++){
            for (int k = 0; k < allVertices.size(); k++){
                if (vertexADownstream.get(i) == vertexBDownstream.get(k)){
                    edgeVertex.add(vertexADownstream.get(i));
                }
            }
        }
        
        return edgeVertex;
    }
    
    
}
