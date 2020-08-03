package custom_concurrent_class.custom_async_stream;

import java.util.concurrent.Flow;

public class MySubscriprion implements Flow.Subscription {
    private boolean canced = false;

    private long requested = 0;

    @Override
    public void request(long n) {
        requested += n;
    }

    @Override
    public void cancel() {
        canced = true;
    }

    public boolean isCanced() {
        return canced;
    }

    public long getRequested() {
        return requested;
    }

    public void decreaseRequested(){
        requested--;
    }
}
