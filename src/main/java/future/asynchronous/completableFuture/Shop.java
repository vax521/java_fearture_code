package future.asynchronous.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private String name;

    public Shop(String name){
        this.name = name;
    }

    //同步api
    public double getPrice(String product){
       return calculatePrice(product);
    }
    public String getName(){
        return  this.name;
    }

    private static void delay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double calculatePrice(String product){
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    //异步API
    public Future<Double> getPriceAsyn(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(()->{
              futurePrice.complete(calculatePrice(product));
        }).start();
        return  futurePrice;
    }

    //使用工厂方法supplyAsync创建CompletableFuture


    //异步API
    public Future<Double> getPriceAsynByFactory(String product){
       return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }
}
