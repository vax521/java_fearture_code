package custom_concurrent_class.custom_async_stream;

import java.util.Date;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MyPublisher myPublisher = new MyPublisher();
        Flow.Subscriber<News> consumer1,consumer2;
        consumer1 = new Consumer("Consumer-1");
        consumer2 = new Consumer("Consumer-2");
        myPublisher.subscribe(consumer1);
        myPublisher.subscribe(consumer2);
        System.out.println("Main:start");

        for (int i = 0; i < 100 ; i++) {
            News news =  new News(i + " News","this is "+i+" news content",new Date());
            myPublisher.publish(news);
            System.out.println("******************************");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
