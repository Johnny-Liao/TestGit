package org.lsh.concurrency;

public abstract class IntGenerator {
	private volatile boolean canceled = false;

	public abstract int next();

	// Allow this to canceled
	public void cancel() {
		canceled = true;
	}
	
	public boolean isCanceled() {
		return canceled;
	}

}
