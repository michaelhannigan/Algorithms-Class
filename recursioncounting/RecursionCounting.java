package recursioncounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RecursionCounting {
    static int total = 0;
    static BigInteger zero = BigInteger.valueOf(0);
    static PrintWriter printWriter;
    static int r;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        
        
        FileWriter writer = new FileWriter("/Users/michaelhannigan/Documents/CSULB Fall 20/CS 328/Prog 2 test files/OutputTesting.txt");
        printWriter = new PrintWriter(writer);
        
        File file = new File("/Users/michaelhannigan/Documents/CSULB Fall 20/CS 328/Prog 2 test files/inputTest.txt"); 
        Scanner scan = new Scanner(file);
        int n = scan.nextInt();
        
        
        int[] xArray = new int[n];
        for(int i =0; i<n; i++) {
            xArray[i] = scan.nextInt();
        }
        
        ArrayList<BigInteger> abArray = new ArrayList<BigInteger>();

        while(scan.hasNextBigInteger()) {
            abArray.add(scan.nextBigInteger()); 
        }
        
        
        long start = System.currentTimeMillis();
        for(int i = 0; i< xArray.length; i++) {
            for(int j = 0; j < abArray.size()-1; j+=2) {
                gcd(abArray.get(j), abArray.get(j+1), xArray[i]);
            }
            
           
            
            
            printWriter.println(xArray[i] + " "+total);
            
            System.out.println(xArray[i] + " "+total);
            total=0;
            
            
            
        }
        long end = System.currentTimeMillis();
        System.out.println("xArray time ---> "+ (end-start));

        printWriter.close();
        System.out.println(r);
}
    
    static void gcd(BigInteger a, BigInteger b, int n) {
    	BigInteger div = BigInteger.valueOf(n);
        boolean leftCheck;
        boolean rightCheck;
        boolean add_points = true;

        for (;;) {
            leftCheck = a.mod(div).equals(zero);
            rightCheck = b.mod(div).equals(zero);
            
            if (a.equals(zero) || b.equals(zero))
                return;
            
            if (leftCheck && rightCheck) {
                total += 2;
                a = a.divide(div);
                b = b.divide(div);
                continue;
            }

            if (leftCheck && !rightCheck) {
                if (add_points)
                    total++;
                a = a.divide(div);
                continue;
            }
            
            if (!leftCheck && rightCheck) {
                if (add_points)
                    total++;
                b = b.divide(div);
                continue;
            }
            
            if (!leftCheck && !rightCheck) {
                BigInteger atmp = a.max(b).subtract(a.max(b));
                BigInteger btmp = a.min(b);
                a = atmp;
                b = btmp;
            	//a = a.max(b).subtract(a.min(b));
                add_points = false;
            }
        }
    } 

        
        
    
}