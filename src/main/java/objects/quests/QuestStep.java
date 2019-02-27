package objects.quests;

import constants.Constant;
import constants.Skills;
import objects.Item;
import objects.ItemStack;

import java.util.ArrayList;
import java.util.EnumMap;

public class QuestStep {
    private int stepNumber;
    private String stepDescription, extraInfo;
    private ArrayList<ItemStack> itemReqs = new ArrayList<ItemStack>();
    private int chunkLocation;
    private boolean completed;

    /*public QuestStep(int stepNumber, String stepDescription, ArrayList<String> itemReqs, int chunkLocation) {
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
        this.itemReqs = itemReqs;
        this.chunkLocation = chunkLocation;
    }*/

    public int getStepNumber() {
        return stepNumber;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public ArrayList<ItemStack> getItemReqs() {
        return itemReqs;
    }

    /**
     *Returns true if section of the quest is completable with current unlocks.
     */
    public boolean completable(ArrayList<Integer> unlockedChunks, EnumMap<Skills, Integer> currentSkills){
        //Check Location
        if(!unlockedChunks.contains(chunkLocation)) return false;

        //Check Items
        for (ItemStack item:itemReqs) {
            if (Constant.UNLOCKED_ITEM_DATABASE.contains(item.getItemName())){
                if(Constant.UNLOCKED_ITEM_DATABASE.contains(item.getItemName())) return false;
            } else {
                return false;
            }
        }

        return true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
