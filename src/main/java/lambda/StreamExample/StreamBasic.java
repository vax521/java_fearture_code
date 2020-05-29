package lambda.StreamExample;

import lambda.StreamExample.collector.SelfTodoListCollector;
import lambda.StreamExample.entity.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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

        //数值流
        //计算所有菜品的卡路里和
        System.out.println(menu.stream().mapToInt(Dish::getColories).sum());

        //数值流与stream的互相转换
        IntStream intStream = menu.stream().mapToInt(Dish::getColories);
        System.out.println(intStream);
     /*   Stream<Integer> streamInt = intStream.boxed();
        System.out.println(streamInt);*/

        //Optional类，这是一个可以表示值存在或不存在的容器。Optional可以用Integer、String等参考类型来参数化。对于三种原始流特化，也分别有一个Optional原始类型特化版本：OptionalInt、OptionalDouble和OptionalLong。
        OptionalInt maxColory = intStream.max();
        System.out.println(maxColory.orElse(1000));//如果没有最大值，设置一个默认的最大值

        //数值范围
        //生成一个1-100之间的偶数流
        IntStream evenStream = IntStream.rangeClosed(1,100).filter(n-> n % 2 == 0);
        System.out.println(evenStream.count());
//        evenStream.forEach(System.out::println);
        //生成勾股数组
        Stream<int[]> gouguTriples = IntStream.rangeClosed(1,100).boxed()
                            .flatMap(a -> IntStream.rangeClosed(a,100).filter(
                                    b -> Math.sqrt(a*a+b*b) % 1==0
                            ).mapToObj(b->new int[]{a,b,(int) Math.sqrt(a*a+b*b)}));

        gouguTriples.limit(5).forEach(t->System.out.println(t[0]+"-"+t[1]+"-"+t[2]));

        /*
        * 构建流的方法
        * */
        //1.通过值创建流
        Stream<String> strStream = Stream.of("moden","hapi","sdads");
        strStream.map(String::toUpperCase).forEach(System.out::println);

        //2.通过空对象创建流
        Stream<String> homeValueStream = Stream.ofNullable(System.getProperty("home"));
        homeValueStream.forEach(System.out::println);
        //3.数组创建流
        int[] numbers = new int[]{1,33,4,5,6,7,8,44,45,55};
        IntStream intergerStream = Arrays.stream(numbers);
        System.out.println(intergerStream.sum());
        //4.文件生成流
        long uniqueWordsNumber = 0L;

        try(Stream<String> lines = Files.lines(Paths.get("D:\\java_fearture_code\\src\\main\\java\\lambda\\StreamExample\\data.txt"), Charset.defaultCharset())) {
            uniqueWordsNumber = lines.flatMap(line ->Arrays.stream(line.split(" "))).distinct().count();
            System.out.println(uniqueWordsNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
          无限流
        * Stream API提供了两个静态方法来从函数生成流：Stream.iterate和Stream.generate。这两个操作可以创建所谓的无限流：不像从固定集合创建的流那样有固定大小的流。
        * */

        Stream.iterate(0,n->n+2).limit(20).forEach(System.out::println);
        System.out.println("-----------------------");
         //斐波那契数列
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(10).map(t->t[0]).forEach(System.out::println);
        //Java 9对iterate方法进行了增强，它现在可以支持谓词操作了。譬如，你可以由0开始生成一个数字序列，一旦数字大于100就停下来：
        System.out.println("------------------------");
        Stream.iterate(0,n -> n < 100,n->n+2).forEach(System.out::println);
        //等价于
        Stream.iterate(0,n->n+2).takeWhile(n->n<100).forEach(System.out::println);

        /*
        * 与iterate方法类似，generate方法也可让你按需生成一个无限流。
        * 但generate不是依次对每个新生成的值应用函数的。它接受一个Supplier<T>类型的Lambda提供新的值。
        * */
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        IntStream intStream1 = IntStream.generate(()->1);
        intStream1.limit(10).forEach(System.out::println);

        IntStream intStream2 = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });
        intStream2.limit(10).forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int prev = 0;
            private int curr = 1;
            @Override
            public int getAsInt() {
                int oldPrev = this.prev;
                int nextValue = this.prev + this.curr;
                this.prev = curr;
                this.curr = nextValue;
                return oldPrev;
            }
        };
        System.out.println("-----------------------");
       IntStream.generate(fib).limit(10).forEach(System.out::println);

       //查找流中的最大和最小值
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getColories);

       Optional<Dish> dishMaxOptional = menu.stream().collect(maxBy(dishComparator));
       System.out.println(dishMaxOptional);

       Optional<Dish> dishMinOptional = menu.stream().collect(minBy(dishComparator));
       System.out.println(dishMinOptional);
       //所有菜品的卡路里
        System.out.println("all colories:"+menu.stream().collect(summingInt(Dish::getColories)));
        System.out.println("all colories:"+(Integer) menu.stream().mapToInt(Dish::getColories).sum());
        System.out.println("average colories:"+menu.stream().mapToInt(Dish::getColories).average());
        System.out.println("summary colories:"+menu.stream().mapToInt(Dish::getColories).summaryStatistics());
        /*
        * joining工厂方法返回的收集器会把对流中每一个对象应用toString方法得到的所有字符串连接成一个字符串
        * */
        String dishNames = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(dishNames);
        Map<ColoricLevel,List<Dish>> dishGroupByColoricLevel = menu.stream().collect(
                groupingBy(dish->{
                    if(dish.getColories()<400) return ColoricLevel.DIET;
                    else if(dish.getColories()<=700) return ColoricLevel.NORMAL;
                    else return ColoricLevel.FAT;
                })
        );
        System.out.println(dishGroupByColoricLevel);
        Map<String, List<String>> dishTags = new HashMap ();
        dishTags.put ("pork", Arrays.asList("greasy", "salty"));
        dishTags.put ("beef", Arrays.asList("salty", "roasted"));
        dishTags.put ("chicken", Arrays.asList("fried", "crisp"));
        dishTags.put ("french fries", Arrays.asList("greasy","fried"));
        dishTags.put ("rice", Arrays.asList("light", "natural"));
        dishTags.put ("season fruit",Arrays.asList ("fresh", "natural"));
        dishTags.put ("pizza", Arrays.asList("tasty", "salty"));
        dishTags.put ("prawns", Arrays.asList("tasty", "roasted"));
        dishTags.put ("salmon", Arrays.asList("delicious", "fresh"));

     /*   Map<Dish.Type,Set<String>> dishTypeTag = menu.stream().collect(
                groupingBy(Dish::getType,flatMapping(dish->dishTags.get(dish.getName()).stream(),toSet())));
        System.out.println(dishTypeTag);*/
        //多级分组
        Map<Dish.Type,Map<ColoricLevel,List<Dish>>> dishesByTypeColoricLevel = menu.stream().collect(
                groupingBy(Dish::getType,groupingBy(dish->{
                    if(dish.getColories()<400) return ColoricLevel.DIET;
                    else if(dish.getColories()<=700) return ColoricLevel.NORMAL;
                    else return ColoricLevel.FAT;
                }))
        );
        System.out.println(dishesByTypeColoricLevel);
        //按子组收集数据
        Map<Dish.Type, Long> typeLongMap = menu.stream().collect(
                groupingBy(Dish::getType,counting())
        );
        System.out.println(typeLongMap);


        //查找菜单中每个不同的种类中热量最高的菜肴
        Map<Dish.Type,Optional<Dish>> mostColoricsDish = menu.stream().collect(
                groupingBy(Dish::getType,maxBy(Comparator.comparing(Dish::getColories)))
        );
        System.out.println(mostColoricsDish);
        Map<Dish.Type,Dish> mostColDish = menu.stream().collect(
                Collectors.toMap(Dish::getType, Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Dish::getColories)))
        );
        System.out.println(mostColDish);

        Map<Dish.Type,Integer> totalColoricsByType = menu.stream().collect(
                groupingBy(Dish::getType,summingInt(Dish::getColories))
        );
        System.out.println(totalColoricsByType);

        Map<Dish.Type,Set<ColoricLevel>> coloricLevelType = menu.stream().collect(
                groupingBy(Dish::getType,mapping(dish->{
                    if(dish.getColories() <= 400) return ColoricLevel.DIET;
                    else if(dish.getColories() <= 700) return ColoricLevel.NORMAL;
                    else return ColoricLevel.FAT;
                },toSet()))
        );
        System.out.println(coloricLevelType);

        // 分区
        Map<Boolean, List<Dish>> partitionMenu = menu.stream().collect(partitioningBy(Dish::isVegetation));
        System.out.println(partitionMenu);

  /*      // 找到素食和非素食中热量最高的菜：
        Map<Boolean,Dish> mostColoricsPartByVegetation = menu.stream().collect(partitioningBy(Dish::isVegetation,collectingAndThen(maxBy(Comparator.comparingInt(Dish::getColories)),Optional::get)));
        System.out.println(mostColoricsPartByVegetation);*/

        // 将数字按质数和非质数分区
        IntStream.rangeClosed(2,100).boxed().filter(StreamBasic::isPrime).forEach(System.out::println);
        System.out.println(IntStream.rangeClosed(2,10).boxed().collect(partitioningBy(StreamBasic::isPrime)));

        //使用自定义Collector
        List<Dish> dishes = menu.stream().collect(new SelfTodoListCollector<>());
        System.out.println(dishes);
    }
    public enum ColoricLevel  {DIET,NORMAL,FAT};

    public static boolean isPrime(int candiNum){
        int candiNumStart = (int)Math.sqrt((double)candiNum);
        return IntStream.rangeClosed(2,candiNumStart).noneMatch(i -> candiNum % i == 0);
    }


}
