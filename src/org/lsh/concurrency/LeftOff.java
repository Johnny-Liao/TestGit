package org.lsh.concurrency;

//Demonstration of the Runnable interface.
public class LeftOff implements Runnable {

	protected int countDown = 10; 
	private static int taskCount = 0;
	private final int id = taskCount++;
	
	public LeftOff() {}
	
	public LeftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#" + id + "(" +
				(countDown > 0 ? countDown : "LeftOff!") + "). ";
	}
	
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
//			Thread.yield();
		}
	}

}
