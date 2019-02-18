package objects;

import com.sun.org.apache.xpath.internal.operations.Bool;
import constants.Constant;
import constants.Skills;

import java.util.ArrayList;
import java.util.EnumMap;

public class Player {
    private EnumMap<Skills, Integer> currentStats;
    private EnumMap<Skills, Boolean> trainableStats;
    private ArrayList<String> completedQuests;
    private ArrayList<Integer> unlockedChunks;

    public Player(){
        currentStats = new EnumMap<Skills, Integer>(Skills.class);
        trainableStats = new EnumMap<Skills, Boolean>(Skills.class);
        for (Skills skill : Skills.values()) {
            currentStats.put(skill, 1);
            trainableStats.put(skill, false);
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
}
