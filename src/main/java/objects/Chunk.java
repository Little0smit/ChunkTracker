package objects;

import databases.ItemDatabase;
import databases.ProcessingToolDB;
import databases.UnlockedItemDatabase;

import java.util.ArrayList;

public class Chunk {
    private int chunkNumber;
    private String chunkName;
    private ArrayList<Mob> mobs;
    private ArrayList<SkillingNode> skillingNodes;
    private ArrayList<String> groundItems;
    private ArrayList<String> processingTools;
    private ArrayList<Shop> shops;
    private int[] accessibleChunks; //0=N, 1=E, 2=S, 3=W
    private String otherNotes;

    //Constructors. One with everything, one with just chunk number and name (unsure if needed but made in case.
    public Chunk(int chunkNumber, String chunkName, ArrayList<Mob> mobs, ArrayList<SkillingNode> skillingNodes,
                 ArrayList<String> groundItems, ArrayList<String> processingTools, ArrayList<Shop> shops, int[] accessibleChunks) {
        this.chunkNumber = chunkNumber;
        this.chunkName = chunkName;
        this.mobs = mobs;
        this.skillingNodes = skillingNodes;
        this.groundItems = groundItems;
        this.processingTools = processingTools;
        this.shops = shops;
        this.accessibleChunks = accessibleChunks;
    }

    public Chunk(int chunkNumber, String chunkName) {
        this.chunkNumber = chunkNumber;
        this.chunkName = chunkName;
        mobs = new ArrayList<Mob>();
        skillingNodes = new ArrayList<SkillingNode>();
        groundItems = new ArrayList<String>();
        processingTools = new ArrayList<String>();
        shops = new ArrayList<Shop>();
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

    public ArrayList<SkillingNode> getSkillingNodes() {
        return skillingNodes;
    }

    public ArrayList<String> getGroundItems() {
        return groundItems;
    }

    public ArrayList<String> getProcessingTools() {
        return processingTools;
    }

    public ArrayList<Shop> getShops() {
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

    public void addSkillingRecource(SkillingNode sr) {
        if (!skillingNodes.contains(sr)) skillingNodes.add(sr);
    }

    public void addGroundItem(String item){
        if(!groundItems.contains(item)) groundItems.add(item);
    }

    public void addProccesingTool(String processingTool){
        if(!processingTools.contains(processingTool)) processingTools.add(processingTool);
    }

    public void addShop(Shop shop) {
        if(!shops.contains(shop)) shops.add(shop);
    }

    public void addAdjacentChunk(int chunkNumber, int position){
        if(accessibleChunks[position]==0) accessibleChunks[position] = chunkNumber;
    }

    //Contains methods to check if a chunk contains something.

    /**
     * Adds all accessible items withing the chunk to the unlocked item's database .
     */
    public void addItemsToDB() {
        //Ground Items
        for (String item : groundItems) {
            UnlockedItemDatabase.addToDB(ItemDatabase.getItem(item));
        }
        //Shop Items
        for (Shop shop : shops) {
            ArrayList<String> stock = shop.getStock();
            for (String item : stock) {
                UnlockedItemDatabase.addToDB(ItemDatabase.getItem(item));
            }
        }
        //objects.Mob Drops
        for (Mob mob : mobs) {
            ArrayList<String> drops = mob.getDrops();
            for (String drop : drops) {
                UnlockedItemDatabase.addToDB(ItemDatabase.getItem(drop));
            }
        }
        //Skilling Resources
        for (SkillingNode node :
                skillingNodes) {
            Item[] items = node.getOutputs();
            for (Item item :
                    items) {
                UnlockedItemDatabase.addToDB(item);
            }
        }
        //Skilling Processes
        for (String processingToolString : processingTools) {
            ProcessingTool processingTool = ProcessingToolDB.getProcess(processingToolString);
            for (Process process :
                    processingTool.getProcesses()) {
                boolean gotInputs = true;
                for (Item input :
                        process.getInputs()) {
                    if (!UnlockedItemDatabase.contains(input)) gotInputs = false;
                }
                if (gotInputs) {
                    for (Item output :
                            process.getOutputs()) {
                        UnlockedItemDatabase.addToDB(output);
                    }
                }
            }
        }
    }

    /*
     private int chunkNumber;
    private String chunkName;
    private int[] accessibleChunks; //0=N, 1=E, 2=S, 3=W
    private String otherNotes;
     */
}
