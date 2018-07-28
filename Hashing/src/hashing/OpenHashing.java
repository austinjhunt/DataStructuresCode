/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;

/**
 *
 * @author austinhunt
 */
public class OpenHashing {
    
    private final static int HASH_TABLE_SIZE = 1001;
    private final ArrayList <HashEntry>[] hashTable; 
    private static int numCollisions; 
    private static double loadFactor; 
    private static int numberOfEntries;
    private static int numProbes;
    
    public OpenHashing()
    {
        hashTable = new ArrayList[HASH_TABLE_SIZE]; 
        
        //initialize your hashtable to all null entries 
        for (int i = 0 ; i < hashTable.length ; i ++)
        {
            hashTable[i] = null;
        } //each ArrayList entry is null to begin with
        numCollisions = 0;
        numProbes = 0;
        numberOfEntries = 0; 
        loadFactor = (double)numberOfEntries / HASH_TABLE_SIZE;
        
    }
    
    //insert method
    
    public int hashFunction1(HashEntry entry)
    {
        String entryKey = entry.getKey();
        int sumVal = 0;
        for (int i = 0; i < entryKey.length(); i ++)
        {  int asciiVal = (int) entryKey.charAt(i);
           sumVal += asciiVal;
        }
        int index = sumVal % HASH_TABLE_SIZE;
        return index ;
    }
    
    public int hashFunction1(String key)
    {
        int sumVal = 0;
        for (int i = 0; i < key.length(); i ++)
        {  int asciiVal = (int) key.charAt(i);
           sumVal += asciiVal;
        }
        int index = sumVal % HASH_TABLE_SIZE;
        return index ;
    }
    
    
    public int hashFunction2 (HashEntry entry) 
    {
        int sumVal = 0 ;
        String entryKey = entry.getKey();
        for (int i = 0; i < entryKey.length(); i ++)
        {
            int asciiVal = (int) entryKey.charAt(i);
            int uniqueVal = asciiVal + (7 * i);
            sumVal += uniqueVal;
        }
        int index = (sumVal * 13) % HASH_TABLE_SIZE;
        return index;
        
    }
    
    public int hashFunction2 (String key) 
    {
        int sumVal = 0 ;
       
        for (int i = 0; i < key.length(); i ++)
        {
            int asciiVal = (int) key.charAt(i);
            int uniqueVal = asciiVal + (7 * i);
            sumVal += uniqueVal;
        }
        int index = (sumVal * 13) % HASH_TABLE_SIZE;
        return index;
    }
    
    
    //insert method using first hash function; param: hashentry
    
    public void insertHF1(HashEntry entry)
    {   
        numProbes = 0; 
        if (containsHF1(entry) == false)
        {
        // System.out.println("\nEntry key: " + entry.getKey());
        // System.out.println("got here1 ");
         
         int index = hashFunction1(entry);
         //System.out.println("Index = " + index);
         //System.out.println("got here11111");
         if (hashTable[index] == null)//no collision
         {
             hashTable[index] = new ArrayList<HashEntry>();
             hashTable[index].add(entry);
             numProbes ++ ;
            // System.out.println("got here 2");
         }
         else if (hashTable[index].size() == 0)
         {
             hashTable[index].add(entry);
             numProbes ++ ;
         }
         //else, there is a collision, add to end of array list and update numCollisions variable
         else if (hashTable[index].size() >= 1)
         {   
             hashTable[index].add(entry);
             numCollisions ++;
             numProbes ++;
           //  System.out.println("got here 3");
         }
         //System.out.println("Got here 4");
         
         
         numberOfEntries ++; //update variable each time entry added
         loadFactor = (double) numberOfEntries / HASH_TABLE_SIZE; //update load factor 
        }
         //each time entry is added
    }
        
    
    public HashEntry getHF1(HashEntry entry)
    {
        return getHF1(entry.getKey());
    }
    
    public HashEntry getHF2(HashEntry entry)
    {
        return getHF2(entry.getKey());
    }
    
