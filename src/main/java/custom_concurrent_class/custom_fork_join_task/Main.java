package custom_concurrent_class.custom_fork_join_task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[10000];
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Task task = new Task("task",array,0,array.length);
        forkJoinPool.execute(task);
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main:End");
    }
}
