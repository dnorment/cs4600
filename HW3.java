/**
  * CS 4600.02 Homework 3 #4
  * @author Daniel Norment
  */


import java.io.*;
import java.security.*;
import java.util.Scanner;
import javax.crypto.*;

public class HW3
{
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException,
		NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		//initialize tools for reading and writing files
		Scanner inputPlain = new Scanner(new File("plaintext.txt"));
		FileOutputStream outputCipher = new FileOutputStream("ciphertext.txt");

		
		//read plaintext input into string and convert into byte array
		String in = "";
		while(inputPlain.hasNext())
		{
			in += inputPlain.nextLine();
		}
		byte[] plainBytes = in.getBytes("UTF8");
		
		
		//generate 128bit AES key
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		keygen.init(128);
		Key key = keygen.generateKey();
		
		
		//create AES cipher object
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		
		//encrypt plaintext with key
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] ciphertext = cipher.doFinal(plainBytes);
		
		
		//write ciphertext to file
		outputCipher.write(ciphertext);
		
		
		//close reader and writer for plaintext and ciphertext
		inputPlain.close();
		outputCipher.close();
		
		
		//initialize tools for reading and writing files
		FileInputStream inputCipher = new FileInputStream(new File("ciphertext.txt"));
		FileOutputStream outputPlain = new FileOutputStream("decryptedtext.txt");
		
		
		//read ciphertext into byte array
		inputCipher.read(ciphertext);
		
		
		//decrypt ciphertext with key
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedtext = cipher.doFinal(ciphertext);
		
		
		//write decrypted text to file
		outputPlain.write(decryptedtext);
		
		
		//close reader and writer for ciphertext and decryptedtext
		inputCipher.close();
		outputPlain.close();
	}
}
