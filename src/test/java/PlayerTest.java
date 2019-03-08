import JSON.DataParser;
import constants.Skills;
import objects.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumMap;

public class PlayerTest {
    Player p;

    @Before
    public void setUp() throws Exception {
        DataParser.parse(true);
        p = new Player(1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCurrentStats() {
        System.out.println("test 1");
        EnumMap<Skills, Integer> testArray = new EnumMap<Skills, Integer>(Skills.class);
        for (Skills skill : Skills.values()) {
            testArray.put(skill, 1);
        }
        testArray.put(Skills.Hitpoints,10);
        Assert.assertEquals(testArray, p.getCurrentStats());
    }

    @Test
    public void getCurrentSkillLvl() {
        p.setCurrentSkillLvl(Skills.Agility, 3);
        Assert.assertEquals(p.getCurrentSkillLvl(Skills.Agility), 3);
        Assert.assertEquals(p.getCurrentSkillLvl(Skills.Attack), 1);

    }

    @Test
    public void setCurrentSkillLvl() {
        Assert.assertEquals(p.getCurrentSkillLvl(Skills.Agility), 1);
        p.setCurrentSkillLvl(Skills.Agility, 3);
        Assert.assertEquals(p.getCurrentSkillLvl(Skills.Agility), 3);
    }

    @Test
    public void isTrainable() {
    }

    @Test
    public void getUnlockedChunks() {
    }

    @Test
    public void getCompletedQuests() {
    }

    @Test
    public void printStats() {
    }
}