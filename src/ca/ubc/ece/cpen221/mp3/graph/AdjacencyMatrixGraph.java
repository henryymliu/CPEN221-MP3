package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
    // stores true and false values for determining edge existence
	List<List<Boolean>> matrix = new LinkedList<List<Boolean>>();
	// matrixIndex stores all the vertices
	List<Vertex> verticesInMatrix = new LinkedList<Vertex>();

	private int indexRow = 0;
	private int indexColumn = 0;

	/**
	 * Adds a vertex to the graph.
	 *
	 * Precondition: v is not already a vertex in the graph
	 */
	public void addVertex(Vertex v) {
		List<Boolean> newColumn = new LinkedList<Boolean>();
		// initialize the column to the same size as the other columns
		for (int i = 0; i < verticesInMatrix.size(); i++) {
			newColumn.add(false);
		}
		matrix.add(newColumn);
		verticesInMatrix.add(v);
		// expand each column by adding new false value to it
		for (List<Boolean> column : matrix) {
			column.add(false);
			//System.out.println(col.size());
		}
	}

	/**
	 * Adds an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		indexRow = verticesInMatrix.indexOf(v1);
		indexColumn = verticesInMatrix.indexOf(v2);
		matrix.get(indexRow).set(indexColumn, true);

	}

	/**
	 * Check if there is an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph Postcondition: return
	 * true iff an edge from v1 connects to v2
	 */
	public boolean edgeExists(Vertex v1, Vertex v2) {
		indexRow = verticesInMatrix.indexOf(v1);
		indexColumn = verticesInMatrix.indexOf(v2);
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
		ArrayList<Vertex> downStreamNeighbors = new ArrayList<Vertex>();
		indexRow = verticesInMatrix.indexOf(v);
		// iterate through vertices and checks if they are downstream neighbors
		for (int i = 0; i < matrix.size(); i++) {
			if (matrix.get(indexRow).get(i)) {
				downStreamNeighbors.add(verticesInMatrix.get(i));
			}
		}

		return downStreamNeighbors;

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
		List<Vertex> upStreamNeighbors = new LinkedList<Vertex>();
		int rowIndex = verticesInMatrix.indexOf(v);
		// iterate along a single row corresponding to Vertex v of the matrix
		for (int i = 0; i < verticesInMatrix.size(); i++) {

			if (matrix.get(i).get(rowIndex)) {
				upStreamNeighbors.add(verticesInMatrix.get(i));
			}

		}
		return upStreamNeighbors;
	}

	/**
	 * Get all vertices in the graph.
	 *
	 * Postcondition: returns a list containing all vertices in the graph. This
	 * method should return a list of size 0 iff the graph has no vertices.
	 */
	public List<Vertex> getVertices() {
		return new LinkedList<Vertex>(verticesInMatrix);
	}

}
