package twitterAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class TwitterAnalysis2 {
	public static void main(String[] args) {
		FileInputStream queryStream;
		FileOutputStream outStream;
		FileInputStream twitterStream;

		final int ARGS_SIZE = 2;
		final String twitterFile = "datasets/twitter.txt";

		Map<List<Vertex>, String> queries;
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
		Graph g = readTwitterFile(twitterStream);

		queries = new LinkedHashMap<List<Vertex>, String>(readQuery(queryStream));
		
		try {
			printResults(outStream, queries, g);
		} catch (IOException e) {
			System.out.println("IOException when writing to " + outFile);
			return;
		}

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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Map<List<Vertex>, String> readQuery(FileInputStream queryStream) {
		try {
			Map<List<Vertex>, String> queries = new LinkedHashMap<List<Vertex>, String>();
			BufferedReader queryReader = new BufferedReader(new InputStreamReader(queryStream));
			String line;
			while ((line = queryReader.readLine()) != null) {
				List<Vertex> userIDs = new LinkedList<Vertex>();
				String[] columns = line.split(" ");
				// first column is query
				// second column is user 1
				// third column is user 2
				// fourth column is question mark
				if (columns[4].equals("?")) {
					userIDs.add(new Vertex(columns[1]));
					userIDs.add(new Vertex(columns[2]));
					if(!queries.containsKey(userIDs)){
						
					}
					queries.put(userIDs, columns[0]);
				}
				
				

			}
			queryReader.close();
			return new LinkedHashMap<List<Vertex>, String>(queries);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static void printResults(FileOutputStream outStream, Map<List<Vertex>, String> queries, Graph g)
			throws IOException {
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(outStream));
		Set<List<Vertex>> userIDs = new LinkedHashSet<List<Vertex>>(queries.keySet());
		final String commonInfluencers = "commonInfluencers";
		final String numRetweets = "numRetweets";

		for (List<Vertex> userPairs : userIDs) {
			String command = queries.get(userPairs);
			Vertex u1 = userPairs.get(0);
			Vertex u2 = userPairs.get(1);
			output.write("query: " + command + "" + u1.toString() + "" + u2.toString() + "\n");
			output.write("<result>\n");
			if (command.equals(commonInfluencers)) {
				List<Vertex> commonFollowers = new LinkedList<Vertex>(Algorithms.downstreamVertices(g, u1, u2));
				for (Vertex v : commonFollowers) {
					output.write("\t" + v.toString() + "\n");
				}
			}

			else if (command.equals(numRetweets)) {
				int distance = Algorithms.shortestDistance(g, u1, u2);
				output.write("\t" + distance + "\n");
			} else {
				output.write("\t Error: invalid command \n" + command);
			}
			output.write("</result>\n");
		}

		output.close();
	}

}
