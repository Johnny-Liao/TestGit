package org.lsh.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {
	
	//≤‚ ‘÷–Œƒ¬“¬Î
	
	public static String read(String fileName) {
		return read(new File(fileName));
	}
	
	public static String read(File fileName) {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String str = "";
			while ((str = in.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(read("src/org/lsh/io/BufferedInputFile.java"));
	}
}
