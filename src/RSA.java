import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class RSA
{

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int p,q,n,z,d=0,e,i;
		final HashMap<String, Integer> messageCode = new HashMap<String, Integer>();
		String messageCode1 = "";
		DataInputStream in = new DataInputStream(System.in);
		String teststring1 = null;		
		int msg;
		double c;
		BigInteger msgback;
		String encryptedMsg = "";
		String decryptedMsg = "";
		List<BigInteger> decriptionCode = new ArrayList<BigInteger>();

		HashMap<String, Integer> mapper = new HashMap<String, Integer>() {{
			put("A", 10);
			put("B", 20);
			put("C", 30);	
			put("D", 40);
			put("E", 50);
			put("F", 60);
			put("G", 70);
			put("H", 80);
			put("I", 90);
			put("J", 15);
			put("K", 25);
			put("L", 35);
			put("M", 45);
			put("N", 55);
			put("O", 65);
			put("P", 75);
			put("Q", 85);
			put("R", 95);
			put("S", 12);
			put("T", 22);
			put("U", 32);
			put("V", 42);
			put("W", 52);
			put("X", 62);
			put("Y", 72);
			put("Z", 82);
			put(" ", 44);

		}};
		 
		System.out.println("Enter the plain text:");
		try {
			teststring1 = in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Enter 1st prime number p");
		p=sc.nextInt();
		System.out.println("Enter 2nd prime number q");
		q=sc.nextInt();

		n=p*q;
		System.out.println("The value of n = "+n);	
		z=(p-1)*(q-1);
		System.out.println("The value of z = "+z);		

		for(e=2;e<z;e++)
		{
			if(gcd(e,z)==1)            // e is for public key exponent
			{				
				break;
			}
		}
		System.out.println("The value of e = "+e);				
		for(i=0;i<=9;i++)
		{
			int x=1+(i*z);
			if(x%e==0)      //d is for private key exponent
			{
				d=x/e;
				break;
			}
		}
		System.out.println("The value of d = "+d);	
		BigInteger N = BigInteger.valueOf(n);
	
		String messageUpperCase = teststring1.toUpperCase();
		for(int k=0;k<messageUpperCase.length();k++){
			for(Map.Entry<String, Integer> entry: mapper.entrySet()) {
				if(entry.getKey().equals(Character.toString(messageUpperCase.charAt(k))))
				{
					messageCode1 = messageCode1 + entry.getValue();
					c = (Math.pow(entry.getValue(),e))%n;
					encryptedMsg = encryptedMsg+c;
					BigInteger C = BigDecimal.valueOf(c).toBigInteger();
					msgback = (C.pow(d)).mod(N);
					decriptionCode.add(msgback);
					decryptedMsg = decryptedMsg+msgback;
					break;

				}

			}	

		}

		System.out.println("MessageCode = "+messageCode1);

		System.out.println("Encrypted message = "+encryptedMsg);

		System.out.println("Decrypted message (Cypher text) = "+DecryptMsg(decriptionCode, mapper));

	}

	private static String DecryptMsg(List<BigInteger> decriptionCode, HashMap<String, Integer> mapper) {
		// TODO Auto-generated method stub

		String decryptionMsg = "";
		for(int k=0;k<decriptionCode.size();k++){
			for(Map.Entry<String, Integer> entry: mapper.entrySet()) {
				if(entry.getValue() == decriptionCode.get(k).intValue())
				{
					decryptionMsg = decryptionMsg + entry.getKey();	    		 		
					break;

				}

			}	

		}
		return decryptionMsg;
	}

	static int gcd(int e, int z)
	{
		if(e==0)
			return z;	
		else
			return gcd(z%e,e);
	}

}