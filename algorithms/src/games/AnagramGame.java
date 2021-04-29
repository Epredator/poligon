package games;

import java.util.Random;

/**
 * Created by Adam on 2015-07-17.
 */
public class AnagramGame {
    char[] tempTab = null;
    char[] sourceWord = null;
    int sourceSize = 0;
    int excludeChar = -1;

    public char[] makeAnagram(char[] letters){
        return null;
    }


    public boolean checkQualityOfAnagram(char[] firstAnagram, char[] secondAnagram) {
        return false;
    }


    public char[] createAnagram(char[] sourceWord) {
        this.sourceWord = sourceWord;
        sourceSize = sourceWord.length;
        tempTab = new char[sourceSize];
        char[] newWord = new char[sourceSize];

        for(int i=0; i<sourceSize; i++){
            Random r = new Random();
            addCharsToTemp(i);
            excludeChar = r.nextInt(tempTab.length);
            newWord[i]=sourceWord[excludeChar];
        }
        return newWord;
    }

    private void addCharsToTemp(int iterationNumber) {
        tempTab=null;
        tempTab = new char[sourceSize-iterationNumber];
        int position =0 ;

        for(int j=0; j<sourceSize; j++)
                if (j != excludeChar && tempTab.length > position){
                    sourceWord[position] = sourceWord[j];
                    position++;
            }
        }
}

