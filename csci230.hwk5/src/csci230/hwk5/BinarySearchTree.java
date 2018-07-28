package csci230.hwk5;

/**
 * 
 * Binary search that does not allow two (or more) binary nodes 
 * in the search tree to have the same element value.
 * 
 * @author CSCI 230: Data Structures and Algorithms Spring 2017
 * Austin Hunt; HW5 completed on 18 March 2017
 *
 * @param <AnyType>
 */
public class BinarySearchTree <AnyType extends Comparable> {
	
	// --------------------------------------
	// instance variable
	private BinaryNode<AnyType> root;
	
	/**
	 * Constructor with one parameter that
	 * sets the root node of the BST.
	 * 
	 * @param root
	 */
	public BinarySearchTree( AnyType rootElement ) throws NullBinaryNodeException {
		
		if ( rootElement == null ) {
			throw new NullBinaryNodeException();
		}
		
		this.root = new BinaryNode<AnyType>( rootElement ) ;
		
	} // end constructor
	
	/**
	 * If the BST does not have a root node, then the BST is empty. 
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		
		return ( root == null );
		
	} // end isEmpty() method
	
	/**
	 * Make the BST empty, i.e. a BST that has 
	 * no nodes (including a root node).
	 * 
	 */
	public void makeEmpty() {
		
		root = null;
		
	} // end makeEmpty() method
	
	/**
	 * Method that adds a new node with the specified element 
	 * value in the BST.
	 * 
	 * This method has one parameter:
	 *  1) The element value to be added
	 * 
	 * If the BST has an existing node with the same element 
	 * value, throw an duplicate element exception.
	 * 
	 * @param element
	 */
	public void add( AnyType element ) throws DuplicateElementException {
		if (isEmpty())
                    root = new BinaryNode(element);
                else
                {  
                    add(root,element);
                }
                /**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * 
		 */
		
		
		
	} // end add() method
	
	/**
	 * Private recursive method that adds a new node (with the 
	 * specified element value) in the BST.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value to be added
	 * 
	 * If the BST has an existing node with the same element 
	 * value, throw an duplicate element exception.
	 * 
	 * @param node
	 * @param element
	 */
	private void add( BinaryNode<AnyType> node, AnyType element )  throws DuplicateElementException {
		
                
                BinaryNode tempNode = new BinaryNode(element);
                
                //if element less than current node's element, either call method
                //on left child or set left child to tempNode
                if (element.compareTo(node.getElement())< 0)
                {
                    if (node.getLeft() != null)
                        add(node.getLeft(),element);
                    
                    else
                        node.setLeft(tempNode);
                }
                
                //if element greater than current node's element, either 
                //call on right child or set right child to tempNode
                else if (element.compareTo(node.getElement()) > 0)
                {
                    if (node.getRight() != null)
                        add(node.getRight(),element);
                    else
                        node.setRight(tempNode);
                }
                
                // if element = current node's element throw exception
                else if (element.compareTo(node.getElement()) == 0)
                {
                    throw new DuplicateElementException(); 
                }

                /**
                 * 
                 * 
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */
		
		
		
	} // end add() overloaded method// end add() overloaded method
	
	/**
	 * Method that determines if a node with the specified element value 
	 * exists in the BST. 
	 * 
	 * This method has one parameter:
	 *  1) The element value that is being searched.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @param element
	 * @return
	 */
	public boolean hasElement( AnyType element ) throws EmptyBSTException {
		
		if ( isEmpty() )
			throw new EmptyBSTException();
		
		return hasElement( root, element );
		
	} // end hasElement() method
	
	/**
	 * Private recursive method that determines if the element is 
	 * currently stored in the BST.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value that is being searched.
	 *  
	 *  Hint: use the compareTo() method
	 * 
	 * @param element
	 * @param node
	 * @return
	 */
	private boolean hasElement( BinaryNode<AnyType> node, AnyType element ) {
		boolean boolVal = false;
                
                if (element.equals(node.getElement()))
                    boolVal = true;
                
		if (element.compareTo(node.getElement()) < 0)
                {
                    if (node.getLeft() == null)
                        boolVal = false;
                    else
                        return hasElement(node.getLeft(), element);
                } 
                else if (element.compareTo(node.getElement()) > 0)
                {   
                    if (node.getRight() == null)
                        boolVal = false;
                    else
                        return hasElement(node.getRight(), element);
                }
                
                return boolVal;
                
                /**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */
		
		
		
	} // end hasElement() overloaded method
	
	/**
	 * Find the node with the minimum element value in the BST.
	 * 
	 * This method has no parameters.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @return
	 * @throws EmptyBSTException
	 */
	public AnyType findMin() throws EmptyBSTException {
		
		if ( isEmpty() )
			throw new EmptyBSTException();
		
		return findMin( root ).getElement();
		
	} // end findMin() method
	
