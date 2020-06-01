package lambda.factoryPattern.entity;

public class Loan extends Product {
    public   String name;

    public Loan() {
      this.name = "Loan";
    }

    public Loan(String name) {
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
