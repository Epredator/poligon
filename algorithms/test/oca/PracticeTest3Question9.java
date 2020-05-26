package oca;

//Question 9: Incorrect
//        Consider the following code:
//        https://nordea.udemy.com/course/oracle-java-certification-shortest-way-to-crack-oca-1z0-808/learn/quiz/4451980/result/315113244#overview

public class PracticeTest3Question9 {
        public static void m1(int x)
        {
            System.out.print("int");
        }
        public static void m1(long l)
        {
            System.out.print("long");
        }
        public static void m1(float f)
        {
            System.out.print("float");
        }
        public static void m1(Object o)
        {
            System.out.print("Object");
        }
        public static void main(String[] args)
        {
            m1('a');
            m1(true);
            m1(10.5);
        }
}


//Explanation
//        char can be promoted to int type hence int-argument method will be executed for the first method call.
//        boolean will be converted to Object (boolean->Boolean->Object) and Object-argument method will be executed for the second method call.
//        double will be converted to Object (double->Double->Object) and Object-argument method will be executed for the third method call. Hence the output is: intObjectObject