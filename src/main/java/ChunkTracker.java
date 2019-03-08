import JSON.DataParser;
import constants.Constant;
import constants.Skills;
import objects.Player;

import java.util.*;

public class ChunkTracker {
    public static void main(String[] args) {
        if (args.length > 0){
            DataParser.parse(args[0].equals("debug"));
        } else {
            DataParser.parse(false);
        }

        Player player = collectPlayerData();
        player.printStats();
    }

    /*
    Not sure if i like the data collection classes being in ChunkTracker, considering new locations they can be instead.
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
            boolean inValid = false;
            int value = 1;
            do {
                System.out.print("Enter your current " + skill.toString() + " level: ");
                //Considering swapping this try catch with hasNextInt() to make it neater
                try{
                    value = in.nextInt();
                    in.nextLine();
                } catch (InputMismatchException e){
                    System.out.println("Invalid input, must be a number");
                    inValid = true;
                }
                if(value < 1||value>99){
                    System.out.println("Invalid input, must be a number between 1-99 inclusive");
                    inValid = true;
                }
            } while (inValid);
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
        boolean inValid = false;
        int[] chunks;
        do{
            System.out.println("Enter a list of all chunks unlocked, separated by a comma.");
            String allChunks = in.nextLine();
            String[] chunksSplitString = allChunks.split(",");
            chunks = new int[chunksSplitString.length];
            for (int i = 0; i < chunks.length; i++) {
                try {
                    chunks[i] = Integer.parseInt(chunksSplitString[i].trim());
                } catch (NumberFormatException e){
                    System.out.println("Error: " + chunksSplitString[i].trim() + " is not a valid int");
                    inValid=true;
                    break;
                }
                if(!Constant.CHUNK_DATABASE.contains(chunksSplitString[i].trim())){
                    System.out.println(chunksSplitString[i].trim() + " is not a valid chunk number.");
                    inValid = true;
                    break;
                }
            }
        } while (inValid);
        return chunks;
    }

    /**
     * Get's the list of quests they user has completed and returns an ArrayList of them
     * @param in a Scanner object to read input from
     * @return ArrayList of all the completed quests as strings
     */
    private static ArrayList<String> getCompletedQuests(Scanner in) {
        boolean inValid = false;
        String[] questsSplit;
        do {
            System.out.println("Enter a list of all quest's completed, as spelt in quest log, separated by a comma.");
            String questsFull = in.nextLine();
            questsSplit = questsFull.split(",");
            for (int i = 0; i < questsSplit.length; i++) {
                questsSplit[i] = questsSplit[i].trim();
                if (!Constant.QUEST_DATABASE.contains(questsSplit[i])) {
                    System.out.println(questsSplit[i] + " is not a valid quest name, try again.");
                    inValid = true;
                    break;
                }
            }
        } while (inValid);
        return new ArrayList<String>(Arrays.asList(questsSplit));
    }
}