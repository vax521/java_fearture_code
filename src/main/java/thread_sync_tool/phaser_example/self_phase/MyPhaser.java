package thread_sync_tool.phaser_example.self_phase;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                return studentsArrived();
            case 1:
                return  finishFirstExerise();
            case 2:
                return finishSecondExerise();
            case 3:
                return finishExam();
                default:
                    return   true;
        }

    }

    private boolean studentsArrived(){
        System.out.println("Phaser: All students have arrived.");
        System.out.printf("Phaser:We have %d students.\n",getArrivedParties());
        return false;
    }
    private boolean finishFirstExerise(){
        System.out.println("Phaser:All students finish first exercise.");
        System.out.println("Phaser:It is turn to start second.");
        return  false;
    }
    private boolean finishSecondExerise(){
        System.out.println("Phaser:All students finish second exercise.");
        System.out.println("Phaser:It is turn to start third.");
        return  false;
    }
    private boolean finishExam(){
        System.out.println("Phaser:All students finish exam.");
        System.out.println("Phaser:Thank you for your time.");
        return  true;
    }
}


