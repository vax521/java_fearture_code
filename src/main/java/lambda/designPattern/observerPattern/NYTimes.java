package lambda.designPattern.observerPattern;

public class NYTimes implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("NYTimes breaking news!"+tweet);
        }
    }
}
