package lambda.designPattern.observerPattern;

public class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("queen")){
            System.out.println("Guardian breaking news!\n" + tweet);
        }
    }
}
