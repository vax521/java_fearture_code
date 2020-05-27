package lambda.StreamExample;

import lambda.StreamExample.entity.Trader;
import lambda.StreamExample.entity.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAction {
    public static void main(String[] args) {
        Trader raou1 = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raou1,2012,1000),
                new Transaction(raou1,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );

        // 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> transIn2010 = transactions.stream().
                filter(transaction -> transaction.getYear()==2011).
                sorted(Comparator.comparing(Transaction::getValue)).
                collect(Collectors.toList());
        System.out.println(transIn2010);

        //(2) 交易员都在哪些不同的城市工作过？
        List<String> citys = transactions.stream().
                map(Transaction::getTrader).
                map(Trader::getCity).
                distinct().collect(Collectors.toList());
        System.out.println(citys);

        //查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> combridgeTrader = transactions.stream().
                map(Transaction::getTrader).
                filter(trader -> "Cambridge".equals(trader.getCity())).
                sorted(Comparator.comparing(Trader::getName)).
                collect(Collectors.toList());
        System.out.println(combridgeTrader);

        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> traderNames = transactions.stream()
                .map(Transaction::getTrader).distinct().
                sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName).collect(Collectors.toList());
        System.out.println(traderNames);

        //(5) 有没有交易员是在米兰工作的？
        System.out.println("If has trader lived in milan:"+ transactions.stream().map(Transaction::getTrader).anyMatch(trader -> "Milan".equals(trader.getCity())));

        //(6) 打印生活在剑桥的交易员的所有交易额。
        System.out.println("all value sum lived in Combridge:"+ transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity())).map(Transaction::getValue).reduce(0,Integer::sum));
        //(7) 所有交易中，最高的交易额是多少？
        System.out.println("max value in all transactions:"+transactions.stream().map(Transaction::getValue).reduce(Integer::max).toString());
        //(8)找到交易额最小的交易
        System.out.println("min value transaction:"+ transactions.stream().reduce((t1,t2)->t1.getValue()<t2.getValue()?t1:t2));
        System.out.println("min value transaction:"+ transactions.stream().min(Comparator.comparing(Transaction::getValue)));
    }
}
