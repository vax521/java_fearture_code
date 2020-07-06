package thread_excutor.scheduled_run_task;

import util.DateUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        System.out.printf("Main start at: %s\n", DateUtil.getCurrTime());
        Task task = new Task("task");
        ScheduledFuture<?> result = executorService.scheduleAtFixedRate(task,0,2, TimeUnit.SECONDS);
        //创建一个步长为10的循环，打印至下次任务的剩余时间。在循环中，使用ScheduledFuture对象的getDelay()方法获取下次任务的时间（毫秒）：
        for (int i = 0; i < 10; i++) {
            System.out.printf("Main:delay %s\n",result.getDelay(TimeUnit.MILLISECONDS));
        }
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main end at %s\n",DateUtil.getCurrTime());
    }
}
