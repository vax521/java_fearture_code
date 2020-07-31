package custom_concurrent_class.custom_thread_pool_executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor(4,8,1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SleepTwoSecondsTask sleepTwoSecondsTask = new SleepTwoSecondsTask();
            Future<String> stringFuture = myExecutor.submit(sleepTwoSecondsTask);
            list.add(stringFuture);
        }
        for (int i = 0; i < 5; i++){
            try {
                String result = list.get(i).get();
                System.out.printf("Main: Result for Task %s : %s\n",i,result);
              } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
             }
         }
         myExecutor.shutdown();
        for (int i = 5; i < 10; i++){
            try {
                String result = list.get(i).get();
                System.out.printf("Main: Result for Task %s : %s\n",i,result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            myExecutor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main:End");
    }
}
