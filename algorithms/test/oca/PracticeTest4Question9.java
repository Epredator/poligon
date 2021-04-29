package oca;

public class PracticeTest4Question9 {
    public static void main(String[] args)
    {
        StringBuilder sb = new StringBuilder("OCJA Exam 8");
        sb.delete(0,sb.length());
        System.out.println(sb.length());
    }
}

//Explanation
//        sb.delete(0,sb.length()); ==>This line removes total content of the StringBuilder.
//        Hence after executing this line, the length will become 0.