package pl.etroya.corejava;

import java.util.ArrayList;

public class Collections {
    ArrayList list = new ArrayList();
    ArrayList<String> listOfText = new ArrayList<>();

   private void fillArray(){
       list.add("Aaa");
       list.add("Bbb");
       System.out.println("Elements: " + list.size());

       for (Object o : list){
           System.out.println(o.toString());
       }
       String s = (String)list.get(0);

   }

    private void fillArrayOfText(){
        listOfText.add("Aaa");
        listOfText.add("Bbb");
        System.out.println("Elements of texy: " + listOfText.size());

        for (String o : listOfText){
            System.out.println(o);
        }
        String s = listOfText.get(0);

    }

}
