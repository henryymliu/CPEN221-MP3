package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {

	List<List<Boolean>> matrix = new LinkedList<List<Boolean>>();
	List<Vertex> matrixIndex = new LinkedList<Vertex>();

	private int indexRow = 0;
	private int indexColumn = 0;

	/**
	 * Adds a vertex to the graph.
	 *
	 * Precondition: v is not already a vertex in the graph
	 */
	public void addVertex(Vertex v) {
		List<Boolean> newCol = new LinkedList<Boolean>();
		for (int i = 0; i < matrixIndex.size(); i++) {
			newCol.add(false);
		}
		matrix.add(newCol);
		matrixIndex.add(v);
		for (List<Boolean> col : matrix) {
			col.add(false);
			//System.out.println(col.size());
		}
	}

	/**
	 * Adds an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		indexRow = matrixIndex.indexOf(v1);
		indexColumn = matrixIndex.indexOf(v2);
		matrix.get(indexRow).set(indexColumn, true);

	}

	/**
	 * Check if there is an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph Postcondition: return
	 * true iff an edge from v1 connects to v2
	 */
	public boolean edgeExists(Vertex v1, Vertex v2) {
		indexRow = matrixIndex.indexOf(v1);
		indexColumn = matrixIndex.indexOf(v2);
		return (matrix.get(indexRow).get(indexColumn));

	}

	/**
	 * Get an array containing all downstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex w such that there is
	 * an edge from v to w. The size of the list must be as small as possible
	 * (No trailing null elements). This method should return a list of size 0
	 * iff v has no downstream neighbors.
	 */
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		ArrayList<Vertex> downStream = new ArrayList<Vertex>();
		indexRow = matrixIndex.indexOf(v);
		for (int i = 0; i < matrix.size(); i++) {
			if (matrix.get(indexRow).get(i)) {
				downStream.add(matrixIndex.get(i));
			}
		}

		return downStream;

	}

	/**
	 * Get an array containing all upstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex u such that there is
	 * an edge from u to v. The size of the list must be as small as possible
	 * (No trailing null elements). This method should return a list of size 0
	 * iff v has no upstream neighbors.
	 */
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		List<Vertex> upStream = new LinkedList<Vertex>();
		int rowIndex = matrixIndex.indexOf(v);
		for (int i = 0; i < matrixIndex.size(); i++) {

			if (matrix.get(i).get(rowIndex)) {
				upStream.add(matrixIndex.get(i));
			}

		}
		return upStream;
	}

	/**
	 * Get all vertices in the graph.
	 *
	 * Postcondition: returns a list containing all vertices in the graph. This
	 * method should return a list of size 0 iff the graph has no vertices.
	 */
	public List<Vertex> getVertices() {
		return new LinkedList<Vertex>(matrixIndex);
	}

}
