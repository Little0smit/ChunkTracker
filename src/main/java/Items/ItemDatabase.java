package Items;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;

public class ItemDatabase {
    private static HashMap<String ,Item> accessibleItems = new HashMap<String, Item>();

    public static void addToDB(Item item){
        if(!accessibleItems.containsKey(item.getName()))
        accessibleItems.put(item.getName(), item);
    }

    public static boolean contains(@NotNull Item item){
        return accessibleItems.containsKey(item.getName());
    }

    public static boolean contains(String name){
        return accessibleItems.containsKey(name);
    }

    public static Item getItem(String name) {
        //if(contains(name)){
            return accessibleItems.get(name);
        //}
        //throw new ItemNotStoredException();
    }
}
