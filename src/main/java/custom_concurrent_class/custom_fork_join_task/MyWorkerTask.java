package custom_concurrent_class.custom_fork_join_task;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

public abstract class MyWorkerTask extends ForkJoinTask<Void> {
    private String name;

    public MyWorkerTask(String name) {
        this.name = name;
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        Date  startDate = new Date();
        complete();
        Date finishDate = new Date();
        long duration = finishDate.getTime() - startDate.getTime();
        System.out.printf("MyWorkerTask:%s MilliSeconds cost;\n",duration);

        return true;
    }

    public String getName() {
        return name;
    }


    protected abstract void complete();
}
