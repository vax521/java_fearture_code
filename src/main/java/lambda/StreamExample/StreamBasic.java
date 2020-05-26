package lambda.StreamExample;

import lambda.StreamExample.entity.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamBasic {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                   new Dish("pork",false,800, Dish.Type.MEAT),
                   new Dish("beef",false,600, Dish.Type.MEAT),
                   new Dish("chiken",false,400, Dish.Type.MEAT),
                   new Dish("french",false,850, Dish.Type.OTHER),
                   new Dish("pizza",false,550, Dish.Type.OTHER),
                   new Dish("fisf",false,200, Dish.Type.FISH),
                   new Dish("salmon",false,100, Dish.Type.FISH),
                   new Dish("nooddles",false,650, Dish.Type.OTHER)
                   );

        List<String> lowColoriesDish = menu.stream().
                filter(dish -> dish.getColories()< 500).
                sorted(Comparator.comparing(Dish::getColories))
                .map(Dish::getName)
                .collect(toList());

        //filter的缺点是，你需要遍历整个流中的数据，对其中的每一个元素执行谓词操作
        List<String> highColoriesDish = menu.stream().
                filter(dish -> dish.getColories() > 500).
                sorted(Comparator.comparing(Dish::getColories))
                .map(Dish::getName)
                .collect(toList());

        List<Dish> highColoryDishes = menu.stream().takeWhile(dish -> dish.getColories() > 320).collect(toList());
        System.out.println(highColoryDishes);

        System.out.println(menu.stream().dropWhile(dish -> dish.getColories() < 320).map(Dish::getName).collect(toList()));

        List<String> threeHighColoriesDish = menu.stream()
                .filter(dish -> dish.getColories()>500)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        Map<Dish.Type,List<Dish>> dishByType = menu.parallelStream().collect(groupingBy(Dish::getType));



//       System.out.println(lowColoriesDish);
//       System.out.println(dishByType);
//        System.out.println(threeHighColoriesDish);
     }
}
