package objects;

import com.sun.org.apache.xpath.internal.operations.Bool;
import constants.Constant;
import constants.Skills;
import objects.quests.Quest;

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

        //If there are enemies in the starting chunk, then the melee skills are trainable
        if (Constant.CHUNK_DATABASE.getElement(
                Integer.toString(startingChunk)).getMobs().size() != 0){
            trainableStats.put(Skills.Attack, true);
            trainableStats.put(Skills.Defence, true);
            trainableStats.put(Skills.Strength, true);
            trainableStats.put(Skills.Hitpoints, true);
        }
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

    public boolean canRollChunk(){
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
    }

    public EnumMap<Skills, Integer> skillsToNextChunk (){
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
}
