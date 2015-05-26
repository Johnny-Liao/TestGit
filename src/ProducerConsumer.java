import java.util.concurrent.BlockingQueue;

class Produce {
	private int i = 0;

	@Override
	public String toString() {
		return "Produce " + ++i;
	}
}

class Producer implements Runnable {
	BlockingQueue<Produce> lock;

	Producer(BlockingQueue<Produce> lock) {
		this.lock = lock;
	}

	public Producer(Produce p) {
		
	}

	@Override
	public void run() {
		while (true) {
			try {
				lock.put(new Produce());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Consumer implements Runnable {
	BlockingQueue<Produce> lock;

	Consumer(BlockingQueue<Produce> lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			lock.peek();
		}
	}
}

public class ProducerConsumer {

	public static void main(String[] args) {
		Produce p = new Produce();
		Producer q = new Producer(p);
	}

}
