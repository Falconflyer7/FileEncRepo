package examples;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestMain {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {

		String key = "KeyStringWithLea";


		TestMain main = new TestMain();
		
		List <IEncryptionFramework> ciphers = new LinkedList<>();
		ciphers.add(new FileEnc2());
		ciphers.add(new FileEnc5DES());
		ciphers.add(new FileEnc4AES());
		
		
		for (IEncryptionFramework cipher : ciphers) {
			
			System.out.println("Running " + cipher.encryptionType());
			long duration = cipher.runEnc(key, "original.txt", "encrypted.txt");
			System.out.println("Encrypt Runtime: " + duration);
			duration = cipher.runDec(key, "encrypted.txt", "decrypted.txt");
			System.out.println("Decrypt Runtime: " + duration);
			boolean decryptSuccess = cipher.decryptSuccess("original.txt", "decrypted.txt");
			System.out.println("Encrypt/decrypt success: " + decryptSuccess);
			
			long bytes = cipher.fileSize("encrypted.txt");
			System.out.println("File Size for encryption: ");
			System.out.println(String.format("%,d bytes", bytes));
            System.out.println(String.format("%,d kilobytes", bytes / 1024));
            bytes = cipher.fileSize("decrypted.txt");
            System.out.println("File Size for decrypted: ");
            System.out.println(String.format("%,d bytes", bytes));
            System.out.println(String.format("%,d kilobytes", bytes / 1024));
			System.out.println();

		}
		
	}
}




