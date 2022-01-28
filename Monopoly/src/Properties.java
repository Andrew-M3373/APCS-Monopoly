
public class Properties extends Game {


	private PropertyGroup1 propertyGroup1;
	private PropertyGroup2 propertyGroup2;
	private PropertyGroup3 propertyGroup3;
	private PropertyGroup4 propertyGroup4;
	private PropertyGroup5 propertyGroup5;
	private PropertyGroup6 propertyGroup6;
	private PropertyGroup7 propertyGroup7;
	private PropertyGroup8 propertyGroup8;
	
	protected String groupTitle;
	protected String property1Name;
	protected int property1Price;
	protected int location1;
	protected String property2Name;
	protected int property2Price;
	protected int location2;
	protected String property3Name;
	protected int property3Price;
	protected int location3;
	
	
	public Properties() {
		
	}
	
	public Properties(PropertyGroup1 g1, PropertyGroup2 g2, PropertyGroup3 g3, PropertyGroup4 g4, PropertyGroup5 g5, 
			PropertyGroup6 g6, PropertyGroup7 g7, PropertyGroup8 g8) {
		
		propertyGroup1 = g1;
		propertyGroup2 = g2;
		propertyGroup3 = g3;
		propertyGroup4 = g4;
		propertyGroup5 = g5;
		propertyGroup6 = g6;
		propertyGroup7 = g7;
		propertyGroup8 = g8;
		
	}

	public PropertyGroup1 getPropertyGroup1() {
		return propertyGroup1;
	}

	public void setPropertyGroup1(PropertyGroup1 propertyGroup1) {
		this.propertyGroup1 = propertyGroup1;
	}

	public PropertyGroup2 getPropertyGroup2() {
		return propertyGroup2;
	}

	public void setPropertyGroup2(PropertyGroup2 propertyGroup2) {
		this.propertyGroup2 = propertyGroup2;
	}

	public PropertyGroup3 getPropertyGroup3() {
		return propertyGroup3;
	}

	public void setPropertyGroup3(PropertyGroup3 propertyGroup3) {
		this.propertyGroup3 = propertyGroup3;
	}

	public PropertyGroup4 getPropertyGroup4() {
		return propertyGroup4;
	}

	public void setPropertyGroup4(PropertyGroup4 propertyGroup4) {
		this.propertyGroup4 = propertyGroup4;
	}

	public PropertyGroup5 getPropertyGroup5() {
		return propertyGroup5;
	}

	public void setPropertyGroup5(PropertyGroup5 propertyGroup5) {
		this.propertyGroup5 = propertyGroup5;
	}

	public PropertyGroup6 getPropertyGroup6() {
		return propertyGroup6;
	}

	public void setPropertyGroup6(PropertyGroup6 propertyGroup6) {
		this.propertyGroup6 = propertyGroup6;
	}

	public PropertyGroup7 getPropertyGroup7() {
		return propertyGroup7;
	}

	public void setPropertyGroup7(PropertyGroup7 propertyGroup7) {
		this.propertyGroup7 = propertyGroup7;
	}

	public PropertyGroup8 getPropertyGroup8() {
		return propertyGroup8;
	}

	public void setPropertyGroup8(PropertyGroup8 propertyGroup8) {
		this.propertyGroup8 = propertyGroup8;
	}

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public String getProperty1Name() {
		return property1Name;
	}

	public void setProperty1Name(String property1Name) {
		this.property1Name = property1Name;
	}

	public int getProperty1Price() {
		return property1Price;
	}

	public void setProperty1Price(int property1Price) {
		this.property1Price = property1Price;
	}

	public int getLocation1() {
		return location1;
	}

	public void setLocation1(int location1) {
		this.location1 = location1;
	}

	public String getProperty2Name() {
		return property2Name;
	}

	public void setProperty2Name(String property2Name) {
		this.property2Name = property2Name;
	}

	public int getProperty2Price() {
		return property2Price;
	}

	public void setProperty2Price(int property2Price) {
		this.property2Price = property2Price;
	}

	public int getLocation2() {
		return location2;
	}

	public void setLocation2(int location2) {
		this.location2 = location2;
	}

	public String getProperty3Name() {
		return property3Name;
	}

	public void setProperty3Name(String property3Name) {
		this.property3Name = property3Name;
	}

	public int getProperty3Price() {
		return property3Price;
	}

	public void setProperty3Price(int property3Price) {
		this.property3Price = property3Price;
	}

	public int getLocation3() {
		return location3;
	}

	public void setLocation3(int location3) {
		this.location3 = location3;
	}
	
	public int payIncomeTax() {
		return Database.gameDatabase.get(MonopolyRunner.getGameIndex()).getSpecialSpaces().getIncomeTaxPrice();
	}
}
