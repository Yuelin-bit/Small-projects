import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

public class Sorting {

	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) <0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {
    	// ADD YOUR CODE HERE
    	ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());
        //return Sorting.mergeSort(sortedUrls,results); 
        quickSort(sortedUrls, 0, sortedUrls.size() - 1, results);
        return sortedUrls;
    }
    
    private static <K, V extends Comparable> void quickSort(ArrayList<K> sorted, int leftIndex, int rightIndex, HashMap<K, V> results){
		if (leftIndex >= rightIndex) {
			return;
		} else {
			int index = (int) (Math.floor(Math.random()*(rightIndex-leftIndex+1)+leftIndex));// You should submitted many times
			K t = sorted.get(rightIndex);
			sorted.set(rightIndex, sorted.get(index));
			sorted.set(index, t);
			V pivot = results.get(sorted.get(rightIndex));
			
			int reference = leftIndex - 1;
			for (int i = leftIndex; i < rightIndex; i++){
				if (results.get(sorted.get(i)).compareTo(pivot)>0){
					reference++;
					if (!(i == reference)){
						K tmp = sorted.get(i);
						sorted.set(i, sorted.get(reference));
						sorted.set(reference, tmp);
					}
				}
			}
			if (!(reference+1 == rightIndex)){
				K tmp = sorted.get(reference + 1);
				sorted.set(reference + 1, sorted.get(rightIndex));
				sorted.set(rightIndex, tmp);
			}

			int t2 = reference + 1;
			quickSort(sorted, leftIndex, t2 - 1, results);
			quickSort(sorted, t2 + 1, rightIndex, results);
		}
	}


	private static <K,V extends Comparable> ArrayList<K> mergeSort(ArrayList<K> a, HashMap<K, V> results){
    	if(a.size()==1) {
    		return a;
    	}
    	else {
    		int mid = (a.size()-1)/2;
    		ArrayList<K> list1 = new ArrayList<K>();
    		ArrayList<K> list2 = new ArrayList<K>();
    		for(int i=0;i<=mid;i++) {
    			list1.add(a.get(i));
    		}
    		for(int i=mid+1;i<a.size();i++) {
    			list2.add(a.get(i));
    		}
    		list1 = Sorting.mergeSort(list1,results);
    		list2 = Sorting.mergeSort(list2,results);
    		return Sorting.merge(list1,list2,results);
    	}
    }


	private static <K,V extends Comparable> ArrayList<K> merge(ArrayList<K> list1, ArrayList<K> list2, HashMap<K, V> results) {
		ArrayList<K> result = new ArrayList<K>();
		while(!list1.isEmpty()&&!list2.isEmpty()) {
			if((results.get(list1.get(0))).compareTo(results.get(list2.get(0)))>0) {
				result.add(list1.remove(0));
			}
			else {
				result.add(list2.remove(0));
			}
		}
		while(!list1.isEmpty()) {
			result.add(list1.remove(0));
		}
		while(!list2.isEmpty()) {
			result.add(list2.remove(0));
		}
		return result;
	}
    

}

