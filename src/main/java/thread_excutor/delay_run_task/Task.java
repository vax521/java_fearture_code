package thread_excutor.delay_run_task;

import util.DateUtil;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("%s start at %s\n",name, DateUtil.getCurrTime());
        return "Hello";
    }
}
