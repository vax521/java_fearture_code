package basic;

public class threadClose {
    public static void main(String[] args) {
        System.out.println("maintainer!");
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true){
                    System.out.println("executing...");
                    try {
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.setDaemon(true);
        t1.start();
        System.out.println("main thread exit!");
    }
}
