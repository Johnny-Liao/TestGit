import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

//用BlockingQueue实现生产者消费者模型
class Producer implements Runnable {
	BlockingQueue<String> queue;

	Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(new Random().nextInt(3));	//随机生成生产时间
				queue.put(produce());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String produce() {
		List<String> produce = new ArrayList<>();		//在这些产品中随机生成一样产品
		produce.add("Food");
		produce.add("Car");
		produce.add("Phone");
		produce.add("Computer");
		String produceProduct = null;					//最终生产的产品
		boolean flag = true;
		Random random = new Random();
		int getProdect = random.nextInt(4);
		while (flag) {
			if (getProdect <= produce.size()) {
				produceProduct = produce.get(getProdect);
				flag = false;
			}
		}
		String tag = UUID.randomUUID().toString().substring(3, 8);	//标识同类产品
		System.out.println("Producer producing " + produceProduct + " " + tag);
		return produceProduct + " " + tag;
	}

}

class Consumer implements Runnable {
	BlockingQueue<String> queue;

	Consumer(BlockingQueue<String> lock) {
		this.queue = lock;
	}

	@Override
	public void run() {
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(new Random().nextInt(3));	//随机生成消费时间
				consume(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void consume(String produce) throws InterruptedException {
		System.out.println("Consumer buy product " + produce);
	}
}

public class ProducerConsumer {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingDeque<>(5);
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Producer(queue));
		service.execute(new Consumer(queue));
		service.shutdown();
	}

}
