package examples;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class FileEnc4AES implements IEncryptionFramework, IEncryptionInterface{

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

		System.out.println("File successfully encrypted!");
		System.out.println("New File: " + fileOutPath);
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

		System.out.println("File successfully decrypted!");
		System.out.println("New File: " + fileOutPath);
	}

	@Override
	public long run(String key, String inputFile, String encryptedFile, String decryptedFile) {
		long startTime = System.nanoTime();
		try {
			encryptedFile(key, inputFile, encryptedFile);
			decryptedFile(key, encryptedFile, decryptedFile);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		return endTime - startTime;

	}

	@Override
	public void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encryptionType() {
		// TODO Auto-generated method stub
		return "AES implementation 1";
	}

	@Override
	public long runEnc(String key, String inputFile, String encryptedFile) {
		long startTime = System.nanoTime();
		try {
			encryptedFile(key, inputFile, encryptedFile);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	@Override
	public long runDec(String key, String encryptedFile, String decryptedFile) {
		long startTime = System.nanoTime();
		try {
			decryptedFile(key, encryptedFile, decryptedFile);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}
	
	public boolean decryptSuccess(String inputFile, String decryptedFile) {
		try {
			
			Path path1 = Paths.get(inputFile);
			Path path2 = Paths.get(decryptedFile);
			long result = filesCompareByByte(path1,path2);
			
			return result == -1;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public long filesCompareByByte(Path path1, Path path2) throws IOException {
	    try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(path1.toFile()));
	            BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(path2.toFile()))) {
	           
	           int ch = 0;
	           long pos = 1;
	           while ((ch = fis1.read()) != -1) {
	               if (ch != fis2.read()) {
	                   return pos;
	               }
	               pos++;
	           }
	           if (fis2.read() == -1) {
	               return -1;
	           }
	           else {
	               return pos;
	           }
	       }
	   }
}
