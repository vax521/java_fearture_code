package lambda.factoryPattern;

import lambda.factoryPattern.entity.Bond;
import lambda.factoryPattern.entity.Loan;
import lambda.factoryPattern.entity.Product;
import lambda.factoryPattern.entity.Stock;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("Loan",Loan::new);
        map.put("Stock",Stock::new);
        map.put("Bond",Bond::new);
    }
    public  static Product createProduct(String name){
        switch (name){
            case "Loan": return new Loan();
            case "Stock": return new Stock();
            case "Bond": return new Bond();
            default: new Product("default");
        }
        return new Product("default");
    }
    public  static Product createProductByMap(String name){
        Supplier<Product> p = map.get(name);
        if( p!= null ){
                return p.get();
        }
        throw new IllegalArgumentException("No Such Product:"+ name);
    }
}
