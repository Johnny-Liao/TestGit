package org.lsh.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FreezeAlize {
	public static void main(String[] args) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("X.file"));
		Alien alien = new Alien();
		out.writeObject(alien);
	}
}
