package thread_excutor.scheduled_run_task;

import util.DateUtil;

public class Task implements Runnable {
    private final String name;

    public Task(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.printf("%s executed at %s\n",name, DateUtil.getCurrTime());
    }
}
