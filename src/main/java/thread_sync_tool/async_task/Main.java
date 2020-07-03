package thread_sync_tool.async_task;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main:Start");
        CompletableFuture<Integer> seedFuture = new CompletableFuture<>();
        SeedGenerator seedGenerator = new SeedGenerator(seedFuture);
        Thread seedThread = new Thread(seedGenerator);
        seedThread.start();

        System.out.println("Main: Getting the seed...");
        int seed= 0;
        try {
            seed = seedFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("The seed is "+ seed);

        System.out.println("Main: launching the list of numbers generator ");
        NumberListGenerator numberListGenerator = new NumberListGenerator(seed);
        CompletableFuture<List<Long>> startFuture = CompletableFuture.supplyAsync(numberListGenerator);
        System.out.println("Main: launching step-1\n");
        CompletableFuture<Long> step1Future = startFuture.thenApplyAsync(list->{
            System.out.printf("%s : step-1 start \n",Thread.currentThread().getName());
            long selected = 0L;
            long selectedDisance = Long.MAX_VALUE;
            long distacne;
            for(long number:list){
                distacne = Math.abs(number);
                if(distacne<selectedDisance){
                    selected = number;
                    selectedDisance = distacne;
                }
            }
            System.out.printf("%s : step-1 result: %s \n",Thread.currentThread().getName(),selected);
            return selected;
        });
        System.out.println("Main: launching step-2");
        CompletableFuture<Long> step2Future = startFuture.thenApplyAsync(list-> list.stream().max(Long::compareTo).get());
        CompletableFuture<Void> write2Future = step2Future.thenAccept(selected-> System.out.printf("%s : step-2 result: %s \n",Thread.currentThread().getName(),selected));
        System.out.println("Main: launching step-3");
        NumberSelector numberSelector = new NumberSelector();
        CompletableFuture<Long> step3Future = startFuture.thenApplyAsync(numberSelector);
        System.out.println("Waiting for the end of the three steps!");
        CompletableFuture<Void> waitFuture = CompletableFuture.allOf(step1Future,write2Future,step3Future);
        CompletableFuture<Void> finishFuture = waitFuture.thenAcceptAsync((param)->{
            System.out.println("Main: The completable future has been finished");
        });
        finishFuture.join();
    }
}
