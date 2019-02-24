package objects;

import constants.Constant;
import constants.Skills;

import java.util.ArrayList;
import java.util.EnumMap;

public class Chunk extends NamedThing {
    private int chunkNumber;
    private String chunkName;
    private ArrayList<Mob> mobs;
    private ArrayList<SkillingNode> skillingNodes;
    private ArrayList<String> groundItems;
    private ArrayList<String> processingTools;
    private ArrayList<String> mobNames; //Dummy field to remove the need for duplication in the JSON files
    private ArrayList<Shop> shops;
    private ArrayList<String> shopNames;
    private int[] accessibleChunks; //0=N, 1=E, 2=S, 3=W
    private String otherNotes;

    //Constructors. One with everything, one with just chunk number and name (unsure if needed but made in case.
    public Chunk(int chunkNumber, String chunkName, ArrayList<Mob> mobs, ArrayList<SkillingNode> skillingNodes, ArrayList<String> mobNames,
                 ArrayList<String> groundItems, ArrayList<String> processingTools, ArrayList<Shop> shops, int[] accessibleChunks, ArrayList<String> shopNames) {
        this.chunkNumber = chunkNumber;
        this.chunkName = chunkName;
        this.mobs = mobs;
        this.skillingNodes = skillingNodes;
        this.groundItems = groundItems;
        this.processingTools = processingTools;
        this.shops = shops;
        this.accessibleChunks = accessibleChunks;
        this.mobNames = mobNames;
        this.shopNames = shopNames;
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

    public void lateParse(){
        for (String s : mobNames){
            if (Constant.MOB_DATABASE.getElement(s) != null)
                mobs.add(Constant.MOB_DATABASE.getElement(s));
        }
        for (String s : shopNames){
            if (Constant.SHOP_DATABASE.getElement(s) != null)
                shops.add(Constant.SHOP_DATABASE.getElement(s));
        }
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

    @Override
    public String getName(){
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
    //TODO: check if items are accesible with current quests/stats/items
    //For example, the cooking apples in the cooking guild are only accessible with 32 cooking and a Chef's hat
    public void addItemsToDB() {
        //Ground Items
        for (String item : groundItems) {
            Constant.UNLOCKED_ITEM_DATABASE.registerElement(Constant.ITEM_DATABASE.getElement(item));
        }
        //Shop Items
        for (Shop shop : shops) {
            ArrayList<String> stock = shop.getStock();
            for (String item : stock) {
                Constant.UNLOCKED_ITEM_DATABASE.registerElement(Constant.ITEM_DATABASE.getElement(item));
            }
        }
        //objects.Mob Drops
        for (Mob mob : mobs) {
            ArrayList<String> drops = mob.getDrops();
            for (String drop : drops) {
                Constant.UNLOCKED_ITEM_DATABASE.registerElement(Constant.ITEM_DATABASE.getElement(drop));
            }
        }
        //Skilling Resources
        for (SkillingNode node :
                skillingNodes) {
            ArrayList<String> items = node.getOutputs();
            for (String item :
                    items) {
                Constant.UNLOCKED_ITEM_DATABASE.registerElement(Constant.ITEM_DATABASE.getElement(item));
            }
        }
        //Skilling Processes
        for (String processingToolString : processingTools) {
            ProcessingTool processingTool = Constant.PROCESSING_TOOL_DATABASE.getElement(processingToolString);
            for (Process process :
                    processingTool.getProcesses()) {
                boolean gotInputs = true;
                for (String input :
                        process.getInputs()) {
                    if (!Constant.UNLOCKED_ITEM_DATABASE.contains(input)) gotInputs = false;
                }
                if (gotInputs) {
                    for (String output :
                            process.getOutputs()) {
                        Constant.UNLOCKED_ITEM_DATABASE.registerElement(Constant.ITEM_DATABASE.getElement(output));
                    }
                }
            }
        }
    }

    public EnumMap<Skills, Integer> getSkillReqs(Player player){
        //Check if skill is trainable, otherwise the required skill is 1
        EnumMap<Skills, Integer> reqs = new EnumMap<Skills, Integer>(Skills.class);
        for (Skills skill : Skills.values()) {
            reqs.put(skill, 0);
        }

        for (Skills skill : Skills.values()){
            if (player.isTrainable(skill)){
                for (String processTool : processingTools){
                    for (Process process :Constant.PROCESSING_TOOL_DATABASE.getElement(processTool).getProcesses()){
                        for (String input : process.getInputs()){
                            if (Constant.UNLOCKED_ITEM_DATABASE.contains(Constant.ITEM_DATABASE.getElement(input))){
                                if (reqs.get(skill) != null){
                                    if (reqs.get(skill) < process.getLevelRequirement()){
                                        reqs.put(skill, process.getLevelRequirement());
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                reqs.put(skill, 1);
            }
        }

        return reqs;
    }

    /*
     private int chunkNumber;
    private String chunkName;
    private int[] accessibleChunks; //0=N, 1=E, 2=S, 3=W
    private String otherNotes;
     */
}
