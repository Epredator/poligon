package oca;

//consider below code:

class TestException extends Exception
{
}

public class PracticeTest4Question7 {
    public static void m1() throws Exception
    {
        System.out.print("A");
        throw new TestException();
    }
    public static void main(String[] args) throws Exception {
        try
        {
            m1();
        }
        catch (TestException e)
        {
            System.out.println("B");
        }
        finally
        {
            System.out.println("C");
        }

    }
}

//    Explanation
//    m1() throws Exception and hence compulsory inside main method we should handle exception,
//    but we are handling TestException.
//    Hence we will get compile time error saying: unreported exception Exception; must be caught or declared to be thrown