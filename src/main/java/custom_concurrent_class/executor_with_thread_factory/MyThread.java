package custom_concurrent_class.executor_with_thread_factory;

import java.util.Date;

public class MyThread extends Thread {
    private final Date  createDate;

    private Date startDate;

    private Date finishDate;

    public MyThread(Runnable target,String name){
        super(target,name);
        createDate = new Date();
    }

    @Override
    public void run() {

        super.run();
    }

    public synchronized  void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public synchronized void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public synchronized long getExecutionTime(){
        return finishDate.getTime() - startDate.getTime();
    }

    @Override
    public synchronized String toString() {
        return "MyThread{" +
                "createDate=" + createDate +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
