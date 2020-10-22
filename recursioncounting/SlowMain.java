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

public class SlowMain{
	static int total = 0;
	static boolean latch = false;
	static boolean stop = false;
	static int multiplier = 1;
	static BigInteger zero = BigInteger.valueOf(0);
	static PrintWriter printWriter;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		FileWriter writer = new FileWriter("out.txt");
		printWriter = new PrintWriter(writer);
		
		File file = new File("input.txt"); 
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
		
		
		
		
		for(int i = 0; i<xArray.length; i++) {
			for(int j = 0; j<abArray.size()-1; j+=2) {
				if(j==0) {
					gcd(abArray.get(j),abArray.get(j+1),xArray[i]);
					latch = false;
				}
			}
		
			printWriter.println(xArray[i] + " "+total);

			total=0;
			
		}
		printWriter.close();
			

}
	
	static BigInteger gcd(BigInteger a, BigInteger b, int n) {
	
	
		BigInteger gcd = BigInteger.valueOf(0);
		BigInteger div = BigInteger.valueOf(n);
		
		Stack<BigInteger[]> stack =new Stack<BigInteger[]>();
		
		BigInteger[] temp = {a,b};
		stack.push(temp);
		
		//boolean zeroCheck = false;
		boolean leftCheck = stack.peek()[0].mod(div).equals(zero);
		boolean rightCheck = stack.peek()[1].mod(div).equals(zero);
		
		while(true){
	
			if(leftCheck && rightCheck) {
				total +=2;
				temp = stack.pop();
				temp[0] = temp[0].divide(div);
				temp[1] = temp[1].divide(div);
//				System.out.println(temp[0]+ " "+ temp[1]);
				stack.push(temp);
				stop = true;
				multiplier *=n;
				
			}

			if(stop == false && leftCheck && !rightCheck ) {
				if(latch == false) {
					total +=1;
				}
					temp = stack.pop();
					temp[0] = temp[0].divide(div);
//					System.out.println(temp[0]+ " "+ temp[1]);
					stack.push(temp);
					stop = true;
				
			}
			
			if(stop == false && !leftCheck && rightCheck) {
				if(latch == false) {
					total +=1;
				}
					temp = stack.pop();
					temp[1] = temp[1].divide(div);
//					System.out.println(temp[0]+ " "+ temp[1]);
					stack.push(temp);
					stop = true;
			}
			if(stop == false  && !leftCheck && !rightCheck) {
				temp = stack.pop();
				temp[1]= temp[0].min(temp[1]);
				temp[0] = temp[0].max(temp[1]).subtract(temp[0].min(temp[1]));
				
//				System.out.println(temp[0]+ " "+ temp[1]);
				stack.push(temp);
				latch = true;
				
				if(stack.peek()[0].equals(zero) || stack.peek()[1].equals(zero)) {
					break;
					//zeroCheck = true;
				}
				
				
			}
			
			leftCheck = stack.peek()[0].mod(div).equals(zero);
			rightCheck = stack.peek()[1].mod(div).equals(zero);
			stop = false;
			
	}
		div = BigInteger.valueOf(multiplier);
		if(leftCheck) {
			gcd = stack.peek()[1].multiply(div);
		}if(rightCheck) {
			gcd = stack.peek()[0].multiply(div);
		}
		multiplier = 1;
		return gcd;
	}
	
}