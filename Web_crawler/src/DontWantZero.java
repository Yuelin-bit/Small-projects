import java.util.HashMap;
import java.util.Random;

public class DontWantZero {

	public static void main(String[] args) {
		try {
			SearchEngine searchEngine = new SearchEngine("test.xml");
			searchEngine.crawlAndIndex("www.ubisoft.com");
			searchEngine.assignPageRanks(0.0001);
			searchEngine.getResults("is");
			
			Random rand = new Random(0);
			HashMap<Long, Double> map = new HashMap<>();
			
			for (int j = 0; j < 500000; j++)
				map.put(Long.valueOf(j),  Double.valueOf(rand.nextDouble() * 1000));
			
			Sorting.fastSort(map);
	            
		}
		catch (StackOverflowError e) {
			System.out.println("Sorry - The grdaer may give a zero!");
			return;
		}
		catch (OutOfMemoryError e) {
			System.out.println("Sorry - The grdaer may give a zero!");
			return;
		}
		catch (Exception e) {
			System.out.println("Congratualations - You will probably get a non-zero grade!");
			return;
		}
		
		System.out.println("Congratualations - You will probably get a non-zero grade!");
	}
}
