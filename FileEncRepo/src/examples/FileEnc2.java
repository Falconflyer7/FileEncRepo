package examples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class FileEnc2 extends AbstractEncryptionFramework{

	public void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");

		cipher.init(Cipher.ENCRYPT_MODE, desKey);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		doCopy(cis, os);
	}

	public void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");

		cipher.init(Cipher.DECRYPT_MODE, desKey);
		CipherOutputStream cos = new CipherOutputStream(os, cipher);
		doCopy(is, cos);

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
		return "DES implementation 1";
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

}
