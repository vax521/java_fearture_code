package custom_concurrent_class.custom_atomic_object;

public class Sensor1 implements Runnable {
    private ParkingCounter counter;

    public Sensor1(ParkingCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.CarIn();
        counter.CarIn();
        counter.CarIn();
        counter.CarOut();
        counter.CarOut();
        counter.CarOut();
        counter.CarIn();
        counter.CarIn();
        counter.CarOut();
        counter.CarIn();
        counter.CarIn();
    }
}
