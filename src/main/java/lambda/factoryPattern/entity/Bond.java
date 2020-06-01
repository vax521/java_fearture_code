package lambda.factoryPattern.entity;

public class Bond extends Product {
    public   String name;

    public Bond() {
        this.name = "Bond";
    }

    public Bond(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
