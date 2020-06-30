package thread_synchronization.method_sync.no_sync_case;

import java.util.concurrent.TimeUnit;

public class Sensor implements Runnable {
    private thread_synchronization.method_sync.no_sync_case.ParkingStats parkingStats;

    public Sensor(thread_synchronization.method_sync.no_sync_case.ParkingStats parkingStats){
        this.parkingStats = parkingStats;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            parkingStats.carComein();
            parkingStats.carComein();
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            parkingStats.motroComin();
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            parkingStats.motroComeout();
            parkingStats.carComout();
            parkingStats.carComout();
        }
    }
}
