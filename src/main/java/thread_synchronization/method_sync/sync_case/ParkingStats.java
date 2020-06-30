package thread_synchronization.method_sync.sync_case;

public class ParkingStats {
    private long numCars;
    private long numMotros;
    private thread_synchronization.method_sync.sync_case.ParkingCash parkingCash;
    /*使用不同的对象来控制汽车和摩托车的计数值。在这里，一个Sensor任务线程可以修改numberCars的值，而同时另一个Sensor任务可以修改numberMotorcycles的值。然而，两个Sensor任务线程不能同时修改同一个属性，因此可以保证两类机动车计数值的最终结果是正确的。*/
    private final Object controlCars,controlMotors;

    public ParkingStats(thread_synchronization.method_sync.sync_case.ParkingCash parkingCash){
        numCars = 0;
        numMotros = 0;
        controlCars = new Object();
        controlMotors = new Object();
        this.parkingCash = parkingCash;
    }

    public void carComein(){
        synchronized (controlCars){
            numCars++;
        }
    }

    public long getNumCars() {
        return numCars;
    }

    public long getNumMotros() {
        return numMotros;
    }

    public void carComout(){
        synchronized (controlCars){
            numCars--;
        }
        parkingCash.vehiclePlay();
    }

    public void motroComin(){
        synchronized (controlMotors){
            numMotros++;
        }
    }

    public void motroComeout(){
        synchronized (controlMotors){
            numMotros--;
        }
        parkingCash.vehiclePlay();
    }


}
