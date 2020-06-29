package thread_basic.exceptionProcess;

public class Main    {
    public static void main(String[] args) {
        ExcepTask excepTask = new ExcepTask();
        Thread thread = new Thread(excepTask);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
