package additional_tips;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        FileSearch system = new FileSearch("C:\\windows","log");
        Task sysTask = new Task(system,null);
        executorService.submit(sysTask);
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        try {
            System.out.printf("SystemTask result size:%s\n",sysTask.get().size());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
