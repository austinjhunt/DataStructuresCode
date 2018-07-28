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
 * @since Jan 12, 2017
 * Purpose: This program determines whether a decimal number input by the user
 * has an EVEN or ODD binary parity. 
 */
public class BinaryParity {
    
    /* recursive method that converts the decimal input to binary; the method 
    recursively calls itself until it reaches one of two base cases: input = 0 or 
    input = 1
    */
    public static int convertToBinary(int input)
    {
        /*create a variable for remainder of input divided by 2 (will be either 0 or 1);
        this is the least significant digit of the binary string
        */
        int remainder = input % 2; 
        
        //base case, return input if input is 0 or 1 (smallest two possibilities)
        if (input == 0 || input == 1) 
            return input; 
        
        /* if input is not 0 or 1, then return remainder + (value returned by 
        convertToBinary(input/2) multiplied by ten); note that if there are n 
        recursive method calls, then the return value of the final recursive 
        method call is the value of the bit in the 2^ns place; when the stack is 
        popped, that value is then multiplied by ten in order to move it one position
        leftward (because this method technically returns a base ten integer, and we
        are simply interpreting it as a binary string), and then the remainder is added to 
        give the value of the least significant bit, and this process repeats until
        the initial method call is reached, at which point the remainder is the 
        least significant bit
        */
        
        else
        { 
           //System.out.println("\nreturn " + remainder + " + convert("+input/2+") * 10");
           return remainder + (convertToBinary(input/2)*10);
        }
        
    }
    
    /*main method calls convertToBinary method on the input decimal number, then
    calculates the number of ones in that resulting binary string; prints EVEN 
    parity if there are even number of ones, ODD parity if there odd number of 
    ones
    */
    
    public static void main(String[]args)
    {
        /*prompt user to input non-negative integer*/
        
        System.out.println("Please enter a non-negative integer value:");
        Scanner keyboard = new Scanner(System.in);
        
        int input = keyboard.nextInt();
        
        /*keep prompting user until they enter an acceptable (non-negative) 
        value*/
        if (input < 0)
        {   while(input < 0)
            {
                System.out.println("Please enter a non-negative integer value:");
                input = keyboard.nextInt();
            }
        }
        
        /* call convertToBinary on the acceptable integer that is input by the user, store 
        this binary value in an int variable named binaryConversion
        */
        int binaryConversion = convertToBinary(input);
        
        //System.out.println("Binary conversion is: " + binaryConversion);
        
        /* to easily count the number of ones in the binary number, convert 
        binaryConversion to a string using Integer wrapper class*/
        
        String binaryString = Integer.toString(binaryConversion);
        //System.out.println("Binary string is: " + binaryString);
        
        /*initialize number of ones to 0, then accumulate using a for loop; check 
        each index value using the string method charAt(index) to see if the character
        at that index is a 1; if so, add one to numOnes*/
        
        int numOnes = 0;
        for (int i = 0; i < binaryString.length();i ++)
        {   if (binaryString.charAt(i) == '1')
                numOnes ++;
        }
        
        /*if numOnes is divisible by two, it's even, which means the input decimal 
        number has even parity
        */
        if (numOnes%2 == 0)
            System.out.println("The number " + input + " has EVEN parity.");
        
        /*if numOnes is NOT divisible by two, it's odd, which means the input decimal 
        number has ODD parity
        */
        
        else 
            System.out.println("The number " + input + " has ODD parity.");
    }
    
    
    
}
