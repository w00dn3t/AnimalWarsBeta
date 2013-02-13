import java.util.ArrayList;




public class TemplateAnimal extends Animal{
	//Rename these abilities, they are displayed on the buttons.
	public String[] abilList = {"Offensive Ability One", "Offensive Ability Two", "Defensive Ability One", "Defensive Ability Two"};

	public TemplateAnimal(String name) {
		super(name, "Template Animal", 0, 0, 100);


	}
	
	
	//Abilities
	public String offAbilOne(Animal enemy){

		//This calculates if it should hit or not
		if (cth(enemy)){
			//Enter code about enemy.health being attacked.
		}
						
		return "The ability misses!";
	}
	public String offAbilTwo(Animal enemy){

		//This calculates if it should hit or not
		if (cth(enemy)){
			//Enter code about enemy.health being attacked.
		}
				
		return "The ability misses!";
	}
	
	public String defAbilOne(Animal enemy){
		return "The ability...";
		//Place code about how the this. is bufffed
	}
	
	public String defAbilTwo(Animal enemy){
		return "The ability...";
		//Place code about how the this. is bufffed
	}

	//Retrieve the abilities
	public String listAbil(int i){
		return abilList[i];
	}
	public String listAbil(){
		String temp = getName() + "'s abilities in order:\n";
		int i = 10;
		for (String x: abilList){
			temp += i + ": " + x + "\n";
			i += 10;
		}
		return temp;
	}
}
