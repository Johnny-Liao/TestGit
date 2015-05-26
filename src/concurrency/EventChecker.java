package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventChecker implements Runnable {
	private IntGenerator generator;
	private final int id;

	public EventChecker(IntGenerator ge, int id) {
		this.generator = ge;
		this.id = id;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {//用于判断并关闭线程
			int val = generator.next();	//方法内部有非原子性操作，导致val值会处于不恰当状态
			if (val % 2 != 0) {
				System.out.println(val + " not even!");// 非偶数的
				generator.cancel(); // Cancels all EventCheckers
			}
		}
	}

	public static void test(IntGenerator gp, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EventChecker(gp, i));
		}
		exec.shutdown();
	}

	public static void test(IntGenerator gp) {
		test(gp, 10);
	}

}
