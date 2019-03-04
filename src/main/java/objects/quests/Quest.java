package objects.quests;

import constants.Skills;
import objects.NamedThing;
import objects.requirements.SkillRequirements;

import java.util.ArrayList;
import java.util.EnumMap;

public class Quest extends NamedThing {
    private String name;
    private ArrayList<QuestStep> steps;
    private int startingChunk;
    private int[] skillReqs;
    private int questPointsReqs;
    private int questPointsReward;
    private ArrayList<String> questReqs;


    private boolean started;
    private boolean completed;

    public Quest(String name, ArrayList<QuestStep> steps, int startingChunk, int[] skillReqs, int questPointsReqs, int questPointsReward, ArrayList<String> questReqs) {
        this.name = name;
        this.steps = steps;
        this.startingChunk = startingChunk;
        this.skillReqs = skillReqs;
        this.questPointsReqs = questPointsReqs;
        this.questPointsReward = questPointsReward;
        this.questReqs = questReqs;
        completed = false;
        started = false;
    }

    /**
     * Checks to see if the next step of the quest is completable.
     * @param unlockedChunks ArrayList of accessible chunks to the player, in ints.
     * @param currentSkills EnumMap of the current stats the player has.
     * @param completedQuests ArrayList of the quests the player has already completed, in Strings.
     * @param questPoints The current quest point count the player is at, in int.
     * @return Boolean whether the next step is completable, False if completed or not possible to start.
     */
    public boolean isCompletable(ArrayList<Integer> unlockedChunks, EnumMap<Skills, Integer> currentSkills, ArrayList<String> completedQuests, int questPoints){
        if (completed) return false;

        SkillRequirements sr = new SkillRequirements(skillReqs);

        //Check pre-quest requirements if it's not been started
        if(!started){
            //Check you have access to start
            if(!unlockedChunks.contains(startingChunk)) return false;
            //Check pre quest skill requirement
            for (Skills skill : Skills.values()) {
                if(currentSkills.get(skill) < sr.getSkills().get(skill)) return false;
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
                return s.completable(unlockedChunks, currentSkills);
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

    /**
     * Gets the number of quest points rewarded for completing the quest
     * @return int the quest points awarded on completion.
     */
    public int getQuestPointsReward(){
        return questPointsReward;
    }
}