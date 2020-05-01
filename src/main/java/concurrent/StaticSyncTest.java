package concurrent;

public class StaticSyncTest {
	
	public static volatile int race = 0;
	
	public static synchronized void increase() {
		race++;
		System.out.println(race);
	}
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[20];
		for (int i = 0; i < 20; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100000; j++) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(race);
	}
}