package thread_excutor.completion_service_example;

import java.util.concurrent.CompletionService;

public class ReportRequest implements Runnable {

    private final String name;

    private final CompletionService<String> completionService;

    public ReportRequest(String name, CompletionService<String> completionService) {
        this.name = name;
        this.completionService = completionService;
    }


    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name,"Report");
        completionService.submit(reportGenerator);

    }
}
