package thread_excutor.return_all_result;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SumTask implements Callable<SumResult> {
    private final String name;

    public SumTask(String name) {
        this.name = name;
    }

    @Override
    public SumResult call() throws Exception {
        System.out.printf("%s: start...\n",this.name);
        long duration = (long)(Math.random()*10);
        System.out.printf("%s: wait %s seconds for computing results\n",this.name,duration);
        TimeUnit.SECONDS.sleep(duration);
        int tempValue = 0;
        for (int i = 0; i < 5; i++) {
            tempValue += (int)(Math.random()*100);
        }
        SumResult sumResult = new SumResult(this.name,tempValue);
        return sumResult;
    }
}
