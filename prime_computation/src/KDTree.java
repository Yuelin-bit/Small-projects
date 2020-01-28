//Name: Yuelin Liu
//ID: 260844113
import java.util.ArrayList;
import java.util.Iterator;
public class KDTree implements Iterable<Datum>{ 

	KDNode 		rootNode;
	int    		k; 
	int			numLeaves;
	
	// constructor

	public KDTree(ArrayList<Datum> datalist) throws Exception {

		Datum[]  dataListArray  = new Datum[ datalist.size() ]; 

		if (datalist.size() == 0) {
			throw new Exception("Trying to create a KD tree with no data");
		}
		else
			this.k = datalist.get(0).x.length;

		int ct=0;
		for (Datum d :  datalist) {
			dataListArray[ct] = datalist.get(ct);
			ct++;
		}
		
	//   Construct a KDNode that is the root node of the KDTree.

		rootNode = new KDNode(dataListArray);
	}
	
	//   KDTree methods
	
	public Datum nearestPoint(Datum queryPoint) {
		return rootNode.nearestPointInNode(queryPoint);
	}
	

	public int height() {
		return this.rootNode.height();	
	}

	public int countNodes() {
		return this.rootNode.countNodes();	
	}
	
	public int size() {
		return this.numLeaves;	
	}

	//-------------------  helper methods for KDTree   ------------------------------

	public static long distSquared(Datum d1, Datum d2) {

		long result = 0;
		for (int dim = 0; dim < d1.x.length; dim++) {
			result +=  (d1.x[dim] - d2.x[dim])*((long) (d1.x[dim] - d2.x[dim]));
		}
		// if the Datum coordinate values are large then we can easily exceed the limit of 'int'.
		return result;
	}

	public double meanDepth(){
		int[] sumdepths_numLeaves =  this.rootNode.sumDepths_numLeaves();
		return 1.0 * sumdepths_numLeaves[0] / sumdepths_numLeaves[1];
	}
	
	class KDNode { 

		boolean leaf;
		Datum leafDatum;           //  only stores Datum if this is a leaf
		
		//  the next two variables are only defined if node is not a leaf

		int splitDim;      // the dimension we will split on
		int splitValue;    // datum is in low if value in splitDim <= splitValue, and high if value in splitDim > splitValue  

		KDNode lowChild, highChild;   //  the low and high child of a particular node (null if leaf)
		  //  You may think of them as "left" and "right" instead of "low" and "high", respectively

		KDNode(Datum[] datalist) throws Exception{

			/*
			 *  This method takes in an array of Datum and returns 
			 *  the calling KDNode object as the root of a sub-tree containing  
			 *  the above fields.
			 */

			//   ADD YOUR CODE BELOW HERE	
			
			if (datalist.length == 0) {
				throw new Exception("Trying to create a KD node with no data");
			}
			
			else if(datalist.length == 1) {
				leaf = true;
				leafDatum = datalist[0];
				numLeaves++;
			}
			
			else {
				
				
				splitDim = findDimWithLargestRange(datalist);
				int[] temp = new int[datalist.length];
				for(int i=0; i<datalist.length; i++) {
					temp[i] = datalist[i].x[splitDim];
				}
				double splitValueDouble = findSplitValueDouble(temp);
				splitValue = (int)splitValueDouble;
				if((splitValueDouble<0)&&(splitValueDouble - (int)splitValueDouble!=0 )){
					splitValue = (int) (splitValueDouble)-1;
				}
				//splitValue = findSplitValue(temp);
				ArrayList<Datum> lowOrEqualA = new ArrayList<Datum>();
				ArrayList<Datum> highA = new ArrayList<Datum>();
				for(int i=0; i<datalist.length; i++) {
					if(datalist[i].x[splitDim] <= splitValue) {
						lowOrEqualA.add(datalist[i]);
					}
					else {
						highA.add(datalist[i]);
					}
				}
				if(highA.size()==0) {
					leaf = true;
					leafDatum = lowOrEqualA.get(0);
					numLeaves++;
				}
				if(lowOrEqualA.size()==0) {
					leaf = true;
					leafDatum = highA.get(0);
					numLeaves++;
				}
				if((lowOrEqualA.size()!=0)&&(highA.size()!=0)) {
					Datum[] lowOrEqual= new Datum[lowOrEqualA.size()];
					Datum[] high= new Datum[highA.size()];
					for(int i=0; i<lowOrEqualA.size(); i++) {
						lowOrEqual[i] = lowOrEqualA.get(i);
					}
					for(int i=0; i<highA.size(); i++) {
						high[i] = highA.get(i);
					}
					lowChild = new KDNode(lowOrEqual);
					highChild = new KDNode(high);
				}
				
			}
			
			//   ADD YOUR CODE ABOVE HERE

		}

