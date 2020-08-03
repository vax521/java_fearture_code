package custom_concurrent_class.custom_async_stream;

public class ConsumerData {

    private Consumer consumer;

    private MySubscriprion mySubscriprion;

    public ConsumerData() {
    }

    public ConsumerData(Consumer consumer, MySubscriprion mySubscriprion) {
        this.consumer = consumer;
        this.mySubscriprion = mySubscriprion;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public MySubscriprion getMySubscriprion() {
        return mySubscriprion;
    }

    public void setMySubscriprion(MySubscriprion mySubscriprion) {
        this.mySubscriprion = mySubscriprion;
    }
}
