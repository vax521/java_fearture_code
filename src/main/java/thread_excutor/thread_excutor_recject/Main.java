package thread_excutor.thread_excutor_recject;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        System.out.println("Main: starting...");
        for (int i = 0; i < 100; i++) {
            Task  task = new Task("Task-"+i);
            server.executeTask(task);
        }
        System.out.println("Main: shutting down the excutor...");
        server.endServer();
        System.out.println("Main: sending the other task...");
        server.executeTask(new Task("Rejected Task"));
        System.out.println("Main: end");
    }
}
