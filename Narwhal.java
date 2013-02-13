import java.util.ArrayList;




public class Narwhal extends Animal{
	public String[] abilList = {"Strong Swipe", "Impale", "Increase Safety", "Recuperate"};
	
	public Narwhal(String name) {
		super(name, "Narwhal", 18, 0, 200);
	

	}
	public Narwhal(String name, int parseInt, int parseInt2, int parseInt3) {
		super(name, "Narwhal", parseInt, parseInt2, parseInt3);
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
	
	public String offAbilOne(Animal enemy){

		int preDmg = str*(rdzr()); 
		if (cth(enemy)){
			enemy.health -= preDmg;
			return name + " swipes for " + preDmg + "!"; 
		}
		return "The ability misses!";
	}
	
	public String offAbilTwo(Animal enemy){
		int preDmg = rdzr()*str/2; 
		if (cth(enemy)){
			enemy.agil = 0;
			enemy.health -= preDmg;
			return name + " impales for " + preDmg + "! Enemy's agility was lowered to 0"; 
		}
		return "The ability misses!";
	}

}