	/**
	 * Private recursive method that walks the BST searching
	 * for the node with the minimum element value.
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 * 
	 * @param node
	 * @return
	 */
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> node ) {
		
		if (node.getLeft() == null)
                    return node;
                else
                    return findMin(node.getLeft());/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */
		
		
		
	} // end findMin() method
	
	/**
	 * Find the node with the maximum element value in the BST.
	 * 
	 * This method has no parameters.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @return
	 * @throws EmptyBSTException
	 */
	public AnyType findMax() throws EmptyBSTException {
		
		if ( isEmpty() )
			throw new EmptyBSTException();
		
		return findMax( root ).getElement();
		
	} // end findMax() method
	
	/**
	 * Private recursive method that walks the BST searching
	 * for the node with the maximum element value.
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 * 
	 * @param node
	 * @return
	 */
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> node ) {
		
                if (node.getRight()== null)
                    return node;
                else
                    return findMax(node.getRight());
		/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */
		
		
		
	} // end findMax() method
	
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
		System.out.println("New Binary Search Tree (Test 1: Null Root "
                        + "Constructor)\n");
		try
                {   BinarySearchTree bst1 = new BinarySearchTree(null);}
                catch (NullBinaryNodeException e) {System.out.println("Null node exception!");}
                
                System.out.println("\nNew Binary Search Tree (Test 2: adding, "
                        + "findMin, findMax, hasElement)\n");
                try
                {
                    System.out.println("Step 1: create tree using 50 as root");
                    BinarySearchTree bst2 = new BinarySearchTree(50);
                    System.out.println("Step 2: add 23 (root's left child)");
                    bst2.add(23);
                    System.out.println("Step 3: add 1 (23's left child)");
                    bst2.add(1);
                    System.out.println("Step 4: add 75 (root's right child)");
                    bst2.add(75);
                    System.out.println("Step 5: add 100 (75's right child)");
                    bst2.add(100);
                    System.out.println("Step 6: add 19 (1's right child)");
                    bst2.add(19);
                    System.out.println("Step 7: add 43 (23's right child)");
                    bst2.add(43);
                    
                    
                    
                    System.out.println("Step 8: find minimum value in BST");
                    System.out.println(bst2.findMin());
                    
                    System.out.println("Step 9: find maximum value in BST");
                    System.out.println(bst2.findMax());
                    
                    //hasElement on nonEmpty tree
                    System.out.println("BST2 has element 19: " + bst2.hasElement(19));
                    System.out.println("BST2 has element 4: " + bst2.hasElement(4));
                    
                    System.out.println("Adding a larger value, should change "
                            + "maximum: add 203 to tree (100's right child)");
                    bst2.add(203);
                    System.out.println("New max: " + bst2.findMax());
                    
                    System.out.println("Adding 0 to tree, should become new "
                            + "minimum");
                    bst2.add(0);
                    System.out.println("New min: " + bst2.findMin());
                    
                    bst2.makeEmpty();
                    System.out.println("BST2 is empty: " + bst2.isEmpty());
                    //hasElement on empty BST
                    System.out.println(bst2.hasElement(203));
                    
                    /*
                    System.out.println("Root: " + bst2.root);
                    System.out.println("Root left child: "+ bst2.root.getLeft());
                    System.out.println("Root left child's left child: "+ bst2.root.getLeft().getLeft());
                    System.out.println("root left child's right child: " + bst2.root.getLeft().getRight());
                    System.out.println("root left child's left child's left child: " + bst2.root.getLeft().getLeft().getLeft()); //should be null
                    System.out.println("root left child's left child's right child: " + bst2.root.getLeft().getLeft().getRight()); //should be 19 
                    System.out.println("Root right child: " + bst2.root.getRight());
                    System.out.println("Root right child's right child: " + bst2.root.getRight().getRight());
                    System.out.println("Root right child's left child: " + bst2.root.getRight().getLeft()); //should be null
                    */
                    
                }
                catch(NullBinaryNodeException e) {System.out.println("Null node "
                        + "exception!");} 
                catch(DuplicateElementException e2) {System.out.println("Duplicate"
                        + " element exception!");}
                catch (EmptyBSTException e3) {System.out.println("EmptyBSTException");}
                
                System.out.println("\nNew Binary Search Tree (Test 3: "
                        + "Duplicate Element Exceptions)\n");
                       
                try
                {
                    System.out.println("Step 1: create BST with 13 as root");
                    BinarySearchTree bst3 = new BinarySearchTree(13);
                    //add to nonempty, non duplicate
                    System.out.println("Step 2: add 12 (root's left child)");
                    bst3.add(12);
                    
                    System.out.println("Step 3: add 25 (root's right child)");
                    bst3.add(25);
                    
                    //add to nonempty, duplicate
                    System.out.println("Step 4: add 12 (duplicate, not allowed)");
                    bst3.add(12);
                }
                catch(NullBinaryNodeException e) {System.out.println("Null node "
                        + "exception!");}
                catch(DuplicateElementException e1) {System.out.println(""
                        + "Duplicate element exception!");}
                
                System.out.println("\nNew Binary Search Tree using CHARACTERS: ");
                System.out.println("Test: add(element), findMin(), findMax(), "
                        + "hasElement(element)");
                        
                try{
                    System.out.println("Step 1: create tree using 'K' as root");
                    BinarySearchTree bst4 = new BinarySearchTree('K');
                    
                    //add to nonempty, non duplicate
                    System.out.println("add() to nonempty BST");
                    System.out.println("Step 2: add 'F'(root's left child)");
                    bst4.add('F');
                    System.out.println("Step 3: add 'D'(F's left child)");
                    bst4.add('D');
                    System.out.println("Step 4: add 'A'(D's left child)");
                    bst4.add('A');
                    
                    bst4.makeEmpty();
                    System.out.println("BST4 is empty: " + bst4.isEmpty());
                    
                    //add to empty
                    System.out.println("add() to empty BST:");
                    
                    System.out.println("Step 5: add 'C'(new root)");
                    bst4.add('C');
                    System.out.println("Step 6: add 'R'(root's right child)");
                    bst4.add('R');
                    System.out.println("Step 7: add 'T'(R's right child)");
                    bst4.add('T');
                    System.out.println("Step 8: add 'M'(R's left child)");
                    bst4.add('M');
                    System.out.println("Step 9: add 'Q'(M's right child)");
                    bst4.add('Q');
                    System.out.println("Step 10: add 'Z' (T's right child");
                    bst4.add('Z');
                    
                    
                    System.out.println("\nMin character (should be C after rebuild): " + 
                            bst4.findMin());
                    System.out.println("Max character (should be Z): " + 
                            bst4.findMax());
                    
                    System.out.println("Tree has element 'C': " + 
                            bst4.hasElement('C'));
                    System.out.println("Tree has element 'L': " + 
                            bst4.hasElement('L'));
                     System.out.println("Tree has element 'P': " + 
                            bst4.hasElement('P'));
                      System.out.println("Tree has element 'Q': " + 
                            bst4.hasElement('Q'));
                    
                   /* 
                    System.out.println("Root: " + bst4.root);
                    System.out.println("Root left child: "+ bst4.root.getLeft());
                    System.out.println("Root left child's left child: "+ bst4.root.getLeft().getLeft());
                    System.out.println("root left child's right child: " + bst4.root.getLeft().getRight());
                    System.out.println("root left child's left child's left child: " + bst4.root.getLeft().getLeft().getLeft()); //should be null
                    System.out.println("root left child's left child's right child: " + bst4.root.getLeft().getLeft().getRight()); //should be 19 
                    System.out.println("Root right child: " + bst4.root.getRight());
                    System.out.println("Root right child's right child: " + bst4.root.getRight().getRight());
                    System.out.println("Root right child's left child: " + bst4.root.getRight().getLeft()); //should be null
                   */ 
                    
                }
                catch(NullBinaryNodeException e) {System.out.println("Null "
                        + "binary node exception!");}
                catch(DuplicateElementException e1){System.out.println(""
                        + "Duplicate Element Exception!");}
                catch(EmptyBSTException e2) {System.out.println("Empty "
                        + "BST Exception thrown by findmin/find max");}
                
                //find min on empty tree, throw exception
                System.out.println("\nNew Binary Search Tree, Testing Find Min"
                        + " Exception handling");
                try{
                    BinarySearchTree bst5 = new BinarySearchTree (100);
                    bst5.makeEmpty();
                    bst5.findMin();
                }
                catch(NullBinaryNodeException e) {System.out.println("Null"
                        + " binary node exception");}
                catch(EmptyBSTException e1) {System.out.println("EmptyBST"
                        + " exception thrown by find min on empty tree");}
                    /**
		 * ------------------------------------
		 * TODO: You put your test cases here
		 * 
		 * 
		 */
		//tree with one element, min = max
                System.out.println("\nNew Binary Search tree, one element (root); "
                        + "min = max");
                try {BinarySearchTree bst6 = new BinarySearchTree (200);
                System.out.println("Min: " + bst6.findMin());
                System.out.println("Max: " + bst6.findMax());}
                catch(NullBinaryNodeException e) {System.out.println("Null "
                        + "binary node exception");}
                catch(EmptyBSTException e1) {System.out.println("Empty BST "
                        + "Exception");}
                
                //what if you construct a BST using a char as root, then 
                // make it empty, and then use the add method to add a different 
                //primitive data type, like an int? 
                
                System.out.println("Construct BST with char as root --> make "
                        + "empty --> add int data type");
                try{
                    System.out.println("Step 1; new BinarySearchTree('M')");
                    BinarySearchTree bst7 = new BinarySearchTree('M');
                    System.out.println("Step 2: bst7.makeEmpty()");
                    bst7.makeEmpty();
                    System.out.println("Step 3: bst7.add(7) (different data"
                            + " type)");
                    bst7.add(7);
                    
                    System.out.println("No exception thrown! --> "
                            + "Emptying a BST allows one to rebuild it with "
                            + "add() using a different data type");
                }
                catch(NullBinaryNodeException e) {System.out.println("Null "
                        + "binary node exception");}
                catch(DuplicateElementException e1) {System.out.println(""
                        + "Duplicate Element exception");}
                
                
		
		
	} // end main method
	

} // end BinarySearchTree class
