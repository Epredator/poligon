package oca;

public class PracticeTest4Question13 {
    public static void main(String[] args)
    {
        try
        {
            System.out.println(10/0);
        }
        catch (Exception e)
        {
            System.out.println(10/0);
            System.out.println("catch");
        }
        finally
        {
            System.out.println("finally");
        }

    }
}