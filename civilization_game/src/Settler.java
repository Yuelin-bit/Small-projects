public class Settler extends Unit {

	
	
	public Settler(Tile position, double hp, String faction) {
		super(position, hp, 2, faction);
	}
	
	public void takeAction(Tile tile) {
		if((tile.isCity()==false)&&(this.getPosition().equals(tile))) {
			tile.foundCity();
			tile.removeUnit(this);			
		}
	}
	
		
		public boolean equals(Object object) {
			if(object instanceof Settler)
				return super.equals(object);
			else return false;
		}
	
	
	
}
	
