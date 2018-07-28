package csci230.hwk4;
 
/**
 * Singly LinkedList Data Structure
 * 
 * @author CSCI 230: Data Structures and Algorithms Spring 2017
 * Austin Hunt 
 * @since 25 January 2017
 * the SinglyLinkedList class was developed to demonstrate the relative nature of 
 * node positions in a singly linked list; each node's position in the list
 * is defined solely by it's connection to the adjacent nodes, and methods are defined
 * which use this property to manipulate nodes in a singly linked list data structure
 * (i.e. adding, removing, clearing, setting, getting)
 * @param <AnyType>
 */
public class SinglyLinkedList<AnyType extends Comparable> implements List<AnyType> {
    // instance variables
    private Node<AnyType> headNode = null;
    private int size = 0;

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param t
     */
    public void add(AnyType t) {
        addNode(new Node<AnyType>(t));
    } // end add() method

    /**
     * implementation for public add(AnyType t) method
     * 
     * @param t
     */
    private void addNode(Node<AnyType> t) {
        if (isEmpty()) headNode = t;
        else getNode(size-1).setNextNode(t);

        size++;
    } // end addNote() method

    /**
     * Inserts the specified element at the specified position in this list.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, AnyType t) throws IndexOutOfBoundsException {
    	addNode(index, new Node<AnyType>(t));
    } // end add() method
    
    /*
     * 
     * Implementation for public add(int index, AnyType t) method
     *
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    private void addNode(int index, Node<AnyType> t) throws IndexOutOfBoundsException {
    	//dont throw exception if index is between 0 and size
        if ((index >= 0) && ((index <= size)))
        {
            //if adding to an empty list, set headNode to passed node argument
            //and increment size
            if ((index == 0) && (size == 0))
            {
                headNode = t;
                size ++;
            }
            //if adding to first position of NONEMPTY list, set t's next node to 
            //headNode; ' headNode' now refers to t; increment size
            else if ((index == 0) && (!isEmpty()))
            {
                t.setNextNode(getNode(0));
                headNode = t;
                size++;
            }
            
            //if index is > 0 and < size, then set t's next node to appropriate node;
            //set previous node's next node to t; increment size
            else if ((index > 0) && (index < size))
            {  
                t.setNextNode(getNode(index-1).getNextNode());
                getNode(index-1).setNextNode(t);
                size ++;
            }
            //if index == size, then you're adding to end of list; set last node's
            //next node to t and increment size
            else if ((index > 0) && (index == size))
            {
                getNode(index-1).setNextNode(t);
                size++;
               
            }  
            
        }// end if statement 
        
        
        
        //only throw an exception if index is < 0 or > size
        else if ((index < 0) || (index > size))
            
            throw new IndexOutOfBoundsException();
        
    } // end addNode() method
 
    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, AnyType t) throws IndexOutOfBoundsException {
        setNode(index, new Node<AnyType>(t));
    } // end set() method
    
    /**
     * 
     * Implementation for public set(int index, AnyType t) method
     *
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    private void setNode(int index, Node<AnyType> t) throws IndexOutOfBoundsException {
    	//don't need to throw exception if index >= 0, !isEmpty() and index < size
        if (((index >= 0) && (index < size)) && (size != 0))
        {
            getNode(index).setData(t.getData());
        }
        else //throw an exception if index < 0 or index >= size
        {
            throw new IndexOutOfBoundsException();
        }
    	
    } // end setNode() method

 
    /**
     * Removes the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public AnyType remove(int index) throws IndexOutOfBoundsException {
    	return removeNode(index).getData();
    } // end remove() method
    
    /**
     *
     * Implementation for public remove(int index) method
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    private Node<AnyType> removeNode(int index) throws IndexOutOfBoundsException {
        //don't need to throw exception if !isEmpty() and index>= 0 and index<size
        if ((index < size) && (index >= 0) && (size != 0))
        {
            Node returnNode;
            /*if removing a node that is not the first or the last from a list 
            that has more than one element, set previous node's nextNode to 
            currentNode's next node; set currentNode's next node to null ('detaching'
            currentNode); decrement size and return currentNode*/
            if ((index !=  0) && (index < (size -1))&& (size > 1))
            {
                returnNode = getNode(index);
                getNode(index-1).setNextNode(getNode(index + 1));    
                returnNode.setNextNode(null);
                size --;
                return returnNode;
            }
            
