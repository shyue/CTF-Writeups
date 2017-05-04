import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import org.apache.commons.codec.binary.Base64;


public class sha1digest {
	
	public static LinkedList<Integer> stringList;
	
	public static void genString(){
		stringList = new LinkedList<Integer>();
		for (int i = 0; i<9*8*7*6*5*4*3; i++){
			
			int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9};
			int n = 7;
			int choices = 9;
			int compartment = 9*8*7*6*5*4*3;
			int result = 0;
			int holdI = i;
			
			while (n>0){
				compartment = compartment/choices;
				int box = holdI/(compartment);
				result = 10*result+digits[box];
				holdI-=box*(compartment);
				choices--;
				n--;
				digits[box] = digits[choices];
			}
			stringList.add(result);
		}
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
	    genString(); //generating all possible strings to check
	    System.out.println("Strings generated");
	    
		for (int i:stringList){
			
	    	String val = Integer.toHexString(i); //generate hex string
	    	while (val.length()<6){  //pad hex string with begining 0s
	    		val = "0"+val;
	    	}
	    	val = val.toUpperCase();

	    	
	    	//writing to AndoridManifest.xml 
	    	RandomAccessFile raf = new RandomAccessFile("AndroidManifest.xml", "rw"); 
	    	try {
	    	    raf.seek(2074); 
	    	    raf.write((int)Long.parseLong(val.substring(0,2), 16)); 
	    	    raf.seek(2073);
	    	    raf.write((int)Long.parseLong(val.substring(2,4), 16));
	    	    raf.seek(2072);
	    	    raf.write((int)Long.parseLong(val.substring(4), 16));
	    	} finally {
	    	    raf.close(); // Flush/save changes and close resource.
	    	}
	    
	    	
	    //generating sha-1 digest
		MessageDigest md = MessageDigest.getInstance("sha-1");
	    FileInputStream in =  new FileInputStream("AndroidManifest.xml");
	    int bytes = 0;
	    while ((bytes = in.read()) != -1) {
	        md.update((byte)bytes);
	    }
	    in.close();
	    byte[] thedigest = md.digest();
	    
	    //comparing value to what it should be
	    if ((Base64.encodeBase64String(thedigest).trim()).equals("F0T3vG9oImHgTmMPeAu0dfJ0sVk=")){
	    	System.out.println(i); //answer
	    	break;
	    }
	    }
	}
}
