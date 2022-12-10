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
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileEnc5DES extends AbstractEncryptionFramework{

	public void encrypt(String inKey, InputStream is, OutputStream os) throws Throwable {
		
		SecretKey key = convertStringToSecretKeyto(inKey);
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		CipherOutputStream cos = new CipherOutputStream(os, cipher);

		byte[] buffer = new byte[2048];
		int bytesRead;
		while ((bytesRead = is.read(buffer)) != -1) {
			cos.write(buffer, 0, bytesRead);
		}
		cos.close();

		java.util.Arrays.fill(buffer, (byte) 0);
	}

	
	public static SecretKey convertStringToSecretKeyto(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");
		return originalKey;
	}
	
	public void decrypt(String inKey, InputStream is, OutputStream os) throws Throwable {
		SecretKey key = convertStringToSecretKeyto(inKey);
		
	    Cipher cipher = Cipher.getInstance("DESede");
	    cipher.init(Cipher.DECRYPT_MODE, key);

	    byte[] buffer = new byte[2048];
	    int bytesRead;
	    while ((bytesRead = is.read(buffer)) != -1) {
	    	os.write(cipher.update(buffer, 0, bytesRead));
	    }

	    os.write(cipher.doFinal());
	    os.flush();
	  }

	@Override
	public long runEncrypt(String key, String inputFile, String encryptedFile) {
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
	public long runDecrypt(String key, String encryptedFile, String decryptedFile) {
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
	public String getAlgorithmDescription() {
		return "DESede (triple DES) Implementation";
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

}