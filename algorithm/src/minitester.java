
public class minitester {
	public static void main(String[] args) throws Exception {
		MyBigInteger a = new MyBigInteger("3",10);
		MyBigInteger b = new MyBigInteger(0,10);
		long s_time = System.currentTimeMillis();
		System.out.println(a.primeFactors());
		long s2_time = System.currentTimeMillis();
		System.out.println(s2_time-s_time);
	}
}
