package custom_concurrent_class.custom_thread_pool_executor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyExecutor extends ThreadPoolExecutor {
    private ConcurrentHashMap<Runnable, Date> startTimes;

    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTimes = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        System.out.println("MyExecutor going to shutdown...");
        System.out.printf("MyExecutor-Completed Tasks: %d\n",getCompletedTaskCount());
        System.out.printf("MyExecutor-Running Tasks: %d\n",getActiveCount());
        System.out.printf("MyExecutor-Pending Tasks: %d\n",getQueue().size());
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.println("MyExecutor going to shutdown now...");
        System.out.printf("MyExecutor-Completed Tasks: %d\n",getCompletedTaskCount());
        System.out.printf("MyExecutor-Running Tasks: %d\n",getActiveCount());
        System.out.printf("MyExecutor-Pending Tasks: %d\n",getQueue().size());
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.printf("MyExecutor: A task is begining:%s :%s\n",t.getName(),r.hashCode());
        startTimes.put(r,new Date());
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> future = (Future<?>) r;
        System.out.println("******************************");
        System.out.println("A task is finished\n");
        try {
            System.out.printf("MyExecutor:Result :%s\n",future.get());
            Date startTime = startTimes.remove(r);
            Date finishTime = new Date();
            long duration = finishTime.getTime() - startTime.getTime();
            System.out.printf("MyExecutor: Duration %s\n",duration);
            System.out.println("******************************");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        super.afterExecute(r, t);
    }
}
