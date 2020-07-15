package fork_join_framework.asny_run_task;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FolderProcessor system = new FolderProcessor("C:\\windows","log");
        FolderProcessor app = new FolderProcessor("C:\\windows","Program Files");
        FolderProcessor document = new FolderProcessor("C:\\Document","log");
        forkJoinPool.execute(system);
        forkJoinPool.execute(app);
        forkJoinPool.execute(document);
        do {
            System.out.println("********************************");
            System.out.printf("Main: Thread Count:%s\n ",forkJoinPool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal:%s\n ",forkJoinPool.getStealCount());
            System.out.printf("Main: Parallelism:%s\n ",forkJoinPool.getParallelism());
            System.out.println("********************************");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!(system.isDone()&&app.isDone()&document.isDone()));

        forkJoinPool.shutdown();
        List<String> results;

        results = (List<String>) system.join();
        System.out.printf("System:%d\n",results.size());
        results = (List<String>) app.join();
        System.out.printf("app:%d\n",results.size());
        results = (List<String>) document.join();
        System.out.printf("document:%d\n",results.size());
    }
}