		public Datum nearestPointInNode(Datum queryPoint) {
			Datum nearestPoint, nearestPoint_otherSide;
		
			//   ADD YOUR CODE BELOW HERE
			
			return findNearestPointInSpecificPlane(queryPoint, rootNode);
			//   ADD YOUR CODE ABOVE HERE
		
		}
		
		// -----------------  KDNode helper methods (might be useful for debugging) -------------------
		private Datum findNearestPointInSpecificPlane(Datum queryPoint, KDNode relativePoint) {
//			if(relativePoint==null) {
//				return null;
//			}
			if(relativePoint.leaf==true) {
				return relativePoint.leafDatum;
			}
			else {
				double distanceToPlaneS;
				if(queryPoint.x[relativePoint.splitDim]<=relativePoint.splitValue) {
					Datum sameSide = findNearestPointInSpecificPlane(queryPoint, relativePoint.lowChild);
					double a = (relativePoint.splitValue - queryPoint.x[relativePoint.splitDim]);
					distanceToPlaneS = a*a;
				
					if(distanceToPlaneS>distSquared(queryPoint, sameSide)) {
						return sameSide;
					}
					else {
						Datum otherSide = findNearestPointInSpecificPlane(queryPoint, relativePoint.highChild);
						if(distSquared(queryPoint, otherSide)<distSquared(queryPoint, sameSide)) {
							return otherSide;
						}
						else {
							return sameSide;
						}
						
					}
				}
				else {
					Datum sameSide = findNearestPointInSpecificPlane(queryPoint, relativePoint.highChild);
					double a = (queryPoint.x[relativePoint.splitDim] - relativePoint.splitValue);
					distanceToPlaneS = a*a;
				
					if(distanceToPlaneS>distSquared(queryPoint, sameSide)) {
						return sameSide;
					}
					else {
						Datum otherSide = findNearestPointInSpecificPlane(queryPoint, relativePoint.lowChild);
						if(distSquared(queryPoint, otherSide)<distSquared(queryPoint, sameSide)) {
							return otherSide;
						}
						else {
							return sameSide;
						}
					}
				}
			}
		}
		
		
		
		private int findSquareDistance(Datum a, Datum b) {
			int result = 0;
			for(int i=0; i<a.x.length; i++) {
				result = result + (a.x[i]-b.x[i])*(a.x[i]-b.x[i]);
			}
			return result;
		}
		private int findDimWithLargestRange(Datum[] datalist6) {
			int result = 0;
			int range = 0;
			
			for(int i=0; i<datalist6[0].x.length; i++) {
				int max = datalist6[0].x[i];
				int mini = datalist6[0].x[i];
				for(int j=0; j<datalist6.length; j++) {
					if(datalist6[j].x[i]>max) {
						max = datalist6[j].x[i];
					}
				}
				for(int j=0; j<datalist6.length; j++) {
					if(datalist6[j].x[i]<mini) {
						mini = datalist6[j].x[i];
					}
				}
				if((max-mini)>range) {
					result = i;
					range = max-mini;
				}		
			}	
			return result;	
		}
		
