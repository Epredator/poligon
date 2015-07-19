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
            addCharsToTemp(i);
            Random r = new Random();
            int randomCharFromSource = r.nextInt(tempTab.length);
            excludeChar = randomCharFromSource;
            newWord[i]=tempTab[randomCharFromSource];
        }

        return newWord;
    }

    private void addCharsToTemp(int iterationNumber) {
        tempTab = new char[sourceSize-iterationNumber];

            int position =0 ;
        for(int j=0; j<sourceSize; j++){
            if (j != excludeChar && tempTab.length > position){
                    tempTab[position] = sourceWord[j];
                    position++;
            }
            }
        }


    }
