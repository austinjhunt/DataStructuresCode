/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;




/**
 *
 * @author austinhunt
 */
public class TestHashing {
    
    public static void main(String[] args) throws FileNotFoundException 
    {
    ArrayList<String> fileStringsList = new ArrayList<>(); 
    
    String fileName = "/Users/austinhunt/Desktop/230Netbeans/Hashing/src/hashing/letters.txt";
    try
    { 
        FileInputStream fileStream = new FileInputStream(fileName); 
        DataInputStream data_input = new DataInputStream(fileStream); 
        BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input)); 
        String stringLine; 
        //create arraylist of strings from text file 
        String fileString = "";
        while ((stringLine = buffer.readLine()) != null) 
        { 
            stringLine = stringLine.trim(); 
            fileString += stringLine;//keep adding strings until you have one string with all elements of text file 
        }
        
    File outFile = new File ("/Users/austinhunt/Desktop/230Netbeans/Hashing/src/hashing/durationsNumProbes.txt");
    FileWriter writer = new FileWriter(outFile);
    BufferedWriter out = new BufferedWriter(writer);
     //System.out.println("fileString = " + fileString) ;
     
    
        
     String[] fileStringArray = fileString.split(" ");
     //create an array of each string element (to be searched for later) 
     
     
     ArrayList<String> entryKeys = new ArrayList<>();
     //create an arraylist of those elements
     
     
     for (String element : fileStringArray) 
        entryKeys.add(element);
        
     
     //create an arraylist of hashentries (construct them with corresponding strings from previous array list
     ArrayList<HashEntry> hashEntries = new ArrayList<>();
     for (int i = 0; i < entryKeys.size(); i ++) 
     {
         hashEntries.add(new HashEntry(entryKeys.get(i)));
     }
    
     //construct openhashing,TEST HASHFUNCTION1 
    writer.write("\n\nTESTINGCHAININGWITHFIRSTHASHFUNCTION");
    OpenHashing testOpen1 = new OpenHashing(); 
    long startTime = System.nanoTime();
    for (int i = 0; i < hashEntries.size()/2; i ++) 
    {
        testOpen1.insertHF1(hashEntries.get(i));
        //System.out.println("Num Entries: " + testOpen1.getNumEntries() + 
          //      "\nLoad Factor: " + testOpen1.getLoadFactor());
    }
    long endTime = System.nanoTime();
    
    long durationToBuildHT = endTime - startTime;
    System.out.println("Number of Hash Entries in HashTable: " + testOpen1.getNumEntries());
    System.out.println("Load factor of testOpen1: " + testOpen1.getLoadFactor());
   
    System.out.println("Duration to build hash table of " + hashEntries.size() + " entries "
            + " with chaining: " + durationToBuildHT + " nanoseconds");

    System.in.read(); //after displaying that, wait for user to enter something and then 
    //proceed to output other information
    
    
    //print out hashtable to observe distribution 
    
    //we want average duration and average number of probes for this particular load factor 
    //this hashTable includes EVERY element from hashEntries, load factor is 9.577
    //System.out.println("Printing Hash Table: " );
    //testOpen1.printHashTable();
    Random rand = new Random();
    int index; 
    ArrayList<Long> durationList = new ArrayList<>();
    ArrayList<Integer> numProbesList = new ArrayList<>(); 
    
