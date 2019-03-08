import JSON.DataParser;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import constants.Constant;
import constants.Skills;
import objects.Player;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        if (args.length > 0){
            DataParser.parse(args[0].equals("debug"));
        } else {
            DataParser.parse(false);
        }

        Player player = collectPlayerData();;
        player.checkTrainableSkills();
    }

    /*
    Not sure if i like the data collection classes being in main, considering new locations they can be instead.
     */
    private static Player collectPlayerData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter player Name");
        String name = in.nextLine();

        //Get player info
        EnumMap<Skills, Integer> currentStats = getCurrentStats(in);
        ArrayList<String> quests = getCompletedQuests(in);
        int[] unlockedChunks = getUnlockedChunks(in);

        return new Player(name, currentStats, quests, unlockedChunks);
    }

    private static EnumMap<Skills, Integer> getCurrentStats(Scanner in) {
        EnumMap<Skills, Integer> currentStats = new EnumMap<Skills, Integer>(Skills.class);
        for (Skills skill: Skills.values()) {
            Boolean valid = false;
            int value = 1;
            while(!valid) {
                System.out.print("Enter your current " + skill.toString() + " level: ");
                value = in.nextInt();
                //TODO input validation here
                valid = true;
            }
            currentStats.put(skill, value);
        }
        return currentStats;
    }

    private static int[] getUnlockedChunks(Scanner in) {
        Boolean valid = false;
        String[] chunksSplitString = new String[0];
        int[] chunks = new int[0];
        while(!valid){
            System.out.println("Enter a list of all chunks unlocked, separated by a comma.");
            String allChunks = in.nextLine();
            //TODO input validation here.
            valid = true;
            chunksSplitString = allChunks.split(",");
        }
        chunks = new int[chunksSplitString.length];
        for (int i = 0; i < chunks.length; i++) {
            chunks[i] = Integer.parseInt(chunksSplitString[i]);
        }
        return chunks;
    }

    public static ArrayList<String> getCompletedQuests(Scanner in){
        Boolean valid = false;
        String[] questsSplit = new String[0];
        ArrayList<String> quests = new ArrayList();
        while(!valid){
            System.out.println("Enter a list of all quest's completed, as spelt in quest log, separated by a comma.");
            String questsFull = in.nextLine();
            //TODO do some input validation here.
            valid = true;
            questsSplit = questsFull.split(",");
        }
        for (String quest: questsSplit) {
            quests.add(quest);
        }
        return quests;
    }
}

