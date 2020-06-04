package future.asynchronous.completableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CompletableFutureExample {
    public static void doSomeThingElse(){
        System.out.println("something else .....");
    }
    public static void main(String[] args) {
        Shop bestShop = new Shop("bestShop");
        long start = System.nanoTime();
//        Future<Double> futurePrice = bestShop.getPriceAsyn("favorite shop");
        Future<Double> futurePrice = bestShop.getPriceAsynByFactory("favorite shop");
        long invocationTime = (System.nanoTime()-start)/1_000_000;
        System.out.println("Invacation returned after "+invocationTime+" msecs");
        doSomeThingElse();
        try {
            System.out.printf("the price is %.2f\n",futurePrice.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long retrivelTime = (System.nanoTime() - start)/1_000_000;
        System.out.println("retrivelTime returned after " + retrivelTime + " msecs");


        List<Shop> shops = List.of(new Shop("BestShop"),new Shop("ButItAll"),new Shop("SellItOut"));
        String product = "product";
        long start1 = System.nanoTime();
        List<String> prices = shops.stream().map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product))).collect(Collectors.toList());
        long invocationTime1 = (System.nanoTime()-start)/1_000_000;
        System.out.println("simple Invacation returned after "+invocationTime1+" msecs");
//        System.out.println(prices);

        long start2 = System.nanoTime();
        List<String> prices1 = shops.parallelStream().map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product))).collect(Collectors.toList());
        long invocationTime2 = (System.nanoTime()-start2)/1_000_000;
        System.out.println("parallelStream Invacation returned after "+invocationTime2+" msecs");
//        System.out.println(prices1);

    }

}
