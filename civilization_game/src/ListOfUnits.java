public class ListOfUnits {
	private Unit[] units;
	private int size;
	
	
	public ListOfUnits() {
		this.units = new Unit[10];
		this.size = units.length - 10;
	}
	
	
	public int size() {
		return this.size;
	}
	
	
	public Unit[] getUnits() {
		Unit[] newunit = new Unit[size];
		for(int i=0;i<size;i++) {
			newunit[i] = this.units[i];}
		return newunit;
	}
	
	
	public Unit get(int i) {
		if((i>=0)&&(i<size))
			return units[i];
		else
		throw new IndexOutOfBoundsException("IndexOutOfBoundsException.");
	}
	
	
	public void add(Unit unit) {
		if(unit == null) return;
		if (size >= units.length) {
		Unit[] b = new Unit[units.length+(units.length/2)+1];
		for(int k=0; k<size; k++) 
			b[k] = units[k];
		units = b;
		}
		units[size] = unit;
		size = size + 1;
	}
	
	
	
	public int indexOf(Unit unit) {
        if (unit == null) {
            for (int i = 0; i < size; i++)
                if (units[i]==null)
                    return i;
        } 
        else {
            for (int i = 0; i < size; i++)
                if (unit.equals(units[i]))
                    return i;
        }
        return -1;
    }
	
	
	public boolean remove(Unit unit) {
		
		 for (int i = 0; i < size; i++) {
            if (unit.equals(units[i])) {
            	for(int k=i; k<size-1; k++) {
   				units[k] = units[k+1];}
   				size = size - 1;	
   				return true;
            }}
             	return false;   		
	}
	
	
	
	
	public MilitaryUnit[] getArmy() {
		int m = 0;
		int n = 0;
		for(int k=0; k<size; k++) {
			if((units[k] instanceof MilitaryUnit))
				m++;
		}
		MilitaryUnit[] a = new MilitaryUnit[m];
		for(int i=0; i<size; i++) {
			if((units[i] instanceof MilitaryUnit)) {
				a[n] = (MilitaryUnit) units[i];
				n++;
				}
			}
		return a;	
	}
	
	
	
	
}





