import Skilling.SkillingResource;

import java.util.ArrayList;

public class Chunk {
    private int chunkNumber;
    private String chunkName;
    private ArrayList<Mob> mobs;
    private ArrayList<SkillingResource> skillingResources;
    private ArrayList<String> groundItems;
    private ArrayList<String> processingTools;
    private ArrayList<String> shops;
    private int[] accessibleChunks; //0=N, 1=E, 2=S, 3=W
    private String otherNotes;

    public Chunk(int chunkNumber, String chunkName, ArrayList<Mob> mobs, ArrayList<SkillingResource> skillingResources,
                 ArrayList<String> groundItems, ArrayList<String> processingTools, ArrayList<String> shops, int[] accessibleChunks) {
        this.chunkNumber = chunkNumber;
        this.chunkName = chunkName;
        this.mobs = mobs;
        this.skillingResources = skillingResources;
        this.groundItems = groundItems;
        this.processingTools = processingTools;
        this.shops = shops;
        this.accessibleChunks = accessibleChunks;
    }

    public Chunk(int chunkNumber, String chunkName) {
        this.chunkNumber = chunkNumber;
        this.chunkName = chunkName;
        mobs = new ArrayList<Mob>();
        skillingResources = new ArrayList<SkillingResource>();
        groundItems = new ArrayList<String>();
        processingTools = new ArrayList<String>();
        shops = new ArrayList<String>();
        accessibleChunks = new int[4];
    }

    /*
    Getters and some setters or adders
    **/

    public int getChunkNumber() {
        return chunkNumber;
    }

    public String getChunkName() {
        return chunkName;
    }

    public ArrayList<Mob> getMobs() {
        return mobs;
    }

    public ArrayList<SkillingResource> getSkillingResources() {
        return skillingResources;
    }

    public ArrayList<String> getGroundItems() {
        return groundItems;
    }

    public ArrayList<String> getProcessingTools() {
        return processingTools;
    }

    public ArrayList<String> getShops() {
        return shops;
    }

    public int[] getAccessibleChunks() {
        return accessibleChunks;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(String notes){
        otherNotes = notes;
    }

    public void addMob(Mob mob) {
        if(!mobs.contains(mob)) mobs.add(mob);
    }

    public void addSkillingRecource(SkillingResource sr){
        if(!skillingResources.contains(sr)) skillingResources.add(sr);
    }

    public void addGroundItem(String item){
        if(!groundItems.contains(item)) groundItems.add(item);
    }

    public void addProccesingTool(String processingTool){
        if(!processingTools.contains(processingTool)) processingTools.add(processingTool);
    }

    public void addShop(String shop){
        if(!shops.contains(shop)) shops.add(shop);
    }

    public void addAdjacentChunk(int chunkNumber, int position){
        if(accessibleChunks[position]==0) accessibleChunks[position] = chunkNumber;
    }
}
