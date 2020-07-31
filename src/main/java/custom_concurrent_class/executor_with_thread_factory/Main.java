package custom_concurrent_class.executor_with_thread_factory;

import custom_concurrent_class.executor_with_thread_factory.MyTask;
import custom_concurrent_class.executor_with_thread_factory.MyThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
         MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");
        ExecutorService executorService = Executors.newCachedThreadPool(myThreadFactory);
         MyTask myTask = new MyTask();
         executorService.submit(myTask);
         executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main:end");
    }
}
