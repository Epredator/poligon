package oca;
//src: https://nordea.udemy.com/course/oracle-java-certification-shortest-way-to-crack-oca-1z0-808/learn/quiz/4451972/result/304862606#overview
//Consider the following code:

public class PracticeTest2Question7
{
    public static void main(String[] args)
    {
        byte b = 10;
        switch(b)
        {
            case 10: System.out.print(10);
            case 100: System.out.print(100);
//            case 1000: System.out.print(1000);
        }
    }
}

//Explanation
//Every case label should be within the range of switch argument type.
// In the above code switch argument is of byte type.
// The valid range of values for case label is : -128 to 127.
// Hence 1000 case label is not allowed and we will get compile time error.
// Test.java:10: error: incompatible types: possible lossy conversion from int to byte case 1000:
// System.out.println(1000);

//    Within the switch, both case and default are optional.
//    We can take any number of case statements, but at most one default statement.