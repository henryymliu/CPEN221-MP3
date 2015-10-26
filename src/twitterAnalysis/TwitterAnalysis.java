package twitterAnalysis;

import java.io.*;
import java.util.*;
import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.*;

public class TwitterAnalysis {

	public static void main(String[] args) {
		FileInputStream queryStream;
		FileOutputStream outStream;
		FileInputStream twitterStream;

		final int ARGS_SIZE = 2;
		final int ARGS_SIZE_DEBUG = 3;
		
		final int QUERY_INDEX = 0;
		final int OUT_INDEX = 1;
		final int DEBUG_INDEX = 2;

		final String twitterFile;
		
		//if less than 2 input arguments

		if (args.length < ARGS_SIZE) {
			System.out.println("Not enough input arguments.");
			return;
		}
		String queryFile = args[QUERY_INDEX];
		String outFile = args[OUT_INDEX];
		
		//can specify separate data file for graph
		if(args.length  == ARGS_SIZE_DEBUG){
			twitterFile = args[DEBUG_INDEX];
		}
		else{
			twitterFile = "datasets/twitter.txt";
		}

		try {
			
			//try to initialize fileinput/output streams
			queryStream = new FileInputStream(queryFile);
			outStream = new FileOutputStream(outFile);

			twitterStream = new FileInputStream(twitterFile);

		} catch (FileNotFoundException e) {
			System.out.println("One or more files not found. Make sure the file name ends in .txt");
			return;
		}
		//times just used to see how long it takes to generate graph
		long startTime = System.currentTimeMillis();
		Graph g = readTwitterFile(twitterStream);
		
		long stopTime = System.currentTimeMillis();
		//Algorithms.breadthFirstSearch(g);
		//System.out.println("Computation took: " + (stopTime - startTime) + " milliseconds.");
		parseQuery(queryStream, outStream, g);

	}
	/**
	 * Generates AdjacencyListGraph based on the values in the input file.
	 * @requires twitterStream is properly initialized
	 * @param twitterStream initialized input stream for reading twitter file
	 * @return generated graph based on input file
	 */
	private static Graph readTwitterFile(FileInputStream twitterStream) {
		final int U1_INDEX = 0;
		final int U2_INDEX = 1;
		try {
			Graph g = new AdjacencyListGraph();
			BufferedReader twitterReader = new BufferedReader(new InputStreamReader(twitterStream));
			String line;
			while ((line = twitterReader.readLine()) != null) {
				
				//eliminate any unnecessary whitespace
				String[] columns = line.trim().replaceAll("\\s+", "").split("->");
				
				// first column is user 1
				// second column is user 2
				Vertex u1 = new Vertex(columns[U1_INDEX]);
				Vertex u2 = new Vertex(columns[U2_INDEX]);
				//System.out.println(columns[0]+","+columns[1]);
				g.addVertex(u1);
				g.addVertex(u2);
				g.addEdge(u1, u2);
				//System.out.println(line);
			}
			twitterReader.close();
			return g;
		} catch (Exception e) { // if something somehow goes wrong
			throw new RuntimeException(e);
		}
	}

	/**
	 * Reads query file and parses the queries.
	 * 

	 * @requires querystream, outstream, are successfully initialized
	 * @requires g to be already generated
	 * @param queryStream file input stream of query file
	 * @param outStream file output stream of output file
	 * @param g
	 * @effects writes results to output file

	 */
	private static void parseQuery(FileInputStream queryStream, FileOutputStream outStream, Graph g) {

		try {
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(outStream));
			BufferedReader queryReader = new BufferedReader(new InputStreamReader(queryStream));
			
			final int COMMAND_INDEX = 0;
			final int U1_INDEX = 1;
			final int U2_INDEX = 2;
			
			final String QUERY_ENDING = " ?";

			Set<Set<String>> queries = new LinkedHashSet<Set<String>>();

			String line;

			while ((line = queryReader.readLine()) != null) {
				
				//each query set contains two user id strings and a command
				//this also handles duplicate user ids with different commands
				Set<String> query = new LinkedHashSet<String>();
				
				//a bit overkill, but eliminate any unnecessary whitespace if needed and replaces with single space
				String[] columns = line.trim().replaceAll("\\s+", " ").split(" ");
		
				// first column is query
				// second column is user 1
				// third column is user 2
		
				String command = columns[COMMAND_INDEX];
				String id1 = columns[U1_INDEX];
				String id2 = columns[U2_INDEX];
				query.add(id1);
				query.add(id2);
				query.add(command);

				// check if query ends with question mark (with leading space) and query is unique
				if (line.endsWith(QUERY_ENDING) && !queries.contains(query)) {

					queries.add(query);

					Vertex u1 = new Vertex(id1);
					Vertex u2 = new Vertex(id2);
					
					printResults(output, g, u1, u2, command);

				}

			}
			queryReader.close();
			output.close();
			// return new LinkedHashMap<List<Vertex>, String>(queries);

		} catch (Exception e) { // if something goes wrong
			throw new RuntimeException(e);
		}

	}

	/**
	 * Helper method for parseQuery. Writes the results of the queries to the output file.
	 * @param output initialized BufferedWriter for the output file
	 * @param g initialized graph of edges and vertices
	 * @param u1 one vertex used in commands
	 * @param u2 another vertex used in commands
	 * @param command command to execute
	 */

	private static void printResults(BufferedWriter output, Graph g, Vertex u1, Vertex u2, String command) {
		final String commonInfluencers = "commonInfluencers";
		final String numRetweets = "numRetweets";
		
		final String VERTEX_NOT_FOUND_ERROR = "ERROR: One or more vertices does not exist in the graph.";
		final String INVALID_COMMAND_ERROR = "\tError: invalid command ";
		final String PATH_NOT_FOUND_ERROR = "\tPath not found.";
		
		List<Vertex> allVertices = new ArrayList<Vertex>(g.getVertices());
		
		try {
			output.write("query: " + command + " " + u1.toString() + " " + u2.toString());
			output.newLine();
			output.write("<result>");
			output.newLine();
			
			//check if vertices exist in graph
			if(!allVertices.contains(u1) || !allVertices.contains(u2) ){
				output.write(VERTEX_NOT_FOUND_ERROR);
				output.newLine();
				output.write("</result>");
				output.newLine();
				output.newLine();
				return;
			}
			// if query is commonInfluencers
			if (command.equals(commonInfluencers)) {
				List<Vertex> commonFollowers = new LinkedList<Vertex>(Algorithms.commonDownstreamVertices(g, u1, u2));
				for (Vertex v : commonFollowers) {
					output.write("\t" + v.toString());
					output.newLine();
				}
			}

			// if query is numRetweets
			else if (command.equals(numRetweets)) {
				//note switch in u1 and u2; this is because tweets go upstream
				int distance = Algorithms.shortestDistance(g, u2, u1);
				
				if (distance == -1) {
					output.write(PATH_NOT_FOUND_ERROR);
				} else {
					//implicitly convert distance to string as printing out ints somehow didn't work
					output.write(""+distance);
					//System.out.println(distance);
				}

				output.newLine();
			} else {
				output.write(INVALID_COMMAND_ERROR + command);
				output.newLine();
			}
			output.write("</result>");
			output.newLine();
			output.newLine();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
