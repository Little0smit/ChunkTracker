package Databases;

import quests.Quest;

import java.util.HashMap;

public class Quests {
    private static HashMap<String, Quest> quests = new HashMap<String, Quest>();

    public static void addToDB(Quest quest){
        if(!quests.containsKey(quest.getName()))
            quests.put(quest.getName(), quest);
    }

    public static boolean contains(Quest quest){
        return quests.containsKey(quest.getName());
    }

    public static boolean contains(String quest){
        return quests.containsKey(quest);
    }

    public static Quest getItem(String quest) {
        return quests.get(quest);
    }

    }
