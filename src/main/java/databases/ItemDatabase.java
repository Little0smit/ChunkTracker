package databases;

import objects.Item;

import java.util.HashMap;

public class ItemDatabase {
    private static HashMap<String, Item> allItems = new HashMap<String, Item>();

    public static Item getItem(String name) {
        return allItems.get(name);
    }

    public static  void registerItem(Item item){ allItems.put(item.getName(), item); }
}
