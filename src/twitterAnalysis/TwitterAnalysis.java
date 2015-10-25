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
		final String twitterFile = "datasets/twitter.txt";
		
		//if less than 2 input arguments
		if (args.length < ARGS_SIZE) {
			System.out.println("Not enough input arguments.");
			return;
		}
		String queryFile = args[0];
		String outFile = args[1];

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
		System.out.println("Computation took: " + (stopTime - startTime) + " milliseconds.");
		parseQuery(queryStream, outStream, g);

	}

	private static Graph readTwitterFile(FileInputStream twitterStream) {
		try {
			Graph g = new AdjacencyListGraph();
			BufferedReader twitterReader = new BufferedReader(new InputStreamReader(twitterStream));
			String line;
			while ((line = twitterReader.readLine()) != null) {
				
				//eliminate any unnecessary whitespace
				String[] columns = line.trim().replaceAll("\\s+", "").split("->");
				
				// first column is user 1
				// second column is user 2
				Vertex u1 = new Vertex(columns[0]);
				Vertex u2 = new Vertex(columns[1]);
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
	 * Reads query file and writes the results to an output file.
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

			Set<Set<String>> queries = new LinkedHashSet<Set<String>>();

			String line;

			while ((line = queryReader.readLine()) != null) {
				
				//each query set contains two user id strings and a command
				//this also handles duplicate user ids with different commands
				Set<String> query = new LinkedHashSet<String>();
				
				//a bit overkill, but eliminate any unnecessary whitespace if needed
				String[] columns = line.trim().replaceAll("\\s+", " ").split(" ");
		
				// first column is query
				// second column is user 1
				// third column is user 2
		
				String command = columns[0];
				String id1 = columns[1];
				String id2 = columns[2];
				query.add(id1);
				query.add(id2);
				query.add(command);

				// check if query ends with question mark (with leading space) and query is unique
				if (line.endsWith(" ?") && !queries.contains(query)) {

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
	
	private static void printResults(BufferedWriter output, Graph g, Vertex u1, Vertex u2, String command) {
		final String commonInfluencers = "commonInfluencers";
		final String numRetweets = "numRetweets";

		try {
			output.write("query: " + command + " " + u1.toString() + " " + u2.toString());
			output.newLine();
			output.write("<result>");
			output.newLine();

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
					output.write("\tPath not found.");
				} else {
					//implicitly convert distance to string as printing out ints somehow didn't work
					output.write(""+distance);
					//System.out.println(distance);
				}

				output.newLine();
			} else {
				output.write("\tError: invalid command " + command);
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
