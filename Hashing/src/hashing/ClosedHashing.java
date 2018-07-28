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
public class ClosedHashing {
    
    private static final int HASH_TABLE_SIZE = 50023; 
    private static int numCollisions; 
    private static double loadFactor; 
    private static int numberOfEntries;
    private static int numProbes;
    private HashEntry[] hashTable ; 
    
    public ClosedHashing()
    {
        hashTable = new HashEntry[HASH_TABLE_SIZE]; 
        for (int i = 0; i < hashTable.length; i ++) 
            hashTable[i] = null;  
        numCollisions = 0; 
        loadFactor = 0; 
        numberOfEntries = 0; 
        numProbes = 0; 
    }
    
    
    //two different hash functions, same ones for open hashing, but collisions will 
    //be handled differently in the INSERT method 
    
    
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
    
    //insert method using first hash function
    
    public void insertHF1(HashEntry entry) 
    {
        numProbes = 0; 
        if (!tableFull())
        {
        if (!containsHF1(entry))   
        {
           // try{
            int index = hashFunction1(entry) ;
            //System.out.println("Index = " + index); 
            //System.out.println("Hash table size = " + HASH_TABLE_SIZE);
            if (hashTable[index] == null) 
            {   
                hashTable[index] = new HashEntry(entry.getKey()); 
                numProbes ++ ; 
                numberOfEntries ++; 
            }
            
            
            else // if index is not null, something is there, which means you enter into 
                // a while loop until you find empty spot 
            {
                
                int indexCopy = index ; 
                int i = 1;  
                
                while (hashTable[index] != null)
                {   numProbes ++ ; index = (indexCopy + (i*i)) % HASH_TABLE_SIZE ; ++i; } 
                
                
                if (hashTable[index] == null)
                    hashTable[index] = new HashEntry(entry.getKey()); 
            }
                
                numberOfEntries ++ ; 
                
                loadFactor = (double) numberOfEntries / HASH_TABLE_SIZE; 
                //catch(IndexOutOfBoundsException e1) {System.out.println("DUDE HERE");}
            
            }
        }//end contains block
            //catch(IndexOutOfBoundsException e) {System.out.println("INDEX PROBLEM IN INSERT METHOD!");}
    
    
        }//end table full if block
    
    
   
    
    public void insertHF2(HashEntry entry) 
    {
        numProbes = 0; 
        if (!tableFull())
        {
        if (!containsHF2(entry))   
        {
            try{
                
            int index = hashFunction2(entry) ;
          //  System.out.println("Index = " + index); 
           // System.out.println("Hash table size = " + HASH_TABLE_SIZE);
            if (hashTable[index] == null) 
            {   hashTable[index] = new HashEntry(entry.getKey()); numProbes ++ ; numberOfEntries ++; }
            
            
            else // if index is not null, something is there, which means you enter into 
                // a while loop until you find empty spot 
            {
                
                int indexCopy = index ; 
                int i = 1;  
                while (hashTable[index] != null)
                {   numProbes ++ ; index = (indexCopy + (i*i)) % HASH_TABLE_SIZE ; ++i; }
                //System.out.println("index = " + index); 
                    hashTable[index] = new HashEntry(entry.getKey()); 
                 
                
                
            
            }
            numberOfEntries ++ ;loadFactor = (double) numberOfEntries / HASH_TABLE_SIZE; 
        }//end contains block
            catch(IndexOutOfBoundsException e) {System.out.println("INDEX PROBLEM IN INSERT METHOD!");}
    
    
        }//end table full if block
    }
    
    }
    
    
    public HashEntry getHF1(HashEntry entry)
    {
        return getHF1(entry.getKey());
    }
    
