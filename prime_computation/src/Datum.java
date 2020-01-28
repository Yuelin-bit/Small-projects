
import java.util.Arrays;

//This class holds the information of a single datapoint

public class Datum {

	int[] x; 
	Datum(int[] x){
		this.x = new int[x.length];
		for (int i = 0 ; i< x.length ; i++){
			this.x[i] = x[i];
		}
	}
	public String toString()
	{
		String str= "[";
		int l = this.x.length;
		for (int i = 0 ; i < l-1 ; i++)
		{
			str += this.x[i] + ",";
		}
		str += this.x[l-1] + "]";
		return str;
	}
	@Override
	public int hashCode() {
		return Arrays.hashCode(this.x);
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		Datum d = (Datum) o;
		return Arrays.equals(d.x, this.x);
	}
}

