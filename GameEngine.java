
public class GameEngine {

	/**
	 * @param args
	 */
	public static void evaluate(Animal castAnimal, Animal targAnimal, String attackNum, String rand) {
		if (attackNum.equals("0")){
			castAnimal.offAbilOne(targAnimal, Integer.parseInt(rand));
		}
		if (attackNum.equals("1")){
			castAnimal.offAbilTwo(targAnimal, Integer.parseInt(rand));

		}
		if (attackNum.equals("2")){
			castAnimal.defAbilOne(targAnimal);

		}
		if (attackNum.equals("3")){
			castAnimal.defAbilTwo(targAnimal);

		}

	}
	public static Animal parseAnimal(String animal, String name){
		if (animal.equals("Aardvark")){
			return new Aardvark(name);
		}
		if (animal.equals("Ocelot")){
			return new Ocelot(name);

		}
		return null;
	}

}
