package csci230.hwk7;

/**
 * Utilities class that will sort the elements of a list.
 *
 * The sorting algorithms supported in this class are:
 *	1. selection sort (increasing order)
 *	2. bubble sort (increasing order)
 *	3. insertion sort (increasing order)
 *  4. heap sort (increasing and decreasing order)
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2016
 *
 */
public class Utils {
	
	/**
	 * 
	 */
	private Utils() {
		
	} // end private constructor
	
	/**
	 * 
	 * @param list
	 */
	public static <AnyType extends Comparable> void selectionSort( List<AnyType> list ) throws IndexOutOfBoundsException {
		
		int min = 0;
		
		for ( int i=0; i< list.size()-1; i++ ) {
			
			min = i;
			
			for ( int j=i+1; j<list.size(); j++ ) {
				
				if ( list.get( j ).compareTo( list.get( min ) ) < 0 ) {
					
					min = j;
					
				}
				
			}
			
			list.swap( i, min );
			
		}
		
	} // end selectionSort() method
	
	/**
	 * 
	 * @param list
	 */
	public static <AnyType extends Comparable> void bubbleSort( List<AnyType> list ) throws IndexOutOfBoundsException {
		
		for ( int i=0; i<list.size()-1; i++ ) {
			
			for ( int j=0; j<list.size()-1-i; j++ ) {
				
				if (  list.get( j+1 ).compareTo( list.get( j ) ) < 0 ) {
					
					list.swap( j, j+1 );
					
				}
				
			}
			
		}
		
	} // end bubbleSort() method
	/* */
        
        public static <AnyType extends Comparable> void fixHeapDownMax(List<AnyType> list) 
        {
            list.add(0, list.get(list.size()-1)); // hole at root
            list.remove(list.size()-1); 
            
            int max; 
            for (int i = 0 ; i <= list.size()/2; i ++) 
          {   
              int left = 2 * i + 1; 
              int right = 2 * i + 2; 
              max = i; 
              if (left < list.size() && list.get(left) != null)
              {    
                  if (list.get(left).compareTo(list.get(i)) > 0)
                      max = left; 
                  if (right < list.size() && list.get(right) != null) 
                  {
                      if (list.get(right).compareTo(list.get(left)) >= 0 ) // if right > left, right is max
                          max = right;
                      else
                          max = left; 
                  }
                  
              }
              if (list.get(i).compareTo(list.get(max)) < 0)
                  list.swap(i, max);
            
          } 
            
            
          
        }
        public static <AnyType extends Comparable> void fixHeapDownMin(List<AnyType> list)
        {
            //System.out.println(list.get(0)); //what is the first element? this should be 
            list.add(0, list.get(list.size()-1)); // hole at root
            list.remove(list.size()-1); //move last element into that hole at root 
           
            //sink that element down until min heap property is satisfied
        
            for (int i = 0; i <= (list.size()/2); i ++) 
            {
                int left = 2 * i + 1; 
                int right = 2 * i + 2; 
                int min = i; 
                
                if (left < list.size())
                { 
                    if (list.get(left).compareTo(list.get(i)) < 0) // left = min
                            min = left; 
                        if (right < list.size() )
                        {
                            if (list.get(right).compareTo(list.get(left)) <= 0)
                                min = right;
                            else 
                                min = left; 
                        }
                }
                if (list.get(min).compareTo(list.get(i)) < 0)
                    list.swap(min,i); 
            }
       
        
           
        }
            
	/*
	 * 
	 * @param list
	 */
	public static <AnyType extends Comparable> void insertionSort( List<AnyType> list ) throws IndexOutOfBoundsException {
		
		AnyType v = null;
		
		int j = 0;
		
		for ( int i=1; i< list.size(); i++ ) {
			
			v = list.get( i );
			
			j = i - 1;
			
			while( j >= 0 && ( list.get( j ).compareTo( v ) > 0 ) ) {
				
				list.set( j+1, list.get( j ) );
				j--;
				
			}
			
			list.set( j+1, v );
			
		}
		
		
	} // end insertionSort() method
	 /* 
	 * @param list
	 * @throws IndexOutOfBoundsException
	 */
       
