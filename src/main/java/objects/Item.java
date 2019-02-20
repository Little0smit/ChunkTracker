package objects;

import java.util.ArrayList;
//TODO create equipable class
public class Item extends NamedThing{
    private String name;
    private ArrayList<Integer> locations;

    public Item(String name, ArrayList<Integer> locations) {
        this.locations = locations;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean foundIn(ArrayList<Integer> unlockedChunks){
        for (int chunk: unlockedChunks) if (locations.contains(chunk)) return true;
        return false;
    }
}
