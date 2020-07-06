package thread_excutor.run_task_get_result;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> futureList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer num = random.nextInt(10);
            FactorialCalculator factorialCalculator = new FactorialCalculator(num);
            Future<Integer> result = threadPoolExecutor.submit(factorialCalculator);
            futureList.add(result);
        }
        do{
            //在控制台打印日志，用执行器的getCompletedTaskNumber()方法来记录执行完成的任务数
            System.out.printf("Main: the number of completed tasks:%d\n",threadPoolExecutor.getCompletedTaskCount());
            //轮询列表中的10个Future对象，调用isDone()方法并打印日志，记录是否该任务已经执行完成
            for (int i = 0; i < futureList.size(); i++) {
                System.out.printf("Main:Task %s:  Status:%s\n",i,futureList.get(i).isDone());
            }
            //让当前线程休眠50ms
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (threadPoolExecutor.getCompletedTaskCount() < 10);
        //在控制台打印日志，记录每个任务的返回结果。在每个Future实例对象中，通过get()方法获取任务执行返回的Integer对象
        System.out.println("Main: Results");
        for (int i = 0; i < futureList.size(); i++) {
            Future<Integer> future = futureList.get(i);
            Integer num = null;
            try {
                num = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("Main:Task %d : Result %d\n",i, num);
        }
        threadPoolExecutor.shutdown();
    }
}
