package lambda.designPattern.observerPattern;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {
    private final List<Observer> observerList = new ArrayList<>();
    @Override
    public void rigisterObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void notifyObserver(String tweet) {
       observerList.forEach(o->o.notify(tweet));
    }

    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.rigisterObserver(new NYTimes());
        feed.rigisterObserver(new Guardian());
        //lambda表达式方式
        feed.rigisterObserver((String tweet)->{
            if(tweet != null && tweet.contains("china")){
                System.out.println("China breaking news!\n" + tweet);
            }
        });
        feed.notifyObserver("the queen said she favorite apple;");
        feed.notifyObserver("the china said she favorite apple;");
        feed.notifyObserver("the queen said she favorite apple;");
    }
}
