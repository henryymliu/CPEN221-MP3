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

		if (args.length < ARGS_SIZE) {
			System.out.println("Not enough input arguments.");
			return;
		}
		String queryFile = args[0];
		String outFile = args[1];

		try {

			queryStream = new FileInputStream(queryFile);
			outStream = new FileOutputStream(outFile);

			twitterStream = new FileInputStream(twitterFile);

		} catch (FileNotFoundException e) {
			System.out.println("One or more files not found. Make sure the file name ends in .txt");
			return;
		}
		long startTime = System.currentTimeMillis();
		Graph g = readTwitterFile(twitterStream);
		long stopTime = System.currentTimeMillis();
		System.out.println("Computation took: " + (stopTime - startTime) + " milliseconds.");
		parseQuery(queryStream, outStream, g);

	}

	private static Graph readTwitterFile(FileInputStream twitterStream) {
		try {
			Graph g = new AdjacencyListGraph();
			BufferedReader twitterReader = new BufferedReader(new InputStreamReader(twitterStream));
			String line;
			while ((line = twitterReader.readLine()) != null) {
				String[] columns = line.split(" -> ");
				// first column is user 1
				// second column is user 2
				Vertex u1 = new Vertex(columns[0]);
				Vertex u2 = new Vertex(columns[1]);

				g.addVertex(u1);
				g.addVertex(u2);
				g.addEdge(u1, u2);
				System.out.println(line);
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
	 * @param queryStream
	 * @param outStream
	 * @param g
	 */
	private static void parseQuery(FileInputStream queryStream, FileOutputStream outStream, Graph g) {

		try {
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(outStream));
			BufferedReader queryReader = new BufferedReader(new InputStreamReader(queryStream));

			Set<Set<String>> queries = new LinkedHashSet<Set<String>>();
			final String commonInfluencers = "commonInfluencers";
			final String numRetweets = "numRetweets";

			String line;

			while ((line = queryReader.readLine()) != null) {
				Set<String> query = new LinkedHashSet<String>();
				String[] columns = line.split(" ");
				// first column is query
				// second column is user 1
				// third column is user 2
				// fourth column is question mark

				String command = columns[0];
				String id1 = columns[1];
				String id2 = columns[2];
				query.add(id1);
				query.add(id2);
				query.add(command);

				// check if query ends with question mark and query is unique
				if (line.endsWith(" ?") && !queries.contains(query)) {

					queries.add(query);

					Vertex u1 = new Vertex(id1);
					Vertex u2 = new Vertex(id2);

					output.write("query: " + command + " " + id1.toString() + " " + id2.toString());
					output.newLine();
					output.write("<result>");
					output.newLine();
					
					//if query is commonI
					if (command.equals(commonInfluencers)) {
						List<Vertex> commonFollowers = new LinkedList<Vertex>(
								Algorithms.commonDownstreamVertices(g, u1, u2));
						for (Vertex v : commonFollowers) {
							output.write("\t" + v.toString());
							output.newLine();
						}
					} else if (command.equals(numRetweets)) {
						int distance = Algorithms.shortestDistance(g, u1, u2);
						if (distance == -1) {
							output.write("\t Path not found.");
						} else {
							output.write("\t" + distance);
						}
						output.newLine();
					} else {
						output.write("\t Error: invalid command " + command);
						output.newLine();
					}
					output.write("</result>");
					output.newLine();
					output.newLine();

				}

			}
			queryReader.close();
			output.close();
			// return new LinkedHashMap<List<Vertex>, String>(queries);

		} catch (Exception e) { // if something goes wrong
			throw new RuntimeException(e);
		}

	}

	/*
	 * private static void printResults(FileOutputStream outStream,
	 * Map<List<Vertex>, String> queries, Graph g) throws IOException {
	 * BufferedWriter output = new BufferedWriter(new
	 * OutputStreamWriter(outStream)); Set<List<Vertex>> userIDs = new
	 * LinkedHashSet<List<Vertex>>(queries.keySet()); final String
	 * commonInfluencers = "commonInfluencers"; final String numRetweets =
	 * "numRetweets";
	 * 
	 * for (List<Vertex> userPairs : userIDs) { String command =
	 * queries.get(userPairs); Vertex u1 = userPairs.get(0); Vertex u2 =
	 * userPairs.get(1); output.write("query: " + command + "" + u1.toString() +
	 * "" + u2.toString() + "\n"); output.write("<result>\n"); if
	 * (command.equals(commonInfluencers)) { List<Vertex> commonFollowers = new
	 * LinkedList<Vertex>(Algorithms.downstreamVertices(g, u1, u2)); for (Vertex
	 * v : commonFollowers) { output.write("\t" + v.toString() + "\n"); } }
	 * 
	 * else if (command.equals(numRetweets)) { int distance =
	 * Algorithms.shortestDistance(g, u1, u2); output.write("\t" + distance +
	 * "\n"); } else { output.write("\t Error: invalid command \n" + command); }
	 * output.write("</result>\n"); }
	 * 
	 * output.close(); }
	 */
}
