package thread_sync_tool.phaser_example.self_phase;

import util.DateUtil;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable {
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf("%s: has arrived to do the exam at %s.\n", Thread.currentThread().getName(), DateUtil.getCurrTime());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: start to do the first exercise at %s.\n", Thread.currentThread().getName(), DateUtil.getCurrTime());
        doExercise1();
        System.out.printf("%s: finish to do the first exercise at %s.\n", Thread.currentThread().getName(), DateUtil.getCurrTime());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: start to do the second exercise at %s.\n", Thread.currentThread().getName(), DateUtil.getCurrTime());
        doExercise2();
        System.out.printf("%s: finish to do the second exercise at %s.\n", Thread.currentThread().getName(), DateUtil.getCurrTime());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: start to do the third exercise at %s.\n", Thread.currentThread().getName(), DateUtil.getCurrTime());
        doExercise2();
        System.out.printf("%s: finish the exam at %s.\n", Thread.currentThread().getName(), DateUtil.getCurrTime());
        phaser.arriveAndAwaitAdvance();
    }

        private void doExercise1(){
        long durarion = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(durarion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExercise2(){
        long durarion = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(durarion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExercise3(){
        long durarion = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(durarion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
