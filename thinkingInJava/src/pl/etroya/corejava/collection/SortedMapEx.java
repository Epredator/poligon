package pl.etroya.corejava.collection;

import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapEx {
    public static void main(String [] args){
        SortedMap<String, String> map = new TreeMap<>();
        map.put("2222", "ghi");
        map.put("3333", "abc");
        map.put("1111", "def");
        map.put("6666", "mno");
        map.put("4444", "avz");

        map.forEach((k, v)-> System.out.println(k + " | " + v));

        SortedMap<String, String> hMap = map.headMap("3333");
        hMap.forEach((k, v)-> System.out.println(k + " | " + v));

        SortedMap<String, String> tMap = map.tailMap("3333");
        tMap.forEach((k, v)-> System.out.println(k + " | " + v));
    }
}
