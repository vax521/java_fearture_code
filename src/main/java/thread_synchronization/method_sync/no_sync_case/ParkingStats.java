package thread_synchronization.method_sync.no_sync_case;

public class ParkingStats {
    private long numCars;
    private long numMotros;
    private thread_synchronization.method_sync.no_sync_case.ParkingCash parkingCash;

    public ParkingStats(thread_synchronization.method_sync.no_sync_case.ParkingCash parkingCash){
        numCars = 0;
        numMotros = 0;
        this.parkingCash = parkingCash;
    }

    public void carComein(){
        numCars++;
    }

    public long getNumCars() {
        return numCars;
    }

    public long getNumMotros() {
        return numMotros;
    }

    public void carComout(){
        numCars--;
        parkingCash.vehiclePlay();
    }

    public void motroComin(){
        numMotros++;
    }

    public void motroComeout(){
        numMotros--;
        parkingCash.vehiclePlay();
    }


}
