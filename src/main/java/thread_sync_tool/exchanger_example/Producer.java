package thread_sync_tool.exchanger_example;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int cycle = 0; cycle < 10; cycle++) {
            System.out.printf("Producer Cycle:%d\n",cycle);
            for (int i = 0; i < 10; i++) {
                String message = "Event-"+(cycle-1)*10+"-"+i;
                System.out.println(message);
                buffer.add(message);
            }
        }
        try {
            buffer = exchanger.exchange(buffer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Produce:" + buffer.size());
    }
}
