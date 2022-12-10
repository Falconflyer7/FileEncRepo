package examples;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class TestMain {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {

		String key = "123abc123abc123abc123abc";

		List <IEncryptionFramework> ciphers = new LinkedList<>();
		ciphers.add(new FileEnc2());
		ciphers.add(new FileEnc5DES());
		ciphers.add(new FileEnc4AES());
		ciphers.add(new CaesarCipher());

		for (IEncryptionFramework cipher : ciphers) {

			System.out.println("Running " + cipher.encryptionType());
			String algorithmType = cipher.getAlgorithmType();
			long duration = cipher.runEnc(hashPasswordToKey(algorithmType, key), "original.txt", "encrypted.txt");
			System.out.println("Encrypt Runtime: " + duration/1000 + " microseconds");
			duration = cipher.runDec(hashPasswordToKey(algorithmType, key), "encrypted.txt", "decrypted.txt");
			System.out.println("Decrypt Runtime: " + duration/1000 + " microseconds");
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

	private static String hashPasswordToKey(String algorithmType, String userpassword) {

		try {
			SecretKey secretKey = getKeyFromPassword(userpassword, "GenericSalt", algorithmType);
			
			String keyString = convertSecretKeyToString(secretKey);
			System.out.println(keyString);
			return keyString;

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return userpassword;
	}

	public static SecretKey getKeyFromPassword(String password, String salt, String algorithmType)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		if (algorithmType.equalsIgnoreCase("DESede")) {
			return getDESedeKeyFromPassword(password, salt);
		} else {
			return getAESKeyFromPassword(password, salt);
		}
	}
	
	public static SecretKey getDESedeKeyFromPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		DESedeKeySpec spec;
		try {
			spec = new DESedeKeySpec(password.getBytes());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			SecretKey originalKey = factory.generateSecret(spec);
			return originalKey;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static SecretKey getAESKeyFromPassword(String password, String salt)
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
}




