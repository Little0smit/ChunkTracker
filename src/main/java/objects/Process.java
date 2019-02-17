package objects;

import constants.Skills;

import java.util.ArrayList;

public class Process {
    Item[] inputs, outputs;
    ArrayList<String> questRequirements;
    SkillRequirements skillReqs;
    Skills skillType;

    public Item[] getInputs() {
        return inputs;
    }

    public Item[] getOutputs() {
        return outputs;
    }

    public ArrayList<String> getQuestRequirements() {
        return questRequirements;
    }

    public SkillRequirements getSkillReqs() {
        return skillReqs;
    }

    public Skills getSkillType() {
        return skillType;
    }
}
