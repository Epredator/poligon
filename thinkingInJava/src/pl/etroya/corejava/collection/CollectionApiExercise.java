package pl.etroya.corejava.collection;

import java.util.*;

public class CollectionApiExercise {
    public static void main(String ... args) {
        ArrayList<CollectionApi> list = new ArrayList<>();

        //TODO why I cannot assign elements to list
        CollectionApi v1 = new CollectionApi("v1", "abc");
        CollectionApi v2 = new CollectionApi("v2", "abc");
        CollectionApi v3 = new CollectionApi("v3", "abc");

        list.add(v1);
        list.add(v2);
        list.add(v3);

       // list.remove(v3);

        for (CollectionApi l : list)
            System.out.println(l.label);

        List<String> listWithNames = new LinkedList<>();
        listWithNames.add("Kasia");
        listWithNames.add("Basia");

        List<String> otherListWithNames = new LinkedList<>();
        otherListWithNames.add("Zosia");
        otherListWithNames.addAll(listWithNames);
        otherListWithNames.add("Zosia");

        System.out.println(otherListWithNames.contains("Zosia"));
        System.out.println(otherListWithNames.get(0));
        System.out.println(otherListWithNames.isEmpty());
        System.out.println(otherListWithNames.indexOf("Zosia"));
        System.out.println(otherListWithNames.lastIndexOf("Zosia"));


        Set<String> setWithNames = new HashSet<>();
        setWithNames.add("Ala");
        setWithNames.add("Ula");
        setWithNames.add("Ala");

        Set<String> otherSet = new HashSet<>();
        otherSet.add("Ola");
        otherSet.add("Ula");

        setWithNames.addAll(otherSet);

        System.out.println(setWithNames.isEmpty());
        System.out.println(setWithNames.size());
        System.out.println(setWithNames.contains("Jola"));
        System.out.println(setWithNames.remove("Ela"));


        Map<String, String> pairsMap = new HashMap<>();
        pairsMap.put("Janek", "Wacek");
        pairsMap.put("Bogdan", "Jan");

        Map<String, String> otherPairsMap = new HashMap<>();
        otherPairsMap.put("Janek", "Wacek");
        otherPairsMap.put("Stefan", "Wacek");

        pairsMap.putAll(otherPairsMap);

        System.out.println(pairsMap.get("Stefan"));
        System.out.println(pairsMap.remove("Wacek"));
        System.out.println(pairsMap.size());
        System.out.println(pairsMap.isEmpty());
        System.out.println(pairsMap.containsKey("Wacek"));
        System.out.println(pairsMap.containsValue("Wacek"));

        Set<String> sampleSet = new HashSet<>();sampleSet.add("Magda");
        sampleSet.add("Adela");sampleSet.add("Marcin");

        sampleSet.add("Marek");
        sampleSet.add("1");
        sampleSet.add("0");
        sampleSet.add("&A&");


        System.out.println("Iterowanie po zbiorze");
        for(String item : sampleSet) {
            System.out.println(item);
        }


        Map<String, String> sampleMap = new HashMap<>();
        sampleMap.put("Marek", "Magda");
        sampleMap.put("Marcin", "Adela");

        System.out.println("Iterowanie po wartosciach");
        for(String value : sampleMap.values()) {
            System.out.println(value);
        }

        System.out.println("Iterowanie po kluczach i pobieranie wartosci");
        for(String key : sampleMap.keySet()) {
            String value = sampleMap.get(key);
            System.out.println(key + ": " + value);
        }

        System.out.println("Iterowanie po kluczach i wartosciach");
        for(Map.Entry<String, String> entry : sampleMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ": " + value);
        }

    }
}
