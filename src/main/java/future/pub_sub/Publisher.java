package future.pub_sub;

public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}
