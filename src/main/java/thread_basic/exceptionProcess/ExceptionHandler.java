package thread_basic.exceptionProcess;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An Exception has been captured!");
        System.out.printf("Thread id :%s \n",t.getId());
        System.out.printf("Thread name :%s\n",t.getName());
        e.printStackTrace(System.out);
        System.out.printf("Thread status:%s\n",t.getState());
    }
}
