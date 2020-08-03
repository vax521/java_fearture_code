package custom_concurrent_class.custom_atomic_object;

import java.util.concurrent.atomic.AtomicInteger;

public class ParkingCounter extends AtomicInteger {
    private final int maxNumber;

    public ParkingCounter( int maxNumber) {
        super(0);
        this.maxNumber = maxNumber;
    }

    public boolean CarIn(){
        for (;;) {
            int value = get();
            if(value == maxNumber){
                System.out.println("ParkingCounter:the parking lot is full");
                return false;
            }else {
                int newValue = value + 1;
                boolean changed = compareAndSet(value,newValue);
                if(changed){
                    System.out.println("ParkingCounter: A car entered!");
                    return true;
                }

            }
        }
    }

    public boolean CarOut(){
        for (;;) {
            int value = get();
            if(value == 0){
                System.out.println("ParkingCounter:the parking lot is empty");
                return false;
            }else {
                int newValue = value - 1;
                boolean changed = compareAndSet(value,newValue);
                if(changed){
                    System.out.println("ParkingCounter: A car leaved!");
                    return true;
                }

            }
        }
    }
}
