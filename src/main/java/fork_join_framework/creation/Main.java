package fork_join_framework.creation;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ProductListGenerator productListGenerator = new ProductListGenerator();
        List<Product> products = productListGenerator.generate(20);
        Task task = new Task(products,0,products.size(),0.2);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(task);
        do {
            System.out.printf("Main: Thread Count:%s\n ",forkJoinPool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal:%s\n ",forkJoinPool.getStealCount());
            System.out.printf("Main: Parallelism:%s\n ",forkJoinPool.getParallelism());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!task.isDone());
        forkJoinPool.shutdown();
        if(task.isCompletedNormally()){
            System.out.println("Task completed normally!");
        }
        for (Product product : products) {
            if (product.getPrice() != 12) {
                System.out.printf("Product:%s,%s\n", product.getName(), product.getPrice());
            }

        }
    }
}
