package org.lsh.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//: concurrency/HorseRace.java
//Using CyclicBarriers.
class Horse implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private int strides = 0;			//大踏步走，跨过
	private static Random rand = new Random(47);
	private static CyclicBarrier barrier;

	public Horse(CyclicBarrier b) {
		barrier = b;
	}

	public synchronized int getStrides() {
		return strides;		//跑的距离
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					strides += rand.nextInt(3); // Produces 0, 1 or 2	跨步数随机产生，跨0，1，2步
				}
				barrier.await();
			}
		} catch (InterruptedException e) {
			// A legitimate way to exit
		} catch (BrokenBarrierException e) {
			// This one we want to know about
			throw new RuntimeException(e);
		}
	}

	public String toString() {
		return "Horse " + id + " ";
	}

	public String tracks() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < getStrides(); i++)		//循环到跨步的总数
			s.append("*");
		s.append(id);
		return s.toString();
	}
}

public class HorseRace {
	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;

	public HorseRace(int nHorses, final int pause) {
		//public CyclicBarrier(int parties, Runnable barrierAction)	
		//parties - 在启动 barrier 前必须调用 await() 的线程数	
		//barrierAction - 在启动 barrier 时执行的命令；如果不执行任何操作，则该参数为 null 
		barrier = new CyclicBarrier(nHorses, new Runnable() {		//栅栏动作：作为匿名内部内创建，提交给CyclicBarrier构造器
			public void run() {
				StringBuilder s = new StringBuilder();
				for (int i = 0; i < FINISH_LINE; i++)
					s.append("="); // The fence on the racetrack
				System.out.println(s);
				for (Horse horse : horses)
					System.out.println(horse.tracks());
				for (Horse horse : horses)
					if (horse.getStrides() >= FINISH_LINE) {
						System.out.println(horse + "won!");
						exec.shutdownNow();
						return;
					}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					System.out.println("barrier-action sleep interrupted");
				}
			}
		});
		for (int i = 0; i < nHorses; i++) {		//new 出所有的马，加入集合  and 启动每匹马的线程
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}

	public static void main(String[] args) {
		int nHorses = 7;
		int pause = 200;
		if (args.length > 0) { // Optional argument
			int n = new Integer(args[0]);
			nHorses = n > 0 ? n : nHorses;
		}
		if (args.length > 1) { // Optional argument
			int p = new Integer(args[1]);
			pause = p > -1 ? p : pause;
		}
		new HorseRace(nHorses, pause);
	}
} /* (Execute to see output) */// :~