package org.lsh.concurrency;

//Creating threads with inner class

//Using a named inner class:
class InnerThread1 {
	private int countDown = 5;
	private Inner inner;

	private class Inner extends Thread {		//�ڲ���
		Inner(String name) {
			super(name);
			start();
		}
		
		public void run() {
			try {
				while(true) {
					System.out.print(this);
					if (--countDown == 0) return;
					sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("interrupted!");
			}
		}
		
		public String toString() {
			return getName() + ": " + countDown;
		}
	}
	
	public InnerThread1(String name) {		//���캯��
		inner = new Inner(name);
	}
}

//Using an anonymous inner class:
class InnerThread2 {
	private int countDown = 5;
	private Thread t;

	public InnerThread2(String name) {		//���캯��
		t = new Thread() {					//anonymous inner class
			public void run() {
				try {
					while(true) {
						System.out.print(this);
						if (--countDown == 0) return;
						sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("interrupted!");
				}
			}
				
		};
		t.start();
	}
}

//Using a named Runnable implementation:
//...............................................

public class ThreadVariations {

	public static void main(String[] args) {

	}

}
