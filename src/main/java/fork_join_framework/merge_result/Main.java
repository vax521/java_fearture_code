package fork_join_framework.merge_result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100,1000,"the");
        DocumentTask documentTask = new DocumentTask(document,0,100,"the");
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        commonPool.execute(documentTask);
        while (!documentTask.isDone()){
            System.out.printf("Main:Active Threads:%s\n",commonPool.getActiveThreadCount());
            System.out.printf("Main:Task Count:%s\n",commonPool.getQueuedTaskCount());
            System.out.printf("Main:Steal Count:%s\n",commonPool.getStealCount());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        commonPool.shutdown();
        try {
            commonPool.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.printf("The document has %s words\n",documentTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
