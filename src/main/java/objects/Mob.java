package objects;

public class Mob {
    String name;
    int combatLvl;
    Item[] drops;

    public Mob(String name, int combatLvl, Item[] drops) {
        this.name = name;
        this.combatLvl = combatLvl;
        this.drops = drops;
    }

    public String getName() {
        return name;
    }

    public int getCombatLvl() {
        return combatLvl;
    }

    public Item[] getDrops() {
        return drops;
    }
}
