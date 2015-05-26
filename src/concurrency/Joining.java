package concurrency;

//Understanding join().

class Sleeper extends Thread {
	private int duration;

	public Sleeper(String name, int sleeptime) {
		super(name);
		duration = sleeptime;
		start();
	}

	public void run() {
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(getName() + " was interrupted. "
					+ " isInterrupted(): " + isInterrupted());
			return;
		}
		System.out.println(getName() + " has awakened.");
	}
}

class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	public void run() {
		try {
			sleeper.join(); // Waits for this thread to die.
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println(sleeper.getName() + "was awakened so " + getName() + " join completed.");
	}
}

public class Joining {

	public static void main(String[] args) {
		Sleeper 
			sleepy = new Sleeper("Sleepy", 1500),
			grumpy = new Sleeper("grumpy", 1500);
		Joiner
			dopey = new Joiner("Dopey", sleepy),
			doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();		//使其提前中断
	}

}
