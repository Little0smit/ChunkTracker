package objects.quests;

import constants.Skills;

import java.util.ArrayList;
import java.util.EnumMap;

public class Quest {
    private String name;
    private ArrayList<QuestStep> steps;
    private int startingChunk;
    private EnumMap<Skills, Integer> skillReqs;
    private int questPointsReqs;
    private ArrayList<String> questReqs;


    private boolean started;
    private boolean completed;

    public Quest(String name, ArrayList<QuestStep> steps, int startingChunk, EnumMap<Skills, Integer> skillReqs, int questPointsReqs, ArrayList<String> questReqs) {
        this.name = name;
        this.steps = steps;
        this.startingChunk = startingChunk;
        this.skillReqs = skillReqs;
        this.questPointsReqs = questPointsReqs;
        this.questReqs = questReqs;
        completed = false;
        started = false;
    }

    /**
     * Returns true if the quest if partially completable with current chunks unlocked.
     */
    public boolean isCompletable(ArrayList<Integer> unlockedChunks, EnumMap<Skills, Integer> currentSkills, ArrayList<String> completedQuests, int questPoints){
        if (completed) return false;
        
        //Check pre-quest requirements if it's not been started
        if(!started){
            //Check you have access to start
            if(!unlockedChunks.contains(startingChunk)) return false;
            //Check pre quest skill requirement
            for (Skills skill : Skills.values()) {
                if(currentSkills.get(skill) < skillReqs.get(skill)) return false;
            }
            //Check pre quest completion requirements
            for (String s:questReqs){
                if(!completedQuests.contains(s)) return false;
            }
            //Check they have enough quest points to start
            if(questPointsReqs > questPoints) return false;
        }

        //Check each step of the quest, returning true if first non completed step is completable.
        for(QuestStep s: steps){
            if(!s.isCompleted()){
                if (s.completable(unlockedChunks, currentSkills)){
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public boolean isStarted(){
        return started;
    }
    
    public void startQuest(){
        started = true;
    }
    
    public String getName(){
        return name;
    }
}