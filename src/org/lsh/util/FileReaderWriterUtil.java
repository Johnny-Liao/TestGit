package org.lsh.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Change by TIJ4 BruceEckel
 * 
 * @author JohnnyLiao
 */
public class FileReaderWriterUtil extends ArrayList<String> {

	// Read a file as a single string
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	// Write a single file in one method call
	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Read by lines
	public FileReaderWriterUtil(String fileName) {
		this(fileName, "\n");
	}

	// Read a file, split by any regular expression:
	public FileReaderWriterUtil(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter))); // 读入的分隔后的字符串传给ArrayList
		// Regular expression split() often leaves an empty String at the first
		// position, so remove it.
		if (get(0).equals(""))
			remove(0);
	}

	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				for (String item : this) { // this is ArrayList
					out.print(item);
					out.print("\n");
				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	// Simple test
	public static void main(String[] args) {

		String file = read(getProjectPath() + "/src/org/lsh/util/FileReaderWriterUtil.java");
		write("test.txt", file);
		// String file = read("src/org/lsh/util/FileReaderWriterUtil.java");
		// //文件直接定位到了项目工程根目录下 访问src时不用加反斜杠

		FileReaderWriterUtil frwu = new FileReaderWriterUtil("test.txt");
		frwu.write("test2.txt");

		/*
		 * TreeSet<String> words = new TreeSet<>(new
		 * FileReaderWriterUtil(getProjectPath() +
		 * "/src/org/lsh/util/FileReaderWriterUtil.java", "\\W+"));
		 * System.out.println(words.headSet("a"));
		 */
	}
}
