package objects;

import constants.*;
import constants.EquipmentSlot;
import constants.WeaponType;

import java.util.ArrayList;
import java.util.EnumMap;

public class Player {
    private EnumMap<Skills, Integer> currentStats;
    private EnumMap<Skills, Boolean> trainableStats;
    private ArrayList<String> completedQuests = new ArrayList<String>();
    private ArrayList<Integer> unlockedChunks = new ArrayList<Integer>();

    public Player(int startingChunk){
        unlockedChunks.add(startingChunk);

        currentStats = new EnumMap<Skills, Integer>(Skills.class);
        trainableStats = new EnumMap<Skills, Boolean>(Skills.class);
        for (Skills skill : Skills.values()) {
            currentStats.put(skill, 1);
            trainableStats.put(skill, false);
        }
        currentStats.put(Skills.Hitpoints, 10);

        checkTrainableSkills();
    }

    public Player(ArrayList<Integer> unlockedChunks){
        this.unlockedChunks = unlockedChunks;

        currentStats = new EnumMap<Skills, Integer>(Skills.class);
        trainableStats = new EnumMap<Skills, Boolean>(Skills.class);
        for (Skills skill : Skills.values()) {
            currentStats.put(skill, 1);
            trainableStats.put(skill, false);
        }
        currentStats.put(Skills.Hitpoints, 10);

        checkTrainableSkills();
    }

    public Player(int[] unlockedChunks){
        for (int i : unlockedChunks){
            this.unlockedChunks.add(i);
        }

        currentStats = new EnumMap<Skills, Integer>(Skills.class);
        trainableStats = new EnumMap<Skills, Boolean>(Skills.class);
        for (Skills skill : Skills.values()) {
            currentStats.put(skill, 1);
            trainableStats.put(skill, false);
        }
        currentStats.put(Skills.Hitpoints, 10);

        checkTrainableSkills();
    }

    public EnumMap<Skills, Integer> getCurrentStats() {
        return currentStats;
    }

    public int getCurrentSkillLvl(Skills skill){
        return currentStats.get(skill);
    }

    public void setCurrentSkillLvl(Skills skill, int lvl){
        currentStats.put(skill, lvl);
    }

    public boolean isTrainable(Skills skill) {
        return trainableStats.get(skill);
    }

    public ArrayList<Integer> getUnlockedChunks() {
        return unlockedChunks;
    }

    public void unlockChunk(int chunk){
        if(!unlockedChunks.contains(chunk)) unlockedChunks.add(chunk);
    }

    public void skillTrainable(Skills skill){
        trainableStats.put(skill, true);
    }

    public ArrayList<String> getCompletedQuests() {
        return completedQuests;
    }

    public void completeQuest(String quest){
        if(!completedQuests.contains(quest)) completedQuests.add(quest);
    }

    public void printStats(){
        for (Skills skill : Skills.values()) {
            System.out.println(skill.toString() + ": " + currentStats.get(skill));
        }
    }

    /*public boolean canRollChunk(){
        for (int chunk : unlockedChunks){
            //Check if all quests that are completable are completed
            for (Quest quest : Constant.QUEST_DATABASE.getAllElements()){

            }

            //Check if the player has the required skills
            for (Skills skill : Skills.values()){
                if (Constant.CHUNK_DATABASE.getElement(Integer.toString(chunk)).getSkillReqs(this).get(skill) > currentStats.get(skill)){
                    return false;
                }
            }
        }

        return true;
    }*/

