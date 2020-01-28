import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Grader {
	int scale = 2;

	private String gradeString(int score, int maxScore, String comment)
	{
		return comment + "Score: " + Integer.toString(score * scale) + "/" + Integer.toString(maxScore * scale);
	}

	private void write(String string)
	{
		System.out.print(string + "\n");

	}

	private int testNumVertices(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test the number of vertices added during crawling.\n";
		int maxScore = 10;
		int grade = 0;
		try {
			SearchEngine searchEngine = new SearchEngine("testAcyclic.xml");
			searchEngine.crawlAndIndex("siteA");

			if (searchEngine.internet.vertexList.size() == 4)
				grade = 10;
			else {
				comment = comment + "Error: Number of vertices added during crawling is incorrect.\n";
				grade = 0;
			}

			write(gradeString(grade, maxScore, comment));
		}
		catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int testVisited(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test if vertices are marked visited during crawling.\n";
		int maxScore = 10;
		int grade = 0;

		try {
			SearchEngine searchEngine = new SearchEngine("testAcyclic.xml");
			searchEngine.crawlAndIndex("siteA");

			Iterator it = searchEngine.internet.vertexList.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        grade = maxScore;
		        if (!searchEngine.internet.getVisited((String) pair.getKey())) {
		        	comment = comment + "Error: Vertices are not marked visited during crawling.\n";
		        	grade = 0;
		        	break;
		        }
		    }

			write(gradeString(grade, maxScore, comment));
		}
		catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}

		return grade;
	}

	private int testNumEdges(int testIdx)	//Test num of edges added during crawl and index. Uses testAcyclic.xml
	{
		String comment = "[" + testIdx + "]: Test the number of edges added during crawling.\n";
		int maxScore = 5;
		int grade = 0;
		try {
			SearchEngine searchEngine = new SearchEngine("testAcyclic.xml");
			searchEngine.crawlAndIndex("siteA");
			ArrayList<String> v = searchEngine.internet.getVertices();
			int numEdges = 0;
			for(String url: v){
				int outDeg = searchEngine.internet.getOutDegree(url);
				numEdges+=outDeg;
			}
			if(numEdges == 4)
				grade=5;
			else {
				comment = comment + "Error: Number of edges added during crawling is incorrect.\n";
				grade = 0;
			}
			write(gradeString(grade, maxScore, comment));
		}
		catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}
		return grade;
	}

	private int testAssignRanks(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test whether AssignPageRanks produces plausible output. Passing this test does not gurantee your output is correct.\n";
		int maxScore = 2;
		
		try {
			boolean result = true;
			SearchEngine searchEngine = new SearchEngine("testAcyclic.xml");
			searchEngine.crawlAndIndex("siteA");

			ArrayList<String> vertices = searchEngine.internet.getVertices();
			ArrayList<Double> rankAfterOneIteration = searchEngine.computeRanks(vertices);

			// set initial values for the ranks
			for (String v : vertices)
				searchEngine.internet.setPageRank(v, 1.0);

			for (int i = 0; i < vertices.size(); i++)  {
				if (Double.isInfinite(rankAfterOneIteration.get(i))) {
					comment = comment + " Page rank for vertex " + vertices.get(i) + " is infinite. Check for divide by zero errors." + "\n";
					result = false;
				}
			}
			
			for (int i = 0; i < vertices.size(); i++) {
				if (Double.isNaN(rankAfterOneIteration.get(i))) {
					comment = comment + " Page rank for vertex " + vertices.get(i) + " is NaN. Check for divide by zero errors." + "\n";
					result = false;
				}
			}
			
			searchEngine.assignPageRanks(1000);
			for (String v : vertices) {
				if (searchEngine.internet.getPageRank(v) <= 0) {
					comment = comment + " Page rank for vertex " + v + " is negetive. Page rank should be positive at each iteration." + "\n";
					result = false;
				}
			}
			
			if (!result) {
				write(gradeString(0, maxScore, comment));
				return 0;
			}

			write(gradeString(maxScore, maxScore, comment));
		}
		catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
			return 0;
		}

		return maxScore;
	}

	private int testComputeRanks(int testIdx)
	{
		String comment = "[" + testIdx + "]: Test whether a single iteration of linear solver produces expected output.\n";
		int maxScore = 8;
		
		try {
			boolean result = true;
			HashMap<String, Double> expectedResults = new HashMap<String, Double>();
			expectedResults.put("siteA", 0.5);
			expectedResults.put("siteC", 0.75);
			expectedResults.put("siteD", 0.75);
			expectedResults.put("siteE", 1.5);

			SearchEngine searchEngine = new SearchEngine("testAcyclic.xml");
			searchEngine.crawlAndIndex("siteA");

			ArrayList<String> vertices = searchEngine.internet.getVertices();

			// set initial values for the ranks
			for (String v : vertices)
				searchEngine.internet.setPageRank(v, 1.0);

			ArrayList<Double> rankAfterOneIteration = searchEngine.computeRanks(vertices);
			for (int i = 0; i < vertices.size(); i++) {
				String vertex = vertices.get(i);
				Double rank = rankAfterOneIteration.get(i);
				Double expectedRank = expectedResults.get(vertex);

				if (Math.abs(expectedRank - rank) > 1e-5) {
					comment = comment + " Expected Page rank for " + vertex + " is " + expectedRank + ", evaluated rank is " + rank + "\n";
					result = false;
				}
			}

			if (!result) {
				write(gradeString(0, maxScore, comment));
				return 0;
			}

			write(gradeString(maxScore, maxScore, comment));
		}
		catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
			return 0;
		}

		return maxScore;
	}

	private int testGetResults0(int testIdx)		//Test the final result. Uses testAcyclic.xml
	{
		String comment = "[" + testIdx + "]: Test whether the search result are correct.\n";
		int maxScore = 5;
		int grade = 0;
		try {
			SearchEngine searchEngine = new SearchEngine("testAcyclic.xml");
			searchEngine.crawlAndIndex("siteA");

			searchEngine.assignPageRanks(0.01);
			ArrayList<String> results = searchEngine.getResults("8258010535");	//try a word in the graph

			if(results != null && results.get(0).equals("siteE")==true){	//Make sure the most relevent url is correct
				grade=5;
			}
			else{
				comment = comment + "Error: Did not return correct url.\n";
	        	grade = 0;
			}
			write(gradeString(grade, maxScore, comment));
		}
		catch (Exception e) {
			comment = comment + "Exception Found: " + e.toString() + "\n";
			e.printStackTrace();
			write(gradeString(0, maxScore, comment));
		}
		return grade;
	}

	private int testSorting(int testIdx) {
        String comment = "[" + testIdx + "]: Test sorting method.\n";
        int maxScore = 10;

        try {
            ArrayList<Integer> results;
            ArrayList<Integer> actualResults = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();

            // check if fastSort was implemented
            for (int j = 0; j < 100; j++) {
                map.put(j, j);
            }
            results = Sorting.fastSort(map);
            if (results == null) {
                write(gradeString(0, maxScore, "Did not implement fastSort method.\n"));
                return 0;
            }

            // test output
            for (int j = 0; j < 100; j++) {
                map.put(j, j);
                actualResults.add(100-j-1);
            }
            results = Sorting.fastSort(map);
            if (!actualResults.equals(results)) {
                write(gradeString(0, maxScore, "fastSort method did not successfully sort in decreasing order.\n"));
                return 0;
            }
           
            boolean tooSlow = false;
            // test complexity
            for (int i = 1000; i <= 100000; i *= 10) {
                System.out.println("Testing fastSort method with " + i + " elements...");
                map.clear();
                for (int j = 0; j <= i; j++) {
                    int val = j + (int)(Math.random() * (i-j+1));
                    map.put(val, val);
                }

                long startTime = System.nanoTime();
                Sorting.slowSort(map);
                long endTime = System.nanoTime();
                long durationSlow = (endTime - startTime);
//                long durationSlow = TimeUnit.NANOSECONDS.toMicros(endTime-startTime);
                System.out.println("time duration for slowSort: " + durationSlow + " ns");
                                
                map.clear();
                for (int j = 0; j <= i; j++) {
                    int val = j + (int)(Math.random() * (i-j+1));
                    map.put(val, val);
                }
                startTime = System.nanoTime();
                Sorting.fastSort(map);
                endTime = System.nanoTime();
                long durationFast = (endTime - startTime);
//                long durationFast = TimeUnit.NANOSECONDS.toMicros(endTime-startTime);
                System.out.println("time duration for fastSort: " + durationFast + " ns");

                if ((durationSlow/durationFast) >= 10) {
                    System.out.println("Implementation for fastSort fast enough for test case sample of " + i + " elements. \n");
                }
                else {
                    System.out.println("Implementation for fastSort not fast enough for test case sample of " + i + " elements. \n");
                    tooSlow = true;
                    break;
                }
            }

            if (tooSlow) {
				write(gradeString(0, maxScore, comment));
				return 0;
			}

			write(gradeString(maxScore, maxScore, comment));
        }
        catch (Exception e) {
            comment = comment + "Exception Found: " + e.toString() + "\n";
            e.printStackTrace();
            write(gradeString(0, maxScore, comment));
            return 0;
        }

        return maxScore;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grader g = new Grader();
		int total = 0;
		total += g.testNumVertices(0);
		total += g.testVisited(1);
		total += g.testNumEdges(2);
		total += g.testAssignRanks(3);
		total += g.testComputeRanks(4);
		total += g.testGetResults0(5);
		total += g.testSorting(6);
		g.write(g.gradeString(total, 50, ""));

	}
}
