package lambda.designPattern.strategyPattern;

public class ValidatorTest {
    public static void main(String[] args) {
//        Validator numbericValidator = new Validator(new IsNumberic());
        //使用lambada表达式
        Validator numbericValidator = new Validator((String s)->s.matches("\\d+"));
        System.out.println(numbericValidator.excute("aaa"));

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        System.out.println(lowerCaseValidator.excute("aaa"));

    }
}
