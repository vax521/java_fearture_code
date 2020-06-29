package thread_basic.waitThreadFinish;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourceLoader implements Runnable{
    @Override
    public void run() {
        System.out.printf("Begin data source load:%s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Finish data source load:%s\n",new Date());
    }
}
