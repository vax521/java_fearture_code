package thread_basic.daemonThread;

import java.util.Date;
import java.util.Deque;

public class CleanerTask  extends Thread{
    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        /*setDaemon()方法只能在start()方法之前调用，一旦线程开始执行，其daemon状态便不可修改。
        此时调用setDaemon()方法，将抛出IllegalThreadStateException异常*/
        setDaemon(true);
    }

    @Override
    public void run() {
        while(true){
            clean(new Date());
        }
    }

    /*
    *  该方法获取队列中的最后一个事件，如果该事件已创建超过10s，
    *  则从队列中删除该事件并检查下一个事件。如果删除了一个事件，
    *  则输出该事件信息及队列大小，便于观察执行过程
    * */
    private void clean(Date date){
        long difference;
        boolean ifHasDeleted;
        if(deque.size()==0){
            return;
        }
        ifHasDeleted = false;
        do{
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            if(difference > 10000){
                deque.removeLast();
                ifHasDeleted = true;
            }

        }while (difference > 10000);
        if(ifHasDeleted){
            System.out.println(deque);
            System.out.printf("Cleaner: size of the deque: %s\n",deque.size());
        }
    }
}
