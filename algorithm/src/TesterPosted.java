//package assignments2019.a2;
import java.math.BigInteger;
import java.util.ArrayList;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class TesterPosted {

	public static void gradeSchoolTester(int base, String s1, String s2, String remarks) throws Exception {
		/*
		 *  We can test the correctness of the arithmetic operations in MyBigInteger class
		 *  by using Java's BigInteger class.
		 */

		BigInteger big1 = new BigInteger(s1, base);
		System.out.println("big1: " + "(" + big1 + ")_"+ base + "     (BigInteger)");
		BigInteger big2 = new BigInteger(s2, base);
		System.out.println("big2: " + "(" + big2 + ")_"+ base + "      (BigInteger)");
		System.out.println();

		MyBigInteger n1 = new MyBigInteger(s1, base);
		System.out.println("n1: " + n1 + "     (MyBigInteger)");
		MyBigInteger n2 = new MyBigInteger(s2, base);
		System.out.println("n2: " + n2 + "      (MyBigInteger)");
		System.out.println();

		String quo,rem;
		String quo_mybig, rem_mybig;

		// divide
		quo = big1.divide(big2).toString(base);
		quo = "(" +quo+ ")_" + base;
		System.out.print("divide: big1/big2     = ");              // BigInteger
		System.out.println(quo);

		long s_time = System.currentTimeMillis();
		System.out.print("divide: n1/n2         = ");                 // MyBigInteger
		try {
			quo_mybig = n1.dividedBy(n2).toString();
			System.out.println(quo_mybig);
			System.out.println("Time Required (Divide): "+(System.currentTimeMillis()-s_time)+" ms");
			System.out.println(remarks);
			if (quo.contentEquals(quo_mybig))
				System.out.println("Test passed.");
			else
				System.out.println("Test failed.");
		} catch (Exception e) {
			System.out.println();
			StringWriter s = new StringWriter();
			e.printStackTrace(new PrintWriter(s));
			System.out.println(s.toString());
		}
		System.out.println();

		// mod
		System.out.print("big1 mod big2 = ");
		rem = big1.mod(big2).toString(base);
		rem = "(" + rem + ")_" + base;
		System.out.println(rem);
		System.out.print("n1 mod n2 = ");
		try {
			rem_mybig = n1.mod(n2).toString();
			System.out.println(rem_mybig);
			if (rem.contentEquals(rem_mybig))
				System.out.println("Test passed.");
			else
				System.out.println("Test failed.");

		} catch (Exception e) {
			System.out.println();
			StringWriter s = new StringWriter();
			e.printStackTrace(new PrintWriter(s));
			System.out.println(s.toString());
		}
	}


	public static void convertTester(MyBigInteger number, int newBase, MyBigInteger expected, String remarks) {
		System.out.println("Convert " + number.toString() + " to base " + newBase);
		System.out.println("Expected: " + expected.toString());
		try {
			long s_time = System.currentTimeMillis();
			MyBigInteger obtained = number.convert(newBase);
			long e_time = System.currentTimeMillis();

			System.out.println("Returned: " + obtained.toString());
			System.out.println("Time Required (Convert): " + (e_time-s_time) + " ms");
			System.out.println(remarks);
			if (expected.getBase() == obtained.getBase() && expected.getCoefficients().equals(obtained.getCoefficients()))
				System.out.println("Test passed.");
			else
				System.out.println("Test failed.");
		} catch (Exception e) {
			StringWriter s = new StringWriter();
			e.printStackTrace(new PrintWriter(s));
			System.out.println(s.toString());
		}
	}
	public static void factorizationTester(MyBigInteger n1,  ArrayList<MyBigInteger> answer_expected, String notes) throws Exception{

		try{
			long s_time = System.currentTimeMillis();
			String ans_obtained;
			ans_obtained = Arrays.toString(n1.primeFactors().toArray());
			System.out.println("Testing primeFactors() method on : "+ n1.toString() + "\n");
			System.out.println("Expected answer : " +  Arrays.toString(answer_expected.toArray()));
			System.out.println("Answer obtained  : " + ans_obtained);
			System.out.println("Time Required (Prime Factorize): "+(System.currentTimeMillis()-s_time)+" ms");
			System.out.println(notes);
			if (Arrays.toString(answer_expected.toArray()).contentEquals(ans_obtained))
				System.out.println("Test passed.\n");
			else
				System.out.println("Test failed.\n");


		}catch (Exception e){
			System.out.println();
			StringWriter s = new StringWriter();
			e.printStackTrace(new PrintWriter(s));
			System.out.println(s.toString());
			System.out.println("Test failed.\n");
		}
		finally {
			System.out.println("#######################################################\n");
		}

	}
	public static void main(String[] args) throws Exception {
						
		/* *****************************************************
		 *Testing for dividedBy()
		 *  We can test the correctness of the dividedBy() method in MyBigInteger class 
		 *  by comapring the output to that of Java's BigInteger class.
		 *******************************************************
		 */
		/*
		 * Grade school algorithms - base 10
		 */
		String remarks = "Remarks: An efficent implementation finds a solution within 1 ms. ";
		gradeSchoolTester(10, "3956", "27", remarks);
		System.out.println("\n############################################\n");


		/*
		 * Grade school algorithms - base 7
		 * The majority of the marks for divide will be based on integers
		 * of this scale. (Note: The grader will timeout after 10000 ms
		 * of computation)
		 */
		remarks = "Remarks : An efficent implementation finds a solution within a few ms.";
		//		+ " \n\t\t The majority of the marks for divide will be based on integers \n"
		//		+ "\t\t of this scale. (The grader will timeout after 10000 ms of computation).";
		gradeSchoolTester(7, "624261025332633", "3245", remarks);
		System.out.println("\n############################################\n");


		/*
		 * Grade school algorithms - base 4
		 * The few remaining marks for divide will be based on numbers
		 * of this scale. (Note: The grader will timeout after 10000 ms
		 * of computation)
		 */
		remarks = "Remarks: An efficent implementation finds a solution within a few hundred ms.";
//				+ "\t\t Note that the few remaining marks for divide will be based on integers \n"
//				+ "\t\t of this scale. (The grader will timeout after 10,000 ms.)";
		gradeSchoolTester(4, "123203120302301230120321030123012301230123"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301"
						+ "12332131230123012031203120301203120301203012030120301",
				"1231321021023012301203120301203012301203012301200202010",
				remarks);
		System.out.println("\n############################################\n");

		MyBigInteger n1;
		String s1;
		int base;


		//*********************************************************
		//   Here is a test for converting from one base to another.
		//**********************************************************

		/*
		 * Convert from base 10
		 */

		MyBigInteger number, expected;
		int newBase;


		/*
		 * Convert from base 10
		 */
		remarks = "Remarks : An efficent implementation finds a solution within 1 ms. ";
		System.out.println("Testing convert method");
		number = new MyBigInteger("513", 10);
		expected = new MyBigInteger("1000000001", newBase = 2);
		convertTester(number, newBase, expected, remarks);
		System.out.println("\n############################################\n");


		/*
		 * Convert from base 5
		 * The majority of the marks for convert will be based on integers
		 * of this scale. (Note: The grader will timeout after 10000 ms
		 * of computation)
		 */
		remarks = "Remarks : An efficent implementation finds a solution within one ms.";
//				+ "\n\t\t Note that the majority of the marks for convert will be based on integers \n"
//				+ "\t\t of this scale. (The grader will timeout after 10000 ms of computation).";
		System.out.println("Testing convert method");
		number = new MyBigInteger("412130", 5);
		expected = new MyBigInteger("200101212", newBase = 3);
		convertTester(number, newBase, expected, remarks);
		System.out.println("\n############################################\n");

		System.out.println("Testing convert method");
		number = new MyBigInteger("412130", 5);
		expected = new MyBigInteger("54053", newBase = 7);
		convertTester(number, newBase, expected, remarks);
		System.out.println("\n############################################\n");


		/*
		 * Convert from base 5
		 * The few remaining marks for convert will be based on integers
		 * of this scale. (Note: The grader will timeout after 10000 ms
		 * of computation)
		 */
		remarks = "Remarks:  An efficent implementation finds a solution within approximately 100 ms. ";
			//	+ "\n\t\t Note that the few remaining marks for convert will be based on integers \n"
			//	+ "\t\t of this scale (the grader will timeout after 10000 ms of computation).";
		System.out.println("Testing convert method");
		number = new MyBigInteger("11234123401234012301423031243130420212343"
				+ "440230412341203341201341203", 5);
		expected = new MyBigInteger("111221221121111210001021100202100102201"
				+ "210100020201212101012200212020202121222022120122221002112112", newBase = 3);
		convertTester(number, newBase, expected, remarks);
		System.out.println("\n############################################\n");

		System.out.println("Testing convert method");
		number = new MyBigInteger("11234123401234012301423031243130420212343"
				+ "440230412341203341201341203", 5);
		expected = new MyBigInteger("264000010022003160046322104222120221566"
				+ "31626524400530340", newBase = 7);
		convertTester(number, newBase, expected, remarks);
		System.out.println("\n############################################\n");

		//*****************************************************************
		//   Testing for primeFactors()
		//*****************************************************************
		MyBigInteger m;

		//test case 1
		base = 10;
		ArrayList expected_factors;

		//For a rough reference, tests like these should take about 1ms to execute.
		//(Note: The grader will timeout after 10000 ms of computation)
		m = new MyBigInteger("5000000000", base);
		remarks = "Remarks : An efficent implementation finds a solution within a few ms.";
		expected_factors = new ArrayList();
		for (int i = 0 ; i < 9 ; i++)
			Collections.addAll(expected_factors, new MyBigInteger("2",10));

		for (int i = 0 ; i < 10 ; i++)
			Collections.addAll(expected_factors, new MyBigInteger("5",10));

		factorizationTester(m, expected_factors, remarks);

		//test case 2
		//For a rough reference, tests like these should take about 5ms to execute
		//Stress tests will include numbers upto roughly 100 times bigger than the numbers provided here
		//(Note: The grader will timeout after 10000 ms of computation)

		base = 5;
		m = new MyBigInteger( "13143241", base);
		expected_factors = new ArrayList();
		remarks = "Remarks : An efficent implementation finds a solution within about 10 ms.";
		//  +  "\n\t\tStress tests will include numbers up to roughly 100 times bigger than the numbers provided here." ;
		Collections.addAll(expected_factors, new MyBigInteger("13143241", base));

		factorizationTester(m, expected_factors, remarks);


		///test 3
		//For a rough reference, tests like these should take about 2ms to execute.
		//(Note: The grader will timeout after 10000 ms of computation)

		//*******************************The test case with the longest execution time
		// used in the grader takes about 700ms so even if your implementation do not
		// match the execution times posted here, you have
		//a margin of being about 10 times slower and still passing all the tests.***********************

		base = 6;
		m = new MyBigInteger("1114510444205521", base);
		expected_factors = new ArrayList();
		remarks = "Remarks : An efficent implementation finds a solution within a few ms.";
		/*
			+	"\n\t\tThe test case with the longest execution time used in the grader takes \n" +
				"\t\tabout 700ms so even if your implementation do not\n" +
				"\t\tmatch the execution times posted here, you have\n" +
				"\t\ta margin of being about 10 times slower and still passing all the tests.";
        */
		Collections.addAll(expected_factors, new MyBigInteger("5", base));

		Collections.addAll(expected_factors, new MyBigInteger("11", base));
		Collections.addAll(expected_factors, new MyBigInteger("11", base));
		Collections.addAll(expected_factors, new MyBigInteger("11", base));
		Collections.addAll(expected_factors, new MyBigInteger("11", base));

		Collections.addAll(expected_factors, new MyBigInteger("15", base));

		Collections.addAll(expected_factors, new MyBigInteger("35", base));
		Collections.addAll(expected_factors, new MyBigInteger("35", base));

		Collections.addAll(expected_factors, new MyBigInteger("101531", base));
		factorizationTester(m, expected_factors, remarks);

	}
}
