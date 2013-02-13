import java.util.ArrayList;


public class Ocelot extends Animal{
	public String[] abilList = {"Cat Scratch", "Deep Cut", "Tense", "Recuperate"};
	private int bleedCounter = 0;
	private int addStrikes = 0;

	public Ocelot(String name) {
		super(name, "Ocelot", 15, 20, 75);

	}
	public Ocelot(String name, int parseInt, int parseInt2, int parseInt3) {
		super(name, "Ocelot", parseInt, parseInt2, parseInt3);
	}
	public String listAbil(int i){
		return abilList[i];
	}
	public String offAbilOne(Animal enemy, int rand){
		String temp = "";
		int strikes = 2 + addStrikes;
		addStrikes = 0;
		int preDmg;
		for(int i = 1; i <= strikes; i++){
			preDmg = str*(rdzr()/2);
			if (cth(enemy, rand)){
				enemy.health -= preDmg;
				temp += name + "'s strike number " + i + " strikes for " + preDmg + " damage!\n"; 
			}else{
				temp += "Strike number " + i + " misses!\n";
			}
		}
		return temp;
	}

	public String defAbilOne(Animal enemy){
		addStrikes++;
		this.agil += 5;
		return this.name + " tenses up!\nNext Cat Scratch will perform an addional strike!\nAgility is now " + this.getAgil() + "!";
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