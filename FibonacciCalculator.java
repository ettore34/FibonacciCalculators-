/************************************************************** 
Purpose/Description: calculates Fibonacci numbers in sub-linear time
Author’s Panther ID: 4818809
Certification:
I hereby certify that this work is my own and none of it is the work of any other person.
**************************************************************/
import java.math.BigInteger;
import java.util.Scanner;
public class lab1part3 {
     
 
    public static void main (String[] args) 
    {
        System.out.println("\n\nfib number : 250 = "+ fib(250));
       
       //prompt user for input 
        Scanner reader = new Scanner(System.in); 
        System.out.println("Enter a number: ");
        int n = reader.nextInt();
        reader.close();

        System.out.println("the fib number :" + n + " = " + fib(n));
    }

    public static BigInteger fib(int n)
    {
        BigInteger[][] base = new BigInteger[2][2];
        if(n == 1|| n ==2)
        {
            return new BigInteger("1");
        }
        else if (n ==0)
        {
            return new BigInteger("0");
        }
        else
        {
            // default values 
            base[0][0] = new BigInteger("1");
            base[0][1] = new BigInteger("1");
            base[1][0] = new BigInteger("1");
            base[1][1] = new BigInteger("0");

            //calculates an exponent log time 
            base = expBySquaring(base, n-1);
            
            //the sum of these two components of the matrix equal the fib number
            BigInteger answer = base[0][1].add(base[1][1]);

            return answer;
        }

    }

    //multiplies 2 matrix 
    public static BigInteger[][] matrixMultiply(BigInteger[][] firstBase, BigInteger[][] secondBase )
    {
        BigInteger[][] newBase = new BigInteger[2][2];

        //first position
        newBase[0][0] = firstBase[0][0].multiply(secondBase[0][0]);
        newBase[0][0] = newBase[0][0].add(firstBase[0][1].multiply(secondBase[1][0]));

        //second position
        newBase[0][1] = firstBase[0][0].multiply(secondBase[0][1]);
        newBase[0][1] = newBase[0][1].add(firstBase[0][1].multiply(secondBase[1][1]));

        ////third position
        newBase[1][0] = firstBase[1][0].multiply(secondBase[0][0]);
        newBase[1][0] = newBase[1][0].add(firstBase[1][1].multiply(secondBase[1][0]));  

        //fourth position
        newBase[1][1] = firstBase[1][0].multiply(secondBase[0][1]);
        newBase[1][1] = newBase[1][1].add(firstBase[1][1].multiply(secondBase[1][1]));  

        return newBase;

    }


//exponentiation by squaring 
    public static BigInteger[][] expBySquaring(BigInteger[][] base, int power)
    {       

        if(power <= 1)
        {
            return base;
        }

        if(power%2 == 0)
        {
            //expBySquaring(base^2 , power)
            return expBySquaring( matrixMultiply(base,base), (power / 2) );
        }
         //base * expBySquaring(base^2 , power)
        return matrixMultiply (base, ( expBySquaring( matrixMultiply(base,base), (power / 2) ) ));
    }



}
