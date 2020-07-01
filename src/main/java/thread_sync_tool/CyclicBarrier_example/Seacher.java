package thread_sync_tool.CyclicBarrier_example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Seacher implements Runnable{
    private final int firstRow;
    private final int lastRow;
    private final MatrixMock matrixMock;
    private final Results results;
    private final int number;
    private final CyclicBarrier cyclicBarrier;

    public Seacher(int firstRow, int lastRow, MatrixMock matrixMock, Results results, int number, CyclicBarrier cyclicBarrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.matrixMock = matrixMock;
        this.results = results;
        this.number = number;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Process rows from %d to %d.\n",Thread.currentThread().getName(),firstRow,lastRow);
        for (int i = firstRow; i < lastRow; i++) {
            int[] row = matrixMock.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if(row[j] == number){
                    counter++;
                }
            }
            results.setData(i,counter);
        }
        System.out.printf("%s : Lined processed!\n",Thread.currentThread().getName());
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
