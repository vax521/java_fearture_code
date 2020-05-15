package lambda;

public class LambdaInit {
    public static void main(String[] args) {
        Runnable t1 = () -> System.out.println("Hello");
        t1.run();
    }
}
