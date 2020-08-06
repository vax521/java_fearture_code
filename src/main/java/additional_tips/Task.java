package additional_tips;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task extends FutureTask<List<String>> {

    private FileSearch fileSearch;

    public Task(Callable<List<String>> callable) {
        super(callable);
    }

    public Task(Runnable runnable, List<String> result) {
        super(runnable, result);
        this.fileSearch = (FileSearch) runnable;
    }

    @Override
    protected void set(List<String> strings) {
        strings = fileSearch.getSearchResults();
        super.set(strings);
    }
}
