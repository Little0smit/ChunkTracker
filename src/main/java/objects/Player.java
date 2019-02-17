package objects;

import constants.Constant;

import java.util.ArrayList;

public class Player {
    private int[] currentStats;
    private boolean[] trainableStats;
    private ArrayList<String> completedQuests;
    private ArrayList<Integer> unlockedChunks;

    public Player(){
        currentStats = new int[Constant.NUMBER_OF_Skills];
        trainableStats = new boolean[Constant.NUMBER_OF_Skills];
        for (int i = 0; i < Constant.NUMBER_OF_Skills; i++) {
            currentStats[i]= 1;
            trainableStats[i] = false;
        }
    }

    public int[] getCurrentStats() {
        return currentStats;
    }

    public int getCurrentSkillLvl(int skill){
        return currentStats[skill];
    }

    public void setCurrentSkillLvl(int skill, int lvl){
        currentStats[skill] = lvl;
    }

    public boolean isTrainable(int i) {
        return trainableStats[i];
    }

    public ArrayList<Integer> getUnlockedChunks() {
        return unlockedChunks;
    }

    public void unlockChunk(int chunk){
        if(!unlockedChunks.contains(chunk)) unlockedChunks.add(chunk);
    }

    public void skillTrainable(int skill){
        trainableStats[skill] = true;
    }

    public ArrayList<String> getCompletedQuests() {
        return completedQuests;
    }

    public void completeQuest(String quest){
        if(!completedQuests.contains(quest)) completedQuests.add(quest);
    }

    public void printStats(){
        for (int i = 0; i < Constant.NUMBER_OF_Skills; i++) {
            System.out.println(Constant.ORDER_OF_STRINGS[i] + ": " + currentStats[i]);
        }
    }
}
