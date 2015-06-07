package org.lsh.concurrency;

public class SynchronizedEventGenerator extends IntGenerator {
	
	private int currentEventValue;

	@Override
	public synchronized int next() {//用于产生偶数
		++currentEventValue;	//Danger point here!
		Thread.yield();			//Cause failure faster
		++currentEventValue;
		return currentEventValue;
	}

	public static void main(String[] args) {
		EventChecker.test(new SynchronizedEventGenerator());
	}

}
