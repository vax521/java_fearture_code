package thread_basic.threadSleepAwake;

import java.util.concurrent.TimeUnit;

public class Main {
    /*
    * 秒
    *
    *
    * */
    public static void main(String[] args) {
        ConsoleClock consoleClock = new ConsoleClock();
        Thread thread = new Thread(consoleClock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
