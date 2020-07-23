package concurrent_collection.atomic_variable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Account {
    private final AtomicLong balance;
    private final LongAdder opreation;
    private final DoubleAccumulator commission;

    public Account() {
        this.balance = new AtomicLong();
        this.opreation = new LongAdder();
        this.commission = new DoubleAccumulator((x,y)->x+y*0.2,0);
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public LongAdder getOpreation() {
        return opreation;
    }

    public DoubleAccumulator getCommission() {
        return commission;
    }

    public void setBalance(long balance){
        this.balance.set(balance);
        this.opreation.reset();
        this.commission.reset();
    }


    public void addAmount(long amount){
        this.balance.getAndAdd(amount);
        this.opreation.increment();
        this.commission.accumulate(amount);
    }
}
