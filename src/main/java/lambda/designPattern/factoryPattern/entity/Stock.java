package lambda.designPattern.factoryPattern.entity;

public class Stock extends Product {
    public   String name;

    public Stock() {
        this.name = "Stock";
    }

    public Stock(String name) {
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
