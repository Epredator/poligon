package pl.etroya.practice;

public class quiz1 {
//    What is a value of y after the five iteration?

    public static void main (String[] args) {
        int y = 0;
        int x = 0;
        while(y < 100){
            System.out.print("Number of iteration " + ++x + " is: "  );
            if(y%3 == 0){
                y += 3;
            }
            if(y%2 == 0){
                y += 2;
                continue;
            }
            y++;
            System.out.println(y);
        };


    }
}
