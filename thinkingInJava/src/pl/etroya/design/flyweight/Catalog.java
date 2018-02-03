package pl.etroya.design.flyweight;

import java.util.HashMap;
import java.util.Map;

public class Catalog { //catalog acts as a facotory and cache for Item flyweught obejcts
    private Map<String, Item> items = new HashMap<String, Item>();

    //factory method
    public Item lookup(String itemName){
        if(!items.containsKey(itemName))
            items.put(itemName, new Item(itemName));
        return items.get(itemName);
    }

    public int totalItemMade(){
        return items.size();
    }

}
