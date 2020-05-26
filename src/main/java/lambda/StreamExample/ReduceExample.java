package lambda.StreamExample;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ReduceExample {
    public static void main(String[] args) {
        List<Integer>  numList = Arrays.asList(1,4,5,6,8,9,9);
        //归约 求和
        System.out.println(numList.stream().reduce(0,(a,b)-> a + b));
        System.out.println(numList.stream().reduce(0,Integer::sum));
        //求最大值
        System.out.println(numList.stream().reduce(Integer::max));
        //求最小值
        System.out.println(numList.stream().reduce(Integer::min));

    }
}