	public static <AnyType extends Comparable> void maxHeapify( List<AnyType> list ) throws IndexOutOfBoundsException {
          //start at bottom-right most subtree (whose root's index is size/2); if root is smaller than 
          //min of its two children, drop down (aka swap with min child) ; 
          int max; 
          for (int a = 0; a < list.size(); a ++)
          {
          for (int i = list.size() / 2; i >= 0; i --) 
          {    int left = 2 * i + 1; 
              int right = 2 * i + 2; 
              max = i; 
              if (left < list.size())
              {    
                  if (list.get(left).compareTo(list.get(i)) > 0)
                      max = left; 
                  if (right < list.size())
                  {
                      if (list.get(right).compareTo(list.get(left)) >= 0 ) // if right > left, right is max
                          max = right;
                      else
                          max = left; 
                  }
                  
                  
              }
              if (list.get(i).compareTo(list.get(max)) < 0)
                  list.swap(i, max);
              
             // System.out.println("list : " + list); 
          } 
          
          /*
         int max;
         for (int i = 0; i <= list.size()/2; i ++) 
         {
             int left = 2 * i + 1; 
              int right = 2 * i + 2; 
              max = i; 
              if (left < list.size())
              {
                   if (list.get(left).compareTo(list.get(i)) > 0)
                      max = left; 
                  if (right < list.size())
                  {
                      if (list.get(right).compareTo(list.get(left)) >= 0 ) // if right > left, right is max
                          max = right;
                      else
                          max = left; 
                  }
                  
                  
              }
              if (list.get(i).compareTo(list.get(max)) < 0)
                  list.swap(i, max);
         }*/
        }
          
            /*
		 * TODO:
		 * ---------------------------------------------
		 * Implement max heapify algorithm as
		 * described in class and in supplemental 
		 * course textbook. The pseudo-code for this 
		 * algorithm can also be found in 
		 * the content section on OAKS.
		 * */
		 
        //end maxHeapify() method
          
	
	/**
	 * 
	 * @param list
	 * @throws IndexOutOfBoundsException
	 */
        }
        
        
	public static <AnyType extends Comparable> void minHeapify( List<AnyType> list ) throws IndexOutOfBoundsException {
            
            {
                int min; 
                for (int a = 0; a < list.size(); a ++) 
                {
                for (int i = list.size()/2 ; i >= 0 ; i --) 
                {   
                    int left = 2 * i + 1;
                    int right = 2 * i + 2; 
                    min = i; 
                    if (left < list.size())
                    {
                        if (list.get(left).compareTo(list.get(i)) < 0) // left = min
                            min = left; 
                        if (right < list.size())
                        {
                            if (list.get(right).compareTo(list.get(left)) <= 0)
                                min = right;
                            else 
                                min = left; 
                        }
                    }
                    if (list.get(min).compareTo(list.get(i)) < 0)
                        list.swap(min,i); 
                    
                   // System.out.println("list: " + list); 
                }
                
            }//interior forloop 
            }//exterior for loop 
            
                
           
                
		/*
		 * TODO:
		 * ---------------------------------------------
		 * Implement min heapify algorithm as
		 * described in class and in the supplemental
		 * course textbook. The pseudo-code for this 
		 * algorithm can also be found in 
		 * the content section on OAKS.
		 * 
		 */
		
		



		
	} // end minHeapify() method
	
	/**
	 * 
	 * @param sorted_list
	 * @param list
	 * @param increasing
	 * @throws IndexOutOfBoundsException
	 */
	public static <AnyType extends Comparable> void heapSort( List<AnyType> sorted_list, List<AnyType> list, Boolean increasing ) throws IndexOutOfBoundsException {
		int size = list.size();
                //copy elements of list into a new list so you don't lose original list
                //this loop is O(n) 
                AnyType max,min;
                
                List<AnyType> newList = list; //(O(n)) loop
            
                
                if (!increasing) //decreasing order, pull max root n times
                {
                    System.out.println("\nDecreasing Order: ");
                    Utils.maxHeapify(list); //maxHeapify list so max is at root ; this is O(nlogn)
                    
                    while (list.size() > 0 )
                    {  
                        max = list.remove(0);
                        sorted_list.add(max); //add root of heap structure (max) to sorted list
                        //successively, then fixHeapDown
                        if (list.size() > 1)
                            Utils.fixHeapDownMax(list);// logn
                        
                    }//end for loop, overall O(2n*logn) = 0(nlogn) 
                    
                }
                
                else if (increasing) // increasing order, pull min root n times
                {
                    System.out.println("\nIncreasing Order: ");
                    Utils.minHeapify(list); //nlog n
                    for(int i = 0; i < size; i ++)
                    {
                        min = list.remove(0); //remove first (MIN) element, place in sorted_list
                        sorted_list.add(min);
                       // System.out.println(list);
                        if (list.size() > 1)
                            Utils.fixHeapDownMin(list); //runs in logN time
                    }//end for loop (O(n* logn)) time
                    
                }
                
                for (int n = 0; n < newList.size(); n ++) //runs in O(n) time 
                {
                    list.add(newList.get(n));
                }
                
                
		/*
		 * TODO:
		 * ---------------------------------------------
		 * Implement heap sort algorithm as
		 * described in class and in the supplemental
		 * course textbook. The pseudo-code for this 
		 * algorithm can also be found in 
		 * the content section on OAKS.
		 * 
		 */
		
	} // end heapSort() method
	
	
} // end Utils class definition
