package lambda.factoryPattern;

import lambda.factoryPattern.entity.Loan;
import lambda.factoryPattern.entity.Product;

import java.nio.file.LinkOption;
import java.util.function.Supplier;

public class factoryPattern {
    public static void main(String[] args) {
      /*  Product p = ProductFactory.createProduct("Loan");
        System.out.println(p.getName());
        Product p1 = ProductFactory.createProduct("Stock");
        System.out.println(p1.getName());
        Product p2 = ProductFactory.createProduct("Bond");
        System.out.println(p2.getName());*/

        Supplier<Loan> loanSupplier = Loan::new;
        Loan  loan= loanSupplier.get();
        System.out.println(loan.getName());

        Product p = ProductFactory.createProductByMap("Loan");
        System.out.println(p.getName());
        Product p7 = ProductFactory.createProductByMap("Test");
        System.out.println(p7.getName());

    }

}
