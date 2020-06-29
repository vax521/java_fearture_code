package thread_basic.monitorLock;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            Task task = new Task(lock);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (int i = 0; i < 15; i++) {
            System.out.println("Main: logging the lock");
            System.out.println("**********************");
            System.out.printf("Lock Owner:%s \n",lock.getOwnerName());
            //显示当前正在排队等待锁的线程数量和名称
            System.out.printf("Lock: If has queued threads: %s\n",lock.hasQueuedThreads());
            if(lock.hasQueuedThreads()){
                System.out.println("Lock: queued threads: ");
                Collection<Thread> lockedThreads = lock.getThreads();
                for(Thread thread:lockedThreads){
                    System.out.printf("%s ",thread.getName());
                }
                System.out.println();
            }
           //显示lock对象的公平性和状态信息
            System.out.printf("Lock: Fairness:  %s\n",lock.isFair());
            System.out.printf("Lock: locked:  %s\n",lock.isLocked());
            System.out.println("**********************");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
