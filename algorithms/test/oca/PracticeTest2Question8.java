package oca;
//src: https://nordea.udemy.com/course/oracle-java-certification-shortest-way-to-crack-oca-1z0-808/learn/quiz/4451972/result/304862606#overview
//Consider the following code:

public class PracticeTest2Question8
{
    public static void main(String[] args)
    {
        int i = 1;
        do
        {
            while(++i<3)
            {
                System.out.print(++i);
            }
        }
        while (++i<10);
    }
}

//Explanation
//In the first iteration of do-while,
// i value will become 2 which is less than 3.
// Hence 3 will be printed to the console.
// inner while loop condition fails.
// For the next iterations of do-while, inner while loop never executes as condition fails always.
// Hence the output is:3