package thread_excutor.completion_service_example;

import java.util.concurrent.*;

public class ReportProcessor implements Runnable {
    private final CompletionService<String> completionService;
    private volatile boolean end;

    public ReportProcessor(CompletionService<String> completionService) {
        this.completionService = completionService;
        this.end = false;
    }

    @Override
    public void run() {
        while (!end){
            try {
                Future<String> stringFuture = completionService.poll(20, TimeUnit.SECONDS);
                if(stringFuture != null){
                    String report  = stringFuture.get();
                    System.out.printf("Report Processor: Report received:%s\n",report);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Report Sender: end.");

    }

    public void stopProcessing(){
        this.end = true;
    }
}
