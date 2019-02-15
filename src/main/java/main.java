import Items.Item;
import Items.ItemDatabase;
import quests.Quest;
import quests.QuestStep;
import constants.Constant;

import java.util.ArrayList;

public class main {
    private static ArrayList<Integer> unlockedChunks;
    private static int[] currentSkills;
    private static ArrayList<String> completedQuests;
    //ItemDatabase db = new ItemDatabase();
    public static void main(String[] args) {

        unlockedChunks = new ArrayList();
        unlockedChunks.add(1);
        currentSkills = new int[Constant.NUMBER_OF_Skills];
        currentSkills[0] = 2;
        currentSkills[1] = 2;
        currentSkills[2] = 2;
        completedQuests = new ArrayList();

        ArrayList<Integer> chunks = new ArrayList();
        chunks.add(1);
        Item item = new Item("Test Item", chunks);

        ItemDatabase.addToDB(item);

        ArrayList<String> itemReqs = new ArrayList();
        itemReqs.add("Test Item 2");
        ArrayList<String> itemReqs2 = new ArrayList();
        QuestStep q1= new QuestStep(1,"step 1",itemReqs,1);
        QuestStep q2 = new QuestStep(2,"step 2", itemReqs2, 2);
        ArrayList<QuestStep> steps = new ArrayList();
        steps.add(q1);
        steps.add(q2);
        int[] skillReqs = new int[Constant.NUMBER_OF_Skills];
        skillReqs[0] = 1;
        skillReqs[1] = 1;
        skillReqs[2] = 1;
        ArrayList<String> questReqs = new ArrayList();
        Quest quest = new Quest("Quest 1",steps,1,skillReqs,0,questReqs);
        System.out.println();
        System.out.println("Quest: " + quest.getName() + " has a completable section: " + quest.isCompletable(unlockedChunks, currentSkills,completedQuests));
    }
}
