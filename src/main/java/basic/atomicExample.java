package basic;

import java.util.concurrent.atomic.AtomicInteger;

public class atomicExample {
    private static AtomicInteger count = new AtomicInteger(0);

    public static int add(){
        return count.getAndIncrement();
    }
    public static int decer(){
        return count.getAndDecrement();
    }
    public static void main(String[] args) {
        add();
        add();
        System.out.println(count);

    }
}
