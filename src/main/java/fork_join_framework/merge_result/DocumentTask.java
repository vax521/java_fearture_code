package fork_join_framework.merge_result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer> {
    private String[][] document;
    private int start,end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {

        return null;
    }

    private Integer processLines(String[][] document,int start,int end,String word){
        List<LineTask> lineTasks = new ArrayList<>();
        for (int i = 0; i < document.length; i++) {
            LineTask lineTask = new LineTask(document[i],0,document[i].length,word);
            lineTasks.add(lineTask);
        }
        invokeAll(lineTasks);
        int result = 0;
        for (int i = 0; i < lineTasks.size(); i++) {
            LineTask lineTask = lineTasks.get(i);
            try {
                result += lineTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
