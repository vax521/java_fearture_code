package custom_concurrent_class.custom_thread_factory;

public class Main {
    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");
        MyTask myTask = new MyTask();
        Thread thread  = myThreadFactory.newThread(myTask);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Info:%s\n",thread);
    }
}
