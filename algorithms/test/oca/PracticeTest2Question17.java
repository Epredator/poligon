package oca;
//src: https://nordea.udemy.com/course/oracle-java-certification-shortest-way-to-crack-oca-1z0-808/learn/quiz/4451972/result/304862606#overview
//Consider the following code:

public class PracticeTest2Question17
{
    public static void main(String[] args)
    {
        int x = 40;
        switch(x)
        {
            default :
                System.out.print("A");
            case 10:
                System.out.print("B");
                break;
            case 20:
                System.out.print("C");
            case 30:
                System.out.print("D");
        }
    }
}

//Explanation
//we can take default case anywhere, but it will be considered if no case is matched.
// In the above program, no case is matched and hence default case will be executed.
// From the default case onwards all statements will be executed until break or end of switch.
// Hence the output is: AB