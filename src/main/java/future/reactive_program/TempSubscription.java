package future.reactive_program;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class TempSubscription implements Flow.Subscription {
    private final Flow.Subscriber<? super TempInfo> subscriber;
    private final String town;

    private final ExecutorService excutorService = Executors.newSingleThreadExecutor();
    public TempSubscription(Flow.Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        excutorService.submit(()->{    //另外起一个线程向subscriber发送下一个元素
                    for(long i=0L;i<n;i++){
                        try {
                            subscriber.onNext(TempInfo.fetch(town));
                            System.out.println("-----------------");
                        }catch (Exception e){
                            subscriber.onError(e);
                            break;
                        }
                    }
                    System.out.println("*********************");
                }
        );
    }

    @Override
    public void cancel() {
         subscriber.onComplete();
    }
}
