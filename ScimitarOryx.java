import java.util.ArrayList;




public class ScimitarOryx extends Animal{
	public String[] abilList = {"Vicous Strike", "Destroy Agility", "Increase Safety", "Recuperate"};

	public ScimitarOryx(String name) {
		super(name, "Scimitar Oryx", 12, 2, 150);


	}
	public ScimitarOryx(String name, int parseInt, int parseInt2, int parseInt3) {
		super(name, "Scimitar Oryx",  parseInt, parseInt2, parseInt3);
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

		int preDmg = str*(rdzr()/2); 
		if (cth(enemy)){
			enemy.health -= preDmg;
			return name + " strikes for " + preDmg + "!"; 
		}
		return "The ability misses!";
	}
	
	public String offAbilTwo(Animal enemy){
		health -= rdzr();
		int preDmg = rdzr(); 
		if (cth(enemy)){
			enemy.agil -= preDmg;
			return name + " strikes agility for " + preDmg + "! Enemy's agility was lowered to " + enemy.agil; 
		}
		return "The ability misses!";
	}

}
