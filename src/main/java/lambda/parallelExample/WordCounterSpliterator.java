package lambda.parallelExample;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
    private final String currString;
    private  int  currCharIndex = 0;

    public WordCounterSpliterator(String currString) {
        this.currString = currString;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(currString.charAt(currCharIndex++));
        return currCharIndex < currString.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currSize = currString.length() - currCharIndex;
        if (currSize < 10){
            return null;
        }
        for(int splitPosi = currSize/2 + currCharIndex;splitPosi<currString.length();splitPosi++){
            if(Character.isWhitespace(currString.charAt(splitPosi))){
                Spliterator<Character> spliterator = new WordCounterSpliterator(currString.substring(currCharIndex,splitPosi));
                currCharIndex = splitPosi;
                return  spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return currString.length()-currCharIndex;
    }

    @Override
    public int characteristics() {
        return ORDERED+SIZED+SUBSIZED+NONNULL+IMMUTABLE;
    }
}