    //get method using first hash function; accept key as parameter
    public HashEntry getHF1(String key)
    {
        HashEntry returnThisEntry = null; 
        try{ 
            numProbes = 0;
        
        
            int index = hashFunction1(key);
        //System.out.println("index is " + index + " and hashTable size is 1001");
        
        if (hashTable[index] == null || hashTable[index].size() == 0)
        {   numProbes++;
            returnThisEntry = null;
            
        }
                
        else if (hashTable[index].size() == 1)
        {   
            if (hashTable[index].get(0).getKey().compareTo(key) == 0)
            {
                returnThisEntry = hashTable[index].get(0);
                numProbes ++;
                
            }
            
            else
                return null;
        }
        
        else if (hashTable[index].size() > 1)
        {
            int i = 0; 
            while (i < hashTable[index].size() && hashTable[index].get(i).getKey().compareTo(key)!= 0)
            {
                i ++; 
                numProbes ++;
            }
            if (i < hashTable[index].size())
            { returnThisEntry =  hashTable[index].get(i); }
            else
                return null;
        }
        /*if (returnThisEntry != null)
          /*  System.out.println("This SUCCESSFUL search took " + numProbes + "probes. "
                    + "\nWith the current load factor value (" + loadFactor + "), "
                    + "the formula (1 + lf/2) gives: " + (1+(loadFactor/2)));
        else 
            System.out.println("This UNSUCCESSFUL search took " + numProbes + "probes. "
                    + "\nWith the current load factor value (" + loadFactor + "), "
                    + "the formula (lf) gives: " + loadFactor );*/
        
        return returnThisEntry;}
       
       catch(IndexOutOfBoundsException e ) {System.out.println("THERES AN INDEX OOB EXCEPTION HERE");}
       return returnThisEntry; 
    }
    
    //delete method using first hash function; param: key 
    
    
    public HashEntry deleteHF1 (String key) 
    {
        numProbes = 0; 
        HashEntry deleteThisEntry = null; 
        int index = hashFunction1(key);
        
        if (hashTable[index] == null || hashTable[index].size() == 0)
            return null;
        else if (hashTable[index].size() == 1)
        {
            if (hashTable[index].get(0).getKey().compareTo(key) == 0)
            {  
                deleteThisEntry = hashTable[index].get(0);
                hashTable[index].remove(0);
                numberOfEntries -- ; 
            }
               //else deleteThisEntry still == null, and don't do anything 
        }
        else if (hashTable[index].size() > 1)
        {
             
            int i = 0; 
            while (i < hashTable[index].size() && hashTable[index].get(i).getKey().compareTo(key)!= 0)
            {
                i ++ ;
                numProbes ++ ;
            }
            if (i < hashTable[index].size())
            {
                deleteThisEntry = hashTable[index].get(i);
                hashTable[index].remove(i);
                numberOfEntries --; 
            }
            //else return null 
        }
        
        //IF SOMETHING IS DELETED, UPDATE YOUR LOAD FACTOR 
        loadFactor = (double) numberOfEntries / HASH_TABLE_SIZE; 
        
        return deleteThisEntry; 
    }
    
    public void insertHF2(HashEntry entry)
    {
         
         numProbes = 0;
         if (!containsHF1(entry))
         {
         int index = hashFunction2(entry);
         if (hashTable[index] == null)
         {
             hashTable[index] = new ArrayList<HashEntry>();
             hashTable[index].add(entry);
             numProbes ++ ;
         }
         if (hashTable[index].size() == 0)
         {
             hashTable[index].add(entry);
             numProbes ++ ;
         }
         //else, there is a collision, add to end of array list and update numCollisions variable
         else 
         {  
             hashTable[index].add(entry);
             numCollisions ++;
             numProbes ++; 
         }
             
         
         numberOfEntries ++; //update variable each time entry added
         loadFactor = (double) numberOfEntries / HASH_TABLE_SIZE; //update load factor 
         //each time entry is added
         }
         }
    
    
    public HashEntry getHF2(String key) 
    {
        HashEntry returnThisEntry = null; 
        
        try
        {
           
            numProbes = 0;
        
         
        int index = hashFunction2(key);
        if (hashTable[index] == null || hashTable[index].size() == 0)
        {
            numProbes ++; returnThisEntry = null;
        }
                
        else if (hashTable[index].size() == 1)
        {   
            if (hashTable[index].get(0).getKey().compareTo(key) == 0)
            {
                returnThisEntry = hashTable[index].get(0);
                numProbes ++;
            }
            
            else
                return null;
        }
        
        else if (hashTable[index].size() > 1)
        {
            int i = 0; 
            while (i < hashTable[index].size() && hashTable[index].get(i).getKey().compareTo(key)!= 0)   
            {
                i ++; 
                numProbes ++;
            }
            if (i < hashTable[index].size())
                returnThisEntry =  hashTable[index].get(i);
            else
                return null;
        }
        /*if (returnThisEntry != null)
            System.out.println("\nThis SUCCESSFUL search took " + numProbes + "probes. "
                    + "\nWith the current load factor value (" + loadFactor + "), "
                    + "the formula (1 + lf/2) gives: " + (1+(loadFactor/2)));
        else 
            System.out.println("\nThis UNSUCCESSFUL search took " + numProbes + "probes. "
                    + "\nWith the current load factor value (" + loadFactor + "), "
                    + "the formula (lf) gives: " + loadFactor );*/
        
        return returnThisEntry;}
        catch(IndexOutOfBoundsException e ) {System.out.println("THERES AN INDEX OOB EXCEPTION HERE");}
       return returnThisEntry; 
    }
    
