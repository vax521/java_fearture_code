package thread_basic.threadFactory;

public class Main {
    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("myThreadFactory");
        Task task = new Task();
        Thread thread;
        System.out.println("Starting the threads....");
        for (int i = 0; i < 10; i++) {
            thread = myThreadFactory.newThread(task);
            thread.start();
        }
        System.out.println("Factory status:");
        System.out.printf("%s\n",myThreadFactory.getStats());
    }
}
