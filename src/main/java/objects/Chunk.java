package objects;

import constants.Constant;
import constants.Skills;

import java.util.ArrayList;
import java.util.EnumMap;

public class Chunk extends NamedThing {
    private int chunkNumber;
    private String chunkName;
    private ArrayList<String> groundItems = new ArrayList<String>();
    private ArrayList<String> skillingLocations = new ArrayList<String>();
    private ArrayList<String> mobs = new ArrayList<String>();
    private ArrayList<String> shops = new ArrayList<String>();
    private int[] accessibleChunks; //0=N, 1=E, 2=S, 3=W
    private String otherNotes;

    public int getChunkNumber() { return chunkNumber; }

    @Override
    public String getName() { return chunkName; }

    public ArrayList<String> getGroundItems() { return groundItems; }

    public ArrayList<String> getSkillingLocations() { return skillingLocations; }

    public ArrayList<String> getMobs() { return mobs; }

    public ArrayList<String> getShops() { return shops; }

    public int[] getAccessibleChunks() { return accessibleChunks; }

    public String getOtherNotes() { return otherNotes; }

    /**
     * Adds all accessible items withing the chunk to the unlocked item's database .
     */
    //TODO: check if items are accessible with current quests/stats/items
    //For example, the cooking apples in the cooking guild are only accessible with 32 cooking and a Chef's hat
    public void addItemsToDB() {
        //Ground Items
        for (String item : groundItems) {
            Constant.UNLOCKED_ITEM_DATABASE.registerElement(Constant.ITEM_DATABASE.getElement(item));
        }
        //Shop Items
        for (String shop : shops) {
            ArrayList<String> stock = Constant.SHOP_DATABASE.getElement(shop).getStock();
            for (String item : stock) {
                Constant.UNLOCKED_ITEM_DATABASE.registerElement(Constant.ITEM_DATABASE.getElement(item));
            }
        }
        //objects.Mob Drops
        for (String mob : mobs) {
            ArrayList<String> drops = Constant.MOB_DATABASE.getElement(mob).getDrops();
            for (String drop : drops) {
                Constant.UNLOCKED_ITEM_DATABASE.registerElement(Constant.ITEM_DATABASE.getElement(drop));
            }
        }
        //Skilling Locations
        for (String skillingLocationString : skillingLocations) {
            SkillingLocation skillingLocation = Constant.SKILLING_LOCATION_DATABASE.getElement(skillingLocationString);
            for (Process process :
                    skillingLocation.getProcesses()) {
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
                for (String skillingLocation : skillingLocations){
                    for (Process process : Constant.SKILLING_LOCATION_DATABASE.getElement(skillingLocation).getProcesses()){
                        for (String input : process.getInputs()){
                            if (Constant.UNLOCKED_ITEM_DATABASE.contains(input)){
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
}
