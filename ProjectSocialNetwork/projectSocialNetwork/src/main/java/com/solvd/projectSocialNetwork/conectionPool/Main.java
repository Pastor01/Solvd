package com.solvd.projectSocialNetwork.conectionPool;



import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	public static void main(String[] args) {
		
		Logger log = LogManager.getLogger(Main.class);
		int POOL_SIZE=6;
		ConnectionPool pool = ConnectionPool.getInstance();
		
		ExecutorService executorPool = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	
		for (int i = 0; i < POOL_SIZE/2; i++) {
			executorPool.execute(new ConnectionRunnable("Connection Runnable " + (i+1), pool));
		}
		for (int i = POOL_SIZE/2; i < POOL_SIZE; i++) {
			executorPool.execute(new ConnectionThread("Connection Thread" + (i+1), pool));
		}
		executorPool.shutdown();
					
		try {
			executorPool.awaitTermination(20, TimeUnit.SECONDS);
			
			if(executorPool.isTerminated()) {
				log.info("All ConnectionThreads/ConnectionRunnables finished");
			}else {
				log.info("Not all ConnectionThreads/ConnectionRunnables finished");
			}
			
		} catch (InterruptedException e) {
			log.error("The executorPool can't awaitTermination",e);		
		}
		
		//Example with Futures
		
		ExecutorService eService = Executors.newFixedThreadPool(10);
		
		Future<String> mf1 = eService.submit(new ConnectionCallable("Callable of future 1"));
		Future<String> mf2 = eService.submit(new ConnectionCallable("Callable of future 2"));
		
		try {
			log.info(mf1.get());
			log.info(mf2.get());
		} catch (InterruptedException | ExecutionException e) {
			log.error("Cannot get the value of one Future",e);
		}
		eService.shutdown();
		
		try {
			executorPool.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			log.error("The executorPool can't awaitTermination",e);		
		}
		
		//Example with CompletableFuture: Run a task specified using lambda expression
		
		CompletableFuture<String> myCompletable = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(5);
		    } catch (InterruptedException e) {
		        throw new IllegalStateException(e);
		    }
		    return "Result of myCompletable";
		});

		
		try {
			// Block and wait for the future to complete
			log.info(myCompletable.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			log.error("Cannot get the value of myCompletable",e);
		}
		
		log.info("Demo of \"ConnectionPool\" and \"Future\" and \"CompletableFuture\" use completed");
	}
}

