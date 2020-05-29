package lambda.StreamExample;

import java.util.Date;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
         long startSecond = new Date().getTime();
        System.out.println(Stream.iterate(0L,i->i+1).limit(100000).reduce(0L,Long::sum));
        System.out.println(String.format("spend %d ms",new Date().getTime() - startSecond));
        //iterate不易并行化，使用parallel反而增加了时间
        long startSecond2 = new Date().getTime();
        System.out.println(Stream.iterate(0L,i->i+1).limit(100000).parallel().reduce(0L,Long::sum));
        System.out.println(String.format("parallel spend %d ms",new Date().getTime() - startSecond2));

        long startSecond3 = new Date().getTime();
        System.out.println(LongStream.rangeClosed(0,1000000000).reduce(0L,Long::sum));
        System.out.println(String.format("spend %d ms",new Date().getTime() - startSecond3));

        long startSecond4 = new Date().getTime();
        System.out.println(LongStream.rangeClosed(0,1000000000).parallel().reduce(0L,Long::sum));
        System.out.println(String.format("parallel spend %d ms",new Date().getTime() - startSecond4));
    }
}
