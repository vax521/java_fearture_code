package thread_excutor.thread_excutor_recject;

import util.DateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private final String initDate;
    private final String name;

    public Task(String name) {
        initDate = DateUtil.getCurrTime();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s created on %s\n",Thread.currentThread().getName(),name, initDate);
        System.out.printf("%s: Task %s started on %s\n",Thread.currentThread().getName(),name, DateUtil.getCurrTime());
        long duration = (long)(Math.random()*10);
        System.out.printf("%s: Task %s doing a task during %d seconds\n",Thread.currentThread().getName(),name, duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Task %s finished on %s\n",Thread.currentThread().getName(),name, DateUtil.getCurrTime());
    }
}
