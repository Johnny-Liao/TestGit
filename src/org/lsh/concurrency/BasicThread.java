package org.lsh.concurrency;

//The most basic use of the Thread class.
public class BasicThread {
	public static void main(String[] args) {
		Thread thread = new Thread(new LeftOff());
		thread.start();
		System.out.println("Waiting for LiftOff!");
	}
}
