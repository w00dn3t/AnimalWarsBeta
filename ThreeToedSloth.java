import java.util.ArrayList;

public class ThreeToedSloth extends Animal{
	public String[] abilList = {"Normal Strike", "Drop", "Climb", "Recuperate"};
	private int heightCounter = 0;

	public ThreeToedSloth(String name) {
		super(name, "Three Toed Sloth", 4, 0, 110);
		

	}
	public ThreeToedSloth(String name, int parseInt, int parseInt2, int parseInt3) {
		super(name, "Three Toed Sloth", parseInt, parseInt2, parseInt3);
	}
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
	
	public String offAbilTwo(Animal enemy){
		int preDmg;
		if(heightCounter == 0){
			preDmg = 7;
		}else{
			preDmg = 10 * heightCounter;
		}
		this.agil -= 10 * heightCounter;
		heightCounter = 0;
		enemy.health -= preDmg;
		return name + " falls from the trees for " + preDmg + " damage!\nIt's agility returns to " + this.getAgil(); 
	}
	
	public String defAbilOne(Animal enemy){
		this.agil += 10;
		heightCounter++;
		return "The Sloth climbs it's way higher into the trees!\nIt's now " + heightCounter * 10 +" meters up!";
	}

}
