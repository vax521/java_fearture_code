package thread_excutor.return_quickest_result;

import java.util.concurrent.Callable;

public class ValidateTask implements Callable<String> {
    private final UserValidator userValidator;
    private final String userName;
    private final String password;

    public ValidateTask(UserValidator userValidator, String userName, String password) {
        this.userValidator = userValidator;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        if(!userValidator.validator(userName,password)){
            System.out.printf("%s: The User has not been found!\n",userValidator.getName());
            throw new Exception("error validating user");
        }
        System.out.printf("%s: The User has been found!\n",userValidator.getName());
        return userValidator.getName();
    }
}
