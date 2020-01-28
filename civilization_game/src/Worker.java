public class Worker extends Unit{
	
	private int jobs_done;
	
	
	public Worker(Tile position, double hp, String faction) {
		super(position, hp, 2, faction);
		this.jobs_done = 0;		
	}
	
	
	private int getJobsdone(){
		return this.jobs_done;
	}
	
	
	
	public void takeAction(Tile tile) {
		if((tile.isImproved()==false)&&(this.getPosition().equals(tile))) {
			tile.buildImprovement();
			this.jobs_done ++;
			}
		if(this.jobs_done==10)
			tile.removeUnit(this);
		}
	
	
	public boolean equals(Object object) {
		if(object instanceof Worker)
			if (((Worker) object).getJobsdone() == this.jobs_done) 
				return super.equals(object);		
		return false;
	}
	



}
