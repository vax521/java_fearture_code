package thread_synchronization.lock_with_multiCondition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer  {

    private final LinkedList<String> buffer;

    private final int maxSize;

    private final ReentrantLock lock;

    private final Condition lines;

    private final Condition spaces;

    //用于表示当前数据缓冲区中是否还有未读取的内容
    private  boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        spaces = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line){
        lock.lock();
        try{
            while(buffer.size()==maxSize){
                spaces.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted line: %s\n",Thread.currentThread().getName(),buffer.size());
            lines.signalAll();}
        catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public String get(){
        String line = null;
        lock.lock();
        try{
            while ((buffer.size()==0)&&(hasPendingLines())){
                lines.await();
            }
            if(hasPendingLines()){
                line = buffer.poll();
                System.out.printf("%s : Line Readed: %s\n",Thread.currentThread().getName(),buffer.size());
                spaces.signalAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return line;
    }

    public synchronized void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public synchronized boolean hasPendingLines(){
       return pendingLines || buffer.size()>0;
    }

}
