import constants.Constant;
import databases.QuestDatabase;
import databases.UnlockedItemDatabase;
import objects.Item;
import objects.Player;
import objects.quests.Quest;
import objects.quests.QuestStep;

import java.util.ArrayList;

public class main {
    private static ArrayList<Integer> unlockedChunks;
    private static int[] currentSkills;
    private static ArrayList<String> completedQuests;

    //TODO create chunk class
    //TODO create skilling/processing
    //TODO create task class
    //Test main to ensure things are working.
    public static void main(String[] args) {
        playerTest();
        unlockedChunks = new ArrayList<Integer>();
        unlockedChunks.add(1);
        currentSkills = new int[Constant.NUMBER_OF_Skills];
        currentSkills[0] = 2;
        currentSkills[1] = 2;
        currentSkills[2] = 2;
        completedQuests = new ArrayList<String>();

        ArrayList<Integer> chunks = new ArrayList<Integer>();
        chunks.add(1);
        Item item = new Item("Test Item", chunks);

        UnlockedItemDatabase.addToDB(item);

        ArrayList<String> itemReqs = new ArrayList<String>();
        itemReqs.add("Test Item 2");
        ArrayList<String> itemReqs2 = new ArrayList<String>();
        QuestStep q1= new QuestStep(1,"step 1",itemReqs,1);
        QuestStep q2 = new QuestStep(2,"step 2", itemReqs2, 2);
        ArrayList<QuestStep> steps = new ArrayList<QuestStep>();
        steps.add(q1);
        steps.add(q2);
        int[] skillReqs = new int[Constant.NUMBER_OF_Skills];
        skillReqs[0] = 1;
        skillReqs[1] = 1;
        skillReqs[2] = 1;
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
        currentSkills = new int[Constant.NUMBER_OF_Skills];

        for (int chunk:unlockedChunks) {
            p.unlockChunk(chunk);
        }

        for (int i = 0; i < Constant.NUMBER_OF_Skills; i++) {
            p.setCurrentSkillLvl(i,currentSkills[i]);
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

        int[] skillReqs = new int[Constant.NUMBER_OF_Skills];
        for (int i = 0; i < Constant.NUMBER_OF_Skills; i++) {
            skillReqs[i] = 0;
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

