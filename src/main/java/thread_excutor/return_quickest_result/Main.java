package thread_excutor.return_quickest_result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String userName = "test";
        String password = "test";
        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DATABASE");
        ValidateTask ldapTask = new ValidateTask(ldapValidator,userName,password);
        ValidateTask dbTask = new ValidateTask(dbValidator,userName,password);
        List<ValidateTask> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);

        ExecutorService executorService = Executors.newCachedThreadPool();
        String result;

        try {
            result = executorService.invokeAny(taskList);
            System.out.println("Main: used validator " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Main: end excution");
    }
}

/*
* 本案例包含两个随机返回的布尔型的UserValidator对象。
* 每个UserValidator对象都由一个实现了Callable接口的TaskValidator对象所调用。
* 如果UserValidator类中的validate()方法返回false，则TaskValidator类会抛出一个异常；
* 否则返回true。若是提交了两个返回true或是抛出异常的任务，则它们会有如下4种可能。
* • 两个任务都返回true。然后invokeAny()方法会返回第一个执行完成任务的名称。
* • 第一个任务返回true，而第二个任务抛出异常。然后，invokeAny()方法会返回第一个任务的名称。
* • 第一个任务抛出异常，而第二个任务返回true。然后，invokeAny()方法会返回第二个任务的名称。
* • 两个任务都抛出异常。
* 在这种情况下，invokeAny()方法抛出一个Execution-Exception异常。
*
* */