package thread_synchronization.method_sync.sync_case;

public class Main {
    public static void main(String[] args) {
        thread_synchronization.method_sync.sync_case.ParkingCash parkingCash = new thread_synchronization.method_sync.sync_case.ParkingCash();
        thread_synchronization.method_sync.sync_case.ParkingStats parkingStats = new thread_synchronization.method_sync.sync_case.ParkingStats(parkingCash);
        System.out.println("Parking Simulator:");
        int numSensors = 2*Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numSensors];
        for (int i = 0; i < numSensors; i++) {
            thread_synchronization.method_sync.sync_case.Sensor sensor = new thread_synchronization.method_sync.sync_case.Sensor(parkingStats);
            Thread thread = new Thread(sensor);
            thread.start();
            threads[i] =  thread;
        }
        for (int i = 0; i < numSensors; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Number of cars: %s \n",parkingStats.getNumCars());
        System.out.printf("Number of motors: %s \n",parkingStats.getNumMotros());
        parkingCash.close();
    }
}
