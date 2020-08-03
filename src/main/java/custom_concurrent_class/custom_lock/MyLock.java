package custom_concurrent_class.custom_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private final MyAbstractQueuedSynchronizer synchronizer;

    public MyLock() {
        this.synchronizer = new MyAbstractQueuedSynchronizer();
    }

    @Override
    public void lock() {
        synchronizer.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        synchronizer.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        try {
            return synchronizer.tryAcquireNanos(1,1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return synchronizer.tryAcquireNanos(1,TimeUnit.NANOSECONDS.convert(time,unit));
    }

    @Override
    public void unlock() {
        synchronizer.release(1);
    }

    @Override
    public Condition newCondition() {
        return synchronizer.new ConditionObject();
    }
}
