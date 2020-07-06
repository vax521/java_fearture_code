package thread_excutor.delay_run_task;

import util.DateUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main  {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        System.out.printf("Main start at: %s\n", DateUtil.getCurrTime());
        for (int i = 0; i < 5; i++) {
            Task task = new Task("task-"+i);
            executorService.schedule(task,i+1, TimeUnit.SECONDS);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
