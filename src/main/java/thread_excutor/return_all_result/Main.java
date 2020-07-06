package thread_excutor.return_all_result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<SumTask> sumTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SumTask sumTask = new SumTask("Task-"+i);
            sumTasks.add(sumTask);
        }
        List<Future<SumResult>> futureList = null;
        try {
            futureList = executorService.invokeAll(sumTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Main: printing the results\n");
        for (int i = 0; i < futureList.size(); i++) {
            Future<SumResult> future = futureList.get(i);
            try {
                SumResult  sumResult = future.get();
                System.out.printf("%s: %s\n",sumResult.getName(),sumResult.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
