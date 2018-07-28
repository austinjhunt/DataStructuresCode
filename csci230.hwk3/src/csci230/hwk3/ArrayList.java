package csci230.hwk4;

/**
 * ArrayList Data Structure
 * 
 * @author CSCI 230: Data Structures and Algorithms Spring 2017
 * Austin Hunt
 * @since 4 Feb 2017
 * This class demonstrates the dynamic nature of the ArrayList data structure
 * 
 * @param <AnyType>
 */
public class ArrayList<AnyType extends Comparable> implements List<AnyType> {
     
    // instance variables
    private Object[] array;
    private int size = 0;
    private final static int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    
    /**
     * Constructs an empty list with an initial capacity
     * (for this assignment initial capacity is 10 - see 
     * constant instance variable)
     * 
     */
    public ArrayList() {
    	
    	array = new Object[ INITIAL_CAPACITY ];
    	
    } // end constructor
 
    /**
     * Appends the specified element to the end of this list.
     * 
     * @param t
     */
    public void add( AnyType t) {
         
    	if ( size >= capacity )
    		grow();
    	
    	array[size]=t;
        size++;
         
    } // end add() method
 
    /**
     * Inserts the specified element at the specified position in this list.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, AnyType t) throws IndexOutOfBoundsException {
         
        if ((index >= 0)&&(index <= size))
        {
            if (size >= capacity)
            {  
                grow();   
            }
            
            if (index == size)
            {
                array[index] = t;
                size ++;
            }
            
            else if (index < size)
            {
                for (int i = size; i > index; i --)
                {array[i] = array[i-1];}
                array[index] = t;
                size ++;
            } 
        }
        else
        { 
            throw new IndexOutOfBoundsException("Can't add item to index < 0 or "
                + "index > size"); 
        }   /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
    	
    	
    	
    } // end add() method
 
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, AnyType t) throws IndexOutOfBoundsException {
       
        if ((index < size) && (index >= 0))
            array[index] = t; 
        else 
            throw new IndexOutOfBoundsException("Can't 'set' index in arraylist if "
                    + "index >= size or if index < 0 or if list is currently empty.");
         
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
    } // end set() method
 
    /**
     * Removes the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    @SuppressWarnings("unchecked")
	public AnyType remove( int index ) throws IndexOutOfBoundsException {
         
        if ((index >= 0) && (index < size))
        {     
            AnyType holder = (AnyType) array[index];
            if (index == size - 1) 
            {
                array[index] = null;
                size --;
            }
            
            else if (index < size - 1)
            {
                for (int i = index; i < size-1; i ++)
                    array[i] = array[i+1];
                array[size-1] = null;
                size --; 
            }
            
            if (size == (capacity/2))
                shrink();
            
            return holder; 
            
        }
        else 
            throw new IndexOutOfBoundsException("Can't remove if index < 0, index >= "
                    + "or if arrayList already empty");
            /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Requirement - you must use loops (i.e. may not use
         * System.arraycopy, or any other array copy operation 
         * available in the Java API) to perform left or right
         * shift operations
         * 
         */
         
    	
         
    } // end remove() method
 
    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    @SuppressWarnings("unchecked")
	public AnyType get(int index) throws IndexOutOfBoundsException {
         
        if ((index < size) && (index >= 0))
            return (AnyType) array[index];
        
        else
            throw new IndexOutOfBoundsException("Can't 'get' item with index < 0 "
                    + "or index >= size!");
            /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
         
    	
         
         
    } // end get() method
 
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
         
        return ( size == 0 );
         
    } // end isEmpty() method
     
     
    /**
     * Removes all of the elements from this list.
     * and the initial capacity is set back to 10
     * 
     */
    public void clear() {
         
        for (int i = 0; i < size; i ++) 
        {
            array[i] = null;
        }
        capacity = 10;
        size = 0; 
        
        
        //System.out.println("Size is: " + size + " and cap is: " +capacity);
            /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         */
    	
         
         
    } // end clear method
    
    /**
     * Double the capacity of the array
     * 
     */
    private void grow() {
        //System.out.println("capacity before: " + capacity);
        capacity = capacity * 2; 
    	Object[] arrayCopy = new Object[capacity];
        
        for (int i = 0; i < size; i ++) 
        {
            arrayCopy[i] = array[i];
        }
       // System.out.println("Capacity after: " + capacity);
       
        array = arrayCopy;
        //System.out.println("New capacity! Capacity = " + capacity*2);
                
        
            
    	/**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Requirement - you must use loops (i.e. may not use
         * System.arraycopy, or any other array copy operation 
         * available in the Java API)
         * 
         */
    	
    	
    	
    } // end grow() method
    
    
    /**
     * Half the capacity of the array
     * 
     */
    private void shrink() {
       // System.out.println("\n\nCapacity before: " + capacity);
        //System.out.println("Size = " + size);
        capacity = capacity / 2; 
    	Object [] copyArray = new Object[capacity];
        for (int i = 0; i < size; i ++) 
        {
            copyArray[i] = array[i];
        }
        
        array = copyArray; 
        //System.out.println("\nCapacity after: " + capacity);
        
    	/**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Requirement - you must use loops (i.e. may not use
         * System.arraycopy, or any other array copy operation 
         * available in the Java API)
         * 
         */
    	
    	
    } // end shrink() method
     
     
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {
         
        ArrayList<Integer> newList = new ArrayList<>();
        
        
        //use loop to develop list
        for (int i = 0; i < 5; i ++)
        {newList.add(i,i);  }
        
        //display list 
        for (int i = 0; i < newList.size(); i ++)
            System.out.print(newList.get(i) + "\t");
        System.out.println("\n\nTesting add(index,t) method on newList: \n");
        try
            //test add method
        {
            //add to index = size
            newList.add(5,5);
            
            //add to beginning of list, index = 0
            newList.add(0,12);
            
            //add to middle of list, index = 3
            newList.add(3, 33);
            
            //add two more to reach capacity
            newList.add(8,6);
            
            //add to list with size = capacity - 1
            newList.add(9,7);
            
            //add to list with size = capacity 
            newList.add(10,8);
            
            //add to list with newly doubled capacity
            
            newList.add(11,9);
            
            for (int i = 0; i < newList.size(); i ++) 
            System.out.print(newList.get(i) + "\t");
                    
        System.out.println("\nSize = " + newList.size() + "\n");
        }//end try block
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Can't add: Index out of bounds");
        }
        
        try
        {
            //add to list with index < 0 (throw exception)
            newList.add(-1,-1);
            
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Can't add: Index < 0!");
        }
        
        try
        {
            //add to list with index > size (throw exception)
            newList.add(newList.size()+1,(newList.size()+1));
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Can't add: Index > size!");
        }
        System.out.println("\nTesting set(index,t) method on newList: \n");
        //test set method; use set method set up list as integers 0 through 11
        try 
        {
            for (int i = 0; i < newList.size(); i ++)
                newList.set(i,i);
            System.out.println();
            //display list
            for (int i = 0; i < newList.size(); i ++) 
                System.out.print( newList.get(i) + "\t");
            
            //set index = 0
            newList.set(0,100);
            
            //set index = size - 1
            newList.set(11,300);
            
            //0 < set index < size 
            newList.set(4,25);
            
            System.out.println("\n\nAfter setting again (newList): \n");
            //display list
            for (int i = 0; i < newList.size(); i ++) 
                System.out.print(newList.get(i) + "\t");
            
            //set index = size, throw exception
            newList.set(12, 45);
            
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("\n\nCan't set index >= size!");
        }
        
        try{newList.set(-1,-1); }
        catch(IndexOutOfBoundsException e)
        {System.out.println("Can't set index < 0!"); }
        
        //create new empty list to ensure set method throws exception
        System.out.println("\nNew Empty List list2: \n");
        ArrayList<Integer> list2 = new ArrayList<>();
        try
        {
            list2.set(0,1);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Can't set index on an empty list!");
        }
        try
        {
         //set index = size when size = capacity 
        for (int i = 0; i < 10; i ++) 
            list2.add(i,i);
        
        System.out.println("Appending list2 until list2.size() = capacity: \n");
        //display list
        for (int i = 0; i < list2.size(); i ++) 
            System.out.print(list2.get(i) + "\t");
        
        //set last element when size = capacity
        list2.set(9,10);
        
        System.out.println("\n\bCAN set last element when size = capacity!\n");
        
        //display list
        for (int i = 0; i < list2.size(); i ++)
            System.out.print( list2.get(i) + "\t");
        
        System.out.println();
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Can't set when size = capacity! Fix code");
        }
        
        System.out.println("\nTesting remove(index) method on newList with size = 13: \n");
        
        for (int i = 0; i < newList.size(); i ++)
            newList.set(i,i);
        newList.add(12,12);
        for (int i = 0; i < newList.size(); i ++)
            System.out.print(newList.get(i) + "\t"); 
        
        try
        { //remove from index = 0
            newList.remove(0); }
        catch(IndexOutOfBoundsException e) 
        {System.out.println("\nCan't remove from index = 0!") ; }
        
        try
        { //remove from index = size - 1 
            newList.remove(newList.size()-1); }
        catch(IndexOutOfBoundsException e) 
        {System.out.println("\nCan't remove from index = (size - 1)!") ; }
        
        try
        { //remove from 0<index<size
            newList.remove(8); }
        catch(IndexOutOfBoundsException e) 
        {System.out.println("\nCan't remove from 0<index<size!") ; }
        System.out.println("\nAfter removing from newList:\n");
        for (int i = 0; i < newList.size(); i ++)
            System.out.print(newList.get(i) + "\t");
        
        //throw exceptions
        //remove at index = size 
        try{newList.remove(newList.size());}
        catch(IndexOutOfBoundsException e) {System.out.println("\nCan't remove from"
                + " index = size");}
        //remove at index > size 
        try{newList.remove(newList.size()+1);}
        catch(IndexOutOfBoundsException e) {System.out.println("Can't remove from"
                + " index > size");}
        //remove at index < 0 
        try{newList.remove(-1);}
        catch(IndexOutOfBoundsException e) {System.out.println("Can't remove from"
                + " index < 0");}
        
        //test clear method
        System.out.println("\nCurrent newList:");
        for (int i = 0; i < newList.size(); i ++)
        {newList.set(i,i);}
        //display
        for (int i = 0; i < newList.size(); i ++)
        {System.out.print(newList.get(i) + "\t");}
        System.out.println("\n\nTesting clear method:");
        newList.clear();
        //display
        for (int i = 0; i < newList.size(); i ++)
        {System.out.print(newList.get(i) + "\t");}
        System.out.println("\nisEmpty() == " + newList.isEmpty());
        System.out.println("\nTesting clear method on already-clear list:");
        newList.clear();
        System.out.println("\nisEmpty() == " + newList.isEmpty());
        
        //try to remove from clear list
        try{newList.remove(0);}
        catch(IndexOutOfBoundsException e) {System.out.println("Can't remove(index) "
                + " from empty list!");}
        
        //try get method on empty list
        try{newList.get(0);}
        catch(IndexOutOfBoundsException e) {System.out.println("Can't use get(index)"
                + " method on empty list!");}
        //try set method on empty list
        try{newList.set(0,0);}
        catch(IndexOutOfBoundsException e) {System.out.println("Can't use set(index, t)"
                + " method on empty list!");}
        
        //test getter method; build list first
        System.out.println("\nRebuilding newList to test get(index) method: \n");
        
        System.out.println("Newlist size = " + newList.size());
        for (int i = 0; i < 10; i ++)
        {   newList.add(i,i);}
        for (int i = 0; i < newList.size(); i ++)
            System.out.print(newList.get(i) + "\t");
        System.out.println("\n");
        System.out.print("\nNew size = " + newList.size());
        try
        {
            //get index = 0
            System.out.println("\n\nnewList.get(0) = " + newList.get(0));
            //get index = size-1 
            System.out.println("newList.get(newList.size()-1) = " + 
                    newList.get(newList.size()-1));
            //get 0 < index < size
            System.out.println("newList.get(4) = " + newList.get(4));
        }
        catch(IndexOutOfBoundsException e)
        { System.out.println("Can't get index = 0 or index = size - 1"); }
        
        //get index = size 
        try{System.out.println("newList.get(newList.size()) = " + 
                    newList.get(newList.size()));}
        catch(IndexOutOfBoundsException e)
        {System.out.println("Can't get index = size; out of bounds!");}
        
        //get index < 0
        try{System.out.println("newList.get(-1) = " + newList.get(-1));}
        catch(IndexOutOfBoundsException e)
        { System.out.println("Can't get index < 0; out of bounds!");}
            
        newList.clear();
        
        //demonstrate grow()
        System.out.println("Demonstrating dynamic growth of arraylist (doubling capacity):");
        for (int i = 0; i < 80; i ++) 
        {  
            newList.add(i,i);
            System.out.println(newList.get(i));
        }
        System.out.println("\nnewList size = " + newList.size());
        
        System.out.println("\nRemoval loop, demonstrating dynamic shrinking of arraylist "
                + "(halving capacity)");
        for(int i = 0; i < 80; i ++) 
            newList.remove(0);
        for (int i = 0; i < newList.size(); i ++)
            System.out.print(newList.get(i)+ "\t");
        
        System.out.println("\nAfter removal loop: ");
        System.out.println("newList.isEmpty() = " + newList.isEmpty());
       
        
        
        
        //HERE
        ArrayList<Integer> finalList = new ArrayList<>();
        for (int i = 0; i < 11 ; i ++)
            finalList.add(i,i);
        
        for(int i = 0; i < finalList.size(); i ++)
            System.out.print(finalList.get(i) + "\t");
        
        System.out.println(finalList.get(0));
        System.out.println(finalList.get(finalList.size()-1));
        System.out.println(finalList.get(3));
        System.out.println(finalList.remove(4));
        System.out.println(finalList.get(4));
        /**
         * -------------------------------------------
         * TODO: Put your test cases here
         * 
         */
                
         
    } // end main() method
  
} // end ArrayList class definition
