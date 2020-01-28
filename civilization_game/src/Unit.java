public abstract class Unit {
		
		
	private Tile Position;
	private double HP;
	private int am_range;
	private String Faction;
	

	public Unit(Tile position, double hp, int m_range, String faction) {
		this.Position = position;
		this.HP = hp;
		this.am_range = m_range;
		this.Faction = faction;	
		
		if(position.addUnit(this)==true);
		else
				throw new IllegalArgumentException("IllegalArgumentException");
	}
	
	
	
	
	public final Tile getPosition() {
		return this.Position;
	}
	public final double getHP(){
		return this.HP;
	}
	public final String getFaction(){
		return this.Faction;
	}
	

	
	public boolean moveTo(Tile position) {
		if(this.am_range+1 > position.getDistance(position,this.Position))
			{		
			if(position.addUnit(this)) 
				{
				this.getPosition().removeUnit(this);
				this.Position = position;
				return true;
				}		
			}
		return false;	
	}
	
	
	
	
	
	public void receiveDamage(double damage_received) {
		if(this.Position.isCity()==false)
			this.HP = this.HP - damage_received;
		if(this.Position.isCity()==true) {
			damage_received = damage_received * 0.9;
			this.HP = this.HP - damage_received;
		}
		if(this.HP<=0)
			this.Position.removeUnit(this);
	}
	
	
	
	public abstract void takeAction(Tile position);
	
	
	
	
	public boolean equals(Object object) {
		if(object instanceof Unit) {
			if((((Unit) object).getPosition().equals(this.getPosition()))
					&&(((Unit)object).getHP() == this.getHP())
					&&(((Unit)object).getFaction().equals(this.getFaction())==true))		
				return true;
		}
			return false;	
	}
		
	
}
	
	
	
	
	
	
	
