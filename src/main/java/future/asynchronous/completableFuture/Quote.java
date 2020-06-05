package future.asynchronous.completableFuture;

public class Quote {
    private final String shopName;
    private final double price;
    private final Discount.Code code;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public static  Quote parse(String s){
           String[] result = s.split(":");
           String shopName = result[0];
           double price  = Double.parseDouble(result[1]);
           Discount.Code code = Discount.Code.valueOf(result[2]);
           return  new Quote(shopName,price,code);
    }
    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }
}

