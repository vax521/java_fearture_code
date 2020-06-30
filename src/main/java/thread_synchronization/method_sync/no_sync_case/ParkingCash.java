package thread_synchronization.method_sync.no_sync_case;

public class ParkingCash {

    private static final int cost = 2;

    private long cash;

    public ParkingCash(){
        cash = 0;
    }

    public void vehiclePlay(){
        cash += cost;
    }

    public  void  close(){
        System.out.println("Closing accounting!");
        long totalAccount;
        totalAccount = cash;
        cash = 0;
        System.out.printf("Total account is %s\n",totalAccount);
    }
}
