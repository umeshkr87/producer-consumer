package kumar.umesh.producer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private final BlockingQueue<Integer> sharedQueue;
	
	public Producer(BlockingQueue<Integer> queue) {
		this.sharedQueue = queue;
	}
	
	@Override
	public void run() {
		try {
			produce();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	private void produce() throws InterruptedException {
		for (int i = 0; i < 20; i++) {
			System.out.println("Producer Put " + i);
			sharedQueue.put(i);
			Thread.sleep(500);
		}
		
	}
}
