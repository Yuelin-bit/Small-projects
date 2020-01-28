import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class YuelinTester {
	public static void main(String[] args) throws Exception {
		SearchEngine searchEngine = new SearchEngine("yuelin.xml");
		searchEngine.crawlAndIndex("www.yuelin.com");
		searchEngine.assignPageRanks(0.000000000000000000001);
		HashMap<String, ArrayList<String> > a = (HashMap<String, ArrayList<String>>) searchEngine.wordIndex.clone();
		System.out.println("The answer should be     www.yuelin.com:   1.3076923076923077  \n" + 
				"                         www.cf.org:   0.717948717948718  \n" + 
				"                         www.mycourses.mcgill.ca:   1.2564102564102564  \n" + 
				"                         www.alibaba.com:   0.717948717948718  ");
		System.out.println("");
		System.out.println("Yours:");
		Iterator i0 = searchEngine.internet.getVertices().iterator();
		while(i0.hasNext()) {
			String nameURL = (String) i0.next();
			System.out.println(nameURL+":   "+searchEngine.internet.getPageRank(nameURL) + "  ");
		}
		System.out.println();System.out.println("");
		System.out.println("==================================");
		System.out.println("==================================");

		
		System.out.println("The answer should be     www.yuelin.com:   1.28125  \n" + 
				"                         www.cf.org:   0.7291666666666666  \n" + 
				"                         www.mycourses.mcgill.ca:   1.2604166666666665  \n" + 
				"                         www.alibaba.com:   0.7291666666666666  ");
		System.out.println("");
		System.out.println("Yours:");
		searchEngine.assignPageRanks(0.1);
		Iterator i1 = searchEngine.internet.getVertices().iterator();
		while(i1.hasNext()) {
			String nameURL = (String) i1.next();
			System.out.println(nameURL+":   "+searchEngine.internet.getPageRank(nameURL) + "  ");
		}
		
		System.out.println("");System.out.println("");
		System.out.println("==================================");
		System.out.println("==================================");System.out.println("");
		Iterator i = a.keySet().iterator();
		while(i.hasNext()) {
			String query = (String) i.next();
			System.out.println("query: " + query);
			ArrayList<String> answer = new ArrayList<String>(); 
			System.out.println(searchEngine.getResults(query));
			if(!(query.equals("was"))) {
				System.out.println(a.get(query).equals(searchEngine.getResults(query)));
			}else {
				System.out.println(!(a.get(query).equals(searchEngine.getResults(query))));
				answer.add("www.yuelin.com");
				answer.add("www.mycourses.mcgill.ca");
				answer.add("www.alibaba.com");
				answer.add("www.cf.org");
			}
			if (query.equals("was") && !(answer.equals(searchEngine.getResults(query)))) {
				System.out.println(answer);
				System.out.println(searchEngine.getResults(query));
				System.out.println("You are wrong!!!! The anwser should be [www.yuelin.com, www.mycourses.mcgill.ca, www.cf.org, www.alibaba.com]");
			}
			System.out.println("==================================");
			System.out.println();
		}
		
		HashMap<String, Double> testSorting = new HashMap<String, Double>();
		testSorting.put("i", -3.6);
		testSorting.put("l", 9.6);
		testSorting.put("e", 15.6);
		testSorting.put("Y", 15.8);
		testSorting.put("u", 15.7);
		testSorting.put("n", -3.7);
		ArrayList<String> result = Sorting.fastSort(testSorting);
		System.out.println("The following answer should be [Y, u, e, l, i, n]");
		System.out.println(result);
		if(result.size()==6 && result.get(0).equals("Y") && result.get(1).equals("u") && result.get(2).equals("e") && result.get(3).equals("l") && result.get(4).equals("i") && result.get(5).equals("n")) {
			System.out.println("You pass the Searching!");
		}else {
			System.out.println("You failed in the Searching test!");
		}
		
	}
	
}
