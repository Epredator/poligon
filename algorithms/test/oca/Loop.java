package oca;
//What is the result? java Test Core Java Advanced Java


public class Loop {
    int[] x =new int[0];

    public static void main(String[] args)
    {
        for(int i =0; i<= args.length-1; i++)
        {
            System.out.print(args[i]);
        }
    }
}

//Explanation
//        args[0]==>Core
//        args[1]==>Java
//        args[2]==>Advanced
//        args[3]==>Java
//        In the for loop, we are trying to print args[4] which is not available and hence we will get ArrayIndexOutOfBoundsException.
//        CoreJavaAdvancedJavaException in thread "main"
//        java.lang.ArrayIndexOutOfBoundsException: 4
//        at Test.main(Test.java:7)

