package future.reactive_program;


import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) {
        getTempratures("New York").subscribe(new TempSubscriber());
    }

    public static Flow.Publisher<TempInfo> getTempratures(String town){
       /* return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber,town));*/
        //使用processor
        return subscriber -> {
           TempProcessor tempProcessor = new TempProcessor();
           tempProcessor.subscribe(subscriber);
           tempProcessor.onSubscribe(new TempSubscription(tempProcessor,town));
       };
    }
}
