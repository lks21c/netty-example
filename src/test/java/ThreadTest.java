/**
 * Discards any incoming data.
 */
public class ThreadTest {

	public static void main(String[] args) throws Exception {

		DigestThread t = new DigestThread();
		t.start();

		Runnable runner = new Runner();
		Thread t2 = new Thread(runner);
		t2.start();
	}

	static class DigestThread extends Thread {

		@Override
		public void run() {
			System.out.println("Run from DigestThread");
		}
	}

	static class Runner implements Runnable {

		public void run() {
			System.out.println("Run from Runnable");
		}
	}
}
