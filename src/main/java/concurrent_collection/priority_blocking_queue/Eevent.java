package concurrent_collection.priority_blocking_queue;



public class Eevent implements Comparable<Eevent> {
    private final int threadId;

    private final int priority;

    public Eevent(int threadId, int priority) {
        this.threadId = threadId;
        this.priority = priority;
    }

    public int getThreadId() {
        return threadId;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Eevent o) {
        if (this.priority > o.getPriority()){
            return -1;
        }else if (this.priority < o.getPriority()){
            return 1;
        }else {
            return 0;
        }
    }
}
