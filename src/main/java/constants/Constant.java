package constants;

import databases.Database;
import objects.*;
import objects.Process;
import objects.quests.Quest;

public class Constant {
    public static final int NUMBER_OF_SKILLS = Skills.values().length;
    public static final String[] ORDER_OF_STRINGS = {"Attack", "Strength", "Defence", "Ranged", "Prayer", "Magic", "Runecrafting", "Construction",
            "Hitpoints", "Agility", "Herblore", "Thieving", "Crafting", "Fletching", "Slayer", "Hunter",
            "Mining", "Smithing", "Fishing", "Cooking", "Firemaking", "Woodcutting", "Farming"};
    public static final Database<Mob> MOB_DATABASE = new Database<Mob>();
    public static final Database<Chunk> CHUNK_DATABASE = new Database<Chunk>();
    public static final Database<Item> ITEM_DATABASE = new Database<Item>();
    public static final Database<Item> UNLOCKED_ITEM_DATABASE = new Database<Item>();
    public static final Database<Quest> QUEST_DATABASE = new Database<Quest>();
    public static final Database<Shop> SHOP_DATABASE = new Database<Shop>();
    public static final Database<ProcessingTool> PROCESSING_TOOL_DATABASE = new Database<ProcessingTool>();
    public static final Database<Process> PROCESS_DATABASE = new Database<Process>();
    public static final Database<EquippableItem> EQUIPPABLE_ITEM_DATABASE = new Database<EquippableItem>();

}
