package custom_concurrent_class.custom_async_stream;

public class PublishTask implements Runnable {

    private ConsumerData consumerData;

    private News news;

    public PublishTask(ConsumerData consumerData, News news) {
        this.consumerData = consumerData;
        this.news = news;
    }

    @Override
    public void run() {
        MySubscriprion subscriprion = consumerData.getMySubscriprion();
        if(!( subscriprion.isCanced() && (subscriprion.getRequested()==0))){
            consumerData.getConsumer().onNext(news);
            subscriprion.decreaseRequested();
        }

    }
}
