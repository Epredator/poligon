package oca;
//src: https://nordea.udemy.com/course/oracle-java-certification-shortest-way-to-crack-oca-1z0-808/learn/quiz/4451972/result/304862606#overview
//Consider the following code:

public class PracticeTest2Question1
{
    public static void main(String[] args)
    {
        int i = 0;
        if(++i<0)
        {
            System.out.print("A");
        }
        {
            System.out.print("B");
        }
        System.out.print("C");
    }
}

//Explanation
//        else part is optional and it is not mandotory.
//        As the condition fails, if block won't be executed. The next block,
//        which is normal block will be executed which prints 'B'.
//        After that 'C' will be printed to the console. Hence output is: BC
