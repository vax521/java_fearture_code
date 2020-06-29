package thread_basic.threadGroup;

public class MyThreadGroup  extends ThreadGroup{

    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("Thread %s throws exception!\n",t.getName());
        e.printStackTrace(System.out);
        System.out.println("Terminate all rest threads!");
        interrupt();
    }
}
