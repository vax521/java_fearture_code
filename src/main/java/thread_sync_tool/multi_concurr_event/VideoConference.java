package thread_sync_tool.multi_concurr_event;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {
    private final CountDownLatch countDownLatch;

    public VideoConference(int num) {
        this.countDownLatch = new CountDownLatch(num);
    }

    public void arrive(String name){
        System.out.printf("%s has arrived!\n",name);
        countDownLatch.countDown();
        System.out.printf("VedioConference: Wait %s participants.\n",countDownLatch.getCount());
    }
    @Override
    public void run() {
        //调用CountDownLatch对象的getCount()方法，获取并输出与会人员的数量：
        System.out.printf("VedioConference: init: total %s participants.\n",countDownLatch.getCount());
        try {
            countDownLatch.await();
            System.out.println("VedioConference:All participants have come;Let us start!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
