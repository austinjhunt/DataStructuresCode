package csci230.hwk7;

public class HeapTest {

	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> sorted_list = new ArrayList<>();
		
		list.add( 5 );
		list.add( 16 );
		list.add( 8 );
		list.add( 14 );
		list.add( 20 );
		list.add( 1 );
		list.add( 26 );
                list.add(4);
                list.add(7);
                list.add(3);
                list.add(11);
                list.add(17);
                list.add(31);
                list.add(5);
		try{
                    
                Utils.maxHeapify(list);
                System.out.println(list);
                
                
		Utils.heapSort( sorted_list, list, false ); //so we want to sort in 
                //decreasing order
		System.out.println ( sorted_list );
		
                list.clear();
                sorted_list.clear();
                list.add( 5 );
		list.add( 16 );
		list.add( 8 );
		list.add( 14 );
		list.add( 20 );
		list.add( 1 );
		list.add( 26 );
                list.add(-5);
                list.add(-10);
                list.add(100);
                list.add(0);
                list.add(-150);
                list.add(43);
                
                Utils.heapSort( sorted_list, list, true );
		System.out.println ( sorted_list );
                
                sorted_list.clear();
                list.add( 5 );
		list.add( 16 );
		list.add( 8 );
		list.add( 14 );
		list.add( 20 );
		list.add( 1 );
		list.add( 26 );
                list.add(-5);
                list.add(-10);
                list.add(100);
                list.add(0);
                list.add(-150);
                list.add(43);
                System.out.println("list = " + list) ;
                Utils.heapSort(sorted_list, list, false);
                System.out.println(sorted_list);
		
               System.out.println("\nSingly Linked Lists:");
                
		SinglyLinkedList<Integer> slist = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> sorted_slist = new SinglyLinkedList<>();
		
		slist.add( 5 );
		slist.add( 16 );
		slist.add( 8 );
		slist.add( 14 );
		slist.add( 20 );
		slist.add( 1 );
		slist.add( 26 );
                slist.add(4); 
                slist.add(-10);
                slist.add(0);
                slist.add(-43);
                slist.add(100);
		
		Utils.heapSort(sorted_slist, slist, true );
		System.out.println( sorted_slist );
                
                sorted_slist.clear();
                
                slist.add( 5 );
		slist.add( 16 );
		slist.add( 8 );
		slist.add( 14 );
		slist.add( 20 );
		slist.add( 1 );
		slist.add( 26 );
                slist.add(4); 
                slist.add(-10);
                slist.add(0);
                slist.add(-43);
                slist.add(100);
                
                Utils.heapSort(sorted_slist, slist, false);
                System.out.println(sorted_slist);
                
                list.clear();
                
                list.add(3);
                list.add(10);
                list.add(1);
                list.add(23);
                list.add(2);
                list.add(0);
                list.add(12);
                
                System.out.println("Original List: " + list); 
                
                Utils.maxHeapify(list);
                
                System.out.println("MaxHeapified List: " + list);
                
                Utils.minHeapify(list);
                
                System.out.println("MinHeapified List: " + list);
                
                
                
                ArrayList<Double> newListD = new ArrayList<>();
                ArrayList<Double> sortedD = new ArrayList<>();
                newListD.add(1.1);
                newListD.add(21.3);
                newListD.add(31.4);
                newListD.add(42.5);
                newListD.add(54.6);
                newListD.add(-532.1);
                newListD.add(0.001);
                newListD.add(-5.3);
                
                
                Utils.heapSort(sortedD, newListD, false);
                for (int i = 0 ; i < sortedD.size(); i ++) 
                    System.out.println(sortedD.get(i)); 
                
                sortedD.clear();
                newListD.add(1.1);
                newListD.add(21.3);
                newListD.add(31.4);
                newListD.add(42.5);
                newListD.add(54.6);
                newListD.add(-532.1);
                newListD.add(0.001);
                newListD.add(-5.3);
                
                Utils.heapSort(sortedD,newListD, true);
                for (int i = 0 ; i < sortedD.size(); i ++) 
                    System.out.println(sortedD.get(i)); 
                
                ArrayList<String> newListD1 = new ArrayList<>();
                ArrayList<String> sortedD1 = new ArrayList<>();
                newListD1.add("Cryptography");
                newListD1.add("Analytics");
                newListD1.add("Data");
                newListD1.add("Computing");
                newListD1.add("Apple");
                newListD1.add("Hello");
                
                Utils.heapSort(sortedD1, newListD1, false);
                for (int i = 0; i < sortedD1.size(); i ++ ) 
                    System.out.println(sortedD1.get(i));
                
                newListD1.clear();
                sortedD1.clear();
                newListD1.add("Cryptography");
                newListD1.add("Analytics");
                newListD1.add("Data");
                newListD1.add("Computing");
                newListD1.add("Apple");
                newListD1.add("Hello");
                Utils.heapSort(sortedD1, newListD1, true);
                for (int i = 0; i < sortedD1.size(); i ++ ) 
                    System.out.println(sortedD1.get(i));
                
                }
                catch(IndexOutOfBoundsException e)
                {System.out.println("INDEX OUT OF BOUNDS!!");}
        }}
                


