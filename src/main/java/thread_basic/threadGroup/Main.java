package thread_basic.threadGroup;

public class Main {
    public static void main(String[] args) {
        int numOfThreads = 2 * Runtime.getRuntime().availableProcessors();
        MyThreadGroup myThreadGroup = new MyThreadGroup("myThreadGroup");
        ErrorTask errorTask = new ErrorTask();
        for (int i = 0; i < numOfThreads; i++) {
            Thread t = new Thread(myThreadGroup,errorTask);
            t.start();
        }
        System.out.printf("Number of threads:%s\n",myThreadGroup.activeCount());
        System.out.println("Information of the thread group:");
        myThreadGroup.list();
        Thread[] threadList = new Thread[myThreadGroup.activeCount()];
        myThreadGroup.enumerate(threadList);
        for (int i = 0; i < myThreadGroup.activeCount(); i++) {
            System.out.printf("%s: %s: %s\n",threadList[i].getId(),threadList[i].getName(),threadList[i].getState());
        }
    }
}
