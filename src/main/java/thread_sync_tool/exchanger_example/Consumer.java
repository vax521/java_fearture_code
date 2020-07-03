package thread_sync_tool.exchanger_example;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int cycle = 0; cycle < 10; cycle++) {
            System.out.printf("Consumer Cycle:%d\n",cycle);
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Consumer :%d\n",buffer.size());
            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.println("Consumer:"+message);
                buffer.remove(0);
            }
        }
    }
}
