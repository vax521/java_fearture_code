package custom_concurrent_class.custom_lock;


import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    private String name;

    private MyLock lock;

    public Task(String name, MyLock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.printf("Task:%s takes the lock\n",name);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
