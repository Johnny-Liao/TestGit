package org.lsh.io.xfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ThrowAlien {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("X.file")));
		Object mystery = in.readObject();
		System.out.println(mystery.getClass());
		System.out.println(mystery);
	}
}
