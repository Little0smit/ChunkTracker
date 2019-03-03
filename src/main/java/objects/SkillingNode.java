package objects;

import constants.SkillingNodeType;
import constants.Skills;
import objects.requirements.SkillRequirements;

import java.util.ArrayList;

public class SkillingNode {
    SkillRequirements requirements;
    ArrayList<String> questRequirements;
    ArrayList<String> gainedItems;
    SkillingNodeType type;
    Skills nodeSkill;

    public SkillingNode (SkillRequirements requirements, ArrayList<String> questRequirements, ArrayList<String> gainedItems, SkillingNodeType type, Skills nodeSkill){
        this.requirements = requirements;
        this.gainedItems = gainedItems;
        this.nodeSkill = nodeSkill;
        this.questRequirements = questRequirements;
        this.type = type;
    }

    public SkillRequirements getRequirements() {
        return requirements;
    }

    public ArrayList<String> getQuestRequirements() {
        return questRequirements;
    }

    public ArrayList<String> getOutputs() {
        return gainedItems;
    }

    public SkillingNodeType getType() {
        return type;
    }

    public Skills getNodeSkill() {
        return nodeSkill;
    }
}
