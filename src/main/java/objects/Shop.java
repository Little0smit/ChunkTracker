package objects;

import java.util.ArrayList;

public class Shop {
    private String name;
    private int chunkLocation;
    private ArrayList<String> stock;
    private ArrayList<String> questReqs;

    public Shop (String name, int chunkLocation, ArrayList<String> stock) {
        this.name = name;
        this.chunkLocation = chunkLocation;
        this.stock = stock;
    }

    public Shop (String name, int chunkLocation, ArrayList<String> stock, ArrayList<String> questReqs) {
        this.name = name;
        this.chunkLocation = chunkLocation;
        this.stock = stock;
        this.questReqs = questReqs;
    }

    public String getName() {
        return name;
    }

    public int getChunkLocation() {
        return chunkLocation;
    }

    public ArrayList<String> getStock() {
        return stock;
    }

    public ArrayList<String> getQuestReqs() {
        return questReqs;
    }
}
