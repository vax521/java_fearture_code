package thread_excutor.completion_service_example;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        ReportRequest faceRequest = new ReportRequest("Face",completionService);
        ReportRequest onlineRequest = new ReportRequest("Online",completionService);
        Thread  faceThread = new Thread(faceRequest);
        Thread onlineThead = new Thread(onlineRequest);
        ReportProcessor reportProcessor = new ReportProcessor(completionService);
        Thread senderThread = new Thread(reportProcessor);
        System.out.println("Main: starting the thead...");
        faceThread.start();
        onlineThead.start();
        senderThread.start();

        System.out.println("Main: Waiting for report generators.. ");
        try {
            faceThread.join();
            senderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: shutting down the  excutor.. ");
        executorService.shutdown();
        try {
            executorService.awaitTermination(2, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reportProcessor.stopProcessing();
        System.out.println("Main: ends");

    }
}
/*
* 本案例的main方法用Executor类的newCachedThreadPool()方法创建了ThreadPoolExecutor实例对象。
* 随后用该Executor对象来初始化一个Completion-Service实例对象，因为CompletionService类用一个执行器来执行任务。
* 在ReportRequest类中，则用submit()方法提交一个任务给CompletionService去执行。当CompletionService执行完成一个任务时，
* service变量在一个队列中存储Future对象，以监控任务的执行。poll()方法会访问队列并检查是否有任务执行完毕。
* 如果有的话，则返回队列中的第一个元素，该元素是一个执行完毕的任务对应的Future对象。当poll()方法返回一个Future对象时，
* 它会在队列中删除对应的Future对象。本案例在方法中传入两个变量来表示任务结束后的等待时间，这样可以防止存储执行完成后任务结果的队列是空的。
* 创建CompletionService对象后，可创建了两个ReportRequest对象来执行ReportGenerator任务。
* 其中执行ReportGenerator任务的ReportRequest对象会使用之前创建的CompletionService对象作为参数来初始化。
* */
