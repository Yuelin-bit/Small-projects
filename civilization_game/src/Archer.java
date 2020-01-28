public class Archer extends MilitaryUnit {
	
	private int a_arrow;
	
	
	public Archer(Tile tile, double hp, String faction) {
		super(tile, hp, 2, faction, 15.0, 2, 0);
		this.a_arrow = 5;
		
	}

	private int getA_arrow(){
		return this.a_arrow;
	}
	
	
	public void takeAction(Tile tile) {
		if(this.a_arrow == 0) {
			this.a_arrow = 5;
			return;
		}
		
		else this.a_arrow --;
		super.takeAction(tile);
	}
		
	
	
	public boolean equals(Object object) {
		if(object instanceof Archer)
			if(((Archer)object).getA_arrow()==this.a_arrow)
			return super.equals(object);
		return false;		
	}
	
	
	
	
}
