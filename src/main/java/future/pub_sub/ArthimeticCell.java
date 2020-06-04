package future.pub_sub;

public class ArthimeticCell extends SimpleCell {
    private  int left;
    private int right;

    public ArthimeticCell(String name) {
        super(name);
    }

    public void setRight(int right){
        this.right = right;
        onNext(left+ this.right);
    }

    public void setLeft(int left){
        this.left = left;
        onNext(this.left + right);
    }

    public static void main(String[] args) {
          ArthimeticCell  c3 = new ArthimeticCell("c3");
          SimpleCell c1 = new SimpleCell("c1");
          SimpleCell c2 = new SimpleCell("c2");

          c1.subscribe(c3::setLeft);
          c2.subscribe(c3::setRight);

          c1.onNext(10);
          c2.onNext(20);

    }
}
