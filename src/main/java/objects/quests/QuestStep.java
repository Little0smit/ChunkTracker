package objects.quests;

import constants.Constant;
import constants.Skills;
import objects.Item;
import objects.requirements.ItemRequirements;

import java.util.ArrayList;
import java.util.EnumMap;

public class QuestStep {
    private int stepNumber;
    private String stepDescription, extraInfo;
    private ItemRequirements itemReqs;
    private int chunkLocation;
    private boolean completed;

    public QuestStep(int stepNumber, String stepDescription, ItemRequirements itemReqs, int chunkLocation) {
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
        this.itemReqs = itemReqs;
        this.chunkLocation = chunkLocation;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getStepDescription() {
        return stepDescription;
    }


    /**
     * This method is used to check if the section of the quest
     * is completable with current unlocks.
     * @param unlockedChunks An Array of all chunk integers the player has access to
     * @param currentSkills  An EnumMap of the players current in-game stats
     * @return boolean Returns whether the step is completable with current progress.
     */
    public boolean completable(ArrayList<Integer> unlockedChunks, EnumMap<Skills, Integer> currentSkills){
        //Check Location
        if(!unlockedChunks.contains(chunkLocation)) return false;

        //Check Items
        //Note this does not check quantity (as it assumes once an item is unlocked, it can be gained repeatably)
        String[] requiredItems = itemReqs.getItems();
        for (String item:requiredItems) {
            if (Constant.UNLOCKED_ITEM_DATABASE.contains(item)){
                Item i = Constant.UNLOCKED_ITEM_DATABASE.getElement(item);
                if(!i.foundIn(unlockedChunks)) return false;
            } else {
                return false;
            }
        }

        /*
        If later discovered some steps have skill requirements rather than the complete quest, check that here.
        */
        return true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
