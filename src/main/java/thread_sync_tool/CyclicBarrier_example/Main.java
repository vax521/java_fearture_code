package thread_sync_tool.CyclicBarrier_example;


import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;

        MatrixMock matrixMock = new MatrixMock(ROWS,NUMBERS,SEARCH);
        Results results = new Results(ROWS);
        Grouper grouper = new Grouper(results);
        //创建一个名为barrier的CyclicBarrier对象，用于等待5个线程。当这5个线程执行完毕后，它会执行之前创建的Grouper对象
        CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS,grouper);
        Seacher[] seachers = new Seacher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            seachers[i] = new Seacher(i*LINES_PARTICIPANT,(i*LINES_PARTICIPANT)+LINES_PARTICIPANT,matrixMock,results,5,cyclicBarrier);
            Thread t = new Thread(seachers[i]);
            t.start();
        }
        System.out.println("The main thread has finished!");
    }

}
