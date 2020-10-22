package recursioncounting;


import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class IterativeInteger {
	static int total = 0;
	static boolean latch = false;
	static boolean stop = false;
	static int multiplier = 1;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file = new File("/Users/michaelhannigan/Documents/CSULB Fall 20/CS 328/Prog 2 test files/input.txt"); 

		Scanner scan = new Scanner(file);
		int n = scan.nextInt();
		
		int[] xArray = new int[n];
		for(int i =0; i<n; i++) {
			xArray[i] = scan.nextInt();
		}
		
		ArrayList<Integer> abArray = new ArrayList<Integer>();
		while(scan.hasNextInt()) {
			abArray.add(scan.nextInt());
		
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
			System.out.println(xArray[i] + " "+total);
			total=0;
		}


}
	
	
	static int gcd(int a, int b, int n) {
	
	
		int gcd = 0;
		
		Stack<int[]> stack =new Stack<int[]>();
		
		int[] temp = {a,b};
		stack.add(temp);
		
		while(stack.peek()[0] !=0 && stack.peek()[1]!= 0) {
			
			if(stack.peek()[0] % n == 0 && stack.peek()[1] % n == 0) {
				total +=2;
				temp = stack.pop();
				temp[0] = temp[0]/n;
				temp[1] = temp[1]/n;
//				System.out.println(temp[0]+ " "+ temp[1]);
				stack.push(temp);
				stop = true;
				multiplier *=n;
			}
			
			if(stop == false && stack.peek()[0] % n == 0 && stack.peek()[1] % n != 0) {
				if(latch == false) {
					total +=1;
				}
					temp = stack.pop();
					temp[0] = temp[0]/n;
//					System.out.println(temp[0]+ " "+ temp[1]);
					stack.push(temp);
					stop = true;
				
			}
			
			if(stop == false && stack.peek()[0] % n != 0 && stack.peek()[1] % n == 0) {
				if(latch == false) {
					total +=1;
				}
					temp = stack.pop();
					temp[1] = temp[1]/n;
//					//System.out.println(temp[0]+ " "+ temp[1]);
					stack.push(temp);
					stop = true;
			}
			if(stop == false  && stack.peek()[0] % n != 0 && stack.peek()[1] % n != 0) {
				temp = stack.pop();
				
				int t1 = Math.max(temp[0], temp[1])-Math.min(temp[0], temp[1]);
				int t2 = Math.min(temp[0], temp[1]);
				temp[0] = t1;
				temp[1] = t2;
//				//System.out.println(temp[0]+ " "+ temp[1]);
				stack.push(temp);
				latch = true;
				
				
			}
			stop = false;
	}
	
		if(stack.peek()[0]== 0) {
			gcd = stack.peek()[1]*multiplier;
		}if(stack.peek()[1]== 0) {
			gcd = stack.peek()[0]*multiplier;
		}if(stack.peek()[0] == 0 && stack.peek()[1] == 0){
			gcd = 0;
		}
		multiplier = 1;
//		//System.out.println(gcd);
//		//System.out.println(total);
		return gcd;
	}
	
}
