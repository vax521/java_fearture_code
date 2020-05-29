package lambda.designPattern.strategyPattern;

public class IsAllLowerCase implements ValidationStrategy {
    @Override
    public boolean excute(String s) {
        return s.matches("[a-z]+");
    }
}
