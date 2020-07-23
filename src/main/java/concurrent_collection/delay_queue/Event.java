package concurrent_collection.delay_queue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Event implements Delayed {
    private final Date startDate;

    public Event(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Date dateNow = new Date();
        long diff = startDate.getTime() - dateNow.getTime();
        return unit.convert(diff,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long result = this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS);
        if(result > 0){
            return 1;
        }else if(result < 0){
            return -1;
        }else {
            return 0;
        }
    }
}
