package objects;

import constants.Skills;

import java.util.ArrayList;

public class Process extends NamedThing {
    private ArrayList<String> inputs, outputs, questRequirements;
    private Skills skillType;
    private String processName, processingToolName;
    int levelRequirement;

    public Process(ArrayList<String> inputs, ArrayList<String> outputs, ArrayList<String> questRequirements, Skills skillType, String processName) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.questRequirements = questRequirements;
        this.skillType = skillType;
        this.processName = processName;
    }

    public ArrayList<String> getInputs() {
        return inputs;
    }

    public ArrayList<String> getOutputs() {
        return outputs;
    }

    public ArrayList<String> getQuestRequirements() {
        return questRequirements;
    }

    public Skills getSkillType() {
        return skillType;
    }

    public String getName() { return processName; }
}
