package thread_sync_tool.phaser_example;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearcher systemSearcher = new FileSearcher("C:\\Windows","log",phaser);
        FileSearcher appsSearcher = new FileSearcher("C:\\Program Files","log",phaser);
        FileSearcher documentsSearcher = new FileSearcher("C:\\Documents","log",phaser);

        Thread systemThread = new Thread(systemSearcher);
        systemThread.start();
        Thread appsThread = new Thread(appsSearcher);
        appsThread.start();
        Thread documentThread = new Thread(documentsSearcher);
        documentThread.start();
        try {
            systemThread.join();
            appsThread.join();
            documentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Terminated: "+phaser.isTerminated());
    }
}
