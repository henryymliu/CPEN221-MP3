package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.*;
import org.junit.BeforeClass;
import org.junit.Test;

import twitterAnalysis.TwitterAnalysis;

public class TwitterAnalysisTest {

	@Test
	public void test() {
		String[] params = {"datasets/TestingQueries.txt", "testResults1.txt", "datasets/test1.txt"};
		TwitterAnalysis.main(params);
		try{
		assertEquals(parseFile("expectedOut.txt"), parseFile("testResults1.txt"));
		}
		catch(IOException e){
			fail("some files not found");
		}
	}
	
	public String parseFile(String file) throws IOException{
	
		BufferedReader queryReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuffer toString = new StringBuffer();
		String line;
		while((line = queryReader.readLine()) != null){
			toString.append(line);
		}
		queryReader.close();
		return toString.toString();
	}

}
