package future.asynchronous;

import java.util.concurrent.*;

public class FutureAsynchronous {
    public static void doSomeThingElse(){
        System.out.println("something else .....");
    }
    public static void main(String[] args) {
        // 使用Future以异步的方式执行一个耗时的操作
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                Thread.sleep(2000);
                return 0.001;
            }
        });
        doSomeThingElse();
        try {
            Double result = future.get(5, TimeUnit.SECONDS);
            System.out.println(result);
        }catch (InterruptedException e) {
            //当前线程等待过程中被中断
            e.printStackTrace();
        } catch (ExecutionException e) {
            //计算抛出异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            //Future 对象完成之前已过期
            System.out.println("Future对象完成之前已过期");
            e.printStackTrace();
        }

        executorService.shutdown();
    }


}
