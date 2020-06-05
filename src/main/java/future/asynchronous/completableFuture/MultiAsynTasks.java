package future.asynchronous.completableFuture;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MultiAsynTasks {


    public static List<String> findPrices(List<Shop> shops, String product) {
        return shops.stream().map(shop -> shop.getPriceWithDiscount(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public static List<String> findPricesAsyn(List<Shop> shops, String product) {
        //使用定制的执行器
        final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100),(Runnable r)->{
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        List<CompletableFuture<String>> priceFutures = shops.stream()
                                        .map(shop -> CompletableFuture.supplyAsync(()->shop.getPriceWithDiscount(product),executor))
                                        .map(future->future.thenApply(Quote::parse))
                                        .map(future->future.thenCompose(quote->CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),executor)))
                .collect(Collectors.toList());

        return  priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());


    }

    public  static Stream<CompletableFuture<String>> findPricesStream(List<Shop> shops, String product){
        //使用定制的执行器
        final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100),(Runnable r)->{
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(()->shop.getPriceWithDiscount(product),executor))
                .map(future->future.thenApply(Quote::parse))
                .map(future->future.thenCompose(quote->CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),executor)));
    }

    public static void main(String[] args) throws InterruptedException {
        List<Shop> shops = List.of(new Shop("BestShop"), new Shop("ButItAll"), new Shop("SellItOut6"), new Shop("SellItOut677")
                , new Shop("SellItOut999"));
/*
        long start1 = System.nanoTime();
        System.out.println(findPrices(shops, "product"));
        System.out.println("Excutor Invacation returned after "+ (System.nanoTime()-start1)/1_000_000+" msecs");

        long start2 = System.nanoTime();
        System.out.println(findPricesAsyn(shops, "product"));
        System.out.println("Excutor Invacation returned after "+ (System.nanoTime()-start2)/1_000_000+" msecs");*/

        findPricesStream(shops,"product").map(future->future.thenAccept(System.out::println));
        CompletableFuture[] futures =
                findPricesStream(shops,"product").map(future->future.thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);
        /*
        allOf工厂方法接受一个由CompletableFuture构成的数组，数组中的所有CompletableFuture对象执行完成之后，
        它返回一个CompletableFuture<Void>对象。这意味着，如果你需要等待最初Stream中的所有CompletableFuture对象执行完毕，
        那么对allOf方法返回的CompletableFuture执行join操作是个不错的主意。
        */
        CompletableFuture.allOf(futures).join();
        /*你可能希望只要CompletableFuture对象数组中有任何一个执行完毕就不再等待，
        比如，你正在查询两个汇率服务器，任何一个返回了结果都能满足你的需求。
        在这种情况下，你可以使用一个类似的工厂方法anyOf。该方法接受一个CompletableFuture对象构成的数组，
        返回由第一个执行完毕的CompletableFuture对象的返回值构成的CompletableFuture<Object>。*/
        CompletableFuture.anyOf(futures).join();

    }

}
