package thread_basic.waitThreadFinish;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DataSourceLoader dsLoader = new DataSourceLoader();
        NetworkConnectionsLoader ncLoadder = new NetworkConnectionsLoader();
        Thread dsThread = new Thread(dsLoader);
        Thread ncThread = new Thread(ncLoadder);
        dsThread.start();
        ncThread.start();
        try {
            dsThread.join();
            ncThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       System.out.printf("Main:configuration has been finished :%s \n",new Date());
    }


}
