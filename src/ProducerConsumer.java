import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

class Food {
	private static int id = 0;
	
	Food () {
		++id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Food: " + ++id;
	}
}

class Producer implements Runnable {
	BlockingQueue<Food> lock;

	Producer(BlockingQueue<Food> lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			try {
				lock.put(produce());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Food produce() throws InterruptedException {
		Food food = new Food();
		while (food.getId() >= 10 )  {
			//wait();
		}
		return food;
	}

}

class Consumer implements Runnable {
	BlockingQueue<Food> lock;

	Consumer(BlockingQueue<Food> lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			try {
				consume(lock.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void consume(Food food) throws InterruptedException {
		System.out.println(food + " be eating!");
		int foodId = food.getId();
		food.setId(--foodId);
		while (foodId <= 0) {
			//lock.wait();
		}
	}
}

public class ProducerConsumer {

	public static void main(String[] args) {
		BlockingQueue<Food> queue = new LinkedBlockingDeque<>();
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		new Thread(producer).start();
		new Thread(consumer).start();
	}

}
