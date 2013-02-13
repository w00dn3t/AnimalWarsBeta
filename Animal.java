import java.util.ArrayList;
import java.util.Random;

public class Animal {
	//Current Stats
	protected int str;
	protected int agil;
	protected int health;

	//Raws -- Raw Stats unmodified
	final private int rstr;
	final private int ragil;
	final private int rhealth;
	//End Raw
	
	//Identity
	protected String name;
	protected String type;
	protected String[] abilList = {"Normal Strike", "Ferocious Strike", "Increase Safety", "Recuperate"};
	
	public Animal(String name){
		this.str = 0;
		this.agil = 0;
		this.name = name;
		this.type = "";
		this.health = 0;
		rstr = 0;
		ragil = 0;
		rhealth = 0;
	}
	public Animal(String name, String type, int str, int agil, int health){
		this.str = str;
		this.agil = agil;
		this.name = name;
		this.type = type;
		this.health = health;
		rstr = str;
		ragil = agil;
		rhealth = health;
	}
	
	//General Abilities
	public String offAbilOne(Animal enemy, int rand){
		
		int preDmg = rand*str; 
		if (cth(enemy, rand)){
			enemy.health -= preDmg;
			return name + " strikes for " + preDmg + "damage!";
		}
		return "The ability misses!";
	}
	public String offAbilTwo(Animal enemy, int rand){
		str += 4;
		agil -= 4;
		int preDmg = rdzr()*str; 
		if (cth(enemy, rand)){
			enemy.health -= preDmg;
			return name + " strikes for " + preDmg + "damage! Strength was raised to " + str + " Agility was lowered to " + agil; 
		}
		return "The ability misses! Strength was raised to " + str + " Agility was lowered to " + agil;
	}
	public String defAbilOne(Animal enemy){
		agil += 5;
		return name + "'s Agility was raised to " + agil;
	}
	public String defAbilTwo(Animal enemy){
		health += getRhealth() * 0.1;
		return name + " recovered 10% Health, it is now  " + health;
	}
	
	public void beastialWrath(){
		this.str += 15;
	}
	//End General Abilities
	public String listStatus(){
		return getName() + " the " + getType() + "'s status:\nHealth:\t" + getHealth() +"\nStrength:\t" + getStr() + "\nAgility:\t" + getAgil();
	}

	public String listAbil(int i){
		return abilList[i];
	}
	
	
	//Randomizer && Ability Chancing
	public static int rdzr(){
		Random rand = new Random();
		return rand.nextInt(10);	
	}
	
	protected boolean cth(Animal targ, int rand){
		if (rand*targ.agil > 100)
			return false;
		return true;	
	}

	
	//Accessors
	public int getStr(){
		return str;
	}
	public int getAgil(){
		return agil;
	}
	public int getHealth(){
		return health;
	}
	public String getName(){
		return name;
	}
	public String getType(){
		return type;
	}
	public String listAbil(){
		String temp = getName() + "'s abilities in order 10, 20, 30, 40: ";
		for (String x: abilList)
			temp += x + ", ";
		return temp;
	}
	public ArrayList<String> statList(){
		//Returns a list
		//0 being Type
		//1 being Name
		//2 being Strength
		//3 being Agility
		ArrayList<String> list = new ArrayList();
		list.add(getType());
		list.add(getName());
		list.add(Integer.toString(getStr()));
		list.add(Integer.toString(getAgil()));
		return list;
	}
	public int getRhealth() {
		return rhealth;
	}
}
