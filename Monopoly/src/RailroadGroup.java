
public class RailroadGroup extends Game {

	private String railroadGroupTitle;
	private int railroadPrices;
	private String railroad1Name;
	private int location1;
	private String railroad2Name;
	private int location2;
	private String railroad3Name;
	private int location3;
	private String railroad4Name;
	private int location4;
	
	public RailroadGroup(String t, int price, String r1n, int l1, String r2n, int l2, String r3n, int l3, String r4n, int l4) {
		
		railroadGroupTitle=t;
		railroadPrices = price;
		railroad1Name = r1n;
		location1=l1;
		railroad2Name = r2n;
		location2=l2;
		railroad3Name = r3n;
		setLocation3(l3);
		railroad4Name = r4n;
		location4=l4;
		
	}

	public int getLocation1() {
		return location1;
	}

	public void setLocation1(int location1) {
		this.location1 = location1;
	}

	public int getLocation2() {
		return location2;
	}

	public void setLocation2(int location2) {
		this.location2 = location2;
	}

	public int getLocation4() {
		return location4;
	}

	public void setLocation4(int location4) {
		this.location4 = location4;
	}

	public String getRailroadGroupTitle() {
		return railroadGroupTitle;
	}

	public void setRailroadGroupTitle(String railroadGroupTitle) {
		this.railroadGroupTitle = railroadGroupTitle;
	}

	public int getRailroadPrices() {
		return railroadPrices;
	}

	public void setRailroadPrices(int railroadPrices) {
		this.railroadPrices = railroadPrices;
	}

	public String getRailroad1Name() {
		return railroad1Name;
	}

	public void setRailroad1Name(String railroad1Name) {
		this.railroad1Name = railroad1Name;
	}

	public String getRailroad2Name() {
		return railroad2Name;
	}

	public void setRailroad2Name(String railroad2Name) {
		this.railroad2Name = railroad2Name;
	}

	public String getRailroad3Name() {
		return railroad3Name;
	}

	public void setRailroad3Name(String railroad3Name) {
		this.railroad3Name = railroad3Name;
	}

	public String getRailroad4Name() {
		return railroad4Name;
	}

	public void setRailroad4Name(String railroad4Name) {
		this.railroad4Name = railroad4Name;
	}
	
	public int getRailroad1Location() {
		return 5;
	}
	
	public int getRailroad2Location() {
		return 15;
	}
	
	public int getRailroad3Location() {
		return 25;
	}
	
	public int getRailroad4Location() {
		return 35;
	}

	public int getLocation3() {
		return location3;
	}

	public void setLocation3(int location3) {
		this.location3 = location3;
	}
}
