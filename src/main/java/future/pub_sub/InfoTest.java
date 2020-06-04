package future.pub_sub;

public class InfoTest {
    public static void main(String[] args) {
        SimpleCell simpleCell1 = new SimpleCell("c1");
        SimpleCell simpleCell2 = new SimpleCell("c2");
        SimpleCell simpleCell3 = new SimpleCell("c3");
        //c3 订阅 c1
        simpleCell1.subscribe(simpleCell3);

        //发布新的值10
        simpleCell1.onNext(10);
        simpleCell2.onNext(20);
    }
}
