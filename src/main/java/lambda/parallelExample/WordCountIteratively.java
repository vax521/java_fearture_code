package lambda.parallelExample;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCountIteratively {

    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean isLastSpace = true;
        for(char c:s.toCharArray()){
            if(Character.isWhitespace(c)){
                isLastSpace = true;
            }else {
                if(isLastSpace) counter++;
                isLastSpace = false;
            }
        }
        return counter;
    }

    public static int countWords(Stream<Character> characterStream){
        WordCounter wordCounter = characterStream.reduce(new WordCounter(0,true),WordCounter::accumulate,WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
        final String SENTENCE = "I have a    dream fsfsff  fff ff fff ff fff  ffff ff ff ff ff fff ";
        System.out.println(countWordsIteratively(SENTENCE));
        Stream<Character> characterStream = IntStream.range(0,SENTENCE.length()).mapToObj(SENTENCE::charAt);
//        characterStream.forEach(System.out::println);
//        System.out.println(countWords(characterStream));
        //并行计算会出现错误，因为原始的String在任意位置拆分，所以有时一个词会被分为两个词，然后数了两次。这就说明，拆分流会影响结果，
        // 而把顺序流换成并行流就可能使结果出错。
//        System.out.println(countWords(characterStream.parallel()));
        //转换为并行流
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> parallelStream = StreamSupport.stream(spliterator,true);
        System.out.println("found:"+countWords(parallelStream)+" words!");



    }



}
