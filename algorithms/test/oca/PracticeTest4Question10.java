package oca;

public class PracticeTest4Question10 {
    public static int m1()
    {
        try
        {
            return 1;
        }
        catch (ArithmeticException e)
        {
            return 2;
        }
        finally
        {
            return 3;
        }
    }

    public static void main(String[] args)
    {
        System.out.print(m1());
    }
}
//Explanation
//        If try,catch and finally blocks contains separate return statements, o
//        then finally block return statement will get more priority. Hence the output is :3