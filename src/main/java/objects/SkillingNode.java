package objects;

import java.util.ArrayList;
import java.util.HashMap;

//TODO make this class

//Code here is very messy and trial code to figure out
//how best to make this class.
//TODO tidy.finish this before commit
public class SkillingNode {
    HashMap<String, Integer> skill = new HashMap<String, Integer>();
    SkillLevelItem skillLevelItem;

    public ArrayList<String> getOutputs() {
        //TODO
        return null;
    }

    private class SkillLevelItem {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        public void test() {
            hashMap.put("String", 1);
        }

    }
}
