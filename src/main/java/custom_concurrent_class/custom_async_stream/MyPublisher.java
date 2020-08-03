package custom_concurrent_class.custom_async_stream;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.ThreadPoolExecutor;

public class MyPublisher implements Flow.Publisher<News> {

    private ConcurrentLinkedDeque<ConsumerData> consumerData;

    private ThreadPoolExecutor threadPoolExecutor;

    public MyPublisher(ConcurrentLinkedDeque<ConsumerData> consumerData, ThreadPoolExecutor threadPoolExecutor) {
        this.consumerData = consumerData;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public MyPublisher() {
        consumerData = new ConcurrentLinkedDeque<>();
        threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    }

    @Override
    public void subscribe(Flow.Subscriber<? super News> subscriber) {
        ConsumerData consumerData = new ConsumerData();
        consumerData.setConsumer((Consumer)subscriber);
        MySubscriprion mySubscriprion = new MySubscriprion();
        consumerData.setMySubscriprion(mySubscriprion);
        subscriber.onSubscribe(mySubscriprion);
        this.consumerData.add(consumerData);
    }

    public void publish(News news){
        consumerData.forEach(consumerData1 -> {
            threadPoolExecutor.execute(new PublishTask(consumerData1,news));
        });
    }
}