    public HashEntry deleteHF2(String key) 
    {
         numProbes = 0; 
        HashEntry deleteThisEntry = null; 
        int index = hashFunction2(key);
        
        if (hashTable[index] == null || hashTable[index].size() == 0)
            return null;
        else if (hashTable[index].size() == 1)
        {
            if (hashTable[index].get(0).getKey().compareTo(key) == 0)
            {  
                deleteThisEntry = hashTable[index].get(0);
                hashTable[index].remove(0);
                numberOfEntries -- ; 
            }
               //else deleteThisEntry still == null, and don't do anything 
        }
        else if (hashTable[index].size() > 1)
        {
             
            int i = 0; 
            while (i < hashTable[index].size() && hashTable[index].get(i).getKey().compareTo(key)!= 0)
            {
                i ++ ;
                numProbes ++ ;
            }
            if (i < hashTable[index].size())
            {
                deleteThisEntry = hashTable[index].get(i);
                hashTable[index].remove(i);
                numberOfEntries --; 
            }
            //else return null 
        }
        
        //IF SOMETHING IS DELETED, UPDATE YOUR LOAD FACTOR 
        loadFactor = (double)numberOfEntries / HASH_TABLE_SIZE; 
        
        return deleteThisEntry;
        
    }
    
    public void printHashTable()
    { int iteration = 1; 
        for (int i = 0; i < hashTable.length; i ++) 
        {   
            if (hashTable[i] == null)
                System.out.println();
            else if (hashTable[i].size() == 0)
                System.out.println();
            else if (hashTable[i].size() == 1)
                System.out.println( hashTable[i].get(0).getKey());
            else if (hashTable[i].size()>1)
            {   for (int j = 0; j< hashTable[i].size(); j ++) 
                    System.out.print(hashTable[i].get(j).getKey() + ", ");
            }   
           
        }
    }
    public void clearHashTable()
    {
       for (int i = 0; i< hashTable.length; i ++) 
       {   
           if (hashTable[i] == null)
               hashTable[i] = null;
           else
               hashTable[i].clear();
       
       }
       numberOfEntries = 0; 
       loadFactor = (double)numberOfEntries/HASH_TABLE_SIZE;
       
       
        
    }
    
    public boolean containsHF1(HashEntry entry) 
    {
        //System.out.println("Here!");
        boolean boolVal = false;
        int index = hashFunction1(entry);
     
        //System.out.println("HERE 2 index = " + index);
        if (hashTable[index] == null)
        {
            
            boolVal = false;
            return boolVal; 
        }
        if (hashTable[index].size() == 0)
        { boolVal = false;  return boolVal; }
        
        else if (hashTable[index].size() >= 1)
        {
            
            int i = 0;
            while (i < hashTable[index].size() && hashTable[index].get(i).getKey().compareTo(entry.getKey())!=0)
            {
                i ++;
            }
           
            if (i< hashTable[index].size())
            {    boolVal = true; return boolVal;}
            else 
            {    boolVal = false; return boolVal;}
            
        }
        
       
        return boolVal; 
        }
    
    
    public boolean containsHF2(HashEntry entry) 
    {
        boolean boolVal = false;
        int index = hashFunction2(entry);
        if (hashTable[index] == null)
        {  boolVal = false; return boolVal;}
        if (hashTable[index].size() == 0)
        {   boolVal = false; return boolVal;}
        
        else if (hashTable[index].size() >= 1)
        {
            int i = 0;
            while (i < hashTable[index].size() && hashTable[index].get(i).getKey().compareTo(entry.getKey())!=0)
            {
                i ++;
            }
            if (i < hashTable[index].size())
            {   boolVal = true; return boolVal;}
            else 
            {  boolVal = false; return boolVal;}
        }
        System.out.println("Bool val = " + boolVal);
        return boolVal; 
        }
    public double getLoadFactor()
    {
        return (double) numberOfEntries/HASH_TABLE_SIZE; 
    }
    public int getNumEntries()
    {
        return numberOfEntries;
    }
    
    public int getNumProbes()
    {
        return numProbes; 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
    }
    
}
