package examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class BruteForceTest {

	private static final String PW_PATH = "rockyou-truncated2.txt";
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {

		int iterations = 1;
		String userPassword = "password1";
 
		try {
			userPassword = getRandomPassword(PW_PATH);
			System.out.println(userPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		createEncryptedFile(userPassword);
		
		BufferedReader pwBank = new BufferedReader(new FileReader(PW_PATH));
		String pw = pwBank.readLine();
		while (pw != null) {
			
			boolean results = bruteForceTry(pw);
			if (results) {
				System.out.println("Success after " + iterations + " number passes");
				break;
			}
		
			iterations++;
			pw = pwBank.readLine();
		}
	}

	private static void createEncryptedFile(String userPassword) {
		IEncryptionFramework cipher = new FileEnc2();
		System.out.println("Running " + cipher.getAlgorithmDescription());
		long duration = cipher.runEncrypt(hashPasswordToKey(10, userPassword), "original.txt", "encrypted.txt");
		System.out.println("Encrypt Runtime: " + duration);
	}

	private static boolean bruteForceTry(String pw) {
		IEncryptionFramework cipher = new FileEnc2();

			long duration;
			duration = cipher.runDecrypt(hashPasswordToKey(10, pw), "encrypted.txt", "decrypted.txt");
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
			
			return decryptSuccess;

	}

	private static String hashPasswordToKey(int neededSize, String userpassword) {

		try {
			SecretKey secretKey = getKeyFromPassword(userpassword, "GenericSalt");
			
			String keyString = convertSecretKeyToString(secretKey);
			System.out.println(keyString);
			return keyString;

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
		
			e.printStackTrace();
		}

		return userpassword;
	}

	public static SecretKey getKeyFromPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
		SecretKey originalKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return originalKey;
	}
	
	public static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
		byte[] rawData = secretKey.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(rawData);
		return encodedKey;
	}
	
	public static SecretKey convertStringToSecretKeyto(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		return originalKey;
	}

	public static String getRandomPassword(String filePath) throws Exception {

        File file = new File(filePath); 
        final RandomAccessFile f = new RandomAccessFile(file, "r");
        final long randomLocation = (long) (Math.random() * f.length());
        f.seek(randomLocation);
        f.readLine();
        String randomLine = f.readLine();
        f.close();
        return randomLine;
    }

}




