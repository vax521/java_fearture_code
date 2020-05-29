package lambda.dutyChainPattern;

public class SpellCheckProcessor extends ProcessingObject<String> {
    @Override
    protected String handleTask(String input) {
        return input.replace("labda","lambda");
    }
}
