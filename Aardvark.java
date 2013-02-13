import java.util.ArrayList;




public class Aardvark extends Animal{
	public String[] abilList = {"Normal Strike", "Ferocious Strike", "Increase Safety", "Recuperate"};

	public Aardvark(String name) {
		super(name, "Aardvark", 6, 15, 90);


	}
	public Aardvark(String name, int str, int agil, int health) {
		super(name, "Aardvark", str, agil, health);


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

}
