package objects;

import constants.Skills;

import java.util.ArrayList;

public class Process {
    Item[] inputs, outputs;
    ArrayList<String> questRequirements;
    SkillRequirements skillReqs;
    Skills skillType;
    String processName;

    public Process(Item[] inputs, Item[] outputs, ArrayList<String> questRequirements, SkillRequirements skillReqs, Skills skillType, String processName) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.questRequirements = questRequirements;
        this.skillReqs = skillReqs;
        this.skillType = skillType;
        this.processName = processName;
    }

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

    public String getProcessName() {
        return processName;
    }
}
