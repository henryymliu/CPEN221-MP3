package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.*;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * 
 */
public class TwitterAnalysisTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		
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
