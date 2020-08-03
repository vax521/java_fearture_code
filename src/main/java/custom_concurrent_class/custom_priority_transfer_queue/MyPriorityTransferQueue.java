package custom_concurrent_class.custom_priority_transfer_queue;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {

    private final AtomicInteger counter;

    private final LinkedBlockingQueue<E> transferred;

    private final ReentrantLock lock;

    public MyPriorityTransferQueue() {
        counter = new AtomicInteger(0);
        transferred = new LinkedBlockingQueue<>();
        lock = new ReentrantLock();
    }

    public MyPriorityTransferQueue(AtomicInteger counter, LinkedBlockingQueue<E> transferred, ReentrantLock lock) {
        this.counter = counter;
        this.transferred = transferred;
        this.lock = lock;
    }

    public MyPriorityTransferQueue(int initialCapacity, AtomicInteger counter, LinkedBlockingQueue<E> transferred, ReentrantLock lock) {
        super(initialCapacity);
        this.counter = counter;
        this.transferred = transferred;
        this.lock = lock;
    }

    public MyPriorityTransferQueue(int initialCapacity, Comparator<? super E> comparator, AtomicInteger counter, LinkedBlockingQueue<E> transferred, ReentrantLock lock) {
        super(initialCapacity, comparator);
        this.counter = counter;
        this.transferred = transferred;
        this.lock = lock;
    }

    public MyPriorityTransferQueue(Collection<? extends E> c, AtomicInteger counter, LinkedBlockingQueue<E> transferred, ReentrantLock lock) {
        super(c);
        this.counter = counter;
        this.transferred = transferred;
        this.lock = lock;
    }

    @Override
    public boolean tryTransfer(E e) {
        boolean value;
        lock.lock();
        try {
            if(counter.get()==0){
                value = false;
            }else {
                put(e);
                value = true;
            }
        }finally {
            lock.unlock();
        }
        return value;
    }

    @Override
    public void transfer(E o) throws InterruptedException {

    }

    @Override
    public boolean tryTransfer(E o, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean hasWaitingConsumer() {
        return false;
    }

    @Override
    public int getWaitingConsumerCount() {
        return 0;
    }
}
