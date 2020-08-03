package custom_concurrent_class.custom_atomic_object;

public class Sensor2 implements Runnable {
    private ParkingCounter counter;

    public Sensor2(ParkingCounter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        counter.CarIn();
        counter.CarIn();
        counter.CarIn();
        counter.CarOut();
        counter.CarOut();
        counter.CarIn();
        counter.CarIn();
        counter.CarIn();
        counter.CarIn();
        counter.CarOut();
        counter.CarIn();
        counter.CarIn();
    }
}
