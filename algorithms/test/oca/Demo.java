package oca;

//Consider the following code:
//and the command invocation
//        java Test Core Java Advanced Java

class Demo
{
    String title ="Demo";
}
class Test
{
    public static void m1(Demo d)
    {
//        d.title = "NewDemo";
    }
    public static void main(String[] args)
    {
        Demo d =new Demo();
        m1(d);
        System.out.println(d.title);
    }
}

//Explanation
//        If we pass an object reference to a method as argument,
//        and within the method if we are performing any changes to the state of object,
//        those changes will be reflected to the caller.
//        In this case just duplicate reference variable will be created but not duplicate object.