package thread_sync_tool.phaser_example;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearcher implements Runnable {
    private final String initPath;

    private final String fileExtension;

    private List<String> results;

    private Phaser phaser;

    public FileSearcher(String initPath, String fileExtension, Phaser phaser) {
        this.initPath = initPath;
        this.fileExtension = fileExtension;
        this.results = new ArrayList<>();
        this.phaser = phaser;
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s started!\n",Thread.currentThread().getName());
        File file = new File(initPath);
        if(file.isDirectory()){
            directoryProcess(file);
        }
        if(!checkResults()){
            return;
        }
        filterResults();
        if(!checkResults()){
            return;
        }
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed!\n",Thread.currentThread().getName());

    }
    private void directoryProcess(File file){
        File[] fileList = file.listFiles();
        if(fileList!=null){
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    directoryProcess(aFileList);
                } else {
                    fileProcess(aFileList);
                }
            }
        }
    }

    private void fileProcess(File file){
        if(file.getName().endsWith(fileExtension)){
            results.add(file.getAbsolutePath());
        }
    }

    private void filterResults(){
        List<String> newResults = new ArrayList<>();
        long nowTime = new Date().getTime();
        for(String filepath :results){
            File file = new File(filepath);
            long fileLastChangeTime = file.lastModified();
            if(nowTime - fileLastChangeTime < TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS)){
                newResults.add(filepath);
            }
        }
        results = newResults;
    }

    private boolean checkResults(){
        if(results.isEmpty()){
            System.out.printf("%s:Phase:%s 0 results\n",Thread.currentThread().getName(),phaser.getPhase());
            System.out.printf("%s:Phase:%s end\n",Thread.currentThread().getName(),phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        }else {
            System.out.printf("%s:Phase:%s: %d results\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo(){
        for(String filePath : results){
            File file = new File(filePath);
            System.out.printf("%s:%s\n",Thread.currentThread().getName(),file.getAbsoluteFile());
        }
        phaser.arriveAndAwaitAdvance();
    }
}
