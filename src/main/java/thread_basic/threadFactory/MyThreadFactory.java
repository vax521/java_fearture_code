package thread_basic.threadFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String name;
    private List<String> stats;

    public  MyThreadFactory(String name){
        counter = 0;
        this.name = name;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r,name+"-Thread_"+counter);
        counter++;
        stats.add(String.format("Created thread %s with name %s on %s\n",t.getId(),t.getName(),new Date()));
        return t;
    }

    public String getStats(){
        StringBuffer stringBuffer = new StringBuffer();
        stats.forEach(stat->{
            stringBuffer.append(stat);
            stringBuffer.append("\n");
        });
        return stringBuffer.toString();
    }
}
