package concurrency;

public class EventGenerator extends IntGenerator {
	
	private int currentEventValue;

	@Override
	public int next() {//用于产生偶数
		++currentEventValue;	//Danger point here!
//		Thread.yield();			//Cause failure faster
		++currentEventValue;
		return currentEventValue;
	}

	public static void main(String[] args) {
		EventChecker.test(new EventGenerator());
	}

}
