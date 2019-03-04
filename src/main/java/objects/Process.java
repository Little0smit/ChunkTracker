package objects;

import constants.Constant;
import constants.Skills;

import java.util.ArrayList;

public class Process extends NamedThing {
    private ArrayList<String> inputs, outputs, questRequirements;
    private Skills skillType;
    private String processName;
    private int levelRequirement;

    public ArrayList<String> getInputs() { return inputs; }

    public ArrayList<String> getOutputs() { return outputs; }

    public ArrayList<String> getQuestRequirements() { return questRequirements; }

    public Skills getSkillType() { return skillType; }


    public String getName() { return processName; }

    public int getLevelRequirement() { return levelRequirement; }

    public boolean isDoable(Player player){
        for (String input : inputs){
            if(!Constant.UNLOCKED_ITEM_DATABASE.contains(input)) return false;
        }
        //if(!Constant.UNLOCKED_ITEM_DATABASE.contains(processingToolName)) return false;
        if (questRequirements.size() != 0) {
            for (String questReqs : questRequirements) {
                if (!player.getCompletedQuests().contains(questReqs)) return false;
            }
        }
        if (player.getCurrentSkillLvl(skillType) < levelRequirement) return false;

        return true;
    }
}
