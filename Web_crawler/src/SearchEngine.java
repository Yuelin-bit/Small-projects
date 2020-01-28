import java.util.HashMap;
import java.util.ArrayList;

public class SearchEngine {
	public HashMap<String, ArrayList<String> > wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception{
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}
	
	/* 
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */
	public void crawlAndIndex(String url) throws Exception {
		// TODO : Add code here
		//if(internet.getVisited(url)==true) return;
		ArrayList<String> current;
		ArrayList<String> current2;
		internet.addVertex(url);
		internet.setVisited(url, true);
		current = parser.getLinks(url);
		current2 = parser.getContent(url);
		for(int i=0; i<current2.size();i++) {
			if(wordIndex.containsKey((current2.get(i).toLowerCase()))) {
				if(!(wordIndex.get(current2.get(i).toLowerCase()).contains(url))) {
					wordIndex.get(current2.get(i).toLowerCase()).add(url);
				}
			}else {
				ArrayList<String> t = new ArrayList<String>();
				t.add(url);
				wordIndex.put(current2.get(i).toLowerCase(), t);
			}		
		}
		for(int i=0; i<current.size(); i++) {
			// if
			internet.addVertex(current.get(i));
			internet.addEdge(url, current.get(i));
			//internet.setVisited(current.get(i), true);
			if(!internet.getVisited(current.get(i)))
				crawlAndIndex(current.get(i));
		}
		
	}
	
	
	
	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */
	public void assignPageRanks(double epsilon) {
		ArrayList<String> all = internet.getVertices();
		for(int i=0; i<all.size(); i++) {
			internet.setPageRank(all.get(i), 1.0);
		}
		ArrayList<Double> previous = new ArrayList<Double> ();
		for(int i=0; i<all.size(); i++) {
			previous.add(internet.getPageRank(all.get(i)));
		}
		ArrayList<Double> temp = computeRanks(internet.getVertices());
		for(int i=0; i<all.size(); i++) {
			internet.setPageRank(all.get(i), temp.get(i));
		}
		while(checkConvergence(previous, temp, epsilon)==false) {
			previous = temp;
			temp = computeRanks(internet.getVertices());
			for(int i=0; i<all.size(); i++) {
				internet.setPageRank(all.get(i), temp.get(i));
			}
		}
		
		// TODO : Add code here
	}
	private boolean checkConvergence(ArrayList<Double> previous, ArrayList<Double> now, double epsilon) {
		for(int i=0; i<previous.size(); i++) {
			if (Math.pow(previous.get(i)-now.get(i), 2) > Math.pow(epsilon,2)) {
				return false;
			}
		}
		return true;
	}
	

	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph 
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls. 
	 * Note that the double in the output list is matched to the url in the input list using 
	 * their position in the list.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		// TODO : Add code here
		ArrayList<Double> result = new ArrayList<Double>();
		for(int i=0;i<vertices.size();i++) {
			Double current = 0.5;
			ArrayList<String> a = internet.getEdgesInto(vertices.get(i));
			for(int j=0;j<a.size();j++) {
				current += 0.5*(internet.getPageRank(a.get(j)) / internet.getOutDegree(a.get(j)));
			}
			result.add(current);
			//System.out.print(current+", ");
		}
		//System.out.println();
		return result;
	}

	
	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 * 
	 * This method should take about 25 lines of code.
	 */
	public ArrayList<String> getResults(String query) {
		// TODO: Add code here
		ArrayList<String> a = wordIndex.get(query);
		HashMap<String, Double> b = new HashMap<String, Double>();
		for(int i=0;i<a.size();i++) {
			b.put(a.get(i),internet.getPageRank(a.get(i)));
		}
		return Sorting.fastSort(b);
	}
}
