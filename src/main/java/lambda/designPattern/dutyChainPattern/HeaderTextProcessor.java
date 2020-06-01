package lambda.designPattern.dutyChainPattern;

public class HeaderTextProcessor extends ProcessingObject<String> {
    @Override
    protected String handleTask(String input) {
        return "From Alan "+input;
    }
}
