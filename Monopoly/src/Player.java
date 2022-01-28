import java.util.ArrayList;

public class Player {

	private String name;
	private int money;
	private int location;
	private boolean inJail;
	private int doublesCounter;
	private int jailCounter;
	private int reverse;
	private ArrayList <String[]> inventory = new ArrayList <String[]> ();
	
	public Player (String n, int m, int l, boolean j, int dc, int jc, int r, ArrayList <String[]> i) {
		
		name = n;
		money = m;
		location = l;
		inJail = j;
		doublesCounter = dc;
		jailCounter = jc;
		reverse = r;
		inventory = i;
		
	}

	public void setInventory(ArrayList<String[]> inventory) {
		this.inventory = inventory;
	}

	public int getReverse() {
		return reverse;
	}

	public void setReverse(int reverse) {
		this.reverse = reverse;
	}

	public int getJailCounter() {
		return jailCounter;
	}

	public void setJailCounter(int jailCounter) {
		this.jailCounter = jailCounter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;

	}
	
	public void addProperty(String[] s)
	{
		inventory.add(s);
	}

	public boolean isInJail() {
		return inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public int getDoublesCounter() {
		return doublesCounter;
	}

	public void setDoublesCounter(int doublesCounter) {
		this.doublesCounter = doublesCounter;
	}
	
	public ArrayList<String[]> getInventory() {
		return inventory;
	}
	
}
