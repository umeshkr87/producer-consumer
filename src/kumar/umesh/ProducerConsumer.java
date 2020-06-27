package kumar.umesh;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import kumar.umesh.consumer.Consumer;
import kumar.umesh.producer.Producer;

public class ProducerConsumer {

	public static void main(String [] args) {
		System.out.println("This is a test producer - consumer problem");
		BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();
		//new Thread(new Producer(sharedQueue)).start();
		//new Thread(new Consumer(sharedQueue)).start();
		ExecutorService execService = Executors.newFixedThreadPool(2);
		
		try {
			// Start consumer thread
			Future<String> future = execService.submit(new Consumer(sharedQueue));
			// Start producer thread
			execService.submit(new Producer(sharedQueue));
			System.out.println("Future get after 5 sec " + future.get(5, TimeUnit.SECONDS));
			System.out.println("Future get " + future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} finally {
			execService.shutdown();
		}
	}
}
