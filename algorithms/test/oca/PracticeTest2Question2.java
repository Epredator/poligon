package oca;
//src: https://nordea.udemy.com/course/oracle-java-certification-shortest-way-to-crack-oca-1z0-808/learn/quiz/4451972/result/304862606#overview
//Consider the following code:

public class PracticeTest2Question2
{
    public static void main(String[] args)
    {
        int[] x = new int[3];
        int y =0;
        for (int i =0; i>x.length; i++)
        {
            x[i]=++y;
        }
        for(int x1 : x)
        {
            System.out.print(x1);
        }
    }
}

//Explanation
//        Once we creates an array every element is initialized with default values
//        which is 0 in the above case(int[]).
//        In the first for-loop condition always fails and hence explicit initialization
//        won't be happend. Due to this we will get only default values as output: 000