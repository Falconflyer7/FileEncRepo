package examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CaesarCipher {
	public static void main(String...s){
		CaesarCipher main = new CaesarCipher();
		
		String message = "generic message";
		int key = 3;
		
		main.encrypt(message, key);
		main.decrypt(message, key);
	}
	
	private void encrypt(String message, int key) {
		String encryptedMessage = "";
		char ch;
		
		for(int i = 0; i < message.length(); ++i){
			ch = message.charAt(i);
			if(ch >= 'a' && ch <= 'z'){
				ch = (char)(ch + key);

				if(ch > 'z'){
					ch = (char)(ch - 'z' + 'a' - 1);
				}

				encryptedMessage += ch;
			}
			else if(ch >= 'A' && ch <= 'Z'){
				ch = (char)(ch + key);

				if(ch > 'Z'){
					ch = (char)(ch - 'Z' + 'A' - 1);
				}

				encryptedMessage += ch;
			}
			else {
				encryptedMessage += ch;
			}
		}
		
		System.out.println("Encrypted Message = " + encryptedMessage);
	}
	
	private void decrypt(String message, int key) {
		String decryptedMessage = "";
		char ch;

		for(int i = 0; i < message.length(); ++i){
			ch = message.charAt(i);
			if(ch >= 'a' && ch <= 'z'){
				ch = (char)(ch - key);

				if(ch < 'a'){
					ch = (char)(ch + 'z' - 'a' + 1);
				}

				decryptedMessage += ch;
			}
			else if(ch >= 'A' && ch <= 'Z'){
				ch = (char)(ch - key);

				if(ch < 'A'){
					ch = (char)(ch + 'Z' - 'A' + 1);
				}

				decryptedMessage += ch;
			}
			else {
				decryptedMessage += ch;
			}
		}

		System.out.println("Decrypted Message = " + decryptedMessage);
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

	private void encrypt(String key, FileInputStream fis, FileOutputStream fos) {
		// TODO Auto-generated method stub
		
	}
}