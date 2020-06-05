package future.asynchronous.completableFuture;

import java.util.Random;

public class Discount {
    public enum Code {
        None(0),Sliver(20),GOLD(40),PLATINUM(50),Diamond(60);
        private final int percentage;
        Code( int percentage) {
            this.percentage = percentage;
        }
    }

    private static void delay(){
        try {
            Random random = new Random();
            Thread.sleep(500+random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String applyDiscount(Quote quote){
       return String.format("%s price is %.2f",quote.getShopName(),apply(quote.getPrice(), quote.getCode()));
    }
    public static double apply(double price,Code code){
         delay();
         return price*(100-code.percentage)/100;
    }
}
