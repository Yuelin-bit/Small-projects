//package assignments2019.a3posted;

import java.util.ArrayList;
import java.util.Iterator;

public class MiniTester {
	
	public static void main(String [] args) throws Exception{
		  int[] arr1 = {3,2};
		  int[] arr2 = {13,2};
		  int[] arr3 = {3,12};
		  int[] arr4 = {45,8};
		  int[] arr5 = {33,10};
		  int[] arr6 = {3,8};
		  int[] arr7 = {3,8};
		  int[] arr8 = {3,8};
		  int[] arr9 = {2,3};
		  int[] arr10 = {3,4};
		  int[] arr11 = {4,5};
		  int[] arr12 = {6,5};
		  int[] arr13 = {7,8};
		  int[] arr14 = {7,19};
		  int[] arr00 = {7,10};
		  
		  Datum a1 = new Datum(arr1);
		  Datum a2 = new Datum(arr2);
		  Datum a3 = new Datum(arr3);
		  Datum a4 = new Datum(arr4);
		  Datum a5 = new Datum(arr5);
		  Datum a6 = new Datum(arr6);
		  Datum a7 = new Datum(arr7);
		  Datum a8 = new Datum(arr8);
		  Datum a9 = new Datum(arr9);
		  Datum a10 = new Datum(arr10);
		  Datum a11 = new Datum(arr11);
		  Datum a12 = new Datum(arr12);
		  Datum a13 = new Datum(arr13);
		  Datum a14 = new Datum(arr14);
		  Datum a00 = new Datum(arr00);
		  
		//  Datum[] listOfPoints = {a1,a2,a3};
		  ArrayList<Datum> listOfPointsA = new ArrayList<Datum>();
		  listOfPointsA.add(a1);
		  listOfPointsA.add(a2);
		  listOfPointsA.add(a3);
		  listOfPointsA.add(a4);
		  listOfPointsA.add(a5);
		  listOfPointsA.add(a6);
		  listOfPointsA.add(a7);
		  listOfPointsA.add(a8);
		  listOfPointsA.add(a9);
		  listOfPointsA.add(a10);
		  listOfPointsA.add(a11);
		  listOfPointsA.add(a12);
		  listOfPointsA.add(a13);
		  listOfPointsA.add(a14);

		  KDTree mytree = new KDTree(listOfPointsA);
		  Iterator<Datum> a = mytree.iterator();
		  while(a.hasNext()) {
		   Datum d = a.next();
		   System.out.println(d);
		  }
		  System.out.println(mytree.numLeaves);
		  System.out.println(mytree.height());



	}
}
