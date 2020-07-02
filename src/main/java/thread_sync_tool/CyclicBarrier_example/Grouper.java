package thread_sync_tool.CyclicBarrier_example;

public class Grouper implements Runnable {
    private Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Thread:%s Processing the result\n",Thread.currentThread().getName());
        int[] data = results.getData();
        for(int number:data){
            finalResult += number;
        }
        System.out.printf("Grouper: Total result:%d\n",finalResult);
    }
}
