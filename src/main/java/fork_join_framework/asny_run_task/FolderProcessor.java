package fork_join_framework.asny_run_task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountedCompleter;

public class FolderProcessor extends CountedCompleter {

    private String path;

    private String extension;

    private List<FolderProcessor> folderProcessors;

    private List<String> resultList;

    public FolderProcessor(CountedCompleter<?> countedCompleter,String path, String extension) {
        super(countedCompleter);
        this.path = path;
        this.extension = extension;
    }

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public List<FolderProcessor> getFolderProcessors() {
        return folderProcessors;
    }

    public void setFolderProcessors(List<FolderProcessor> folderProcessors) {
        this.folderProcessors = folderProcessors;
    }

    public List<String> getResultList() {
        return resultList;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }

    @Override
    public void compute() {
        resultList = new ArrayList<>();
        folderProcessors = new ArrayList<>();
        File file = new File(path);
        File[] content = file.listFiles();
        if (content != null){
            for (int i = 0; i < content.length; i++) {
                if(content[i].isDirectory()){
                    FolderProcessor folderProcessor = new FolderProcessor(this,content[i].getAbsolutePath(),extension);
                    folderProcessor.fork();
                    addToPendingCount(1);
                    folderProcessors.add(folderProcessor);
                }else {
                    if(checkFile(content[i].getName())){
                        resultList.add(content[i].getAbsolutePath());
                    }
                }
                if(folderProcessors.size() >50){
                    System.out.printf("%s: %d tasks ran\n",file.getAbsolutePath(),folderProcessors.size());
                }
            }
            tryComplete();
        }
    }

    @Override
    public void onCompletion(CountedCompleter caller) {
        for(FolderProcessor folderProcessor: folderProcessors){
            resultList.addAll(folderProcessor.getResultList());
        }
    }

    public boolean checkFile(String name){
        return name.endsWith(extension);
    }
}
