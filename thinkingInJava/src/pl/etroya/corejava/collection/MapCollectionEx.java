package pl.etroya.corejava.collection;

import java.util.HashMap;
import java.util.Map;

public class MapCollectionEx {
    public static void main(String [] args){
        Map<String, String> map = new HashMap<>();
        map.put("2222", "ghi");
        map.put("3333", "abc");
        map.put("1111", "def");

        map.forEach((k, v)-> System.out.println(k + " | " + v));
        map.replaceAll((k, v)-> v.toUpperCase());
        map.forEach((k, v)-> System.out.println(k + " | " + v));

        String s1 = map.get("3333"); //return: abc
        String s2 = map.get("9999");//return: null
        String s3 = map.getOrDefault("9999", "xyz");//return: xyz
    }
}