            /*
            if removing last node from a list that has more than one node, 
            set second to last node's nextNode to null; decrement size and return
            currentNode
            */
            else if ((index != 0) && (index == size - 1) && (size > 1))
            {
                returnNode = getNode(index);
                getNode(index-1).setNextNode(null);
                size--;
                return returnNode;
            }
             /*if removing one of one node, it's the headNode, so set headNode to null 
            and return it
            */
            else if ((index == 0) && (size == 1))
            {
                returnNode = headNode;
                headNode = null;
                size --;
                return returnNode;
            }
            /*if removing first node from a list that has more than one node, 
            set headnode equal to the node right after the current headNode,
            set current node's nextNode to null; decrement size and return 
            current node
            */
            else if ((index == 0) && (size > 1));
            {
                returnNode = getNode(index);
                headNode = getNode(index+1);
                //getNode(index).setNextNode(null); can't use this statement because
                //getNode(index) now refers to the node that was next to it
                size --;
                //System.out.println("Prob here");
                return returnNode;
            }
           
        }
        
        else //only throw exception when index < 0, or index >= size, or size == 0
            throw new IndexOutOfBoundsException();
        
         
    } // end removeNode() method
 
    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public AnyType get(int index) throws IndexOutOfBoundsException {
    	return getNode(index).getData();
    } // end get() method
    
    /**
     *
     * Implementation for public get(int index) method
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    private Node<AnyType> getNode(int index) throws IndexOutOfBoundsException {
        //two variables: one representing node under current observation, other 
        //used to increment index
        int counter = 0; 
        Node currentNode = headNode;
        //don't need to throw an exception as long as index < size, >= 0 and 
        //!isEmpty()
       
        if ((index < size) && (size > 0) && (index >= 0))
        {
            //loop continues until counter reaches passed index argument
            while (counter != index)
            {
                counter ++;
                currentNode = currentNode.getNextNode();
                
            }
            //loop finishes only once counter reaches appropriate index,
            //so return the currentNode here
            return currentNode;
        }      
        
        //throw an exception if the above conditions are not met by the passed
        //index argument 
        else
        {
            throw new IndexOutOfBoundsException();
            
        }
        
    } // end getNode() method
 
    /**
     * Returns the number of elements in this list. 
     * 
     * @return
     */
    public int size() {
        return size;
    } // end size() method
 
    /**
     * Returns true if this list contains no elements.
     * 
     * @return
     */
    public Boolean isEmpty() {
        return (size == 0) ? true : false;
    } // end isEmpty() method
     
     
    /**
     * Removes all of the elements from this list.
     * 
     */
    public void clear() {
        /*start at back, take off last node by setting second to last node's 
        next node to null*/
        for (int i = size-1; i >= 1; i --)
        { getNode(i-1).setNextNode(null);}
        
        //one node remaining (headNode), so set it equal to null, and set size to 0
        headNode = null;
        size = 0;
         
    } // end clear method
     
     
    /**
     * 
     * @param args
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        
        //create a new instance of a singly linked list
        SinglyLinkedList <Integer> items = new SinglyLinkedList<>();
        
        //create 5 nodes to be added to 'items'
        Node nodeA = new Node('A');
        Node nodeB = new Node('B');
        Node nodeC = new Node('C');
        Node nodeD = new Node('D');
        Node nodeE = new Node ('E');
        
        //put all addNode() method calls in a try-catch block because the method 
        //may throw an exception, and this exception must be handled appropriately
        try
        {
            //Test cases for addNode(index,Node) method
            
            // test addNode to empty linkedlist */
            System.out.println("\"ITEMS\" LINKED LIST: ");
            items.addNode(0,nodeA);
            System.out.println("nodeA added to position 0");
            
            //test addNode to last position of linkedlist
            items.addNode(1,nodeB);
            System.out.println("nodeB added to position 1");
            
            //test addNode to beginning of nonempty linkedlist 
            items.addNode(0,nodeC);
            System.out.println("nodeC added to position 0");
            
            //test addNode to already-filled middle position of linkedlist
            items.addNode(1,nodeD);
            System.out.println("nodeD added to position 1");
            
            //test addNode to last position of filled list
            items.addNode(4,nodeE);
            System.out.println("nodeE added to last position (4)");
            
            //use getNode method (combined with the toString method in node class)
            //to print out and label each node
            for (int i = 0; i < items.size(); i ++)
            { System.out.print("Node " + i + ": " + items.getNode(i) + "\n"); }
            
            
            //throw exception with addNode method (index must be > size or < 0)
            System.out.println("Attempting to add node to position 6 (greater "
                    + "than size (" + items.size() + "))");
            items.addNode(6, nodeA); 
        }
        
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        
        
        //test cases for getNode(index) method; using already-built linked list 
        //"items"
        
        //test getNode(0)
        try
        {
        System.out.println("\n\nTesting getNode method on \"items\" linked list: "
                + "\n\nPosition 0:" + items.getNode(0));
        
        //test getNode(1)
        
        System.out.println("Position 1: " + items.getNode(1));
        
        //test getNode((last position in linkedList))
        System.out.println("Last position: " + items.getNode(items.size()-1));
        
        
        //use getNode method to print out entire list sequentially
        System.out.println("Full \"items\" list: ");
        for (int i = 0; i < items.size(); i ++)
        { System.out.print("Node " + i + ": " + items.getNode(i) + "\n");  }
        
        }//end of try block
        
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index Out of Bounds!");
        }
        
        //test exception handling with getNode method
        
        try
        { 
            System.out.println("\nAttempting getNode on index == size");
            items.getNode(items.size());
        }
        
        catch(IndexOutOfBoundsException e)
        {
             System.out.println("Index is out of bounds!");
        }
        
        try
        { 
            System.out.println("\nAttempting getNode on index > size");
            items.getNode(items.size() + 1);
        }
        catch(IndexOutOfBoundsException e)
        {
             System.out.println("Index is out of bounds!");
        }
        
        try
        { 
            System.out.println("\nAttempting getNode on index < 0");
            items.getNode(-1);
        }
        catch(IndexOutOfBoundsException e)
        {
             System.out.println("Index is out of bounds!");
        }
        try
        { 
            System.out.println("\nAttempting getNode on empty list:");
            SinglyLinkedList<String> strings = new SinglyLinkedList<>();
            strings.getNode(0);
        }
        catch(IndexOutOfBoundsException e)
        {
             System.out.println("Index is out of bounds! ");
        }
        for (int i = 0; i < items.size(); i ++)
        { System.out.print("Node " + i + ": " + items.getNode(i) + "\n"); }
        
        
        //test cases for setNode
        try
        {
        //test setNode(0,node)
        System.out.println("\nSetting position 0 of \"items\" to node E (whose"
                + " data = 'E'; node E currently in position 4):");
        items.setNode(0, nodeE);
        
        for (int i = 0; i < items.size(); i ++)
        { System.out.print("Node " + i + ": " + items.getNode(i) + "\n"); }
        
        //test setNode(lastNode,node)
        
        //System.out.println("(Note: Node C's data is 'E' after above set method)");
        System.out.println("\nSetting position (size-1) of \"items\" to node C "
                + "(whose data is NOW 'E' (node c is in position 0) ");
        
        items.setNode((items.size()-1),nodeC);
        //display list
        for (int i = 0; i < items.size(); i ++)
        {System.out.print("Node " + i + ": " + items.getNode(i) + "\n"); }
        
        System.out.println("\nSetting position 2 of \"items\" to node D (whose data is"
                + "'D')");
        items.setNode(2,nodeD);
        for (int i = 0; i < items.size(); i ++)
        {System.out.print("Node " + i + ": " + items.getNode(i) + "\n"); }
        
        Node nodeR = new Node('R');
        System.out.println("Setting position 3 to new nodeR (whose data is 'R')");
        items.setNode(3,nodeR);
        for (int i = 0; i < items.size(); i ++)
        {System.out.print("Node " + i + ": " + items.getNode(i) + "\n"); }
        }
        
        catch(IndexOutOfBoundsException e)
        { System.out.println("Index out of bounds!");}
        
        System.out.println("\n\nNEW LIST 'NUMS'");
        
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        SinglyLinkedList<Integer> nums = new SinglyLinkedList<>();
        nums.addNode(0,node0);
        nums.addNode(1,node1);
        nums.addNode(2,node2);
        nums.addNode(3,node3);
        nums.addNode(4,node4);
        
        for (int i = 0; i < nums.size(); i ++)
        {System.out.print("Node " + i + ": " + nums.getNode(i) + "\n"); }
        try
        {
        System.out.println("Attempting to setNode (index = size): ");
        nums.setNode(nums.size(), node0);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        
        try
        {
        System.out.println("Attempting to setNode (index > size): ");
        nums.setNode(nums.size(), node0);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        try
        {
        System.out.println("Attempting to setNode (index < 0): ");
        nums.setNode(-1, node0);
        
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        
        try
        {
        //throw an exception with setNode (empty list)
        SinglyLinkedList <Integer> list = new SinglyLinkedList<>();
        System.out.println("Attempting to setNode (size = 0): ");
        list.setNode(0, node0);
        
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        
        
        //testing removeNode method
        
        System.out.println("\n\nTesting removeNode(index) method: ") ;
        
        System.out.println("\n\nInitial \"NUMS\" list:");
        
        for (int i = 0; i < nums.size(); i ++ )
        { System.out.println("Node " + i + ": " + nums.getNode(i));}

        
        try
        {
            //test remove first node
        System.out.println("Removing node0: ");
        nums.removeNode(0);
        for (int i = 0; i < nums.size(); i ++ )
        { System.out.println("Node " + i + ": " + nums.getNode(i));}
        
        
        //replace node that was removed, try removing last node (index = size-1)
        System.out.println("Replacing node0 and removing node4"
                + " (with index = size -1)");
        nums.addNode(0,node0);
        nums.removeNode(nums.size()-1);
        for (int i = 0; i < nums.size(); i ++ )
        { System.out.println("Node " + i + ": " + nums.getNode(i));}
        
        
        
        
        //remove node from the middle of list
        System.out.println("Replacing node4:");
        nums.addNode(4, node4);
        for (int i = 0; i < nums.size(); i ++ )
        { System.out.println("Node " + i + ": " + nums.getNode(i));}
        
        System.out.println("Removing node2:");
        System.out.println("Size before removal: " + nums.size());
        nums.removeNode(2);
        
        
        for (int i = 0; i < nums.size(); i ++ )
        { System.out.println("Node " + i + ": " + nums.getNode(i));}
        System.out.println("Size after removing node2: " + nums.size());
        System.out.println("getNode(3): " + nums.getNode(3));
        }//end of try block
        
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
             
        //throw exceptions with removeNode method
        
        try
        {
            System.out.println("Attempting to remove node from index = size:");
            nums.removeNode(nums.size());
            
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        
        try
        {
            System.out.println("Attempting to remove node from index > size:");
            nums.removeNode(nums.size()+1);
            
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        
        try
        {
            System.out.println("Attempting to remove node from index < 0:");
            nums.removeNode(-1);
            
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        
        SinglyLinkedList<Integer> nums1 = new SinglyLinkedList<>();
        
        try
        {
            System.out.println("Attempting to remove node from empty linked list:");
            nums1.removeNode(0);
            
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Index is out of bounds!");
        }
        
        //testing clear() method
        System.out.println("Testing clear() method on \"items\" linked list. ") ;
        
        System.out.println("\"NUMS\" linked list CURRENTLY (nums.isEmpty() = " + 
                nums.isEmpty());
        nums.addNode(2, node2);
        //display initial list 
        for (int i = 0; i < nums.size(); i ++)
        {System.out.print("Node " + i + ": " + nums.getNode(i) + "\n"); }
        
        //does not throw exception regardless of whether list is empty 
        nums.clear();
        System.out.println("\"NUMS\" linked list after clearing: ");
        try
        {
            //display list after clearing
        for (int i = 0; i < nums.size(); i ++)
        { System.out.print("Node " + i + ": " + nums.getNode(i) + "\n");}
        
        //tell user whether list is actually emptied by clear method
        System.out.println("nums.isEmpty() == " +nums.isEmpty());
        System.out.println("Using clear() method on already-empty \"nums\" linked list:");
        items.clear();
        System.out.println("No exception thrown!");
                
        }
       catch(IndexOutOfBoundsException e)
       {
           System.out.println("Index out of bounds!");
        }
        
         
    } // end main() method
  
} // end SinglyLinkedList class definition
