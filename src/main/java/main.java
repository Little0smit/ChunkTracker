import JSON.DataParser;
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
        player.printStats();
    }

    /*
    Not sure if i like the data collection classes being in main, considering new locations they can be instead.
     */

    /**
     * Gets data from the user and returns a Player Object containing this data.
     * @return Player Object with all the players entered data
     */
    private static Player collectPlayerData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter player Name");
        String name = in.nextLine();
        EnumMap<Skills, Integer> currentStats = getCurrentStats(in);
        ArrayList<String> quests = getCompletedQuests(in);
        int[] unlockedChunks = getUnlockedChunks(in);

        return new Player(name, currentStats, quests, unlockedChunks);
    }

    /**
     * Gets the player's skill level in each skill from the given input, returning them as a EnumMap
     * @param in a Scanner object to read input from
     * @return EnumMap with Key:Skill and value:level of every skill.
     */
    private static EnumMap<Skills, Integer> getCurrentStats(Scanner in) {
        EnumMap<Skills, Integer> currentStats = new EnumMap<Skills, Integer>(Skills.class);
        for (Skills skill: Skills.values()) {
            Boolean valid = false;
            int value = 1;
            while(!valid) {
                System.out.print("Enter your current " + skill.toString() + " level: ");
                value = in.nextInt();
                in.nextLine();
                //TODO input validation here
                valid = true;
            }
            currentStats.put(skill, value);
        }
        return currentStats;
    }

    /**
     *Get's all of the chunks a user has unlocked and returns them as an int array
     * @param in a Scanner object to read input from
     * @return int[] of all unlocked chunks.
     */
    private static int[] getUnlockedChunks(Scanner in) {
        Boolean valid = false;
        String[] chunksSplitString = new String[0];
        while(!valid){
            System.out.println("Enter a list of all chunks unlocked, separated by a comma.");
            String allChunks = in.nextLine();
            chunksSplitString = allChunks.split(",");
            //TODO input validation here.
            valid = true;
        }
        int[] chunks = new int[chunksSplitString.length];
        for (int i = 0; i < chunks.length; i++) {
            chunks[i] = Integer.parseInt(chunksSplitString[i].trim());
        }
        return chunks;
    }

    /**
     * Get's the list of quests they user has completed and returns an ArrayList of them
     * @param in a Scanner object to read input from
     * @return ArrayList of all the completed quests as strings
     */
    public static ArrayList<String> getCompletedQuests(Scanner in){
        Boolean valid = false;
        String[] questsSplit = new String[0];
        ArrayList<String> quests = new ArrayList();
        while(!valid){
            System.out.println("Enter a list of all quest's completed, as spelt in quest log, separated by a comma.");
            String questsFull = in.nextLine();
            //TODO do some input validation here.
            valid = true;
            //TODO got to change this as only getting 1 quest.
            questsSplit = questsFull.split(",");
        }
        for (String quest: questsSplit) {
            quests.add(quest.trim());
        }
        return quests;
    }
}
