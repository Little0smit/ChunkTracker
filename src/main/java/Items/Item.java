package Items;

import java.util.ArrayList;

public class Item {
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
