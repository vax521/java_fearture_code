package custom_concurrent_class.custom_lock;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        for (int i = 0; i < 10; i++) {
            Task task = new Task("task-"+i,myLock);
            Thread thread = new Thread(task);
            thread.start();
        }

        boolean value;
        do{
            try {
                value = myLock.tryLock(1, TimeUnit.SECONDS);
                if(!value){
                    System.out.println("Main:try to get the lock!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                value = false;
            }
        }while (!value);

        System.out.println("Main: got the lock");
        myLock.unlock();
        System.out.println("Main:end");
    }
}
