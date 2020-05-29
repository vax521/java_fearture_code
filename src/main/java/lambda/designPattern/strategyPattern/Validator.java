package lambda.designPattern.strategyPattern;

public class Validator {
    private ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy){
        this.validationStrategy = validationStrategy;
    }
    public boolean excute(String s){
        return  validationStrategy.excute(s);
    }
}
