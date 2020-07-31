package custom_concurrent_class.custom_scheduled_thread_pool;

import java.util.Date;
import java.util.concurrent.*;

public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
    private RunnableScheduledFuture<V> task;

    private ScheduledThreadPoolExecutor executor;

    private long period;

    private long startDate;

    public MyScheduledTask(Callable<V> callable) {
        super(callable);
    }

    public MyScheduledTask(Runnable runnable, V result) {
        super(runnable, result);
    }

    public MyScheduledTask(Callable<V> callable, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor) {
        super(callable);
        this.task = task;
        this.executor = executor;
    }

    public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor) {
        super(runnable, result);
        this.task = task;
        this.executor = executor;
    }

    @Override
    public boolean isPeriodic() {
        return false;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        if(!isPeriodic()){
            return task.getDelay(unit);
        }else {
            if(startDate==0){
                return task.getDelay(unit);
            }else {
               Date now =  new Date();
               long delay = startDate - now.getTime();
               return unit.convert(delay,TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }
}
