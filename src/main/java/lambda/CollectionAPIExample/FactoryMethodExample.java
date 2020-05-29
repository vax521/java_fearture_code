package lambda.CollectionAPIExample;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FactoryMethodExample {
    public static void main(String[] args) {
        //List 工厂
        List<String> friendss = Arrays.asList("Alan","Bruce","BOB");
        System.out.println(friendss);
       // 创建列表元素的不可变Set集合
        List<String> friendList = List.of("Alan","Bruce","BOB");
        System.out.println(friendList);
        //List 工厂
        Set<String> friendsSet= Set.of("Alan", "Bruce", "BOB");
        System.out.println(friendsSet);

        Map<String,Integer> ageOfFriends = Map.of("Alan",10, "Bruce",20, "BOB",30);
        System.out.println(ageOfFriends);

        Map<String,Integer> ageOfFriendsEntry = Map.ofEntries(Map.entry("Alan",10),Map.entry("Bruce",12),
                Map.entry("BOB",11));
        System.out.println(ageOfFriendsEntry);

       //map排序
        ageOfFriendsEntry.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(System.out::println);
        ageOfFriendsEntry.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(System.out::println);
    }
}
