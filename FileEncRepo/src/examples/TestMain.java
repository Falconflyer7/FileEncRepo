package examples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestMain {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {

		String key = "KeyStringWithLea";


		TestMain main = new TestMain();
		//		main.example2();
		//		main.example4();
		//		main.example5();
		main.runE2(key, "original.txt", "encrypted.txt", "decrypted.txt");
		main.runE4(key, "original.txt", "encrypted.txt", "decrypted.txt");
		main.runE5(key, "original.txt", "encrypted.txt", "decrypted.txt");

		//main.AES.enc(key, filepath,outputpath)

	}

	//	private void example2() {
	//
	//		try {
	//			String key = "mypassword"; // needs to be at least 8 characters for DES
	//
	//			FileInputStream fis = new FileInputStream("original.txt");
	//			FileOutputStream fos = new FileOutputStream("encrypted.txt");
	//			CipherFileExample2.encrypt(key, fis, fos);
	//
	//			FileInputStream fis2 = new FileInputStream("encrypted.txt");
	//			FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
	//			CipherFileExample2.decrypt(key, fis2, fos2);
	//		} catch (Throwable e) {
	//			e.printStackTrace();
	//		}
	//	}	

	private void runE2(String key, String inputFile, String encryptedFile, String decryptedFile) {

		try {
			FileInputStream fis = new FileInputStream(inputFile);
			FileOutputStream fos = new FileOutputStream(encryptedFile);
			CipherFileExample2.encrypt(key, fis, fos);

			FileInputStream fis2 = new FileInputStream(encryptedFile);
			FileOutputStream fos2 = new FileOutputStream(decryptedFile);
			CipherFileExample2.decrypt(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}


	private void runE4(String key, String inputFile, String encryptedFile, String decryptedFile) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		//		  var key = "markrutorial.com";

		//		  System.out.println("File input: " + "original.txt");

		//encryptedFile
		FileEncExample4AES.encryptedFile(key, inputFile, encryptedFile);

		//decryptedFile
		FileEncExample4AES.decryptedFile(key, encryptedFile, decryptedFile);
	}

	//	private void example5() {
	//		try {
	//			String key = "squirrel123"; // needs to be at least 8 characters for DES
	//
	//			FileInputStream fis = new FileInputStream("original.txt");
	//			FileOutputStream fos = new FileOutputStream("encrypted.txt");
	//			FileEncExample5DES.encrypt(key, fis, fos);
	//
	//			FileInputStream fis2 = new FileInputStream("encrypted.txt");
	//			FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
	//			FileEncExample5DES.decrypt(key, fis2, fos2);
	//		} catch (Throwable e) {
	//			e.printStackTrace();
	//		}
	//	}
	//	

	private void runE5(String key, String inputFile, String encryptedFile, String decryptedFile) {
		try {

			FileInputStream fis = new FileInputStream(inputFile);
			FileOutputStream fos = new FileOutputStream(encryptedFile);
			FileEncExample5DES.encrypt(key, fis, fos);

			FileInputStream fis2 = new FileInputStream(encryptedFile);
			FileOutputStream fos2 = new FileOutputStream(decryptedFile);
			FileEncExample5DES.decrypt(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
