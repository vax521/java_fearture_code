package custom_concurrent_class.custom_async_stream;

import java.util.concurrent.Flow.*;

public class Consumer implements Subscriber<News> {

    private String name;

    private Subscription subscription;

    public Consumer(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
        System.out.printf("%s Consumer-Subscription\n",name);
    }

    @Override
    public void onNext(News item) {
        System.out.printf("%s-%s Consumer-News\n",name,Thread.currentThread().getName());
        System.out.printf("%s-%s Title\n",name,item.getName());
        System.out.printf("%s-%s Content\n",name,item.getContent());
        System.out.printf("%s-%s Date\n",name,item.getDate());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("%s-%s Consumer err:%s\n",name,Thread.currentThread().getName(),throwable.getMessage());

    }

    @Override
    public void onComplete() {
        System.out.printf("%s-%s Consumer completed\n",name,Thread.currentThread().getName());
    }
}
