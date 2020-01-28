public class Tile {
	private int x_coordinate;
	private int y_coordinate;
	private boolean cityBuilt;
	private boolean receive_impr;
	private ListOfUnits list = new  ListOfUnits();

	public Tile(int x, int y) {
		this.x_coordinate = x;
		this.y_coordinate = y;
		this.cityBuilt = false;
		this.receive_impr = false;

	}



	public int getX() {
		return this.x_coordinate;
	}
	public int getY() {
		return this.y_coordinate;
	}
	public boolean isCity() {
		return this.cityBuilt;
	}
	public boolean isImproved() {
		return this.receive_impr;
	}
	
	
	
	public void foundCity() {
		if (this.cityBuilt==false) 
			this.cityBuilt = true;
	}
	public void buildImprovement() {
		if(this.receive_impr==false)
			this.receive_impr = true;
	}



	

	public boolean addUnit(Unit unit) {
		if(unit == null) return false;
		if(unit instanceof MilitaryUnit) {
			MilitaryUnit[] army = this.list.getArmy();

			if(army.length == 0) {
				list.add(unit);
				return true;
			}
			
			for(int k=0; k<army.length;k++) {
			//for(MilitaryUnit i : army) {
				if(!(army[k].getFaction().equals(unit.getFaction()))){
					return false;
				}
				else {
					list.add(unit);
					return true;}
			}
			

		}
		else {
			list.add(unit);
			return true;
		}
		return false;
	}


	public boolean removeUnit(Unit unit) {
		if(this.list.indexOf(unit)==-1) 
			return false;
		else
			this.list.remove(unit);
		return true;
	}
	
	
	/*public boolean removeUnit(Unit unit) {		
		if(unit==null) return false;
		
		int number = 0;
		
		for(int i=0; i<=this.list.size();i++) {
		 if(this.list.indexOf(unit)!=-1)
			number++;}
		if(number==0) return false;
		
		for(int k=0;k<number;k++) {
			this.list.remove(unit);}
		return true;	
	}*/
	

	
	private Unit findLowHp(Unit[] unit) {
		Unit tmp = unit[0];
		for(int i=0; i<unit.length; i++) {
			if (unit[i].getHP()<tmp.getHP())
				tmp = unit[i];}
		return tmp;

	}
	


	public Unit selectWeakEnemy(String faction) {
		int m =0;
		int n = 0;
		for(int i=0; i<list.size(); i++) {
			if(!(list.get(i).getFaction().equals(faction)))
				m++;
		}
		if(m==0) return null;
		
		Unit[] tmp = new Unit[m] ;
		for(int i=0; i<list.size(); i++) {
			if(!(list.get(i).getFaction().equals(faction)) ){
				tmp[n] = list.get(i);
				n++;}
		}
		return this.findLowHp(tmp);
	}


	public static double getDistance(Tile tile1, Tile tile2) {
		double x1 = tile1.x_coordinate;
		double y1 = tile1.y_coordinate;
		double x2 = tile2.x_coordinate;
		double y2 = tile2.y_coordinate;
		double answer;
		answer = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		return answer;
	}


}