    public void checkTrainableSkills(){
        for (int chunk : unlockedChunks) {
            //Combat skills
            if (Constant.CHUNK_DATABASE.getElement(Integer.toString(chunk)).getMobs().size() != 0) {
                trainableStats.put(Skills.Attack, true);
                trainableStats.put(Skills.Defence, true);
                trainableStats.put(Skills.Strength, true);
                trainableStats.put(Skills.Hitpoints, true);
                //Assuming bone drops are 100%
                //Some mobs might drop bones, but not all the time, making bones a secondary item
                //which means prayer only has secondary training methods
                for (String mob : Constant.CHUNK_DATABASE.getElement(Integer.toString(chunk)).getMobs()){
                    //TODO: Make a better list of all bones/items that give prayer XP
                    for (String bone : new String[]{"bones", "bigBones", "dragonBones"})
                    if (Constant.MOB_DATABASE.getElement(mob).drops.contains(bone)){
                        trainableStats.put(Skills.Prayer, true);
                    }
                }
            }

            //TODO: Make the check for ranged training availability better
            for (String arrow : new String[]{"bronzeArrow", "ironArrow"}){
                for (String bow : new String[]{"shortbow", "oakShortbow"}) {
                    if (Constant.UNLOCKED_ITEM_DATABASE.contains(arrow) && Constant.UNLOCKED_ITEM_DATABASE.contains(bow)) {
                        trainableStats.put(Skills.Ranged, true);
                    }
                }
            }

            //TODO: Add better checking for rune availability, through 100% drops and rune shops
            for (String shopName : Constant.CHUNK_DATABASE.getElement(Integer.toString(chunk)).getShops()){
                for (String item : Constant.SHOP_DATABASE.getElement(shopName).getStock()){
                    if (item == "mindRune"){
                        trainableStats.put(Skills.Magic, true);
                    }
                }
            }


            //Can every non-combat skill be classified as a process, sometimes without inputs/outputs or both?
            for (String skillingLocations : Constant.CHUNK_DATABASE.getElement(Integer.toString(chunk)).getSkillingLocations()){
                for (String process : Constant.PROCESSING_TOOL_DATABASE.getElement(skillingLocations).getProcessesNames()){
                    if (Constant.PROCESS_DATABASE.getElement(process).isDoable(this)){
                        trainableStats.put(Constant.PROCESS_DATABASE.getElement(process).getSkillType(), true);
                    }
                }
            }
        }
    }

    public EnumMap<Skills, Integer> skillsToNextChunk (){
        //TODO: loop through skilling nodes to determine levels instead of looking at the chunks
        EnumMap<Skills, Integer> out = new EnumMap<Skills, Integer>(Skills.class);
        for (Skills skill : Skills.values()) {
            out.put(skill, 0);
        }

        for (int chunk : unlockedChunks){
            EnumMap<Skills, Integer> chunkSkills = Constant.CHUNK_DATABASE.getElement(Integer.toString(chunk)).getSkillReqs(this);
            for (Skills skill : Skills.values()){
                out.put(skill, Math.max(out.get(skill), chunkSkills.get(skill)));
            }
        }

        return out;
    }

    public EnumMap<WeaponType, EnumMap<EquipmentSlot, EquippableItem[]>> BiSItems(){
        EnumMap<WeaponType, EnumMap<EquipmentSlot, EquippableItem[]>> bisItems = new EnumMap<WeaponType, EnumMap<EquipmentSlot, EquippableItem[]>>(WeaponType.class);

        for (WeaponType type : WeaponType.values()) {
            EnumMap<EquipmentSlot, EquippableItem[]> BiS = new EnumMap<EquipmentSlot, EquippableItem[]>(EquipmentSlot.class);
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                BiS.put(slot, null);
            }

            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ArrayList<EquippableItem> bisArr = new ArrayList<EquippableItem>();
                for (Item item : Constant.UNLOCKED_ITEM_DATABASE.getAllElements()) {
                    if (Constant.EQUIPPABLE_ITEM_DATABASE.contains(item.getName())) {
                        EquippableItem eItem = Constant.EQUIPPABLE_ITEM_DATABASE.getElement(item.getName());
                        try {
                            if (eItem.getSlot() == slot) {
                                if (BiSArr.size() == 0) {
                                    BiSArr.add(eItem);
                                } else if (BiSArr.get(0).getBiSNumber() < eItem.getBiSNumber()) {
                                    BiSArr.clear();
                                    BiSArr.add(eItem);
                                } else if (BiSArr.get(0).getBiSNumber() == eItem.getBiSNumber()) {
                                    BiSArr.add(eItem);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                BiS.put(slot, BiSArr.toArray(new EquippableItem[BiSArr.size()]));
            }

            bisItems.put(type, BiS);
        }

        return p;
    }
}
