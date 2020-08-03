package custom_concurrent_class.custom_atomic_object;

import thread_synchronization.method_sync.no_sync_case.Sensor;

public class Main {
    public static void main(String[] args) {
        ParkingCounter counter = new ParkingCounter(100);
        Sensor1 sensor1 = new Sensor1(counter);
        Sensor2 sensor2 = new Sensor2(counter);
        Thread thread1 = new Thread(sensor1);
        Thread thread2 = new Thread(sensor2);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main:Number of cars %d\n",counter.get());
        System.out.println("Main:end");
    }
}
