package additional_tips;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch implements Runnable{

    private String initPath;

    private String end;

    List<String> searchResults;

    public FileSearch(String initPath, String end) {
        this.initPath = initPath;
        this.end = end;
        searchResults = new ArrayList<>();
    }

    private void directoryProcess(File file){
        File[]  files = file.listFiles();
        if (files!=null){
            for (int i = 0; i < files.length; i++) {
                File tempFile = files[i];
                if(tempFile.isDirectory()){
                    directoryProcess(tempFile);
                }else {
                    fileProcess(tempFile);
                }
            }
        }

    }
    private void fileProcess(File file){
        if (file.getName().endsWith(end)){
            searchResults.add(file.getAbsolutePath());
        }
    }

  public List<String> getSearchResults(){
        return searchResults;
  }

    @Override
    public void run() {
        System.out.printf("%s starting\n",Thread.currentThread().getName());
        File file = new File(initPath);
        if(file.isDirectory()){
          directoryProcess(file);
        }
    }
}
