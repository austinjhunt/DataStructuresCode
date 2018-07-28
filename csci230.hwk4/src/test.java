/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author austinhunt
 */
public class test {
    public static int log(double x, int base)
{
    return (int) (Math.log(x) / Math.log(base));
}
    
    public static void main(String [] args) 
    {
        double loadFactor = .1; 
        for (int i = 0; i < 10 ; i ++ )
        {
            System.out.println(i); 
            
            System.out.println("\nFor load factor " + loadFactor + " : " );
            System.out.println("Success = ((-log(1-LF))/LF) = " + ((-log((1-loadFactor),2))/loadFactor));
            System.out.println("Unsuccess = 1/ 1- LF = " + 1/(1-loadFactor));
            loadFactor += .1; 
                    
            
            
            System.out.println("Log base 2 of 8 is " + log(8,2));
        }
    }
}
