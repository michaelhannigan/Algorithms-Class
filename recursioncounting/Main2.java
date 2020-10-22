package recursioncounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
	static BigInteger zero = BigInteger.valueOf(0);
	static int total = 0;
	static PrintWriter printWriter;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileWriter writer = new FileWriter("output.txt");
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
		
		//long start = System.currentTimeMillis();
		for(int i = 0; i<xArray.length; i++) {
			for(int j = 0; j<abArray.size(); j+=2) {
					gcd(abArray.get(j),abArray.get(j+1),xArray[i]);
				
		}
	
	

			printWriter.println(xArray[i] + " "+total);
			System.out.println(xArray[i] + " "+total);
            total=0;
		}
		//long end = System.currentTimeMillis();
		
		printWriter.close();
	}




public static BigInteger gcd(BigInteger a, BigInteger b, int n) {
	BigInteger num = BigInteger.valueOf(n);
	boolean leftCheck = a.mod(num).equals(zero);
    boolean rightCheck = b.mod(num).equals(zero);
    boolean addPoints = true;
	
	
	if(a.equals(zero)||b.equals(zero))
		return zero;
	
	if(leftCheck && rightCheck) {
		total += 2;
        a = a.divide(num);
        b = b.divide(num);
		return gcd(a, b, n).multiply(num);
	}
	if(leftCheck && !rightCheck) {
		if(addPoints) 
			total++;
        a = a.divide(num);
		return gcd(a, b, n);
		
	}
	if(!leftCheck && rightCheck) {
		if(addPoints) 
			total++;
        b = b.divide(num);
		return gcd(a, b, n);
	}
	else
		addPoints = false;
        a = a.max(b).subtract(a.max(b));
        b = a.min(b);
		return gcd(a, b, n);
	
}
}
