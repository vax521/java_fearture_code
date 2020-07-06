package thread_excutor.wait_task_finish;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ResultTask extends FutureTask<String> {
    private final String name;
    public ResultTask(ExecutableTask executableTask) {
        super(executableTask);
        name = executableTask.getName();
    }

    @Override
    public boolean isDone() {
        return super.isDone();
    }

    @Override
    protected void done() {
        if(this.isCancelled()){
            System.out.printf("The task %s has been concelled!\n",name);
        }else {
            System.out.printf("The task %s has been finished!\n",name);
        }
    }
}
