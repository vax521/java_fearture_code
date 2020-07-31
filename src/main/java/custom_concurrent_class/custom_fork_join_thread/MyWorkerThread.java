package custom_concurrent_class.custom_fork_join_thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

public class MyWorkerThread extends ForkJoinWorkerThread {
    private final static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();


    /**
     * Creates a ForkJoinWorkerThread operating in the given pool.
     *
     * @param pool the pool this thread works in
     * @throws NullPointerException if pool is null
     */
    protected MyWorkerThread(ForkJoinPool pool) {
        super(pool);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.printf("MyWorkerThread:%d,initialize task counter\n",taskCounter.get());
        taskCounter.set(0);
    }

    @Override
    protected void onTermination(Throwable exception) {

        super.onTermination(exception);
    }


}