    public HashEntry getHF1(String key)
    {
        HashEntry returnThisEntry = null; 
        try
        {
            numProbes = 0; 
            int index = hashFunction1(key); 
            
            if (hashTable[index] == null) //nothing in this cell, so return null
            {numProbes ++; returnThisEntry = null; }
            else
            {
                if (hashTable[index].getKey().compareTo(key) != 0)  // if something in cell, but not what you want, quadratically probe 
                {
                    int indexCopy = index;
                    int i = 1; //while this entry's key not equal to KEY AND hf1(thisentry) == hf1(nextentry), then proceed to 
                    //quadratically probe
                    numProbes++; 
                    while (hashTable[index].getKey().compareTo(key)!= 0 && 
                            hashTable[(indexCopy + (i * i))%HASH_TABLE_SIZE]!= null && 
                            hashFunction1(hashTable[index]) ==
                            hashFunction1(hashTable[(indexCopy + (i * i) )%HASH_TABLE_SIZE]))
                    { //redefine index , increment i afterward
                        numProbes ++ ; 
                        index = (indexCopy + (i * i)) % HASH_TABLE_SIZE; 
                        i ++ ; 
                        
                    }
                    //while loop ends on 1 of 2 conditions: this entry key = KEY OR hf1(thisentry) != hf1(nextEntry)
                    
                    if (hashTable[(indexCopy + (i * i) )%HASH_TABLE_SIZE] == null && hashTable[index].getKey().compareTo(key) != 0)
                        returnThisEntry = null; 
                    if (hashTable[(indexCopy + (i * i) )%HASH_TABLE_SIZE] != null && hashFunction1(hashTable[index]) !=
                            hashFunction1(hashTable[(indexCopy + (i * i) )%HASH_TABLE_SIZE]) &&
                                    hashTable[index].getKey().compareTo(key) != 0)
                        returnThisEntry = null;
                    else 
                        returnThisEntry = hashTable[index]; 

                }
                //if something in cell, IS what you want, return this entry 
                else 
                    returnThisEntry = hashTable[index]; 
                
            }
            return returnThisEntry; 
        }
    
        catch(IndexOutOfBoundsException e ) {System.out.println("THERES AN INDEX OOB EXCEPTION HERE");}
       return returnThisEntry; 
    }
    
    public HashEntry getHF2(HashEntry entry) 
    {
        return getHF2(entry.getKey()); 
    }
    public HashEntry getHF2(String key)
    {
        HashEntry returnThisEntry = null; 
        try
        {
            numProbes = 0; 
            int index = hashFunction2(key); 
            
            if (hashTable[index] == null) //nothing in this cell, so return null
            {numProbes ++;  returnThisEntry = null;}
            else
            {
                if (hashTable[index].getKey().compareTo(key) != 0)  // if something in cell, but not what you want, quadratically probe 
                {
                    int indexCopy = index;
                    int i = 1; //while this entry's key not equal to KEY AND hf1(thisentry) == hf1(nextentry), then proceed to 
                    //quadratically probe
                    numProbes ++; 
                    while (hashTable[index].getKey().compareTo(key)!= 0 && 
                            hashTable[(indexCopy + (i * i))%HASH_TABLE_SIZE]!= null && 
                            hashFunction2(hashTable[index]) ==
                            hashFunction2(hashTable[(indexCopy + (i * i) )%HASH_TABLE_SIZE]))
                    { //redefine index , increment i afterward
                        numProbes ++ ; 
                        index = (indexCopy + (i * i)) % HASH_TABLE_SIZE; 
                        i ++ ; 
                    }
                    //while loop ends on 1 of 2 conditions: this entry key = KEY OR hf1(thisentry) != hf1(nextEntry)
                    if (hashTable[(indexCopy + (i * i) )%HASH_TABLE_SIZE] == null && hashTable[index].getKey().compareTo(key) != 0)
                        returnThisEntry = null; 
                    else if (hashTable[(indexCopy + (i * i) )%HASH_TABLE_SIZE] != null && hashFunction2(hashTable[index]) !=
                            hashFunction2(hashTable[(indexCopy + (i * i) )%HASH_TABLE_SIZE]) &&
                                    hashTable[index].getKey().compareTo(key) != 0)
                        returnThisEntry = null; 
                    else 
                        returnThisEntry = hashTable[index]; 

                }
                //if something in cell, IS what you want, return this entry 
                else 
                    returnThisEntry = hashTable[index]; 
                
            }
            return returnThisEntry; 
        }
    
        catch(IndexOutOfBoundsException e ) {System.out.println("THERES AN INDEX OOB EXCEPTION HERE");}
       return returnThisEntry; 
    }
    
