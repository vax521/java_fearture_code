package thread_synchronization.sync_read_write_block;

import java.util.Date;

public class PriceWriter implements Runnable {

    private PriceInfo priceInfo;

    public PriceWriter(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s: Writer start change price\n",new Date());
            priceInfo.setPrices(Math.random()*10,Math.random()*8);
            System.out.printf("%s: Writer finish change price\n",new Date());
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
