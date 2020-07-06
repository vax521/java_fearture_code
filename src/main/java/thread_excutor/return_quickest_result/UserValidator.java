package thread_excutor.return_quickest_result;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {
    private  final String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public boolean validator(String name,String password){
        Random random = new Random();
        long duration = (long)(Math.random()*10);
        System.out.printf("Validator: %s validate a user during %d seconds\n",this.getName(),duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextBoolean();
    }

    public String getName() {
        return name;
    }
}
