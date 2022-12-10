package examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class FileEnc4AES extends AbstractEncryptionFramework{

	public void encryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		var key = new SecretKeySpec(secretKey.getBytes(), "AES");
		var cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		var fileInput = new File(fileInputPath);
		var inputStream = new FileInputStream(fileInput);
		var inputBytes = new byte[(int) fileInput.length()];
		inputStream.read(inputBytes);

		var outputBytes = cipher.doFinal(inputBytes);

		var fileEncryptOut = new File(fileOutPath);
		var outputStream = new FileOutputStream(fileEncryptOut);
		outputStream.write(outputBytes);

		inputStream.close();
		outputStream.close();
	}

	public void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		var key = new SecretKeySpec(secretKey.getBytes(), "AES");
		var cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);

		var fileInput = new File(fileInputPath);
		var inputStream = new FileInputStream(fileInput);
		var inputBytes = new byte[(int) fileInput.length()];
		inputStream.read(inputBytes);

		byte[] outputBytes = cipher.doFinal(inputBytes);

		var fileEncryptOut = new File(fileOutPath);
		var outputStream = new FileOutputStream(fileEncryptOut);
		outputStream.write(outputBytes);

		inputStream.close();
		outputStream.close();
	}


	@Override
	public long runEncrypt(String key, String inputFile, String encryptedFile) {
		long startTime = System.nanoTime();
		try {
			encryptedFile(key, inputFile, encryptedFile);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	@Override
	public long runDecrypt(String key, String encryptedFile, String decryptedFile) {
		long startTime = System.nanoTime();
		try {
			decryptedFile(key, encryptedFile, decryptedFile);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	@Override
	public String getAlgorithmDescription() {
		return "AES implementation";
	}

	@Override
	public void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		// Unneccessary for this particular implementation
		
	}

	@Override
	public void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		// Unneccessary for this particular implementation
		
	}

	@Override
	public String getAlgorithmType() {
		return "AES";
	}
	
	
}
