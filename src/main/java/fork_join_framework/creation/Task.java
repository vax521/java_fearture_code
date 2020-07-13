package fork_join_framework.creation;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if(last - first < 10){
            updatePrices();
        }else {
            int middle = (last+first)/2;
            System.out.printf("Task: Pendding tasks:%s\n",getQueuedTaskCount());
            Task task1 = new Task(products,first,middle+1,increment);
            Task task2 = new Task(products,middle+1,last,increment);
            invokeAll(task1,task2);
        }

    }
    private void updatePrices(){
        for (int i = first; i < last; i++) {
            Product  product = products.get(i);
            product.setPrice((int) (product.getPrice()*(1+increment)));
        }
    }
    /*
    * ForkJoinTask类中的invokeAll()方法是Executor和fork/join框架最主要的区别之一。
    * 在Executor框架中，所有任务都提交给执行器来执行。而本案例中，任务包含执行和控制池中任务的方法。
    * 调用的invoke方法来自Task类，Task类继承了RecursiveAction类，而该类继承了ForkJoinTask类。
    * 用execute()方法提交唯一的任务到池中，从而更新列表中的全部产品。
    * 在本案例中，该方法以同步方式在主线程中调用。
    * 此外还使用了ForkJoinPool类中的部分方法来检查运行中任务的状态和执行情况。
    * 为了更好地实现本节目的，该类还提供了其他方法，在9.5节中，有这些方法的完整列表。*/
}
