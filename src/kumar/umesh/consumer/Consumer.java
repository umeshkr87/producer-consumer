package kumar.umesh.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Consumer implements Callable {

	private final BlockingQueue<Integer> sharedQueue;
	
	
	public Consumer(BlockingQueue<Integer> sharedQueue) {
		super();
		this.sharedQueue = sharedQueue;
	}
	
	@Override
	public String call() {
		try {
			while (true) {
				Integer num = sharedQueue.take();
				process(num);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		return "done";
	}

	/*@Override
	public void run() {
		try {
			while (true) {
				Integer num = sharedQueue.take();
				process(num);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}*/


	private void process(Integer num) throws InterruptedException {
		System.out.println("Consumer take " + num);
		Thread.sleep(1000);
	}

}
