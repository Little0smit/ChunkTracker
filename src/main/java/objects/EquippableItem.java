package objects;

import constants.EquipmentSlot;
import constants.WeaponType;

public class EquippableItem extends Item{
	private int[] offensiveStats, //stab, slash, crush, magic, ranged
			defensiveStats, //stab, slash, crush, magic, ranged
			otherStats; //strength, ranged strength, magic damage, prayer, attack speed
	private WeaponType type;
	private EquipmentSlot slot;

	public int[] getOffensiveStats() { return offensiveStats; }

	public int[] getDefensiveStats() { return defensiveStats; }

	public int[] getOtherStats() { return otherStats; }

	public WeaponType getType() { return type; }

	public EquipmentSlot getSlot() { return slot; }

	public float getBiSNumber(){
		return getBiSNumber(type);
	}

	public float getBiSNumber(WeaponType type){
		if (slot == EquipmentSlot.MainHand || slot == EquipmentSlot.Ammo){
			switch (type){
				case Magic:
					return offensiveStats[3];
				case Melee:
					return (float)((Math.max(Math.max(offensiveStats[0], offensiveStats[1]), offensiveStats[2]) +
							otherStats[0])) /
							otherStats[4];
				case Ranged:
					return (float)((offensiveStats[4] +
							otherStats[1])) /
							otherStats[4];
			}
		} else {
			switch (type){
				case Ranged:
					return otherStats[1] + (float)(defensiveStats[4])/1000;
				case Melee:
					return otherStats[0] + (float)(Math.max(Math.max(defensiveStats[0], defensiveStats[1]), defensiveStats[2]))/1000;
				case Magic:
					return otherStats[2] + (float)(defensiveStats[3])/1000;
			}
			return otherStats[0] + (float)(Math.max(Math.max(defensiveStats[0], defensiveStats[1]), defensiveStats[2]))/1000;
		}

		//This shouldn't ever be triggered
		return 0;
	}

	public int getPrayerNumber(){ return otherStats[3]; }
}
