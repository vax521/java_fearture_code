package future.pub_sub;

public interface Subscriber<T> {
    void onNext(T t);
}