    public void clearHashTable()
    {
        for (int i = 0; i < hashTable.length; i ++) 
        {   if (hashTable[i] == null) //conditional statement to prevent throwing exception
            { hashTable[i] = null; }
            hashTable[i] = null; 
        }
        numberOfEntries = 0; 
        loadFactor = (double) numberOfEntries / HASH_TABLE_SIZE; 
        } 
    
    
    public int getNumProbes()
    {return numProbes;  }
    
    public double getLoadFactor()
    { return loadFactor; }
    
    public void printHashTable()
    {
        for (int i = 0; i < hashTable.length; i ++)
        {
            if (hashTable[i] == null) 
                System.out.println();
            else 
                System.out.println(hashTable[i].getKey());
        }
    }
    
    public boolean containsHF1(HashEntry entry) 
    {
        boolean boolVal = false; 
        
        int index = hashFunction1(entry) ; 
        if (hashTable[index] == null)
            boolVal = false; 
        else 
        {   
            if (hashTable[index].getKey().equals((String)entry.getKey()))
                return true; 
            else 
            {
                int indexCopy = index; 
                int i = 1; 
                while(hashTable[index].getKey().compareTo(entry.getKey()) != 0 && 
                        hashTable[(indexCopy + (i * i))%HASH_TABLE_SIZE]!= null && 
                        hashFunction1(hashTable[index]) == 
                        hashFunction1(hashTable[(indexCopy + (i * i))%HASH_TABLE_SIZE]))
                    //while this entry's key not equal to argument key AND this entry has same 
                    //returns same index with hash function as the previous entry traversed 
                {    
                    index = (indexCopy + (i*i)) % HASH_TABLE_SIZE; 
                    i ++ ; 
                }
                if (hashTable[index].getKey().compareTo(entry.getKey()) != 0) 
                    boolVal = false; 
                else
                    boolVal = true;
            }
        
        }
        return boolVal; 
        
    }
    public boolean containsHF2(HashEntry entry) 
    {
        
        boolean boolVal = false; 
        
        int index = hashFunction1(entry) ; 
        if (hashTable[index] == null)
            boolVal = false; 
        else 
        {   
            if (hashTable[index].getKey().compareTo(entry.getKey()) == 0)
                return true; 
            else 
            {
                int indexCopy = index; 
                int i = 1; 
                while(hashTable[index].getKey().compareTo(entry.getKey()) != 0 && 
                        hashTable[(indexCopy + (i * i))%HASH_TABLE_SIZE]!= null && 
                        hashFunction1(hashTable[index]) == 
                        hashFunction1(hashTable[(indexCopy + (i * i))%HASH_TABLE_SIZE]))//while this entry's key not equal to argument key AND this entry has same 
                    //returns same index with hash function as the previous entry traversed 
                {   
                    index = (indexCopy + (i*i)) % HASH_TABLE_SIZE; i ++; 
                }
                
                
                if (hashTable[index].getKey().compareTo(entry.getKey()) != 0 ) 
                    boolVal = false; 
                else
                    boolVal = true;
            }
        }
        
            return boolVal ; 
    }
    
    public int getNumEntries()
    {
        return numberOfEntries; 
    }
    public boolean tableFull()
    {
        int i = 0; 
        while ( i < hashTable.length && hashTable[i] != null)
            i++; 
        if (i >= hashTable.length && hashTable[i] != null)
            return true; 
        else
            return false; 
    }
}
