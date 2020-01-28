import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/* YOU SHOULD NOT NEED TO LOOK AT THIS CODE AT ALL.
   BUT IT COULD BE INTERESTING FOR YOU TO SEE HOW IT WORKS. */

// This class implements a simple parser for xml documents
public class XmlParser {
    File inputFile;
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;
    
    // Read the xml file
    public XmlParser(String fileName) throws SAXException, IOException, ParserConfigurationException
    {
    	inputFile = new File(fileName);
    	dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
    }
    
    // Search the xml file for the given webpage/url and return links contained in the webpage.
	public ArrayList<String> getLinks(String url)
	{
		ArrayList<String> urls = new ArrayList<String>();
		NodeList webPages = doc.getElementsByTagName("webpage");
		
		// loop over available webpages in the xml file
		for (int i = 0; i < webPages.getLength(); i++)
		{	
			Node webpage = webPages.item(i);
			
			if (url.equals(((Element) webpage).getAttribute("name"))) 
			{
				NodeList links = ((Element)webpage).getElementsByTagName("link");
				
				for (int j = 0; j < links.getLength(); j++)
				{
					Node link = links.item(j);
					urls.add(((Element) link).getAttribute("name"));
				}
			}
		}
		
		return urls;
	}
	
	// Search the xml file for the given webpage/url and return the text contents of the webpage.
	public ArrayList<String> getContent(String url)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		NodeList webPages = doc.getElementsByTagName("webpage");
			
		// loop over available webpages in the xml file
		for (int i = 0; i < webPages.getLength(); i++)
		{	
			Node webpage = webPages.item(i);
			if (url.equals(((Element) webpage).getAttribute("name"))) 
			{
				NodeList links = ((Element)webpage).getElementsByTagName("content");
					
				for (int j = 0; j < links.getLength(); j++)
				{
					Node link = links.item(j);
					String[] content = ((Element) link).getAttribute("value").split("\\s|,|\\.|!|\\(|\\)|-");
					for (String s : content)
						tokens.add(s);
				}
					
				break;
			}
		}
		
		return tokens;
	}
	
	// Search the xml file for the given webpage/url and return the expected rank of the webpage.
	public double getPageRank(String url)
	{
		NodeList webPages = doc.getElementsByTagName("webpage");
			
		// loop over available webpages in the xml file
		for (int i = 0; i < webPages.getLength(); i++)
		{	
			Node webpage = webPages.item(i);
			if (url.equals(((Element) webpage).getAttribute("name"))) 
			{
				NodeList rankNode = ((Element)webpage).getElementsByTagName("rank");
				
				if (rankNode.getLength() > 0) 
				{
					Node rank = rankNode.item(0);
					return Double.parseDouble(((Element) rank).getAttribute("value"));
				}
					
				break;
			}
		}
			
		return 0.0;
	}
	
	
    public static void main(String []args) throws Exception
    {
    	XmlParser xmlParser = new XmlParser("test.xml");
    	
    	ArrayList<String> urls = xmlParser.getLinks("www.ea.com");
    	ArrayList<String> content = xmlParser.getContent("www.ea.com");
    	double rank = xmlParser.getPageRank("www.ea.com");
    	
    	System.out.println("\nShow linked urls");
    	for (String url : urls)
    	{
    		System.out.println(url);
    	}
    	
    	System.out.println("\nWebpage content");
    	for (String c : content)
    	{
    		System.out.println(c);
    	}
    	
    	System.out.println("\nWebpage rank " + rank);
    }
} // end of writeContent
