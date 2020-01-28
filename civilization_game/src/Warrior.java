public class Warrior extends MilitaryUnit {

	public Warrior(Tile tile, double hp, String faction) {
		super(tile, hp, 1, faction, 20.0, 1, 25);
		
	}
	
	public boolean equals(Object object) {
		if(object instanceof Warrior)
			return super.equals(object);
		return false;
		
	}
	

}
