package gameTest;

import games.AnagramGame;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Adam on 2015-07-17.
 */
public class AnagramTest {

    @Test
    public void checkAnagram(){
        AnagramGame a = new AnagramGame();
        char[] firstAnagram = new char[]{'a','d','a','m'};

//         int[] result = a.bubbleSort(tabBeforeSort);
        char[] secondAnagram = a.createAnagram(firstAnagram);
        boolean checker = a.checkQualityOfAnagram(firstAnagram,secondAnagram);
        Assert.assertTrue(checker); //if return true the Anagram is properly

    }
}
