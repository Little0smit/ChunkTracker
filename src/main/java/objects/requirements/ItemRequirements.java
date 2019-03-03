package objects.requirements;

import java.util.*;

/**
 * ItemRequirements is the class used to store a list of items,
 * each with an associated quantity required.
 *
 * @author  Little0Smit
 */
public class ItemRequirements {
    private HashMap<String, Integer> items;

    public ItemRequirements(){
        items = new HashMap<String, Integer>();
    }

    /**
     * Gets the quantity required for a specified item.
     * @param itemName the item to get the quantity of.
     * @return int the quantity required. 0 if the item does not exist
     */
    public int getQuantity(String itemName){
        if(items.containsKey(itemName)) return items.get(itemName);
        return 0;
    }

    /**
     * Add a new item to the HashMap of items.
     * @param item the name of the item to be stored
     * @param quantity the quantity of the item required.
     */
    public void addItem(String item, int quantity){
        if(!items.containsKey(item)) items.put(item, quantity);
    }

    /**
     * Gets all the items stored in this Class as a String[]
     * @return String[] all the items required
     */
    public String[] getItems(){
        return items.keySet().toArray(new String[0]);
    }
}
