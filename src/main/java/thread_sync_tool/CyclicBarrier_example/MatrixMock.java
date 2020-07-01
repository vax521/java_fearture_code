package thread_sync_tool.CyclicBarrier_example;

import java.util.Random;

public class MatrixMock  {
    private final int[][] data;

    public MatrixMock(int rowNum,int columnNum,int number){
        int counter = 0;
        data = new int[rowNum][columnNum];
        Random random = new Random();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                data[i][j] = random.nextInt(10);
                if(data[i][j] == number){
                    counter ++;
                }
            }
        }
        System.out.printf("Mock: There are %s number in generated data!\n",counter);
    }

    public int[] getRow(int rowId){
        if(rowId >= 0 && rowId< data.length){
            return data[rowId];
        }
        return null;
    }

}
