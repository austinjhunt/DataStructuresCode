package csci230.hwk6;

/**
 * 
 * Binary search that does not allow two (or more) binary nodes 
 * in the search tree to have the same element value.
 * 
 * @author CSCI 230: Data Structures and Algorithms Spring 2017
 * Austin Hunt 
 * @since 2 April 2017 
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
	 * @throws NullBinaryNodeException 
	 * @throws DuplicateElementException 
	 */
	public void add( AnyType element ) throws DuplicateElementException, NullBinaryNodeException {

		if ( element == null ) {

			throw new NullBinaryNodeException();

		} if ( root == null ) {

			this.root = new BinaryNode<AnyType>( element );

		} 
                else {

			add( root, element );

		}

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

		int compare = element.compareTo( node.getElement() );

		if ( compare == 0 ) {

			throw new DuplicateElementException();

		} else if ( compare < 0 ) {

			if ( node.getLeft() == null ) {

				node.setLeft( new BinaryNode<AnyType>( element ) );
				node.getLeft().setParent( node );

			} else {

				add( node.getLeft(), element );

			}

		} else if ( compare > 0 ) {

			if ( node.getRight() == null ) {

				node.setRight( new BinaryNode<AnyType>( element ) );
				node.getRight().setParent( node );

			} else {

				add( node.getRight(), element );

			}

		}

	} // end add() overloaded method

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
	 * @throws NullBinaryNodeException 
	 * @throws EmptyBSTException 
	 * 
	 */
	public boolean hasElement( AnyType element ) throws EmptyBSTException, NullBinaryNodeException {

		if ( isEmpty() )
			throw new EmptyBSTException();
		else if ( element == null )
			throw new NullBinaryNodeException();

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

		if ( node == null )
			return false;

		int compare = element.compareTo( node.getElement() );

		if ( compare == 0 ) {

			return true;

		} else if ( compare < 0 ) {

			return hasElement( node.getLeft(), element );

		} else {

			return hasElement( node.getRight(), element );

		}

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

		if ( node.getLeft() == null ) {

			return node;

		} else {

			return findMin( node.getLeft() );
		}

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

		if ( node.getRight() == null ) {

			return node;

		} else {

			return findMax( node.getRight() );
		}

	} // end findMax() method

	/**
	 * Delete the node with the specified element value in the BST.
	 * 
	 * This method has one parameter:
	 * 	1) the element value to be deleted
	 * 
	 * This method performs the following delete operations
	 * 	1) delete node with no children (case 1)
	 * 	2) delete node with one child (case 2)
	 * 	3) delete node with two children (case 3)
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * If the element is null, throw a null binary node 
	 * exception
	 *  
	 * @param element
	 * @throws EmptyBSTException
	 * @throws NullBinaryNodeException
	 */
	public void delete( AnyType element ) throws EmptyBSTException, NullBinaryNodeException {
            if (isEmpty())
                throw new EmptyBSTException();
            if (element == null) 
                throw new NullBinaryNodeException();
            delete(root,element);


	} // end delete() method

	/**
	 * Private recursive method that walk the BST looking for 
	 * the specified element value to be removed.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value that is being removed.
	 *  
	 * @param node
	 * @param element
	 */
	private void delete( BinaryNode<AnyType> node, AnyType element ) {
            //element passed is not in the BST
            if (node == null)
                return;
            
            int compare = element.compareTo(node.getElement());
            
            //if element less than current node element, call method on left child 
            if (compare < 0 ) 
                delete(node.getLeft(),element);
            //if element greater than current node element, call method on right child 
            else if (compare > 0 )
                delete(node.getRight(),element);
            
            //base case, compare == 0; node to be removed FOUND; 3 cases
            else 
            {
                //Case 1: two children; replace current node with max of left subtree
                //then delete that maximum from original position; only want one 
                if (node.getRight()!= null && node.getLeft()!= null)
                {
                    node.setElement(findMax(node.getLeft()).getElement());//set to max of left subtree
                    delete(node.getLeft(),node.getElement());
                }
                /*Case 2.1: only has left child; if current node > parent, then 
                set parent's right to current node's left; if current node < parent, 
                then set parent's left to current node's left; set node = to left 
                child, set original current node to null after changing pointer*/
                else if (node.getLeft() != null)
                {
                    BinaryNode deleteThis = node;
                    AnyType parent = node.getParent().getElement();
                    if(node.getElement().compareTo(parent)>0)
                        node.getParent().setRight(node.getLeft());
                    if (node.getElement().compareTo(parent)<0)
                        node.getParent().setLeft(node.getLeft());
                    node = node.getLeft(); //Make sure parent points to the new node!!
                    node.setParent(deleteThis.getParent());
                    deleteThis = null; //not necessary to do this, no longer referenced
                    //by anything 
                    
                    
                }
                
                /*case 2.2 only has right child; if node > parent, set parent's
                right child to current node's right child; if node < parent, set 
                parent's left child to current node's right child; make node refer 
                to its right child, then set original current node to null 
                */
                else if (node.getRight() != null) 
                {
                    BinaryNode deleteThis = node; 
                    AnyType parent = node.getParent().getElement();
                    if(node.getElement().compareTo(parent)> 0)
                        node.getParent().setRight(node.getRight());
                    if(node.getElement().compareTo(parent) < 0)
                        node.getParent().setLeft(node.getRight());
                    node = node.getRight();
                    node.setParent(deleteThis.getParent());
                    deleteThis = null; 
                }
                
                //Case 3 has NO children; leaf reached, simply make this node null
                // make all POINTERS TO THIS NODE (from parent) null 
                else
                {
                    AnyType parent = node.getParent().getElement();
                    if (node.getElement().compareTo(parent)< 0)
                        node.getParent().setLeft(null);
                    else if (node.getElement().compareTo(parent)> 0)
                        node.getParent().setRight(null);
                    node = null; 
                }     
            }
	} // end delete() method

	/**
	 * Recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an post-order 
	 * tree traversal format.
	 * 
	 * The return string MUST be formated as follows:
	 * <element>,<element>,<element>,<element>, .... <element>
	 * where <element> is the element value
	 * For example,
	 * 	1,3,2
	 * Hint: you may want to use regular expressions
	 * 
	 * Discussed in class, please review 
	 * your notes
	 * 
	 * This method has no parameters.
	 * 
	 * If the BST is empty, throw an empty binary search 
	 * tree exception
	 * 
	 * @throws EmptyBSTException 
	 * @return
	 * 
	 */
	public String postOrder() throws EmptyBSTException {

		if (isEmpty())
                    throw new EmptyBSTException();
                String postTrav = postOrder(root) ;
                return postTrav.substring(0,postTrav.length()-1);/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */

		

		

	} // end postOrder() method

	/**
	 * Private recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an post-order 
	 * tree traversal format. 
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 *  
	 * @param node
	 * @return
	 * 
	 */
	private String postOrder( BinaryNode<AnyType> node ) {
            
            String postOrder = "";
            
            if (node == null) 
                return "";
          
            postOrder += postOrder(node.getLeft());
            postOrder += postOrder(node.getRight());
            
            postOrder += ("" + node.getElement() + ","); //(LRV) 
            return postOrder;
            
                        
            
                /*
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */

		




	} // end postOrder() method

	/**
	 * Recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an pre-order 
	 * tree traversal format.
	 * 
	 * The return string MUST be formated as follows:
	 * <element>,<element>,<element>,<element>, .... <element>
	 * where <element> is the element value
	 * For example,
	 * 	2,1,3
	 * Hint: you may want to use regular expressions
	 * 
	 * Discussed in class, please review 
	 * your notes
	 * 
	 * If the BST is empty, throw an empty binary search 
	 * tree exception
	 * 
	 * @throws EmptyBSTException 
	 * @return 
	 * 
	 */
	public String preOrder() throws EmptyBSTException {

            if (isEmpty())
                throw new EmptyBSTException();
            String preTrav = preOrder(root) ;
            return preTrav.substring(0, preTrav.length()-1); //removes last comma 
            /**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */

		



	} // end preOrder() method

	/**
	 * Private recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an pre-order 
	 * tree traversal format. 
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 *  
	 * @param node
	 * @return
	 * 
	 */
	private String preOrder( BinaryNode<AnyType> node ) {
            String preOrder = "";
            if (node == null) 
                return "";
            preOrder += ("" + node.getElement() + ",");
            preOrder += preOrder(node.getLeft());
            preOrder += preOrder(node.getRight());
            return preOrder;
            

		



	} // end preOrder() method

	/**
	 * Recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an in-order 
	 * tree traversal format. 
	 * 
	 * The return string MUST be formated as follows:
	 * <element>,<element>,<element>,<element>, .... <element>
	 * where <element> is the element value
	 * For example,
	 * 	1,2,3
	 * Hint: you may want to use regular expressions
	 * 
	 * Discussed in class, please review 
	 * your notes
	 * 
	 * If the BST is empty, throw an empty binary search 
	 * tree exception
	 * 
	 * @throws EmptyBSTException 
	 * @return
	 * 
	 */
	public String inOrder() throws EmptyBSTException {

            if (isEmpty())
                throw new EmptyBSTException();
            String inTrav = inOrder(root);
            return inTrav.substring(0, inTrav.length()-1);
                    
            
                /**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */

		



	} // end inOrder() method


	/**
	 * Private recursive method that traverses the BST 
	 * dynamically creating a string with the element 
	 * values stored in an in-order tree traversal format.
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 *  
	 * @param node
	 * @return
	 * 
	 */
	private String inOrder( BinaryNode<AnyType> node ) {

            String inOrder = "";
            if(node == null) 
                return "";
            
            inOrder += inOrder(node.getLeft());
            inOrder += ("" + node.getElement() + ",");
            inOrder += inOrder(node.getRight());
            
            return inOrder;
                
                /**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */
	} // end inOrder() method

	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {

            try{
                BinarySearchTree bst1 = new BinarySearchTree(10);
                bst1.add(5);
                bst1.add(15);
                bst1.add(3);
                bst1.add(7);
                bst1.add(1);
                bst1.add(2);
                bst1.add(6);
                bst1.add(8);
                bst1.add(13);
                bst1.add(18);
                bst1.add(12);
                bst1.add(14);
                bst1.add(16);
                bst1.add(19);
                
                String inOrder = bst1.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                String postOrder = bst1.postOrder(); 
                System.out.println("postorder traversal: " + postOrder);
                
                String preOrder = bst1.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                System.out.println("\nAfter deleting 10 (root) ");
                
                bst1.delete(10);
                inOrder = bst1.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                postOrder = bst1.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                preOrder = bst1.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                bst1.delete(15);
                System.out.println("\nAfter deleting 15");
                inOrder = bst1.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                postOrder = bst1.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                preOrder = bst1.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                bst1.delete(2);
                System.out.println("\nAfter deleting 2");
                inOrder = bst1.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                postOrder = bst1.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                preOrder = bst1.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                bst1.delete(19);
                System.out.println("\nAfter deleting 19");
                inOrder = bst1.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                postOrder = bst1.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                preOrder = bst1.preOrder();
                System.out.println("preorder traversal: " + preOrder);
            }
            catch(NullBinaryNodeException e) {System.out.println("Null node "
                    + "exception!");}
            catch(DuplicateElementException e1){System.out.println("Duplicate"
                    + " element exception!");}
            catch(EmptyBSTException e2){System.out.println("Empty BST exception!");}
            
            try{
                BinarySearchTree bst2 = new BinarySearchTree("Hello");
                System.out.println("\n\nNew BST: Strings\n");
                bst2.add("World");
                bst2.add("Binary");
                bst2.add("Search");
                bst2.add("Tree");
                bst2.add("Graphs");
                bst2.add("Traversal");
                bst2.add("Computers");
                bst2.add("Charleston");
                bst2.add("Delete");
                bst2.add("Add");
                bst2.add("Recursion");
                String inOrder = bst2.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                String postOrder = bst2.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                String preOrder = bst2.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                System.out.println("\nAfter deleting 'Hello' (root) ");
                
                bst2.delete("Hello");
                inOrder = bst2.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                postOrder = bst2.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                preOrder = bst2.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                bst2.delete("Traversal");
                System.out.println("\nAfter deleting 'Traversal'");
                inOrder = bst2.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                postOrder = bst2.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                preOrder = bst2.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                bst2.delete("Graphs");
                System.out.println("\nAfter deleting 'Graphs'");
                inOrder = bst2.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                postOrder = bst2.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                preOrder = bst2.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                bst2.delete("Search");
                System.out.println("\nAfter deleting 'Search'");
                inOrder = bst2.inOrder();
                System.out.println("inorder traversal: " + inOrder);
                
                postOrder = bst2.postOrder();
                System.out.println("postorder traversal: " + postOrder);
                
                preOrder = bst2.preOrder();
                System.out.println("preorder traversal: " + preOrder);
                
                bst2.makeEmpty();
                bst2.delete("Hello");//empty bst exception 
            }
            catch(NullBinaryNodeException e) {System.out.println("Null node "
                    + "exception!");}
            catch(DuplicateElementException e1){System.out.println("Duplicate"
                    + " element exception!");}
            catch(EmptyBSTException e2){System.out.println("Empty BST exception!");}
            
            try{BinarySearchTree testNullNode = new BinarySearchTree(10);
            testNullNode.delete(null);}
            catch(NullBinaryNodeException e){System.out.println("\nNew BST: Test "
                    + "delete null node: \nCan't delete null "
                    + "node!");}
            catch(EmptyBSTException e) {System.out.println("Empty BST Exception");}
            
            try
            {
                System.out.println("\nNew BST: Test inOrder traversal exception:\n");
                BinarySearchTree inOrderException = new BinarySearchTree(10);
                inOrderException.makeEmpty();
                System.out.println("InOrder: " + inOrderException.inOrder());
            }
            catch(NullBinaryNodeException e1){System.out.println("Null node!");}
            catch(EmptyBSTException e2){System.out.println("Can't inOrder "
                    + "traverse empty BST!");}
            
            try
            {
                System.out.println("\nNew BST: Test preOrder traversal exception:\n");
                BinarySearchTree preOrderException = new BinarySearchTree(10);
                preOrderException.makeEmpty();
                System.out.println("PreOrder: " + preOrderException.preOrder());
            }
            catch(NullBinaryNodeException e1){System.out.println("Null node!");}
            catch(EmptyBSTException e2){System.out.println("Can't preOrder "
                    + "traverse empty BST!");}
            
            try
            {
                System.out.println("\nNew BST: Test postOrder traversal exception:\n");
                BinarySearchTree postOrderException = new BinarySearchTree(10);
                postOrderException.makeEmpty();
                System.out.println("PostOrder: " + postOrderException.postOrder());
            }
            catch(NullBinaryNodeException e1){System.out.println("Null node!");}
            catch(EmptyBSTException e2){System.out.println("Can't postOrder "
                    + "traverse empty BST!");}
            /**
		 * ------------------------------------
		 * TODO: You put your test cases here
		 * 
		 * 
		 */

		
	} // end main method

        
} // end BinarySearchTree class
