package thread_excutor.completion_service_example;

import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ReportGenerator implements Callable<String> {
    private final String sender;
    private final String title;

    public ReportGenerator(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        long duration =(long)(Math.random()*10);
        System.out.printf("ReportGenerator:%s_%sGenerating a report during %s seconds\n",this.sender,this.title,duration);
        TimeUnit.SECONDS.sleep(duration);
        return sender+": "+title;
    }
}
