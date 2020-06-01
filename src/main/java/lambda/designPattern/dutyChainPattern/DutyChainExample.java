package lambda.designPattern.dutyChainPattern;

public class DutyChainExample {
    public static void main(String[] args) {
        //普通使用方式
        ProcessingObject<String> p1 = new HeaderTextProcessor();
        System.out.println(p1.handle("Arent labda very good?"));
        ProcessingObject<String> p2 = new SpellCheckProcessor();
        System.out.println(p2.handle("Arent labda very good?"));
        p1.setProcessingObject(p2);
        System.out.println(p1.handle("Arent labda very good?"));

    }
}