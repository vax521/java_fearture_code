package thread_synchronization.method_sync.sync_case;

public class ParkingCash {

    private static final int cost = 2;

    private long cash;

    public ParkingCash(){
        cash = 0;
    }

    public synchronized void vehiclePlay(){
        cash += cost;
    }

    public  void  close(){
        System.out.println("Closing accounting");
        long totalAccount;
        synchronized (this){
            totalAccount = cash;
            cash = 0;
        }
        System.out.printf("Total account is %s\n",totalAccount);
    }
}
