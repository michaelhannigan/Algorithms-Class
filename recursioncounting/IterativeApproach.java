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

public class IterativeApproach {
	static int total = 0;
	static boolean latch = false;
	static boolean stop = false;
	static BigInteger multiplier = BigInteger.valueOf(1);
	static BigInteger zero = BigInteger.valueOf(0);
	static PrintWriter printWriter;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file = new File("/Users/michaelhannigan/Documents/CSULB Fall 20/CS 328/Prog 2 test files/input.txt"); 
		Scanner scan = new Scanner(file);
		int n = scan.nextInt();
		
		
		BigInteger[] xArray = new BigInteger[n];
		for(int i =0; i<n; i++) {
			xArray[i] = scan.nextBigInteger();
			
		}
		
		ArrayList<BigInteger> abArray = new ArrayList<BigInteger>();
		

		

		while(scan.hasNextBigInteger()) {
			abArray.add(scan.nextBigInteger()); 
		}
		
		
		
		for(int i = 0; i<xArray.length; i++) {
			for(int j = 0; j<abArray.size()-1; j+=2) {
				if(j==0) {
					gcd(abArray.get(j),abArray.get(j+1),xArray[i]);
					latch = false;
				}else {
					gcd(abArray.get(j),abArray.get(j+1),xArray[i]);
					latch = false;
				}
			}
			
			FileWriter writer = null;
			try {
				writer = new FileWriter("/Users/michaelhannigan/Documents/CSULB Fall 20/CS 328/Prog 2 test files/OutputTesting.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printWriter = new PrintWriter(writer);
			printWriter.println(xArray[i] + " "+total);
			
			System.out.println(xArray[i] + " "+total);
			total=0;
		}
			printWriter.close();

}
	
	static BigInteger gcd(BigInteger a, BigInteger b, BigInteger n) {
	
	
		BigInteger gcd = BigInteger.valueOf(0);
		
		Stack<BigInteger[]> stack =new Stack<BigInteger[]>();
		
		BigInteger[] temp = {a,b};
		stack.add(temp);
		
		boolean leftCheck = stack.peek()[0].mod(n).equals(zero);
		boolean rightCheck = stack.peek()[1].mod(n).equals(zero);
		
		while(!stack.peek()[0].equals(zero) && !stack.peek()[1].equals(zero)){
	
			if(leftCheck && rightCheck) {
				total +=2;
				temp = stack.pop();
				temp[0] = temp[0].divide(n);
				temp[1] = temp[1].divide(n);
//				System.out.println(temp[0]+ " "+ temp[1]);
				stack.push(temp);
				stop = true;
				multiplier = multiplier.multiply(n);
			}

			if(stop == false && leftCheck && !rightCheck ) {
				if(latch == false) {
					total +=1;
				}
					temp = stack.pop();
					temp[0] = temp[0].divide(n);
//					System.out.println(temp[0]+ " "+ temp[1]);
					stack.push(temp);
					stop = true;
				
			}
			
			if(stop == false && !leftCheck && rightCheck) {
				if(latch == false) {
					total +=1;
				}
					temp = stack.pop();
					temp[1] = temp[1].divide(n);
//					System.out.println(temp[0]+ " "+ temp[1]);
					stack.push(temp);
					stop = true;
			}
			if(stop == false  && !leftCheck && !rightCheck) {
				temp = stack.pop();

				BigInteger t2 = temp[0].min(temp[1]);
				BigInteger t1 = temp[0].max(temp[1]).subtract(t2);
				temp[0] = t1;
				temp[1] = t2;
//				System.out.println(temp[0]+ " "+ temp[1]);
				stack.push(temp);
				latch = true;
				
				
			}
			leftCheck = stack.peek()[0].mod(n).equals(zero);
			rightCheck = stack.peek()[1].mod(n).equals(zero);
			stop = false;
	}
	
		if(leftCheck) {
			gcd = stack.peek()[1].multiply(multiplier);
		}if(rightCheck) {
			gcd = stack.peek()[0].multiply(multiplier);
		}if(leftCheck && rightCheck){
			gcd = zero;
		}
		
		multiplier = BigInteger.valueOf(0);
		return gcd;
	}
	
}
