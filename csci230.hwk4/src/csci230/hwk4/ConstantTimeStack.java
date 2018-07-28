package csci230.hwk4;

import java.util.EmptyStackException;

/**
 * A LIFO stack that has constant time complexity O(1) for
 * all three stack interface methods (i.e., push, pop, and 
 * peek).
 * 
 * This data structure was discussed in class along with the 
 * operations, please review your notes.
 * 
 * @author CSCI 230: Data Structures and Algorithms Spring 2017
 *Austin Hunt
 * @since 12 Feb 2017 
 * @param <AnyType>
 */
public class ConstantTimeStack<AnyType extends Comparable/*<AnyType>*/> implements Stack<AnyType>{
	
	/**
	 * private instance variables
	 */
	private SinglyLinkedList<AnyType> list = new SinglyLinkedList<AnyType>();

	/**
	 * Pushes an item onto the top of this stack in constant 
	 * time O(1)
	 * 
	 * @param t the item to be pushed onto this stack.
	 */
	public void push(AnyType t) {
		list.add(0,t); //using get(0) every time, constant
                //System.out.print(t + "\n");
		/**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your push solution must be a constant 
         * time O(1) operation
         * 
         */
		
		
		
	} // end push() method

	/**
	 * Removes the object at the top of this stack and return the 
	 * item in constant time O(1)
	 * .
	 * @return The item at the top of this stack
	 * @throws EmptyStackException - if this stack is empty.
	 */
	public AnyType pop() throws EmptyStackException {
		
                if (!list.isEmpty())
                    return list.remove(0);
                else
                    throw new EmptyStackException();
		/**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your pop solution must be a constant 
         * time O(1) operation
         * 
         */
	} // end pop() method

	/**
	 * Looks at the item at the top of this stack without removing it 
	 * from the stack in constant time O(1)
	 * 
	 * @return the item at the top of this stack
	 * @throws EmptyStackException  - if this stack is empty.
	 */
        
	public AnyType peek() throws EmptyStackException {
		
                if (!list.isEmpty())
                        return list.get(0);
                else
                    throw new EmptyStackException();
		/**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your peek solution must be a constant 
         * time O(1) operation
         * 
         */
		
	} // end peek() method
	
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
		
            ConstantTimeStack<Integer> stackTest = new ConstantTimeStack<>();
            
            //test 7 cases: 10 points each
            
            //1) peek on empty stack 
            try
            {
                System.out.println("Peeking on empty stack: " + stackTest.peek());
            }
            catch(EmptyStackException e)
            {
                System.out.println("Exception: Can't peek on an empty stack!");
            }
            
            //2)pop on empty stack
            try
            {
                System.out.println("Popping on empty stack: " + stackTest.pop());
                
            }
            catch(EmptyStackException e) 
            {
                System.out.println("Exception: Can't pop an empty stack!");
            }
            System.out.println("Stack is currently empty! ");
            
            //3) push on empty stack
           
            System.out.println("Pushing 0 onto empty stack: "); 
            stackTest.push(0);
            
            System.out.println("Stack is now non-empty!");
            //4)push on non-empty stack
            for (int i = 1; i < 10; i ++)
            { System.out.println("Push: " + i );
                stackTest.push(i);}
            
            //
            
            //5) peek on non-empty stack
            try
            {
                System.out.println("Peeking on nonempty stack: " + stackTest.peek());
            }
            catch(EmptyStackException e) 
            {System.out.println("Exception: Can't peek on empty stack! (fix code)");}
            
            //6) pop on non-empty stack with size > 1
            try
            {
                System.out.println("Popping on non empty stack (size>1): ");
                for (int i = 0; i < 9; i ++)
                {   
                    System.out.println("Pop: " +stackTest.pop());
                }
                
            //7) pop on non-empty stack with size = 1
            System.out.println("Popping with size = 1: " + stackTest.pop());
            }
            catch(EmptyStackException ee)
            {System.out.println("Exception: Can't pop on empty stack! (fix code)");}
            
            
            
            
            System.out.println("\nNew ConstantTimeStack: Strings\n");
            ConstantTimeStack <String> stringStack = new ConstantTimeStack<>();
            
            try{
            stringStack.peek();
            stringStack.pop();
            }
            catch(EmptyStackException e)
            {System.out.println("Stack is empty, can't peek/pop!");}
            
            stringStack.push("estás?");
            stringStack.push("cómo");
            stringStack.push("Hola,");
            
            for (int i = 0; i < 3; i ++) 
                System.out.println(stringStack.pop());
            
            
            /**
         * -------------------------------------------
         * TODO: You put your test cases here
         * 
         */
		
		
		
		
	} // end main method

} // end ConstantTimeStack class definition
