package thread_synchronization.sync_read_write_block;

import java.util.Date;

public class PriceReader implements Runnable {
    private  PriceInfo priceInfo;

    public PriceReader(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            System.out.printf("%s:%s:price:%f\n",new Date(),Thread.currentThread().getName(),priceInfo.getPrice());
            System.out.printf("%s:%s:discountPrice:%f\n",new Date(),Thread.currentThread().getName(),priceInfo.getDiscountPrice());
        }
    }
}
