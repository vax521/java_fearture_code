package future.pub_sub;

import java.util.ArrayList;
import java.util.List;


public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;

    private String name ;

    private List<Subscriber> subscribers = new ArrayList<>();

    public SimpleCell(String name){
        this.name = name;
    }


    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    private void notifyAllSubscribers(){
         subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }
    @Override
    public void onNext(Integer integer) {
        this.value = integer;
        System.out.println("notify " + this.name + " change value to : " + this.value);
         notifyAllSubscribers();
    }
}
