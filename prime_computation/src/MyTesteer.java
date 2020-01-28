import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class MyTesteer {
	public static void main(String [] args) throws Exception{
		double grade = 0.00;
		int[] arr1_1 = {10};
		int[] arr2_1 = {-94};
		int[] arr3_1 = {-2};
		int[] arr4_1 = {-3};
		int[] arr5_1 = {-1};
		int[] arr6_1 = {-1};
		int[] arr7_1 = {1};
		int[] arr8_1 = {0};
		int[] arr9_1 = {-1};
		int[] arr10_1 = {8};
		int[] arr11_1 = {8};
		int[] arr12_1 = {8};
		int[] arr13_1 = {9};
		int[] arr14_1 = {-159};
		int[] arr00_1 = {5};
		int[] arr01_1 = {0};
		int[] arr02_1 = {-50};
		
		Datum a1_1 = new Datum(arr1_1);
		Datum a2_1 = new Datum(arr2_1);
		Datum a3_1 = new Datum(arr3_1);
		Datum a4_1 = new Datum(arr4_1);
		Datum a5_1 = new Datum(arr5_1);
		Datum a6_1 = new Datum(arr6_1);
		Datum a7_1 = new Datum(arr7_1);
		Datum a8_1 = new Datum(arr8_1);
		Datum a9_1 = new Datum(arr9_1);
		Datum a10_1 = new Datum(arr10_1);
		Datum a11_1 = new Datum(arr11_1);
		Datum a12_1 = new Datum(arr12_1);
		Datum a13_1 = new Datum(arr13_1);
		Datum a14_1 = new Datum(arr14_1);
		Datum a00_1 = new Datum(arr00_1);
		Datum a01_1 = new Datum(arr01_1);
		Datum a02_1 = new Datum(arr02_1);
		Datum[] listOfPointsD1 = {a1_1,a2_1,a3_1,a4_1,a5_1,a6_1,a7_1,a8_1,a9_1,a10_1,a11_1,a12_1,a13_1,a14_1};
		ArrayList<Datum> answerD1 = new ArrayList<Datum>();
		answerD1.add(a14_1);
		answerD1.add(a2_1);
		answerD1.add(a4_1);
		answerD1.add(a3_1);
		answerD1.add(a5_1);
		answerD1.add(a8_1);
		answerD1.add(a7_1);
		answerD1.add(a10_1);
		answerD1.add(a13_1);
		answerD1.add(a1_1);
		
		ArrayList<Datum> listOfPointsAD1 = new ArrayList<Datum>();
		listOfPointsAD1.add(a1_1);
		listOfPointsAD1.add(a2_1);
		listOfPointsAD1.add(a3_1);
		listOfPointsAD1.add(a4_1);
		listOfPointsAD1.add(a5_1);
		listOfPointsAD1.add(a6_1);
		listOfPointsAD1.add(a7_1);
		listOfPointsAD1.add(a8_1);
		listOfPointsAD1.add(a9_1);
		listOfPointsAD1.add(a10_1);
		listOfPointsAD1.add(a11_1);
		listOfPointsAD1.add(a12_1);
		listOfPointsAD1.add(a13_1);
		listOfPointsAD1.add(a14_1);
		boolean passConstrutorD1 = false;
		boolean passCreatingIteratorD1 = false;
		boolean passIteratorD1 = false;
		boolean passFindD1 = false;
		try {
			KDTree mytreeD1 = new KDTree(listOfPointsAD1);
			passConstrutorD1 = true;
			grade +=5;
		}catch(Exception e) {
			System.out.println("err in constructor");
		}
		try {
			KDTree mytreeD1 = new KDTree(listOfPointsAD1);
			Iterator<Datum> d1 = mytreeD1.iterator();
			passCreatingIteratorD1 = true;
			grade +=5;
		}catch(Exception e) {
			System.out.println("err in creating iterator ");
		}
		
		try {
			KDTree mytreeD1 = new KDTree(listOfPointsAD1);
			Iterator<Datum> d1 = mytreeD1.iterator();
			int i=0;
			passIteratorD1 = true;
			while(d1.hasNext()) {
				Datum s = d1.next();
				if(!(s.equals(answerD1.get(i)))){
					grade -= 0.5;
					passIteratorD1 = false;
				}
				i++;
			}
			//System.out.println("pass");
			passIteratorD1 = true;
			grade +=10;
		}catch(Exception e) {
			System.out.println("err in size");
		}
		try {
			KDTree mytreeD1 = new KDTree(listOfPointsAD1);
			Iterator<Datum> d1 = mytreeD1.iterator();
			//System.out.println(mytreeD1.numLeaves);
			//System.out.println(mytreeD1	.height());
			if(mytreeD1.nearestPoint(a00_1).equals(a10_1)) {
				if(mytreeD1.nearestPoint(a01_1).equals(a8_1)) {
					if(mytreeD1.nearestPoint(a02_1).equals(a2_1)){
						grade += 10;
						passFindD1 = true;
					}
				}
			}
			
			System.out.println("Your grade: " + grade+"/30.0");
		}catch(Exception e) {
			System.out.println("err in findNearestPoint");
		}
		
		
		
		int[] arr1_2 = {-2,-1};
		int[] arr2_2 = {-1,-1};
		int[] arr3_2 = {3,12};
		int[] arr4_2 = {45,8};
		int[] arr5_2 = {33,10};
		int[] arr6_2 = {3,8};
		int[] arr7_2 = {7,11};
		int[] arr8_2 = {1,2};
		int[] arr9_2 = {2,3};
		int[] arr10_2 = {3,4};
		int[] arr11_2 = {4,5};
		int[] arr12_2 = {6,5};
		int[] arr13_2 = {230,540};
		int[] arr14_2 = {240,540};
		int[] arr00_2 = {-1,-1};
		int[] arr01_2 = {6,6};
		int[] arr02_2 = {234,540};
		
		Datum a1_2 = new Datum(arr1_2);
		Datum a2_2 = new Datum(arr2_2);
		Datum a3_2 = new Datum(arr3_2);
		Datum a4_2 = new Datum(arr4_2);
		Datum a5_2 = new Datum(arr5_2);
		Datum a6_2 = new Datum(arr6_2);
		Datum a7_2 = new Datum(arr7_2);
		Datum a8_2 = new Datum(arr8_2);
		Datum a9_2 = new Datum(arr9_2);
		Datum a10_2 = new Datum(arr10_2);
		Datum a11_2 = new Datum(arr11_2);
		Datum a12_2 = new Datum(arr12_2);
		Datum a13_2 = new Datum(arr13_2);
		Datum a14_2 = new Datum(arr14_2);
		Datum a00_2 = new Datum(arr00_2);
		Datum a01_2 = new Datum(arr01_2);
		Datum a02_2 = new Datum(arr01_2);
		
		//Datum[] listOfPoints = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14};
		ArrayList<Datum> listOfPointsAD2 = new ArrayList<Datum>();
		listOfPointsAD2.add(a1_2);
		listOfPointsAD2.add(a2_2);
		listOfPointsAD2.add(a3_2);
		listOfPointsAD2.add(a4_2);
		listOfPointsAD2.add(a5_2);
		listOfPointsAD2.add(a6_2);
		listOfPointsAD2.add(a7_2);
		listOfPointsAD2.add(a8_2);
		listOfPointsAD2.add(a9_2);
		listOfPointsAD2.add(a10_2);
		listOfPointsAD2.add(a11_2);
		listOfPointsAD2.add(a12_2);
		listOfPointsAD2.add(a13_2);
		listOfPointsAD2.add(a14_2);
		listOfPointsAD2.add(a14_2);
		listOfPointsAD2.add(a14_2);
		listOfPointsAD2.add(a14_2);
		listOfPointsAD2.add(a14_2);
		listOfPointsAD2.add(a14_2);
		listOfPointsAD2.add(a14_2);
		listOfPointsAD2.add(a14_2);
		ArrayList<Datum> answerD2 = new ArrayList<Datum>();
		answerD2.add(a14_1);
		answerD2.add(a2_1);
		answerD2.add(a4_1);
		answerD2.add(a3_1);
		answerD2.add(a5_1);
		answerD2.add(a8_1);
		answerD2.add(a7_1);
		answerD2.add(a10_1);
		answerD2.add(a13_1);
		answerD2.add(a1_1);
		boolean passConstrutorD2 = false;
		boolean passCreatingIteratorD2 = false;
		boolean passNumLeaves = false;
		boolean passIteratorD2 = false;
		boolean passFindD2 = false;
		try {
			KDTree mytreeD2 = new KDTree(listOfPointsAD2);
			grade += 5;
			passConstrutorD2 = true;
		}catch(Exception e) {
			
		}
		try {
			KDTree mytreeD2 = new KDTree(listOfPointsAD2);
			Iterator<Datum> a = mytreeD2.iterator();
			grade += 5;
			passCreatingIteratorD2 = true;
		}catch(Exception e) {
			
		}
		try {
			KDTree mytreeD2 = new KDTree(listOfPointsAD2);
			Iterator<Datum> a = mytreeD2.iterator();
			if(mytreeD2.numLeaves == 14) {
				grade += 5;
				passNumLeaves = true;
			}
		}catch(Exception e) {
			
		}
		try {
			KDTree mytreeD2 = new KDTree(listOfPointsAD2);
			Iterator<Datum> a = mytreeD2.iterator();
			if(mytreeD2.nearestPoint(a00_2).equals(a2_2)) {
				if(mytreeD2.nearestPoint(a01_2).equals(a12_2)) {
					if(mytreeD2.nearestPoint(a02_2).equals(a12_2)){
						grade += 10;
						passFindD2 = true;
					}
				}
			}
		}catch(Exception e) {
			
		}
		ArrayList<Datum> listOfPointsAD2_2 = new ArrayList<Datum>();
		listOfPointsAD2_2.add(a14_2);
		listOfPointsAD2_2.add(a1_2);
		listOfPointsAD2_2.add(a14_2);
		listOfPointsAD2_2.add(a2_2);
		ArrayList<Datum> answerD2_2 = new ArrayList<Datum>();
		answerD2_2.add(a1_2);
		answerD2_2.add(a2_2);
		answerD2_2.add(a14_2);
		//System.out.println("Your grade: " + grade+"/55.0");
		try {
			KDTree mytreeD2_2 = new KDTree(listOfPointsAD2_2);
			Iterator<Datum> a = mytreeD2_2.iterator();
			int j=0;
			passIteratorD2 = true;
			while(a.hasNext()) {
				Datum d = a.next();
				if(!(d.equals(answerD2_2.get(j)))) {
					grade -= 0.5;
					passIteratorD2 = false;
				}
				j++;
			}
			grade += 10;
		}catch(Exception e) {
			
		}
		System.out.println("Your grade: " + grade+"/65.0");
		
	}
}
