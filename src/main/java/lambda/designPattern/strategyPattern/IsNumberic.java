package lambda.designPattern.strategyPattern;

public class IsNumberic implements ValidationStrategy {
    @Override
    public boolean excute(String s) {
        return s.matches("\\d+");
    }
}
