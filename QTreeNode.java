
/**
 * Represents a node in a Quadtree structure.
 *  Samantha Murillo Carmona 
 */

 
 
public class QTreeNode {
	// declaring private instance variables 
	private int x;
	private int y;
	private int size;
	
	private int color;
	private QTreeNode parent;
	private QTreeNode[] children;
	
	/**
	 *  constructor for QTreeNode.
	 */
	public QTreeNode() {
		
		this.parent = null;
		this.x = 0;
		this.y = 0;
		this.size = 0;
		this.color = 0;
		
		//creates an array of size 4 for children, setting every entry to null. 
		this.children = new QTreeNode[4];

		for( int i =0; i < 4; i++) {
			this.children[i] = null;
		}
	
	}
	
	/**
	 * Constructor for QTreeNode with specified parameters.
	 * 
	 * @param theChildren The children nodes of this node.
	 * @param xcoord      The x-coordinate of the node.
	 * @param ycoord      The y-coordinate of the node.
	 * @param theSize     The size of the node.
	 * @param theColor    The color of the node.
	 */
	public QTreeNode(QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
		
		//Initializes the instance variables to
		//the values passed as parameters
		this.x = xcoord; 
		this.y = ycoord;
		this.size = theSize;
		this.children = theChildren;
		this.color = theColor;
	}
	
	
	/**
	 * Checks if a point is contained within the node's boundaries.
	 * 
	 * @param xcoord The x-coordinate of the point.
	 * @param ycoord The y-coordinate of the point.
	 * @return True if the point is contained in the node, false otherwise.
	 */
	public boolean contains(int xcoord, int ycoord) {
		boolean yWithinQuadrant = ycoord >= this.y && ycoord <= this.y + this.size - 1;
		boolean xWithinQuadrant = xcoord >= this.x && xcoord <= this.x + this.size - 1;
		return ( yWithinQuadrant && xWithinQuadrant);
	}
	
	/**
	 * Gets the x-coordinate of the node.
	 * 
	 * @return The x-coordinate.
	 */
	public int getx() {
	 return this.x;
	}
	
	/**
	 * Gets the y-coordinate of the node.
	 * 
	 * @return The y-coordinate.
	 */
	public int gety() {
		return this.y;
	}
	
	/**
	 * Gets the size of the node.
	 * 
	 * @return The size of the node.
	 */
    public int getSize() {
    	return this.size;
	}
    
    /**
     * Gets the color of the node.
     * 
     * @return The color of the node.
     */
    public int getColor() {
    	return this.color;
	}
    
    /**
     * Gets the parent node.
     * 
     * @return The parent node.
     */
    public QTreeNode getParent() {
    	return this.parent;
    }
	
    /**
	 * Gets the child node at the specified index.
	 * 
	 * @param index The index of the child node.
	 * @return The child node at the specified index.
	 * @throws QTreeException If the index is invalid or the children array is null.
	 */
	public QTreeNode getChild(int index) throws QTreeException{
		if(this.children == null || index < 0 || index > 3) { 
			throw new QTreeException("Invalid index or null children array."); 
		}   
		return this.children[index];
	}
	
	/**
	 * Sets the x-coordinate of the node.
	 * 
	 * @param newx The new x-coordinate.
	 */
	public void setx (int newx) {
		this.x = newx;
		
	}
	
	/**
	 * Sets the y-coordinate of the node.
	 * 
	 * @param newy The new y-coordinate.
	 */
	public void sety (int newy) {
		this.y = newy;
	}
	
	/**
	 * Sets the size of the node.
	 * 
	 * @param newSize The new size of the node.
	 */
	public void setSize(int newSize) {
		this.size = newSize;
	}
	
	/**
	 * Sets the color of the node.
	 * 
	 * @param newColor The new color of the node.
	 */
	public void setColor(int newColor) {
		this.color = newColor;
	}
	
	/**
	 * Sets the parent node.
	 * 
	 * @param newParent The new parent node.
	 */
	public void setParent(QTreeNode newParent) {
		 this.parent = newParent;
	}
	
	
	/**
	 * Sets the child node at the specified index.
	 * 
	 * @param newChild The new child node.
	 * @param index    The index of the child node.
	 * @throws QTreeException If the index is invalid or the children array is null.
	 */
	public void setChild(QTreeNode newChild, int index) {
	
		
		if(this.children == null || index < 0 || index > 3) {
			throw new QTreeException("Invalid index or null children array.");
		}
		this.children[index] = newChild;
		
	}
	
	/**
	 * Checks if the node is a leaf node.
	 * 
	 * @return True if the node is a leaf node, false otherwise.
	 */
	public boolean isLeaf() {
		if(this.children == null) { // if null children then the node must be a leaf
			return true;
		}
		for(QTreeNode child : this.children) {
			if(child == null) { 
				return true;
			}
		}
		return false; //returning false otherwise 

	}

}
