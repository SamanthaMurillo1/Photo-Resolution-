/**
 * the quadrant tree 
 * Samantha Murillo 
 */
public class QuadrantTree {
	
	private QTreeNode root; // The root of the quadrant tree
	
	/**
	 * Constructs a QuadrantTree with the given pixel array.
	 * @param thePixels The pixel array to build the tree from.
	 */
	public QuadrantTree (int[][] thePixels) {
		root = buildTree(thePixels, 0, 0, thePixels.length);
		
	}
	
	/**
	 * Recursively builds the quadrant tree.
	 * @param pixels The pixel array.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param size The size of the quadrant.
	 * @return The root node of the constructed quadrant tree.
	 */
	private QTreeNode buildTree(int[][] pixels, int x, int y, int size) {
		// average color of pixels in Q
		int colorAverage = Gui.averageColor(pixels, x, y, size);
		
		// Base case: leaf node
		if (size == 1) {
            return new QTreeNode(null, x, y, size, colorAverage); //setting children as null
        } else {
        	
            // Recursive case: internal node
        	 QTreeNode[] childrenFour = new QTreeNode[4];
        	 
        	int halfSizeQ = size / 2;
           
        	// Build the upper left quadrant
            childrenFour[0] = buildTree(pixels, x, y, halfSizeQ);
            
         // Build the upper right quadrant
            childrenFour[1] = buildTree(pixels, x + halfSizeQ, y, halfSizeQ);
            
         // Build the lower left quadrant
            childrenFour[2] = buildTree(pixels, x, y + halfSizeQ, halfSizeQ);
            
         // Build the lower right quadrant
            childrenFour[3] = buildTree(pixels, x + halfSizeQ, y + halfSizeQ, halfSizeQ);
      
            // Create the parent node that contains the four child nodes
            QTreeNode parentNode = new QTreeNode(childrenFour, x, y, size, colorAverage);
            
            // Set parent for children
            for (QTreeNode child : childrenFour) {
                if (isNotNull(child)) {
                	child.setParent(parentNode);
                }
            }
            return parentNode;
        }
    }
	
	/**
	 * Gets the root node of the quadrant tree.
	 * @return The root node.
	 */
	public QTreeNode getRoot() {
		return this.root;
		
	}
	
