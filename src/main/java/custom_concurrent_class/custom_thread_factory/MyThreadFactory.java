package custom_concurrent_class.custom_thread_factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {
    private AtomicInteger counter;

    private String prefix;

    public MyThreadFactory(String prefix) {
        this.counter = new AtomicInteger(1);
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        MyThread myThread = new MyThread(r,prefix+"-"+counter.getAndIncrement());
        return myThread;
    }
}
