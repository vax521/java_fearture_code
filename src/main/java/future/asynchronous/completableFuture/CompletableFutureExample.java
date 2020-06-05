package future.asynchronous.completableFuture;

import java.util.List;
import java.util.concurrent.*;
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


        List<Shop> shops = List.of(new Shop("BestShop"),new Shop("ButItAll"),new Shop("SellItOut"),new Shop("SellItOut")
                ,new Shop("SellItOut"),new Shop("SellItOut"),new Shop("SellItOut"));
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

        //使用CompletableFuture实现
        long start3 = System.nanoTime();
        List<CompletableFuture<String>> priceFutures = shops.stream().map(shop -> CompletableFuture.supplyAsync(()-> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))).collect(Collectors.toList());
        List<String> prices2 = priceFutures.stream()
                 .map(CompletableFuture::join)//等待所有异步操作结束
                .collect(Collectors.toList());
        System.out.println(prices2);
        long invocationTime3 = (System.nanoTime()-start2)/1_000_000;
        System.out.println("异步 Invacation returned after "+invocationTime3+" msecs");


        //使用定制的执行器
        final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100),(Runnable r)->{
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        //创建更适合你的应用特性的执行器，利用CompletableFuture向其提交任务执行是个不错的主意。处理需大量使用异步操作的情况时，这几乎是最有效的策略。
        long start4 = System.nanoTime();
        List<CompletableFuture<String>> priceFuturesExcu = shops.stream().map(shop -> CompletableFuture.supplyAsync(()-> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product),executor))).collect(Collectors.toList());
        List<String> prices3= priceFuturesExcu.stream()
                .map(CompletableFuture::join)//等待所有异步操作结束
                .collect(Collectors.toList());
        System.out.println(prices3);
        long invocationTime4 = (System.nanoTime()-start2)/1_000_000;
        System.out.println("Excutor Invacation returned after "+invocationTime4+" msecs");
    }

}