	/**
	 * Retrieves pixels at a specific level in the quadrant tree.
	 * @param r The root node of the tree.
	 * @param theLevel The level to retrieve pixels from.
	 * @return A list of nodes at the specified level.
	 */
	public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel){
		 if (r == null) return null;  // if root is null return null
		    //Base case: : If r is a leaf or theLevel = 0 create and return
		 //a list storing r.
		 if (r.isLeaf() || theLevel <= 0) {
		        return new ListNode<>(r); 
		    } else {
		    	
//		    	performig recursive calls on the children of r concatenating
//		    	the lists that these calls return
		        ListNode<QTreeNode> nodeList = null; // Initialize the result list
		        for (int i = 0; i < 4; i++) { // Iterate through each child
		            QTreeNode childNode = r.getChild(i);
		            
		            //Checks if the child node is not null, ensuring that only valid child nodes are processed.
		            if (isNotNull(childNode)) {
		            	
		            	//Recursively calls the getPixels method on the current child node with a decremented level, obtaining a list of nodes at the specified level.
		                ListNode<QTreeNode> childList = getPixels(childNode, theLevel - 1); // Recursive call
		                
		                
		                nodeList = concatenate(nodeList, childList); // Concatenate the nodeList with the new childList
		            }
		        }
		        return nodeList;
		    }

	
	}
	
	/**
	 * Finds nodes in the tree that match a specified color and level.
	 * @param r The root node of the tree.
	 * @param theColor The color to match.
	 * @param theLevel The level to search.
	 * @return A Duple containing the matching nodes and their count.
	 */
	public Duple findMatching (QTreeNode r, int theColor, int theLevel) {
		// Base case: If the node is null, return an empty list and a count of 0.
	    if (isRootNull(r)) {
	        return new Duple(null, 0);
	    }
	    // If we're at the target level, or the node is a leaf (implying we've gone as deep as we can ,
	    // check if the color matches.
	    // then return the root node
	    if (r.isLeaf() || theLevel <= 0) {
	        if (Gui.similarColor(r.getColor(), theColor)) {
	            // returns the root node in a list and a count of 1.
	            return new Duple(new ListNode<>(r), 1);
	        } else {
	            // Color does not match, return an empty list and a count of 0.
	            return new Duple(null, 0);
	        }
	    } else {
	        // Not yet at the target level. Recurse deeper into the tree.
	    	//initializig the list and the count 
	        ListNode<QTreeNode> nodeList = null;
	        int count = 0;
	        
	        for (int i = 0; i < 4; i++) { // Assuming each node has up to 4 children.
	            QTreeNode childNode = r.getChild(i);
	            if (isNotNull(childNode)) {
	            	//Calls the findMatching method recursively on the current child node to find nodes with matching color and level.
	                Duple childList = findMatching(childNode, theColor, theLevel - 1);
	                
	                //Concatenates the list of nodes from the current child node's Duple result with the existing nodeList
	                nodeList = concatenate(nodeList, (ListNode<QTreeNode>) childList.getFront()); // Correctly access the list part of the Duple
	                count += childList.getCount(); // Correctly access the count part of the Duple
	            }
	        }
	        // Return the combined list and count of all matching nodes.
	        return new Duple(nodeList, count);
	    }

	}
	
	/**
	 * Concatenates two lists of nodes.
	 * @param list1 The first list.
	 * @param list2 The second list.
	 * @return The concatenated list.
	 */
	private static ListNode<QTreeNode> concatenate(ListNode<QTreeNode> list1, ListNode<QTreeNode> list2) {
		//Checks if list2 is null, and if so, returns list1 immediately same thing goes for next line 
		if (list2 == null) return list1;
		if (list1 == null) return list2;
	    
		//Initializes a temporary ListNode variable tmp, to concatenate the two lists using this variable  
	    ListNode<QTreeNode> tmp = list1;
	    
	    while (tmp.getNext() != null) { // Navigate to the end of list1
	        tmp = tmp.getNext();
	    }
	    tmp.setNext(list2); // Connect the end of list1 to the beginning of list2
	   
	    //Returns the modified list1, which now includes all nodes from both list1 and list2 connected in sequence.
	    return list1;  
	}
	
	/**
	 * Finds a node in the tree based on level and coordinates.
	 * @param r The root node of the tree.
	 * @param theLevel The level to search.
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 * @return The node found, or null if not found.
	 */
	public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
		// Base case: if the node is null or the level is less than 0, return null
	    if (theLevel < 0 || isRootNull(r)) {
	        return null;
	    }

	    // Base case: if the level is 0, check if the point (x, y) lies within the quadrant represented by the current node
	    if (theLevel == 0) {
	        if (isInQuadrant(r, x, y)) {
	            return r; // returning root node 
	        } else {
	            return null;
	        }
	    }

	    // Recursively search in the child quadrants if they exist and contain the point (x, y)
	    for (int i = 0; i < 4; i++) {
	        QTreeNode childNode = r.getChild(i);
	        if (isNotNull(childNode) && isInQuadrant(childNode, x, y)) {
	            // If the child quadrant contains the point, recursively search in that quadrant
	            QTreeNode nodeFound = findNode(childNode, theLevel - 1, x, y);
	            
	            if (isNotNull(nodeFound)) {
	                return nodeFound; // If a non-null node is found in the child quadrant, return it
	            }
	        }
	    }

	    // If no matching node is found in any child quadrant, return null
	    return null;
		
	}
	
	/**
	 * 
	 * @param r The root node of the tree.
	 * Checks if the root node is null
	 */
	private boolean isRootNull(QTreeNode r) {
		return r == null;
	}
	
	/**
	 * 
	 * @param node of tree and checks if it is not null
	 */
	private boolean isNotNull(QTreeNode node) {
		return node != null;
	}
	
	/**
	 * Checks if a given point (x, y) is within the quadrant represented by a node.
	 * @param node The node representing the quadrant.
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 * @return true if the point is within the quadrant, false otherwise.
	 */
	private boolean isInQuadrant(QTreeNode node, int x, int y) {
		//These lines retrieve the x and y coordinates of the minimum corner of the quadrant
		//represented by the given node.
        int xMin = node.getx();
        int yMin = node.gety();
        
       // calculating the x and y coordinates of the maximum corner of the quadrant
        int xMax = xMin + node.getSize();
        int yMax = yMin + node.getSize();
        
       // checks if the given point (x, y) lies within the calculated bounds of the quadrant 
        boolean xInQadrant = x < xMax  && x >= xMin;
        boolean yInQuadrant = y < yMax  && y >= yMin;
        
        
        return xInQadrant  && yInQuadrant;
    }
	
}
