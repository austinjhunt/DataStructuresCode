package csci230.hwk4;

import java.util.NoSuchElementException;

/**
 * A semi-constant time FIFO queue. The time complexity for 
 * the interface methods are:
 * ----------------------------------
 * 1) add: O(1) - not considering capacity resize operations
 * 2) remove: O(n) - not considering capacity resize operations
 * 3) peek: O(1)
 * 
 * Please note: the above time complexities do not factor in
 * capacity resize (grow and shrink) operations. Even though
 * capacity resize operations will occur, for this assignment 
 * you may assume the are negligible.
 * 
 * This data structure was discussed in class along with the 
 * operations, please review your notes.
 * 
 * @author CSCI 230: Data Structures and Algorithms Spring 2017
 * Austin Hunt
 * @since 12 Feb 2017 
 *
 * @param <AnyType>
 */
public class SemiConstantTimeQueue<AnyType extends Comparable> implements Queue<AnyType> {
	
	/**
	 * private instance variables
	 */
	private ArrayList<AnyType> list = new ArrayList<AnyType>();


	/**
	 * Inserts the specified element at the end of the queue in 
	 * constant time O(1)
	 * 
	 * @param t element to add
	 * @throws NullPointerException- if the specified element is null 
	 *                               (queue does not permit null elements)
	 */
	public void add(AnyType t) throws NullPointerException {
		if(t != null)
                    list.add((list.size()),t);//adding to end, constant time
                else
                    throw new NullPointerException();
		/**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your add solution must be a constant 
         * time O(1) operation (not considering capacity 
         * resize operations)
         * 
         */
		
		
		
	} // end add() method

	/**
	 * Retrieves and removes the head of the queue in
	 * linear time O(n).
	 * 
	 * Hint: shift operations will make this a linear time
	 * operation.
	 * 
	 * @return the head of the queue
	 * @throws NoSuchElementException - if this queue is empty
	 */
	public AnyType remove() throws NoSuchElementException {
		if (!list.isEmpty())
                    return list.remove(0); //will have to shift elements, linear
                else
                    throw new NoSuchElementException();
		/**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your push solution must be a linear 
         * time O(n) operation. See hint above.
         * 
         *
         */
		
		
		
	} // end remove() method

	/**
	 * Retrieves, but does not remove, the head of the queue, 
	 * or returns null if this queue is empty.
	 * 
	 * @return the head of this queue, or null if this queue is empty
	 */
	public AnyType peek() {
		if (!list.isEmpty())
                    return list.get(list.size()-1); //return last index position, constant
                else
                    return null;
		/**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your add solution must be a constant 
         * time O(1) operation 
         * 
         */
		
		
		
	} // end peek() method
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
            //7 test cases 10 points each
            //construct queue
            SemiConstantTimeQueue <Integer> queueTest = new SemiConstantTimeQueue<>();
            
            //1) peek on empty queue (return null)
            System.out.println("Peeking on Empty Queue: " + queueTest.peek());
            
            //2) remove on empty queue (exception)
            try{
                System.out.println("Removing from Empty Queue: " + queueTest.remove());
            }
            catch(NoSuchElementException e)
            {System.out.println("Exception: Can't remove elements from Empty Queue!");}
            
            //3) add null on empty queue 
            try{
                queueTest.add(null);
            }
            catch(NullPointerException e)
            {System.out.println("Exception: Can't add null element to (empty) queue!");}
            
            //4) add VALID on empty queue
            try
            {System.out.println("Adding 0");
            queueTest.add(0);}
            
            catch(NullPointerException e)
            {System.out.println("Exception: Can't add null element to (empty) queue!");}
            
            //5) add VALID on non-empty queue
            try
            {
                for (int i = 1; i < 16; i ++)
                {  System.out.println("Adding " + i);
                    queueTest.add(i);}
                
            }
            catch(NullPointerException e)
            {System.out.println("Can't add null item to queue!");}
            
            //6 add null on NON empty queue
            try{queueTest.add(null);}
            catch(NullPointerException e)
            {System.out.println("Can't add null item to (non-empty) queue!");}
                
            //7) peek on non-empty queue
            System.out.println("Peeking on non-empty queue: " + queueTest.peek());
            
            //8) remove from non-empty queue
            try{
            for (int i = 0; i < 17; i ++) 
                System.out.println("Removing " + queueTest.remove());
            }
            catch(NoSuchElementException e)
            {
                System.out.println("Can't remove elements from Empty Queue!");
            }
            
            
		/**
         * -------------------------------------------
         * TODO: You put your test cases here
         * 
         */
		
		

	} // end main() method

} // end ConstantTimeQueue class definition
