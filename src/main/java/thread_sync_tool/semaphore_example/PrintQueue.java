package thread_sync_tool.semaphore_example;

import util.DateUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    private final Semaphore semaphore;
    private final boolean[] freePrinters;
    private final Lock  printersLock;

    public PrintQueue() {
        semaphore = new Semaphore(3,true);
        freePrinters = new boolean[3];
        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }
        printersLock = new ReentrantLock();
    }

    /*
    * • 调用acquire()方法获得信号量
    * • 操作共享资源
    * • 调用release()方法来释放信号量
    **/
    public void printJob(Object doc){
        try {
            semaphore.acquire();
            int assignedPrinter = getPrinter();
            long duration = (long)(Math.random()*10);
            System.out.printf("%s-%s:PrintQueue:Print a job in printer %s during %s seconds!\n", DateUtil.getCurrTime(),Thread.currentThread().getName(),assignedPrinter,duration);
            TimeUnit.SECONDS.sleep(duration);
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }


    private int getPrinter(){
        int ret = -1;
        try{
            printersLock.lock();
            for (int i = 0; i < freePrinters.length; i++) {
                if(freePrinters[i]){
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            printersLock.unlock();
        }
        return ret;
    }
}


