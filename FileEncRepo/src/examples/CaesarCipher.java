package examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CaesarCipher extends AbstractEncryptionFramework {
	public static void main(String...s){
		

	//	private void encrypt(String message, int key) {
	//		String encryptedMessage = "";
	//		char ch;
	//		
	//		for(int i = 0; i < message.length(); ++i){
	//			ch = message.charAt(i);
	//			if(ch >= 'a' && ch <= 'z'){
	//				ch = (char)(ch + key);
	//
	//				if(ch > 'z'){
	//					ch = (char)(ch - 'z' + 'a' - 1);
	//				}
	//
	//				encryptedMessage += ch;
	//			}
	//			else if(ch >= 'A' && ch <= 'Z'){
	//				ch = (char)(ch + key);
	//
	//				if(ch > 'Z'){
	//					ch = (char)(ch - 'Z' + 'A' - 1);
	//				}
	//
	//				encryptedMessage += ch;
	//			}
	//			else {
	//				encryptedMessage += ch;
	//			}
	//		}
	//		
	//		System.out.println("Encrypted Message = " + encryptedMessage);
	//	}

	private char encrypt(char ch, int key) {

		if(ch >= 'a' && ch <= 'z'){
			ch = (char)(ch + key);

			if(ch > 'z'){
				ch = (char)(ch - 'z' + 'a' - 1);
			}

			return ch;
		}
		else if(ch >= 'A' && ch <= 'Z'){
			ch = (char)(ch + key);

			if(ch > 'Z'){
				ch = (char)(ch - 'Z' + 'A' - 1);
			}

			return ch;
		}
		else {
			return ch;
		}
	}

	//	private void decrypt(String message, int key) {
	//		String decryptedMessage = "";
	//		char ch;
	//
	//		for(int i = 0; i < message.length(); ++i){
	//			ch = message.charAt(i);
	//			if(ch >= 'a' && ch <= 'z'){
	//				ch = (char)(ch - key);
	//
	//				if(ch < 'a'){
	//					ch = (char)(ch + 'z' - 'a' + 1);
	//				}
	//
	//				decryptedMessage += ch;
	//			}
	//			else if(ch >= 'A' && ch <= 'Z'){
	//				ch = (char)(ch - key);
	//
	//				if(ch < 'A'){
	//					ch = (char)(ch + 'Z' - 'A' + 1);
	//				}
	//
	//				decryptedMessage += ch;
	//			}
	//			else {
	//				decryptedMessage += ch;
	//			}
	//		}
	//
	//		System.out.println("Decrypted Message = " + decryptedMessage);
	//	}

	private char decrypt(char ch, int key) {

		if(ch >= 'a' && ch <= 'z'){
			ch = (char)(ch - key);

			if(ch < 'a'){
				ch = (char)(ch + 'z' - 'a' + 1);
			}

			return ch;
		}
		else if(ch >= 'A' && ch <= 'Z'){
			ch = (char)(ch - key);

			if(ch < 'A'){
				ch = (char)(ch + 'Z' - 'A' + 1);
			}

			return ch;
		}
		else {
			return ch;
		}
	}



	public long runEnc(String key, String inputFile, String encryptedFile) {
		long startTime = System.nanoTime();
		try {
			FileInputStream fis = new FileInputStream(inputFile);
			FileOutputStream fos = new FileOutputStream(encryptedFile);
			encrypt(key, fis, fos);

		} catch (Throwable e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	@Override
	public long runDec(String key, String encryptedFile, String decryptedFile) {
		long startTime = System.nanoTime();
		try {
			FileInputStream fis2 = new FileInputStream(encryptedFile);
			FileOutputStream fos2 = new FileOutputStream(decryptedFile);
			decrypt(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	@Override
	public void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		int size = key.length();
		try {
			while (is.available() != -1){
				char ch = (char) is.read();
				char encryptedChar = encrypt(ch, size);
				os.write(encryptedChar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		int size = key.length();
		try {
			while (is.available() != -1){
				char ch = (char) is.read();
				char decryptedChar = decrypt(ch, size);
				os.write(decryptedChar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String encryptionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean decryptSuccess(String inputFile, String decryptedFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long run(String key, String inputFile, String encryptedFile, String decryptedFile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		
	}
}