package fork_join_framework.merge_result;

import java.util.Random;

public class DocumentMock {

    private String[] words = {"the","world","goodbye","java","go","shifit","random","suiji"};

    public String[][] generateDocument(int rowNum,int columnNum,String word){
        int counter = 0;
        String[][] document = new String[rowNum][columnNum];
        Random random = new Random();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (word.equals(document[i][j])){
                    counter++;
                }
            }
        }
        System.out.printf("DocumentMock:the word appears %s times in the document\n",counter);
        return document;
    }


}
