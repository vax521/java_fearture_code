package custom_concurrent_class.priority_executor;

import java.util.concurrent.TimeUnit;

public class MyPriorityTask implements Runnable,Comparable<MyPriorityTask> {

    private int priority;

    private String name;

    public MyPriorityTask(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(MyPriorityTask o) {
        return Integer.compare(o.getPriority(),this.getPriority());
    }

    @Override
    public void run() {
        System.out.printf("MyPriorityTask: %s: Priority: %s\n",name,getPriority());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
