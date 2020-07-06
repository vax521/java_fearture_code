package thread_excutor.run_task_get_result;

import java.util.concurrent.Callable;

public class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if(number==0 || number==1){
            result = 1;
        }else {
            for (int i = 2; i <= number; i++) {
                result *= i;
            }
        }
        System.out.printf("Thread %s: Seed:%d Result %d\n",Thread.currentThread().getName(),number, result);
        return result;
    }
}
