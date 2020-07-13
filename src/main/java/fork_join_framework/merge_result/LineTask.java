package fork_join_framework.merge_result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class LineTask extends RecursiveTask<Integer> {

    private String[] line;

    private int start;

    private int end;

    private String word;

    public LineTask(String[] line, int start, int end, String word) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        Integer result = 0;
        if (end - start < 100){
            result = count(line,start,end,word);
        }else {
            int middle = (start+end)/2;
            LineTask lineTask1 = new LineTask(line,start,middle,word);
            LineTask lineTask2 = new LineTask(line,middle,end,word);
            invokeAll(lineTask1,lineTask2);
            try {
                result = lineTask1.get()+ lineTask2.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private Integer count(String[] line,int start,int end,String word){
        int result = 0;
        for (int i = start; i < end ; i++) {
            if(word.equals(line[i])){
                result++;
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
