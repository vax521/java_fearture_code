package thread_synchronization.sync_read_write_block;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PriceInfo {

    private double price;

    private double discountPrice;

    private ReadWriteLock readWriteLock;

    public PriceInfo() {
        this.price = 2.0;
        this.discountPrice = 1.0;
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public double getPrice(){
        readWriteLock.readLock().lock();
        double value = price;
        readWriteLock.readLock().unlock();
        return value;
    }

    public double getDiscountPrice() {
        readWriteLock.readLock().lock();
        double value = discountPrice;
        readWriteLock.readLock().unlock();
        return value;
    }

    public void setPrices(double price, double discountPrice){
        readWriteLock.writeLock().lock();
        System.out.printf("%s: PricesInfo: Write lock acquired!\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.price = price;
        this.discountPrice = discountPrice;
        System.out.printf("%s: PricesInfo: Write lock released!\n",new Date());
        readWriteLock.writeLock().unlock();
    }
}
