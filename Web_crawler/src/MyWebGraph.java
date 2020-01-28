import java.util.ArrayList;
import java.util.HashMap;

public class MyWebGraph {
	HashMap<String, WebVertex> vertexList; 
	
	public MyWebGraph () {
		vertexList = new HashMap<String, WebVertex>();
	}
	
	
	/*
	 * adds a vertex given a url
	 * returns true if the graph has changed as a result of this operation
	 * false otherwise. 
	 */
	public boolean addVertex(String s) {
		// add a vertex to the graph if it's not there yet
		if (! vertexList.containsKey(s)) {
			WebVertex v = new WebVertex(s);
			vertexList.put(s, v);
			return true;
		}
		return false;	
	}
	

	/*
	 * add an edge between two vertices.
	 * returns true if the graph has changed as a result of this operation
	 * false otherwise. 
	 */
	public boolean addEdge(String s, String t) {
		if (vertexList.containsKey(s) && vertexList.containsKey(t)) {
			WebVertex v = vertexList.get(s);
			return v.addEdge(t);
		}
		return false;
	}
	
	
	// Returns an ArrayList of urls that are neighbors with the given url
    public ArrayList<String> getNeighbors(String url) {
        return vertexList.get(url).getNeighbors();
    } 
    
    
    // Returns a list of all urls in the graph
    public ArrayList<String> getVertices() {
        ArrayList<String> urls = new ArrayList<String>();
        for (String url: vertexList.keySet()) 
        	urls.add(url);
        return urls;
    } 
    
    // Returns the list of pages that have links to v
    public ArrayList<String> getEdgesInto(String v) {
        ArrayList<String> linksInto = new ArrayList<String>();
        for (String url: vertexList.keySet()) {
        	WebVertex page = vertexList.get(url);
        	if (page.containsEdge(v))
        		linksInto.add(page.url);
        }
        return linksInto;
    } 
    
    // Returns the number of links in the page with the specified url
    int getOutDegree(String url) {
    	// NullPointerException raised if there's no vertex with specified url
        return vertexList.get(url).links.size();
    }        
    
    // returns the page rank of a given url. If the vertex with the specified url doesn't exist, returns 0
    double getPageRank(String url) {
        if (vertexList.containsKey(url)) 
        	return (vertexList.get(url)).rank;
        
        return 0;
    }


    // sets the pageRank of a given url
    void setPageRank(String url, double pr) {
        vertexList.get(url).rank = pr;
    }


    // returns the visited status of a given url
    boolean getVisited(String url) {
        if (vertexList.containsKey(url)) 
        	return (vertexList.get(url)).visited;
        
        return false;
    }

    
    // sets the visited status of a given url
    boolean setVisited(String url, boolean b) {
        if (vertexList.containsKey(url)) {
        	(vertexList.get(url)).visited = b;
        	return true;
        }
        return false;
    }

    
    public String toString() {
    	String info = "";
        for (String s: vertexList.keySet()) {
        	info += s.toString() + "\n";
        }
        return info;
    }
	
	class WebVertex {
		private String url;
		private ArrayList<String> links;
		private boolean visited;
		private double rank;
		
		
		/*
		 *  Creates a vertex given a url.
		 *  This vertex has no edges yet. 
		 */
		WebVertex (String url) {
			this.url = url;
			this.links = new ArrayList<String>();
			this.visited = false;
			this.rank = 0;
		}
		
		
		boolean addEdge(String v) {
			if (!this.links.contains(v)) {
				this.links.add(v);
				return true;
			}
			return false;
		}
		
		
	    public ArrayList<String> getNeighbors() {
	        return this.links;
	    } 
	    
	    boolean containsEdge(String e) {
	    	return this.links.contains(e);
	    }
		
		public String toString() {
			return this.url + "\t" + this.visited + "\t" + this.rank;
		}
		
	}
	
	

}
