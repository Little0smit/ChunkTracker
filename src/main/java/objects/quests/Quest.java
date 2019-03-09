package objects.quests;

import constants.Constant;
import constants.Skills;
import objects.NamedThing;
import objects.Player;
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
    public boolean isCompletable(ArrayList<Integer> unlockedChunks, EnumMap<Skills, Integer> currentSkills,
                                 ArrayList<String> completedQuests, int questPoints, EnumMap<Skills, Boolean> trainableSkills){
        if (completed) return false;

        SkillRequirements sr = new SkillRequirements(skillReqs);

        //Check pre-quest requirements if it's not been started
        if(!started){
            //Check you have access to start
            if(!unlockedChunks.contains(startingChunk)) return false;
            //Check pre quest skill requirement
            for (Skills skill : Skills.values()) {
                if(currentSkills.get(skill) < sr.getSkills().get(skill)  && !trainableSkills.get(skill)) return false;
            }
            //Check pre quest completion requirements
            for (String s : questReqs){
                if(!completedQuests.contains(s) && !Constant.QUEST_DATABASE.getElement(s)
                        .isFullyCompletable(unlockedChunks, currentSkills, completedQuests, questPoints, trainableSkills)) return false;
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

    /**
     * Gets the step number of the last completable step in a quest. Returns 0 if no steps are completable
     * @param unlockedChunks ArrayList of accessible chunks to the player, in ints.
     * @param currentSkills EnumMap of the current stats the player has.
     * @param completedQuests ArrayList of the quests the player has already completed, in Strings.
     * @param questPoints The current quest point count the player is at, in int.
     * @return int The step number of the last completable step in a quest.
    */
    public int getLastCompletableStep(ArrayList<Integer> unlockedChunks, EnumMap<Skills, Integer> currentSkills,
                                      ArrayList<String> completedQuests, int questPoints){
        int lastCompletableStep = 0;
        for(QuestStep s: steps){
            if(!s.isCompleted() && s.completable(unlockedChunks, currentSkills)){
                lastCompletableStep = Math.max(s.getStepNumber(), lastCompletableStep);
            }
        }
        return lastCompletableStep;
    }

    /**
     * Returns if the quest is able to be finished
     * @param unlockedChunks ArrayList of accessible chunks to the player, in ints.
     * @param currentSkills EnumMap of the current stats the player has.
     * @param completedQuests ArrayList of the quests the player has already completed, in Strings.
     * @param questPoints The current quest point count the player is at, in int.
     * @return boolean If the quest able to be finished
     */
    public boolean isFullyCompletable(ArrayList<Integer> unlockedChunks, EnumMap<Skills, Integer> currentSkills,
                                      ArrayList<String> completedQuests, int questPoints, EnumMap<Skills, Boolean> trainableSkills){
        SkillRequirements sr = new SkillRequirements(skillReqs);

        //Check pre-quest requirements if it's not been started
        if(!started){
            //Check you have access to start
            if(!unlockedChunks.contains(startingChunk)) return false;
            //Check pre quest skill requirement
            for (Skills skill : Skills.values()) {
                if(currentSkills.get(skill) < sr.getSkills().get(skill) && !trainableSkills.get(skill)) return false;
            }
            //Check pre quest completion requirements
            for (String s : questReqs){
                if(!completedQuests.contains(s) && !Constant.QUEST_DATABASE.getElement(s)
                        .isFullyCompletable(unlockedChunks, currentSkills, completedQuests, questPoints, trainableSkills))
                    return false;
            }
            //Check they have enough quest points to start
            if(questPointsReqs > questPoints) return false;
        }

        //Check each step of the quest, returning true if first non completed step is completable.
        for(QuestStep s: steps){
            if(!s.completable(unlockedChunks, currentSkills)){
                return false;
            }
        }
        return true;
    }

    public boolean isFullyCompletable(Player player) {
        ArrayList<Integer> unlockedChunks = player.getUnlockedChunks();
        EnumMap<Skills, Integer> currentSkills = player.getCurrentStats();
        ArrayList<String> completedQuests = player.getCompletedQuests();
        int questPoints = player.getQuestPoints();
        return this.isFullyCompletable(unlockedChunks, currentSkills,completedQuests,questPoints,player.getTrainableStats());
    }

    public boolean isCompletable(Player player) {
        ArrayList<Integer> unlockedChunks = player.getUnlockedChunks();
        EnumMap<Skills, Integer> currentSkills = player.getCurrentStats();
        ArrayList<String> completedQuests = player.getCompletedQuests();
        int questPoints = player.getQuestPoints();
        return this.isCompletable(unlockedChunks, currentSkills,completedQuests,questPoints,player.getTrainableStats());
    }

    public int getLastCompletableStep(Player player) {
        ArrayList<Integer> unlockedChunks = player.getUnlockedChunks();
        EnumMap<Skills, Integer> currentSkills = player.getCurrentStats();
        ArrayList<String> completedQuests = player.getCompletedQuests();
        int questPoints = player.getQuestPoints();
        return this.getLastCompletableStep(unlockedChunks, currentSkills,completedQuests,questPoints);
    }

    public EnumMap<Skills, Integer> getRequiredSkills(){ return new SkillRequirements(skillReqs).getSkills(); }

    public int getStartingChunk(){ return startingChunk; }

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

    public ArrayList<QuestStep> getQuestSteps(){ return steps; }

    /**
     * Gets the number of quest points rewarded for completing the quest
     * @return int the quest points awarded on completion.
     */
    public int getQuestPointsReward(){
        return questPointsReward;
    }
}