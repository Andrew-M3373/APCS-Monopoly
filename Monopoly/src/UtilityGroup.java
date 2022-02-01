
public class UtilityGroup extends Game {

	private String utilityGroupTitle;
	private String utility1Name;
	private int utility1Price;
	private int location1;
	private String utility2Name;
	private int utility2Price;
	private int location2;
	
	public UtilityGroup(String t, String p1n, int p1p, int l1, String p2n, int p2p, int l2) {
		
		utilityGroupTitle=t;
		utility1Name=p1n;
		utility1Price=p1p;
		location1=l1;
		utility2Name=p2n;
		utility2Price=p2p;
		location2=l2;
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

	public String getGroupTitle() {
		return utilityGroupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.utilityGroupTitle = groupTitle;
	}

	public String getProperty1Name() {
		return utility1Name;
	}

	public void setProperty1Name(String property1Name) {
		this.utility1Name = property1Name;
	}

	public int getProperty1Price() {
		return utility1Price;
	}

	public void setProperty1Price(int property1Price) {
		this.utility1Price = property1Price;
	}

	public String getProperty2Name() {
		return utility2Name;
	}

	public void setProperty2Name(String property2Name) {
		this.utility2Name = property2Name;
	}

	public int getProperty2Price() {
		return utility2Price;
	}

	public void setProperty2Price(int property2Price) {
		this.utility2Price = property2Price;
	}
}
