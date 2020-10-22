//Michael Hannigan
package magnets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static int n = 10;
	static PrintWriter printWriter;
	static HashSet<LinkedList<Integer>> finalSet = new HashSet<>();
	static HashSet<LinkedList<Integer>> mSet = new HashSet<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		///// building matrix from file
		FileWriter writer = new FileWriter("output.txt");
        printWriter = new PrintWriter(writer);
		
		File file = new File("input.txt"); 
		Scanner scan = new Scanner(file);
		
		n = Integer.parseInt(scan.nextLine());
		
		String line1 = scan.nextLine();
		int cols = line1.length();
		int rows = 2;
		
		char [][] m = new char[rows][cols];
		String[] stringChars = line1.split("");
		for(int i = 0; i<cols; i++) {
			m[0][i] = stringChars[i].charAt(0);
		}
		
		String line2 = scan.nextLine();
		String[] stringChars2 = line2.split("");
		
		for(int i = 0; i<cols; i++) {
			m[1][i] = stringChars2[i].charAt(0);
		}
		

		
		LinkedList<LinkedList<Integer>> initialSet = generateSet(m);
		
		
		initialAdd(m, initialSet);
			
		populate(m);
		
		int count = 0;
		for(LinkedList<Integer> list : finalSet) {
			for(int i: list) {
				if(count<list.size()-1) {
				printWriter.print(i + " ");
				count++;
				}
				else {
					printWriter.print(i);
					count++;
				}
			}
				count=0;
				printWriter.println();
		}
		printWriter.close();
	}
	
		public static void print(char[][]m) {
			int rows = m.length;
			int columns = m[0].length;
			
			for(int i = 0; i<rows; i++) {
				for(int j = 0; j<columns;j++) {
					System.out.print(m[i][j]+ " ");
				}
				System.out.println();
			}
		}
	
		public static char[][] populate(char[][] m){
			int rows = m.length;
			int columns = m[0].length;
			
			for(int i = 0; i<rows; i++) {
				for(int j = 0; j<columns;j++) {
					add(m, i, j);
					
				}
				
			}
			
			return m;
		}
		
		public static LinkedList<LinkedList<Integer>> generateSet(char[][]m) {
			LinkedList<LinkedList<Integer>>set = new LinkedList<LinkedList<Integer>>();
			for(int i = 0; i<m.length; i++) {
				for(int j = 0; j<m[0].length;j++) {
					if(m[i][j]=='+'||m[i][j]=='-') {
						LinkedList<Integer> list = new LinkedList<>();
						list.add(i);
						list.add(j);
						set.add(list);
					}
						
				}
			}
			return set;
		}
		
		// this method will add a magnet to all the initial polls and store in set first
		// before going and adding more magnets
		public static char[][] initialAdd(char[][] m, LinkedList<LinkedList<Integer>> set) {
			if(n<1) {
				return m;
			}
			int rows = m.length;
			int columns = m[0].length;
			char c = '+';
			for(int i = 0; i<m.length; i++) {
				for(int j = 0; j<m[0].length;j++) {
					if(n<1) {
						return m;
					}
					LinkedList<Integer> list = new LinkedList<>();
					list.addLast(i);
					list.addLast(j);
					
					if(set.contains(list)&&!mSet.contains(list)) {
						LinkedList<Integer> finalList = new LinkedList<Integer>();
						LinkedList<Integer> pList = new LinkedList<Integer>();
						LinkedList<Integer> nList = new LinkedList<Integer>();
						if(m[i][j]=='+')
							c = '-';
						else
							c= '+';
						
						if(i+1<rows && initialCheck(m, c, i+1, j)) {
							
							if(m[i+1][j] =='*') {
								m[i+1][j]=c;
							}
								if(m[i][j]=='+') {
									finalList.addLast(i);
									finalList.addLast(j);
									
									pList.addLast(i);
									pList.addLast(j);
									mSet.add(pList);
									finalList.addLast(i+1);
									finalList.addLast(j);
									
									// adding second location to mSet
									nList.addLast(i+1);
									nList.addLast(j);
									mSet.add(nList);
									
									finalSet.add(finalList);
									n--;
								}
								else {
									finalList.addLast(i+1);
									finalList.addLast(j);
									
									pList.addLast(i+1);
									pList.addLast(j);
									
									mSet.add(pList);
									
									finalList.addLast(i);
									finalList.addLast(j);
									
									nList.addLast(i);
									nList.addLast(j);
									
									mSet.add(nList);
									
									finalSet.add(finalList);
									n--;
								}
								
								continue;
							
						}
						if(i-1>=0 && initialCheck(m, c, i-1, j)) {
							
							if(m[i-1][j] =='*') {
								m[i-1][j]=c;
							}
								if(m[i][j]=='+') {
									finalList.addLast(i);
									finalList.addLast(j);
									
									pList.addLast(i);
									pList.addLast(j);
									mSet.add(pList);
									
									finalList.addLast(i-1);
									finalList.addLast(j);
									
									nList.addLast(i-1);
									nList.addLast(j);
									
									mSet.add(nList);
									
									finalSet.add(finalList);
									n--;

								}
								else {
									finalList.addLast(i-1);
									finalList.addLast(j);
									
									pList.addLast(i-1);
									pList.addLast(j);
									
									mSet.add(pList);
									
									finalList.addLast(i);
									finalList.addLast(j);
									
									nList.addLast(i);
									nList.addLast(j);
									
									mSet.add(nList);
									
									finalSet.add(finalList);
									n--;

								}
								
	
								continue;
							
						}
						if(j+1<columns && initialCheck(m, c, i, j+1)) {
							
							if(m[i][j+1] =='*') {
							m[i][j+1]=c;
							}
								if(m[i][j]=='+') {
									finalList.addLast(i);
									finalList.addLast(j);
									
									pList.addLast(i);
									pList.addLast(j);
									
									mSet.add(pList);
									
									finalList.addLast(i+1);
									finalList.addLast(j);
									
									nList.addLast(i+1);
									nList.addLast(j);
									
									mSet.add(nList);
									
									finalSet.add(finalList);
									n--;

								}
								else {
									finalList.addLast(i);
									finalList.addLast(j+1);
									
									pList.addLast(i);
									pList.addLast(j+1);
									
									mSet.add(pList);
									
									finalList.addLast(i);
									finalList.addLast(j);
									
									nList.addLast(i);
									nList.addLast(j);
									
									mSet.add(nList);
									
									finalSet.add(finalList);
									n--;

								}
								
	
								continue;
							
						}
						if(j-1>=0 && initialCheck(m, c, i, j-1)) {
							if(m[i+1][j] =='*') {
							m[i][j-1]=c;
							}
								if(m[i][j]=='+') {
									finalList.addLast(i);
									finalList.addLast(j);
									
									pList.addLast(i);
									pList.addLast(j);
									
									mSet.add(pList);
									
									finalList.addLast(i);
									finalList.addLast(j-1);
									
									nList.addLast(i);
									nList.addLast(j-1);
									
									mSet.add(nList);
									
									finalSet.add(finalList);
									n--;

								}
								else {
									finalList.addLast(i);
									finalList.addLast(j-1);
									
									pList.addLast(i);
									pList.addLast(j-1);
									
									mSet.add(pList);
									
									finalList.addLast(i);
									finalList.addLast(j);
									
									nList.addLast(i);
									nList.addLast(j);
									
									mSet.add(nList);
									finalSet.add(finalList);
									n--;

								}
								
	
								continue;
							
						}
						
					}
						
				}
			}
			
			return m;
		}
		
		private static boolean initialCheck(char[][] m, char c, int row, int col) {
			int rows = m.length;
			int columns = m[0].length;
			LinkedList<Integer> checkList = new LinkedList<>();
			checkList.addLast(row);
			checkList.addLast(col);
			
			if(mSet.contains(checkList))
				return false;
			
			if(row+1<rows) {
				if(c==m[row+1][col])
					return false;
			}
			if(col+1<columns) {
				if(c==m[row][col+1])
					return false;
			}
			if(row-1>=0) {
				if(c==m[row-1][col])
					return false;
			}
			if(col-1>=0) {
				if(c==m[row][col-1])
					return false;
			}
			
			return true;
		}
		
		private static boolean check(char[][] m, char c, int row, int col) {
			int rows = m.length;
			int columns = m[0].length;

			
			if(row+1<rows) {
				if(c==m[row+1][col])
					return false;
			}
			if(col+1<columns) {
				if(c==m[row][col+1])
					return false;
			}
			if(row-1>=0) {
				if(c==m[row-1][col])
					return false;
			}
			if(col-1>=0) {
				if(c==m[row][col-1])
					return false;
			}
			
			return true;
		}
		
		private static char[][] add(char[][] m, int row, int col) {
			if(n <1)
				return m;
			if(m[row][col]!='*')
				return m;
			
			
			int rows = m.length;
			int columns = m[0].length;
			
			char c = '+';

			if(row+1<rows) {
				if(m[row+1][col]=='*') {
					if(check(m, c, row, col) && check(m,'-', row+1, col)) {
						LinkedList<Integer> finalList  = new LinkedList<Integer>();
						m[row][col] = c;
						m[row+1][col] = '-';
						finalList.add(row);
						finalList.add(col);
						finalList.add(row+1);
						finalList.add(col);
						finalSet.add(finalList);
						n--;
						return m;
					}
				}
			}
			
			if(row-1>=0) {
				if(m[row-1][col]=='*') {
					if(check(m, c, row, col) && check(m,'-', row-1, col)) {
						LinkedList<Integer> finalList  = new LinkedList<Integer>();
						m[row][col] = c;
						m[row+1][col] = '-';
						finalList.add(row);
						finalList.add(col);
						finalList.add(row-1);
						finalList.add(col);
						finalSet.add(finalList);
						n--;
						return m;
					}
				}
			}
			
			if(col+1<columns) {
				if(m[row][col+1]=='*') {
					if(check(m, c, row, col) && check(m,'-', row, col+1)) {
						LinkedList<Integer> finalList  = new LinkedList<Integer>();
						m[row][col] = c;
						m[row][col+1] = '-';
						finalList.add(row);
						finalList.add(col);
						finalList.add(row);
						finalList.add(col+1);
						finalSet.add(finalList);
						n--;
						return m;
					}
				}
			}
			
			if(col-1>=0) {
				if(m[row][col-1]=='*') {
					if(check(m, c, row, col) && check(m,'-', row, col-1)) {
						LinkedList<Integer> finalList  = new LinkedList<Integer>();
						m[row][col] = c;
						m[row][col-1] = '-';
						finalList.add(row);
						finalList.add(col);
						finalList.add(row);
						finalList.add(col-1);
						finalSet.add(finalList);
						n--;
						return m;
					}
				}
			}
			
			//////////////////////////Adding negative First/////////////////////
			
			c = '-';
			
			
			if(row+1<rows) {
				if(m[row+1][col]=='*') {
					if(check(m, c, row, col) && check(m,'+', row+1, col)) {
						LinkedList<Integer> finalList  = new LinkedList<Integer>();
						m[row][col] = c;
						m[row+1][col] = '+';
						finalList.add(row+1);
						finalList.add(col);
						finalList.add(row);
						finalList.add(col);
						finalSet.add(finalList);
						n--;
						return m;
					}
				}
			}
			
			if(row-1>=0) {
				if(m[row-1][col]=='*') {
					if(check(m, c, row, col) && check(m,'+', row-1, col)) {
						LinkedList<Integer> finalList  = new LinkedList<Integer>();
						m[row][col] = c;
						m[row+1][col] = '+';
						finalList.add(row-1);
						finalList.add(col);
						finalList.add(row);
						finalList.add(col);
						finalSet.add(finalList);
						n--;
						return m;
					}
				}
			}
			
			if(col+1<columns) {
				if(m[row][col+1]=='*') {
					if(check(m, c, row, col) && check(m,'+', row, col+1)) {
						LinkedList<Integer> finalList  = new LinkedList<Integer>();
						m[row][col] = c;
						m[row][col+1] = '+';
						finalList.add(row);
						finalList.add(col+1);
						finalList.add(row);
						finalList.add(col);
						finalSet.add(finalList);
						n--;
						return m;
					}
				}
			}
			
			if(col-1>=0) {
				if(m[row][col-1]=='*') {
					if(check(m, c, row, col) && check(m,'+', row, col-1)) {
						LinkedList<Integer> finalList  = new LinkedList<Integer>();
						m[row][col] = c;
						m[row][col-1] = '+';
						finalList.add(row);
						finalList.add(col-1);
						finalList.add(row);
						finalList.add(col);
						finalSet.add(finalList);
						n--;
						return m;
					}
				}
			}
			
			
			return m;
		}
		
		
}
