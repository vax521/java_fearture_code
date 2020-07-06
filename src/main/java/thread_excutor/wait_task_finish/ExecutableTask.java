package thread_excutor.wait_task_finish;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ExecutableTask implements Callable<String> {
    private final String name;

    public String getName() {
        return name;
    }

    public ExecutableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        long duration = (long)(Math.random()*10);
        System.out.printf("%s: wait %s seconds for results\n",this.name,duration);
        TimeUnit.SECONDS.sleep(duration);
        return "hello,I am "+name;
    }
}
