package examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class FileEncExample4AES implements IEncryptionInterface{

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
	IllegalBlockSizeException, BadPaddingException, IOException {
		var key = "markrutorial.com";

		System.out.println("File input: " + "original.txt");

		//encryptedFile
		encryptedFile(key, "original.txt", "encrypted.txt");

		//decryptedFile
		decryptedFile(key, "encrypted.txt", "decrypted.txt");
	}

	public static void encryptedFile(String secretKey, String fileInputPath, String fileOutPath)
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

		System.out.println("File successfully encrypted!");
		System.out.println("New File: " + fileOutPath);
	}

	public static void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)
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

		System.out.println("File successfully decrypted!");
		System.out.println("New File: " + fileOutPath);
	}

	@Override
	public void run(String key, String inputFile, String encryptedFile, String decryptedFile) {
		try {
			encryptedFile(key, inputFile, encryptedFile);
			decryptedFile(key, encryptedFile, decryptedFile);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
