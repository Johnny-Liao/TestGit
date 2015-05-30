package org.lsh.io;

import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {
	
	//Then read( ) is used to read each character one at a time and send it out to the console
	public static void read(String fileName) throws IOException {
		StringReader sr = new StringReader(BufferedInputFile.read(fileName));
		int c;
		while((c = sr.read()) != -1) {
			System.out.println((char) c);
		}
	}

	public static void main(String[] args) {
		try {
			read("src/org/lsh/io/BufferedInputFile.java");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
