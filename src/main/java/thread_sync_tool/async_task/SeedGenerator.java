package thread_sync_tool.async_task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class SeedGenerator implements Runnable {
    private CompletableFuture<Integer> resultCommunicator;

    public SeedGenerator(CompletableFuture<Integer> resultCommunicator) {
        this.resultCommunicator = resultCommunicator;
    }

    @Override
    public void run() {
        System.out.print("Seed Generator: generating seed...\n");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int seed = (int)Math.rint(Math.random()*10);
        System.out.printf("Seed Generator: seed generated: %d\n",seed);
        resultCommunicator.complete(seed);
    }
}
