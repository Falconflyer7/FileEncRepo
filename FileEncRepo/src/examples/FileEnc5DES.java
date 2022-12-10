package examples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class FileEnc5DES extends AbstractEncryptionFramework{

	public void encrypt(String inKey, InputStream is, OutputStream os) throws Throwable {
		
		SecretKey key = convertStringToSecretKeyto(inKey);
		// Create and initialize the encryption engine
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// Create a special output stream to do the work for us
		CipherOutputStream cos = new CipherOutputStream(os, cipher);

		// Read from the input and write to the encrypting output stream
		byte[] buffer = new byte[2048];
		int bytesRead;
		while ((bytesRead = is.read(buffer)) != -1) {
			cos.write(buffer, 0, bytesRead);
		}
		cos.close();

		// For extra security, don't leave any plaintext hanging around memory.
		java.util.Arrays.fill(buffer, (byte) 0);

	}

	
	public static SecretKey convertStringToSecretKeyto(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");
		return originalKey;
	}
	
	public void decrypt(String inKey, InputStream is, OutputStream os) throws Throwable {
		SecretKey key = convertStringToSecretKeyto(inKey);
		// Create and initialize the decryption engine
	    Cipher cipher = Cipher.getInstance("DESede");
	    cipher.init(Cipher.DECRYPT_MODE, key);

	    // Read bytes, decrypt, and write them out.
	    byte[] buffer = new byte[2048];
	    int bytesRead;
	    while ((bytesRead = is.read(buffer)) != -1) {
	    	os.write(cipher.update(buffer, 0, bytesRead));
	    }

	    // Write out the final bunch of decrypted bytes
	    os.write(cipher.doFinal());
	    os.flush();
	  }

	@Override
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
	public String encryptionType() {
		return "DES implementation 2";
	}

	@Override
	public void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		// Unneccessary for this particular implementation

	}

	@Override
	public void encryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		// Unneccessary for this particular implementation

	}


	@Override
	public String getAlgorithmType() {
		return "DESede";
	}









	//////



	//	public void desede() {
	//		
	//		byte[] secretKey = "9mng65v8jf4lxn93nabf981m".getBytes();
	//		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");
	//		byte[] iv = "a76nb5h9".getBytes();
	//		IvParameterSpec ivSpec = new IvParameterSpec(iv);
	//		String secretMessage = "Baeldung secret message";
	//		Cipher encryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
	//		encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
	//		byte[] secretMessagesBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
	//		byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessagesBytes);
	//		Cipher decryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
	//		decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
	//		byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
	//		String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
	//		
	//		String originalContent = "Secret Baeldung message";
	//		Path tempFile = Files.createTempFile("temp", "txt");
	//		writeString(tempFile, originalContent);
	//		byte[] fileBytes = Files.readAllBytes(tempFile);
	//		Cipher encryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
	//		encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
	//		byte[] encryptedFileBytes = encryptCipher.doFinal(fileBytes);
	//		try (FileOutputStream stream = new FileOutputStream(tempFile.toFile())) {
	//		    stream.write(encryptedFileBytes);
	//		    encryptedFileBytes = Files.readAllBytes(tempFile);
	//		    Cipher decryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
	//		    decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
	//		    byte[] decryptedFileBytes = decryptCipher.doFinal(encryptedFileBytes);
	//		    try (FileOutputStream stream = new FileOutputStream(tempFile.toFile())) {
	//		        stream.write(decryptedFileBytes);
	//		        
	//		    }
	//		    String fileContent = readString(tempFile);
	//		    Assertions.assertEquals(originalContent, fileContent);
	//		}
	//		
	//	}




}