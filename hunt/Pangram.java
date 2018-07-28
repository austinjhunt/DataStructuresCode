/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci230.hw1;


import java.util.Scanner;
/**
 *
 * @author austinhunt
 * @since Jan 11, 2017 
 * Purpose: This program determines whether a string input by the user IS or IS
 * NOT a pangram (a sentence containing all letters of the alphabet); does not 
 * check grammar / semantics
 */
public class Pangram {

    /**
     * @param input
     * @return boolean
     */
    
    /* isPangram returns boolean value representing whether or not input string 
    is a pangram
    */
    public static boolean isPangram(String input)
    {
        /*if string doesn't have 26 characters, it can't be a pangram*/
        
        if (input.length()<26)
            return false;
        
        /*for each character of the alphabet, check to see if it has an index
        in the passed string argument; if not, its index position is -1; if the 
        index of any alphabet character is -1, then the passed string
        argument can't be a pangram (because every alphabet character must be present)
        */
        for (char checkLetter = 'a'; checkLetter <= 'z'; checkLetter ++)
        {
            if (input.indexOf(checkLetter) == -1)
                return false;
        }
        
        /*if the for loop finishes without returning false, that means every 
        character of the alphabet is contained in the input string, so return true
        */
        return true; 
        
    }
    
    /*test isPangram*/
    
    public static void main(String[] args) {
       
        //prompt user to enter string
        
        System.out.println("Please enter a string for pangram testing:");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        
        /* because case doesn't matter for this assignment, convert every character
        of the input string to lower case
        */
        input = input.toLowerCase();
        
        //if isPangram returns true, sentence is a pangram, vice versa
        if (isPangram(input))
            System.out.println("This sentence IS a pangram");
        else
            System.out.println("This sentence IS NOT a pangram.");
    }
        
        
        
        
        
       
    }
    

