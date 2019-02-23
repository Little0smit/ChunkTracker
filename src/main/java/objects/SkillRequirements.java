package objects;

import constants.Constant;
import constants.Skills;

import java.util.EnumMap;

//Wrapper class for skill requirements
public class SkillRequirements {
	private EnumMap<Skills, Integer> skills;

	public SkillRequirements (){
		skills = new EnumMap<Skills, Integer>(Skills.class);
		for (Skills skill : Skills.values()){
			skills.put(skill, 1);
		}
	}

	public SkillRequirements (int[] skillz){
		skills = new EnumMap<Skills, Integer>(Skills.class);
		for (int i = 0; i < skillz.length; i++){
			skills.put(Skills.valueOf(Constant.ORDER_OF_STRINGS[i]), skillz[i]);
		}
	}

	public SkillRequirements (EnumMap<Skills, Integer> skillReqs){
		skills = new EnumMap<Skills, Integer>(skillReqs);
	}

	public EnumMap<Skills, Integer> getSkills() {
		return skills;
	}

	public void setSkillRequirement (Skills skill, int level){
		skills.put(skill, level);
	}

	public boolean fulfillsRequirements(EnumMap<Skills, Integer> currentSkills){
		for (Skills skill : Skills.values()) {
			if(currentSkills.get(skill) < skills.get(skill)) return false;
		}
		return true;
	}

	public int[] toIntArr(){
		int[] arr = new int[Constant.NUMBER_OF_SKILLS];

		for (int i = 0; i < Constant.NUMBER_OF_SKILLS; i++){
			arr[i] = skills.get(Skills.valueOf(Constant.ORDER_OF_STRINGS[i]));
		}

		return arr;
	}
}
