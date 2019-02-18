import constants.Constant;
import constants.Skills;
import databases.QuestDatabase;
import databases.UnlockedItemDatabase;
import objects.Item;
import objects.Player;
import objects.quests.Quest;
import objects.quests.QuestStep;

import java.util.ArrayList;
import java.util.EnumMap;

public class main {
    private static ArrayList<Integer> unlockedChunks;
    private static EnumMap<Skills, Integer> currentSkills;
    private static ArrayList<String> completedQuests;

    //TODO create chunk class
    //TODO create skilling/processing
    //TODO create task class
    //Test main to ensure things are working.
    public static void main(String[] args) {
        playerTest();
        unlockedChunks = new ArrayList<Integer>();
        unlockedChunks.add(1);
        currentSkills = new EnumMap<Skills, Integer>(Skills.class);
        for (Skills skill : Skills.values()){
            currentSkills.put(skill, 1);
        }
        currentSkills.put(Skills.Attack, 2);
        currentSkills.put(Skills.Defence, 2);
        currentSkills.put(Skills.Strength, 2);
        completedQuests = new ArrayList<String>();

        ArrayList<Integer> chunks = new ArrayList<Integer>();
        chunks.add(1);
        Item item = new Item("Test Item", chunks);

        UnlockedItemDatabase.addToDB(item);

        ArrayList<String> itemReqs = new ArrayList<String>();
        itemReqs.add("Test Item");
        ArrayList<String> itemReqs2 = new ArrayList<String>();
        QuestStep q1= new QuestStep(1,"step 1",itemReqs,1);
        QuestStep q2 = new QuestStep(2,"step 2", itemReqs2, 2);
        ArrayList<QuestStep> steps = new ArrayList<QuestStep>();
        steps.add(q1);
        steps.add(q2);
        EnumMap<Skills, Integer> skillReqs = new EnumMap<Skills, Integer>(Skills.class);
        for (Skills skill : Skills.values()){
            skillReqs.put(skill, 1);
        }
        skillReqs.put(Skills.Attack, 2);
        skillReqs.put(Skills.Defence, 2);
        skillReqs.put(Skills.Strength, 2);

        ArrayList<String> questReqs = new ArrayList<String>();
        Quest quest = new Quest("Quest 1",steps,1,skillReqs,0,questReqs);
        System.out.println();
        System.out.println("Quest: " + quest.getName() + " has a completable section: " + quest.isCompletable(unlockedChunks, currentSkills,completedQuests,0));
    }

    //Junk test method.
    private static void playerTest(){
        Player p = new Player();
        unlockedChunks = new ArrayList<Integer>();
        completedQuests = new ArrayList<String>();
        currentSkills = new EnumMap<Skills, Integer>(Skills.class);

        for (Skills skill : Skills.values()){
            currentSkills.put(skill, 1);
        }

        for (int chunk:unlockedChunks) {
            p.unlockChunk(chunk);
        }

        for (Skills skill : Skills.values()) {
            p.setCurrentSkillLvl(skill, currentSkills.get(skill));
        }

        for (String quest:completedQuests) {
            p.completeQuest(quest);
        }

        ArrayList<Quest> allQuests;
        allQuests = createQuests();
        for (Quest q:allQuests) {
            QuestDatabase.addToDB(q);
        }

        System.out.println("10");
    }

    private static ArrayList<Quest> createQuests() {
        //TODO incorporate a filereader rather than manual quest info.
        ArrayList<Quest> quests = new ArrayList<Quest>();
        //Create Cooks assistant and imp catcher;

        //Imp Catcher
        ArrayList<String> itemReqs = new ArrayList<String>();
        itemReqs.add("Yellow Bead");
        itemReqs.add("Red Bead");
        itemReqs.add("Black Bead");
        itemReqs.add("While Bead");
        QuestStep qs1 = new QuestStep(1, "Hand in beads to wizard", itemReqs,12337);
        ArrayList<QuestStep> questSteps = new ArrayList<QuestStep>();
        questSteps.add(qs1);

        EnumMap<Skills, Integer> skillReqs = new EnumMap<Skills, Integer>(Skills.class);
        for (Skills skill : Skills.values()){
            skillReqs.put(skill, 1);
        }
        ArrayList<String> questReqs = new ArrayList<String>();
        Quest q1 = new Quest("Imp Catcher", questSteps,12337, skillReqs, 0, questReqs);
        quests.add(q1);

        //Cooks assistant
        itemReqs = new ArrayList<String>();
        itemReqs.add("Pot of Flour");
        itemReqs.add("Egg");
        itemReqs.add("Bucket of Milk");
        qs1 = new QuestStep(1, "Hand in ingredients to chef", itemReqs,12850);
        questSteps = new ArrayList<QuestStep>();
        questSteps.add(qs1);


        Quest q2 = new Quest("Cooks Assistant", questSteps,12850, skillReqs, 0, questReqs);
        quests.add(q2);
        return quests;
    }
}

