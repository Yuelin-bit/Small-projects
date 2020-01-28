public abstract class MilitaryUnit extends Unit {
	
	private double attack_damage;
	private int attack_range;
	private int armor;
	
	
	public MilitaryUnit(Tile tile, double hp, int m_range, String faction, 
			double attack_damage, int attack_range, int armor) {
		
		super(tile, hp, m_range, faction);
		this.attack_damage = attack_damage;
		this.armor = armor;
		this.attack_range = attack_range;		
	}
	
	
	
	public void takeAction(Tile tile) {
		if(this.attack_range+1 > tile.getDistance(tile, this.getPosition())) {
			if(tile.selectWeakEnemy(this.getFaction()) != null) {
				if(this.getPosition().isImproved()==true) 
					tile.selectWeakEnemy(this.getFaction()).receiveDamage(attack_damage*1.05);
				else
					tile.selectWeakEnemy(this.getFaction()).receiveDamage(attack_damage*1.0);		
			}	
			else return;
		}
	}
	
	
	
	public void receiveDamage(double damage_received) {
		double multiplier;
		multiplier = 100.0/(100.0 + this.armor);
		super.receiveDamage(damage_received * multiplier);
	}
	
	

}
