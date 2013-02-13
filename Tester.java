import javax.swing.JOptionPane;


public class Tester {

	public static void main(String[] args){
		
//		AnimalUI UI = new AnimalUI(JOptionPane.showInputDialog("Player 1's name?"), JOptionPane.showInputDialog("Player 2's name?"));
		Animal jeff = new Aardvark("Jeff");
		System.out.println(jeff.toString());
	}

}