    //CALCULATE AVG NUM PROBES /AVG DURATION FOR SUCCESSFUL SEARCHES
    for (int i = 0; i < 500; i ++) 
    {
        index = rand.nextInt(hashEntries.size()/2);
        startTime = System.nanoTime();
        testOpen1.getHF1(hashEntries.get(index));
        endTime = System.nanoTime();
        long duration = endTime - startTime;
        durationList.add(duration);
        numProbesList.add(testOpen1.getNumProbes());
    }
    //now have a list of durations and a list of numProbes
    //calculate average duration, average numProbes
    int sumDurations = 0;
    for (int i = 0; i < durationList.size(); i++)
        sumDurations += durationList.get(i);
    double durationAvg = (double)sumDurations / durationList.size();
   // System.out.println("Average duration for SUCCESSFUL search for load factor of " + testOpen1.getLoadFactor() + 
      //      " is " + avg + "nanoseconds");
      
      
    int sumNumProbes = 0; 
    for (int i = 0; i < numProbesList.size(); i++)
        sumNumProbes += numProbesList.get(i) ;
    double numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\nSUCCESSFULSEARCH");
    writer.write("\n\nLoadFactor: " + testOpen1.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //CALCULATE AVG DURATION/AVG NUM PROBES FOR UNSUCCESSFUL SEARCHES
    // nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive

    for (int i = 0; i < 500; i ++) 
    {
        index = ThreadLocalRandom.current().nextInt((hashEntries.size()/2)+1, hashEntries.size());
        startTime = System.nanoTime();
        testOpen1.getHF1(hashEntries.get(index));
        endTime = System.nanoTime();
        long duration = endTime - startTime;
        durationList.add(duration);
        numProbesList.add(testOpen1.getNumProbes());
    }
    //now have a list of durations and a list of numProbes
    //calculate average duration, average numProbes
    sumDurations = 0;
    for (int i = 0; i < durationList.size(); i++)
        sumDurations += durationList.get(i);
    durationAvg = (double)sumDurations / durationList.size();
   // System.out.println("Average duration for SUCCESSFUL search for load factor of " + testOpen1.getLoadFactor() + 
      //      " is " + avg + "nanoseconds");
      
      
    sumNumProbes = 0; 
    for (int i = 0; i < numProbesList.size(); i++)
        sumNumProbes += numProbesList.get(i) ;
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\nUNSUCCESSFULSEARCH");
    writer.write("\n\nLoadFactor: " + testOpen1.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //create MANY hash tables, each with different load factors, then for each of them, 
    //calculate average time  & number of probes for successful search 
    double loadFactorLimit = 28; 
    for (int q = 0; q < 27; q ++) 
    {
        
        testOpen1.clearHashTable();
    //System.out.println("hash table after clearing: " );
    //testOpen1.printHashTable();
    
    //now, testOpen1 is empty; our first load factor was 9.577, we want to test avg 
    //duration and num probes for load factor of 9, 8, 7, 6, 5,4, 3, 2,1 
    //start with 9, keep inserting elements from hashEntries until load factor is 9
    loadFactorLimit --; 
    
    
    int j = 0; 
    while(testOpen1.getLoadFactor() < (loadFactorLimit ))
    {
        
        testOpen1.insertHF1(hashEntries.get(j));
        j++; 
        
    }
    //writer.write("\nj = " + j); //j = number of entries inserted 
    //
    //LOAD FACTOR = 9, WE WANT AVG DURATION AND AVG NUM PROBES FOR SUCCESSFUL SEARCH ON THIS HASHTABLE//first 
    //
    durationList.clear();
    numProbesList.clear();
    long duration; 
    for (int a = 0; a < j ; a ++ )
    {
        index = rand.nextInt(j);
        startTime = System.nanoTime();
        testOpen1.getHF1(hashEntries.get(index));
        endTime = System.nanoTime();
        duration = endTime - startTime; 
        durationList.add(duration);
        numProbesList.add(testOpen1.getNumProbes());
    }
    sumDurations = 0;
    sumNumProbes = 0; 
   // System.out.println("duration list size: " + durationList.size());
    //System.out.println("numProbes list size: " + numProbesList.size());
    for (int b = 0; b < durationList.size(); b ++) 
    {
        sumDurations += durationList.get(b);
        sumNumProbes += numProbesList.get(b);
    }
    durationAvg = (double) sumDurations/durationList.size();
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\nSUCCESSFULSEARCH");
    writer.write("\nLoadFactor: " + testOpen1.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //now want avg duration and avg num probes for unsuccessful search on this hashtable
    durationList.clear();
    numProbesList.clear();
     
    for (int a = 0; a < (hashEntries.size() - (j+1)) ; a ++ )
    {
        index = ThreadLocalRandom.current().nextInt((j+1), hashEntries.size());
        startTime = System.nanoTime();
        testOpen1.getHF1(hashEntries.get(index));
        endTime = System.nanoTime();
        duration = endTime - startTime; 
        durationList.add(duration);
        numProbesList.add(testOpen1.getNumProbes());
    }
    sumDurations = 0;
    sumNumProbes = 0; 
    //System.out.println("duration list size: " + durationList.size());
    //System.out.println("numProbes list size: " + numProbesList.size());
    for (int b = 0; b < durationList.size(); b ++) 
    {
        sumDurations += durationList.get(b);
        sumNumProbes += numProbesList.get(b);
    }
    durationAvg = (double) sumDurations/durationList.size();
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    
    writer.write("\n\nUNSUCCESSFULSEARCH"); 
    writer.write("\nLoadFactor: " + testOpen1.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    }
    
    
    
    
    //TEST HASH FUNCTION 2
    writer.write("\n\n\nTESTINGCHAININGWITHSECONDHASHFUNCTION");
     //construct openhashing 
    OpenHashing testOpen2 = new OpenHashing(); 
    startTime = System.nanoTime();
    for (int i = 0; i < hashEntries.size()/2; i ++) 
    {
        testOpen2.insertHF2(hashEntries.get(i));
        //System.out.println("Num Entries: " + testOpen2.getNumEntries() + 
          //      "\nLoad Factor: " + testOpen2.getLoadFactor());
    }
    endTime = System.nanoTime();
    
    durationToBuildHT = endTime - startTime;
   // System.out.println("Number of Hash Entries in HashTable: " + testOpen2.getNumEntries());
    //System.out.println("Load factor of testOpen2: " + testOpen2.getLoadFactor());
   
    //System.out.println("Duration to build hash table of " + hashEntries.size() + " entries "
        //    + " with chaining: " + durationToBuildHT + " nanoseconds");

    //System.in.read(); //after displaying that, wait for user to enter something and then 
    //proceed to output other information
    
    
    //print out hashtable to observe distribution 
    
    //we want average duration and average number of probes for this particular load factor 
    //this hashTable includes EVERY element from hashEntries, load factor is 9.577
    //System.out.println("Printing Hash Table: " );
    //testOpen2.printHashTable();
    Random rand2 = new Random();
    
    //CALCULATE AVG NUM PROBES /AVG DURATION FOR SUCCESSFUL SEARCHES
    for (int i = 0; i < 500; i ++) 
    {
        index = rand.nextInt(hashEntries.size()/2);
        startTime = System.nanoTime();
        testOpen2.getHF2(hashEntries.get(index));
        endTime = System.nanoTime();
        long duration = endTime - startTime;
        durationList.add(duration);
        numProbesList.add(testOpen2.getNumProbes());
    }
    //now have a list of durations and a list of numProbes
    //calculate average duration, average numProbes
    sumDurations = 0;
    for (int i = 0; i < durationList.size(); i++)
        sumDurations += durationList.get(i);
    durationAvg = (double)sumDurations / durationList.size();
   // System.out.println("Average duration for SUCCESSFUL search for load factor of " + testOpen2.getLoadFactor() + 
      //      " is " + avg + "nanoseconds");
      
      
    sumNumProbes = 0; 
    for (int i = 0; i < numProbesList.size(); i++)
        sumNumProbes += numProbesList.get(i) ;
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\n\nSUCCESSFULSEARCH");
    writer.write("\n\nLoadFactor: " + testOpen2.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //CALCULATE AVG DURATION/AVG NUM PROBES FOR UNSUCCESSFUL SEARCHES
    // nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive

    for (int i = 0; i < 500; i ++) 
    {
        index = ThreadLocalRandom.current().nextInt((hashEntries.size()/2)+1, hashEntries.size());
        startTime = System.nanoTime();
        testOpen2.getHF2(hashEntries.get(index));
        endTime = System.nanoTime();
        long duration = endTime - startTime;
        durationList.add(duration);
        numProbesList.add(testOpen2.getNumProbes());
    }
    //now have a list of durations and a list of numProbes
    //calculate average duration, average numProbes
    sumDurations = 0;
    for (int i = 0; i < durationList.size(); i++)
        sumDurations += durationList.get(i);
    durationAvg = (double)sumDurations / durationList.size();
   // System.out.println("Average duration for SUCCESSFUL search for load factor of " + testOpen2.getLoadFactor() + 
      //      " is " + avg + "nanoseconds");
      
      
    sumNumProbes = 0; 
    for (int i = 0; i < numProbesList.size(); i++)
        sumNumProbes += numProbesList.get(i) ;
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\nUNSUCCESSFULSEARCH");
    writer.write("\n\nLoadFactor: " + testOpen2.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //create MANY hash tables, each with different load factors, then for each of them, 
    //calculate average time  & number of probes for successful search 
    loadFactorLimit = 28; 
    for (int q = 0; q < 27; q ++) 
    {
        
        testOpen2.clearHashTable();
    //System.out.println("hash table after clearing: " );
    //testOpen2.printHashTable();
    
    //now, testOpen2 is empty; our first load factor was 9.577, we want to test avg 
    //duration and num probes for load factor of 9, 8, 7, 6, 5,4, 3, 2,1 
    //start with 9, keep inserting elements from hashEntries until load factor is 9
    loadFactorLimit --; 
    
    
    int j = 0; 
    while(testOpen2.getLoadFactor() < (loadFactorLimit ))
    {
        
        testOpen2.insertHF2(hashEntries.get(j));
        j++; 
        
    }
    //writer.write("\nj = " + j); //j = number of entries inserted 
    //
    //LOAD FACTOR = 9, WE WANT AVG DURATION AND AVG NUM PROBES FOR SUCCESSFUL SEARCH ON THIS HASHTABLE//first 
    //
    durationList.clear();
    numProbesList.clear();
    long duration; 
    for (int a = 0; a < j ; a ++ )
    {
        index = rand.nextInt(j);
        startTime = System.nanoTime();
        testOpen2.getHF2(hashEntries.get(index));
        endTime = System.nanoTime();
        duration = endTime - startTime; 
        durationList.add(duration);
        numProbesList.add(testOpen2.getNumProbes());
    }
    sumDurations = 0;
    sumNumProbes = 0; 
    //System.out.println("duration list size: " + durationList.size());
    //System.out.println("numProbes list size: " + numProbesList.size());
    for (int b = 0; b < durationList.size(); b ++) 
    {
        sumDurations += durationList.get(b);
        sumNumProbes += numProbesList.get(b);
    }
    durationAvg = (double) sumDurations/durationList.size();
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\nSUCCESSFULSEARCH");
    writer.write("\nLoadFactor: " + testOpen2.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //now want avg duration and avg num probes for unsuccessful search on this hashtable
    durationList.clear();
    numProbesList.clear();
     
    for (int a = 0; a < (hashEntries.size() - (j+1)) ; a ++ )
    {
        index = ThreadLocalRandom.current().nextInt((j+1), hashEntries.size());
        startTime = System.nanoTime();
        testOpen2.getHF2(hashEntries.get(index));
        endTime = System.nanoTime();
        duration = endTime - startTime; 
        durationList.add(duration);
        numProbesList.add(testOpen2.getNumProbes());
    }
    sumDurations = 0;
    sumNumProbes = 0; 
    //System.out.println("duration list size: " + durationList.size());
   // System.out.println("numProbes list size: " + numProbesList.size());
    for (int b = 0; b < durationList.size(); b ++) 
    {
        sumDurations += durationList.get(b);
        sumNumProbes += numProbesList.get(b);
    }
    durationAvg = (double) sumDurations/durationList.size();
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    
    writer.write("\n\nUNSUCCESSFULSEARCH"); 
    writer.write("\nLoadFactor: " + testOpen2.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    }
    
    //wanna construct closed hash, get avg duration and avg num probes for get()
    //for various load factors 
   
    System.out.println(hashEntries.size());
    
     


    //TEST CLOSED HASHING
    writer.write("\n\n\nTESTINGQUADRATICPROBINGWITHFIRSTHASHFUNCTION\n");
     //construct closedhashing 
    ClosedHashing testClosed1 = new ClosedHashing(); 
    
    
    startTime = System.nanoTime();
    //System.out.println("hash entries size: " + hashEntries.size()); //hash entries = list of entries which you are putting
    //into hashTable
    //first build a FULL closed hashing table 
    int j = 0 ; 
    while ( j < hashEntries.size() && testClosed1.getLoadFactor() < 1)
    {
        testClosed1.insertHF1(hashEntries.get(j));//throwing index out of bounds, but why? entry index , gets to 
        //j = 85834 before throwing exception, size of hashEntries is 293549, my insert method is messed up 
        j++; //System.out.println("j = " + j ); 
    }
    endTime = System.nanoTime();
    
    durationToBuildHT = endTime - startTime;
    System.out.println("HashEntries arraylist too big, (size : " + hashEntries.size()+")"); 
    System.out.println("Number of Hash Entries in HashTable: " + testClosed1.getNumEntries());
    System.out.println("Load factor of testClosed1: " + testClosed1.getLoadFactor());
    System.out.println("J = " + j );
    System.out.println("Duration to build hash table of " + hashEntries.size() + " entries "
            + " with chaining: " + durationToBuildHT + " nanoseconds");
    
    //so we now have a hashTable with load factor of 1, we now want to 
    // calculate the average duration and num probes for implementing the get method on this 
    //hash tablewe want to build one for 
    //.9,.8,.7,.6,.5,.4,.3,.2,.1
    //first calculate avg duration and num probes for load factor 1
    //CALCULATE AVG NUM PROBES /AVG DURATION FOR SUCCESSFUL SEARCHES
    
    
    durationList.clear();
    numProbesList.clear();
    for (int i = 0; i < 500; i ++) 
    {
        index = rand.nextInt(j);
        startTime = System.nanoTime();
        testClosed1.getHF1(hashEntries.get(index));
        endTime = System.nanoTime();
        long duration = endTime - startTime;
        durationList.add(duration);
        numProbesList.add(testClosed1.getNumProbes());
    }
    //now have a list of durations and a list of numProbes
    //calculate average duration, average numProbes
    sumDurations = 0;
    for (int i = 0; i < durationList.size(); i++)
        sumDurations += durationList.get(i);
    durationAvg = (double)sumDurations / durationList.size();
   
    sumNumProbes = 0; 
    for (int i = 0; i < numProbesList.size(); i++)
        sumNumProbes += numProbesList.get(i) ;
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\nSUCCESSFULSEARCH");
    writer.write("\n\nLoadFactor: " + testClosed1.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //CALCULATE AVG DURATION/AVG NUM PROBES FOR UNSUCCESSFUL SEARCHES
    // nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive

    for (int i = 0; i < 500; i ++) 
    {
        index = ThreadLocalRandom.current().nextInt(j+1, hashEntries.size());
        startTime = System.nanoTime();
        testClosed1.getHF1(hashEntries.get(index));
        endTime = System.nanoTime();
        long duration = endTime - startTime;
        durationList.add(duration);
        numProbesList.add(testClosed1.getNumProbes());
    }
    //now have a list of durations and a list of numProbes
    //calculate average duration, average numProbes
    sumDurations = 0;
    for (int i = 0; i < durationList.size(); i++)
        sumDurations += durationList.get(i);
    durationAvg = (double)sumDurations / durationList.size();
   // System.out.println("Average duration for SUCCESSFUL search for load factor of " + testClosed1.getLoadFactor() + 
      //      " is " + avg + "nanoseconds");
      
      
    sumNumProbes = 0; 
    for (int i = 0; i < numProbesList.size(); i++)
        sumNumProbes += numProbesList.get(i) ;
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\nUNSUCCESSFULSEARCH");
    writer.write("\n\nLoadFactor: " + testClosed1.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
  
    //same process but for many hashTables, varying load factors (from .9 to .1 ) 
    
    loadFactorLimit = 1; 
    for (int q = 0; q < 9; q ++) 
    {
        
        testClosed1.clearHashTable();
    //System.out.println("hash table after clearing: " );
    //testOpen1.printHashTable();
    
    //now, testOpen1 is empty; our first load factor was 9.577, we want to test avg 
    //duration and num probes for load factor of 9, 8, 7, 6, 5,4, 3, 2,1 
    //start with 9, keep inserting elements from hashEntries until load factor is 9
    loadFactorLimit -= .1; 
    
    
    j = 0; 
    while(testClosed1.getLoadFactor() < (loadFactorLimit ))
    {
        
        testClosed1.insertHF1(hashEntries.get(j));
        j++; 
        
    }
    //writer.write("\nj = " + j); //j = number of entries inserted 
    //
    //LOAD FACTOR = 9, WE WANT AVG DURATION AND AVG NUM PROBES FOR SUCCESSFUL SEARCH ON THIS HASHTABLE//first 
    //
    durationList.clear();
    numProbesList.clear();
    long duration; 
    for (int a = 0; a < j ; a ++ )
    {
        index = rand.nextInt(j);
        startTime = System.nanoTime();
        testClosed1.getHF1(hashEntries.get(index));
        endTime = System.nanoTime();
        duration = endTime - startTime; 
        durationList.add(duration);
        numProbesList.add(testClosed1.getNumProbes());
    }
    sumDurations = 0;
    sumNumProbes = 0; 
   // System.out.println("duration list size: " + durationList.size());
    //System.out.println("numProbes list size: " + numProbesList.size());
    for (int b = 0; b < durationList.size(); b ++) 
    {
        sumDurations += durationList.get(b);
        sumNumProbes += numProbesList.get(b);
    }
    durationAvg = (double) sumDurations/durationList.size();
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\nSUCCESSFULSEARCH");
    writer.write("\nLoadFactor: " + testClosed1.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //now want avg duration and avg num probes for unsuccessful search on this hashtable
    durationList.clear();
    numProbesList.clear();
     
    for (int a = 0; a < (hashEntries.size() - (j+1)) ; a ++ )
    {
        index = ThreadLocalRandom.current().nextInt((j+1), hashEntries.size());
        startTime = System.nanoTime();
        testClosed1.getHF1(hashEntries.get(index));
        endTime = System.nanoTime();
        duration = endTime - startTime; 
        durationList.add(duration);
        numProbesList.add(testClosed1.getNumProbes());
    }
    sumDurations = 0;
    sumNumProbes = 0; 
    //System.out.println("duration list size: " + durationList.size());
    //System.out.println("numProbes list size: " + numProbesList.size());
    for (int b = 0; b < durationList.size(); b ++) 
    {
        sumDurations += durationList.get(b);
        sumNumProbes += numProbesList.get(b);
    }
    durationAvg = (double) sumDurations/durationList.size();
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    
    writer.write("\n\nUNSUCCESSFULSEARCH"); 
    writer.write("\nLoadFactor: " + testClosed1.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    }// end loop for finding average durations/num probes for each load factor with FIRST hash function
    
    
    
    //test second hash function
    writer.write("\n\n\nTESTINGQUADRATICPROBINGWITHSECONDHASHFUNCTION\n");
    ClosedHashing testClosed2 = new ClosedHashing(); 
    
    loadFactorLimit = 1.1; 
    for (int q = 0; q < 10; q ++) 
    {
        
        testClosed2.clearHashTable();
    //System.out.println("hash table after clearing: " );
    //testOpen1.printHashTable();
    
    //now, testOpen1 is empty; our first load factor was 9.577, we want to test avg 
    //duration and num probes for load factor of 9, 8, 7, 6, 5,4, 3, 2,1 
    //start with 9, keep inserting elements from hashEntries until load factor is 9
    loadFactorLimit -= .1; 
    
    
    j = 0; 
    while(testClosed2.getLoadFactor() < (loadFactorLimit ))
    {
        
        testClosed2.insertHF2(hashEntries.get(j));
        j++; 
        
    }
    //writer.write("\nj = " + j); //j = number of entries inserted 
    //
    //LOAD FACTOR = 9, WE WANT AVG DURATION AND AVG NUM PROBES FOR SUCCESSFUL SEARCH ON THIS HASHTABLE//first 
    //
    durationList.clear();
    numProbesList.clear();
    long duration; 
    for (int a = 0; a < j ; a ++ )
    {
        index = rand.nextInt(j);
        startTime = System.nanoTime();
        testClosed2.getHF2(hashEntries.get(index));
        endTime = System.nanoTime();
        duration = endTime - startTime; 
        durationList.add(duration);
        numProbesList.add(testClosed2.getNumProbes());
    }
    sumDurations = 0;
    sumNumProbes = 0; 
   // System.out.println("duration list size: " + durationList.size());
    //System.out.println("numProbes list size: " + numProbesList.size());
    for (int b = 0; b < durationList.size(); b ++) 
    {
        sumDurations += durationList.get(b);
        sumNumProbes += numProbesList.get(b);
    }
    durationAvg = (double) sumDurations/durationList.size();
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    writer.write("\n\nSUCCESSFULSEARCH");
    writer.write("\nLoadFactor: " + testClosed2.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    
    
    
    //now want avg duration and avg num probes for unsuccessful search on this hashtable
    durationList.clear();
    numProbesList.clear();
     
    for (int a = 0; a < (hashEntries.size() - (j+1)) ; a ++ )
    {
        index = ThreadLocalRandom.current().nextInt((j+1), hashEntries.size());
        startTime = System.nanoTime();
        testClosed2.getHF2(hashEntries.get(index));
        endTime = System.nanoTime();
        duration = endTime - startTime; 
        durationList.add(duration);
        numProbesList.add(testClosed2.getNumProbes());
    }
    sumDurations = 0;
    sumNumProbes = 0; 
    //System.out.println("duration list size: " + durationList.size());
    //System.out.println("numProbes list size: " + numProbesList.size());
    for (int b = 0; b < durationList.size(); b ++) 
    {
        sumDurations += durationList.get(b);
        sumNumProbes += numProbesList.get(b);
    }
    durationAvg = (double) sumDurations/durationList.size();
    numProbesAvg = (double) sumNumProbes / numProbesList.size();
    
    writer.write("\n\nUNSUCCESSFULSEARCH"); 
    writer.write("\nLoadFactor: " + testClosed2.getLoadFactor());
    writer.write("\nDurationAvg: " + durationAvg);
    writer.write("\nNumberofProbesAvg: " + numProbesAvg);
    }
    
    System.out.println("FINISHED!");
    
    
    
    
    
    
    
    
    writer.close();
    
    
    }
    catch(IndexOutOfBoundsException e) {System.out.println("WOW");}
    catch(IOException e) {System.out.println("IO Exception!");}
    
    
    }
    //catch(IOException e) {System.out.println("IO Exception");}
    //catch( IndexOutOfBoundsException e1) {System.out.println(" index out of bounds EXCEPTION THROWN");}
    
    //end main try block 
    }
    
    
    //need to plot load factor affect on successful search and unsuccessful search 
    