		private int findSplitValue(int[] arr) {
			int max = arr[0];
			int mini = arr[0];
			for(int i=0; i<arr.length; i++) {
				if(arr[i]>max) {
					max = arr[i];
				}
			}
			for(int i=0; i<arr.length; i++) {
				if(arr[i]<mini) {
					mini = arr[i];
				}
			}
			int result = (max+mini)/2;
			return result;
		}
		
		private double findSplitValueDouble(int[] arr) {
			double max = arr[0];
			double mini = arr[0];
			for(int i=0; i<arr.length; i++) {
				if(arr[i]>max) {
					max = arr[i];
				}
			}
			for(int i=0; i<arr.length; i++) {
				if(arr[i]<mini) {
					mini = arr[i];
				}
			}
			double result = (max+mini)/2;
			return result;
		}
		

		public int height() {
			if (this.leaf) 	
				return 0;
			else {
				return 1 + Math.max( this.lowChild.height(), this.highChild.height());
			}
		}

		public int countNodes() {
			if (this.leaf)
				return 1;
			else
				return 1 + this.lowChild.countNodes() + this.highChild.countNodes();
		}
		
		/*  
		 * Returns a 2D array of ints.  The first element is the sum of the depths of leaves
		 * of the subtree rooted at this KDNode.   The second element is the number of leaves
		 * this subtree.    Hence,  I call the variables  sumDepth_size_*  where sumDepth refers
		 * to element 0 and size refers to element 1.
		 */
				
		public int[] sumDepths_numLeaves(){
			int[] sumDepths_numLeaves_low, sumDepths_numLeaves_high;
			int[] return_sumDepths_numLeaves = new int[2];
			
			/*     
			 *  The sum of the depths of the leaves is the sum of the depth of the leaves of the subtrees, 
			 *  plus the number of leaves (size) since each leaf defines a path and the depth of each leaf 
			 *  is one greater than the depth of each leaf in the subtree.
			 */
			
			if (this.leaf) {  // base case
				return_sumDepths_numLeaves[0] = 0;
				return_sumDepths_numLeaves[1] = 1;
			}
			else {
				sumDepths_numLeaves_low  = this.lowChild.sumDepths_numLeaves();
				sumDepths_numLeaves_high = this.highChild.sumDepths_numLeaves();
				return_sumDepths_numLeaves[0] = sumDepths_numLeaves_low[0] + sumDepths_numLeaves_high[0] + sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
				return_sumDepths_numLeaves[1] = sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
			}	
			return return_sumDepths_numLeaves;
		}
		
	}

	public Iterator<Datum> iterator() {
		return new KDTreeIterator();
	}
	
	private class KDTreeIterator implements Iterator<Datum> {
		//   ADD YOUR CODE BELOW HERE
		private ArrayList<Datum> myContainer = new ArrayList<Datum> ();
		private KDNode currentNode = rootNode;
		int size;
		private int cursor = -1;
		//adding Datum in container.....
		private void myAdder(KDNode node) {
			if(node == null) {
				return;
			}
			if(node.leaf==true) {
				myContainer.add(node.leafDatum);
			}
			else {
				this.myAdder(node.lowChild);
				this.myAdder(node.highChild);
				
			}
		}
		KDTreeIterator(){
			this.myAdder(rootNode);
			size = myContainer.size();
		}
		//boolean hasAdded = false;
			
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
//			if(hasAdded == false) {
//				this.myAdder(rootNode);
//				size = myContainer.size();
//				hasAdded = true;
//			}
			return cursor+1<size;
		}
		@Override
		public Datum next() {
			// TODO Auto-generated method stub
//			if(hasAdded == false) {
//				this.myAdder(rootNode);
//				size = myContainer.size();
//				hasAdded = true;
//			}
			cursor++;
			return myContainer.get(cursor);
		}
		//   ADD YOUR CODE ABOVE HERE

	}


}

