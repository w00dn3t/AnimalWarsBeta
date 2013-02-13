
import java.awt.*;
import java.io.*;
import java.util.*;


public class FileSaveLoad {
	
	
	public static void main(String[] args) throws IOException{
		Animal Raayan = new Ocelot("Raayan");
		FileSaveLoad load = new FileSaveLoad("C:\\AWZRaay.txt", "Raayan");
		load.printStats(Raayan);
//		Animal pl1 = load.load();
//		load.printStats(pl1);
//		System.out.println(load.listAll());
//		System.out.println(load.load().listAbil());
	}
	public Animal load(){
	if (name.equals(getList().get(1))){
		if (getList().get(0).equals("Aardvark")){
			return new Aardvark(name, Integer.parseInt(getList().get(2)), Integer.parseInt(getList().get(3)), Integer.parseInt(getList().get(4)));
		}
		if (getList().get(0).equals("Narwhal")){
			return new Narwhal(name, Integer.parseInt(getList().get(2)), Integer.parseInt(getList().get(3)), Integer.parseInt(getList().get(4)));
		}
		if (getList().get(0).equals("Ocelot")){
			return new Ocelot(name, Integer.parseInt(getList().get(2)), Integer.parseInt(getList().get(3)), Integer.parseInt(getList().get(4)));
		}
		if (getList().get(0).equals("Scimitar Oryx")){
			return new ScimitarOryx(name, Integer.parseInt(getList().get(2)), Integer.parseInt(getList().get(3)), Integer.parseInt(getList().get(4)));
		}
		if (getList().get(0).equals("Three Toed Sloth")){
			return new ThreeToedSloth(name, Integer.parseInt(getList().get(2)), Integer.parseInt(getList().get(3)), Integer.parseInt(getList().get(4)));
		}
	}
		return new Animal("Nope");
	}
	private ArrayList<String> list = new ArrayList();
	private File file;
	private String name;
	
	public ArrayList<String> getList(){
		return list;
	}
	public void printStats(Animal animal) throws IOException{

		if (getList().get(1).equals(animal.getName())){
		PrintWriter out = new PrintWriter(file); 
		
		out.println(animal.getType()); 
		out.println(animal.getName()); 
		out.println(animal.getStr()); 
		out.println(animal.getAgil()); 
		out.println(animal.getHealth()); 
		out.close();
		}
	}
	
	
	public String listAll(){
		String temp = "";
		for (Object x: list)
			temp += x + "\n";
		return temp;
	}
	
	
	
	//Constructor
	public FileSaveLoad(String location, String plname) {
	    file = new File(location);
	    name = plname;
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;

	    try {
	      fis = new FileInputStream(file);

	      // Here BufferedInputStream is added for fast reading.
	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);

	      // dis.available() returns 0 if the file does not have more lines.
	      while (dis.available() != 0) {

	      // this statement reads the line from the file and print it to
	        // the console.
	        list.add(dis.readLine());
	      }

	      // dispose all the resources after using them.
	      fis.close();
	      bis.close();
	      dis.close();

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	  }
	}
