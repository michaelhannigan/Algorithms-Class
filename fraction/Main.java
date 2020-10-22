package fraction;
import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class Main{
	
	private BigInteger n;
	private BigInteger d;
	private boolean switches = false;
	BigInteger big0 = new BigInteger("0");
	BigInteger big1 = new BigInteger("1");
	BigInteger big2 = new BigInteger("2");

	public static void main(String[] args) throws FileNotFoundException {

		final BigInteger TOTAL = new BigInteger("100000000");

		File file = new File("input.txt"); 
		Scanner scan = new Scanner(file); 
		BigInteger DJT= scan.nextBigInteger();
		BigInteger DJTJR = scan.nextBigInteger();

		
		Main test = new Main();
		test.generateMAGA(TOTAL, DJT, DJTJR);

	}
	
	Main(){
		
	}
	
	Main(BigInteger n, BigInteger d){
		this.n = n;
		this.d = d;
	}
	
	public Main add(Main left, Main right) {
		
		BigInteger numerator = left.n.add(right.n);
		BigInteger denominator = left.d.add(right.d);
		
		Main result = new Main(numerator, denominator);
		return result;
	}
	
	
	public void generateKAG(Main trump, Main leftAncestor,
		    Main rightAncestor, BigInteger index, BigInteger total, BigInteger M, BigInteger N)
	{
		if (switches) {
			return;
		}
		Main leftChild;
		Main rightChild;
		BigInteger leftIndex = (index.multiply(big2)).add(big1);
		BigInteger rightIndex = (index.multiply(big2)).add(big2);

		if (index.compareTo(total) > 0)
			return;

		BigInteger num =  ((N.multiply(trump.n.pow(2)))).subtract((M.multiply((trump.d.pow(2))))).abs();
		if (num.compareTo(trump.d) < 0) {
//			System.out.println(trump.n);
//			System.out.println(trump.d);
			FileWriter writer = null;
			try {
				writer = new FileWriter("output.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println(trump.n);
			printWriter.println(trump.d);
			printWriter.close();
			switches = true;
			return;
		}
		
		leftChild = add(trump, leftAncestor);
		rightChild = add(trump, rightAncestor);

		generateKAG(leftChild, leftAncestor, trump, leftIndex, total, M, N);
		generateKAG(rightChild, trump, rightAncestor, rightIndex, total, M, N);

}
	
	public void generateMAGA(BigInteger total, BigInteger M, BigInteger N)
		{
		
		Main left = new Main(big0,big1);
		Main right = new Main(big1,big0);
		Main trump = new Main(big1,big1);
	
		generateKAG(trump, left, right, big0, total, M, N);
		}
	

	
	
}