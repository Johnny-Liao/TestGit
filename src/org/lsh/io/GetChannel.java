package org.lsh.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Getting channel from stream 演示如何获取通道，以及在上面用基础的ByteBuffer操作
 * 
 * @author JohnnyLiao
 * 
 */
public class GetChannel {
	private static final int BSiZE = 1024;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// Write a file
		FileChannel fc = new FileOutputStream("data.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text ".getBytes()));
		fc.close();

		fc = new RandomAccessFile("data.txt", "rw").getChannel();
		fc.position(fc.size()); // move to the end
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();

		// Read a file
		fc = new FileInputStream("data.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSiZE);
		fc.read(buff);
		buff.flip();
		while (buff.hasRemaining())
			System.out.print((char) buff.get());
	}
}
