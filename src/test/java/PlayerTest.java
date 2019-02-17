import constants.Constant;
import objects.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player p;

    @Before
    public void setUp() throws Exception {
        p = new Player();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCurrentStats() {
        System.out.println("test 1");
        int[] testArray = new int[Constant.NUMBER_OF_Skills];
        for (int i = 0; i < Constant.NUMBER_OF_Skills; i++) {
            testArray[i] = 1;
        }
        Assert.assertArrayEquals(testArray, p.getCurrentStats());
    }

    @Test
    public void getCurrentSkillLvl() {
        p.setCurrentSkillLvl(Constant.Agility, 3);
        Assert.assertEquals(p.getCurrentSkillLvl(Constant.Agility), 3);
        Assert.assertEquals(p.getCurrentSkillLvl(Constant.Attack), 1);

    }

    @Test
    public void setCurrentSkillLvl() {
        Assert.assertEquals(p.getCurrentSkillLvl(Constant.Agility), 1);
        p.setCurrentSkillLvl(Constant.Agility, 3);
        Assert.assertEquals(p.getCurrentSkillLvl(Constant.Agility), 3);
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