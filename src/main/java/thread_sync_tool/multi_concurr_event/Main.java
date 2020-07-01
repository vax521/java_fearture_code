package thread_sync_tool.multi_concurr_event;

public class Main {
    public static void main(String[] args) {
        VideoConference videoConference = new VideoConference(10);
        Thread threadConference = new Thread(videoConference);
        threadConference.start();
        for (int i = 0; i < 10; i++) {
            Paticipant paticipant = new Paticipant(videoConference,"paticipant-"+i);
            Thread t = new Thread(paticipant);
            t.start();
        }
    }
}
