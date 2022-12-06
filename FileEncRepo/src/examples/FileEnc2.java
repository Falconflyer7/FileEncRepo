package examples;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class FileEnc2 implements IEncryptionFramework, IEncryptionInterface{

	public void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}

	@Override
	public long run(String key, String inputFile, String encryptedFile, String decryptedFile) {
		long startTime = System.nanoTime();
		try {
			FileInputStream fis = new FileInputStream(inputFile);
			FileOutputStream fos = new FileOutputStream(encryptedFile);
			encrypt(key, fis, fos);

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
	public void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void encryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub

	}

	@Override
	public String encryptionType() {
		// TODO Auto-generated method stub
		return "DES implementation 1";
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
	//	public boolean contentEquals(FileInputStream input1, FileInputStream input2) throws IOException
	//	  {
	//		input1 = new BufferedInputStream(input1);
	//		input2 = new BufferedInputStream(input2);
	//	    int ch = input1.read();
	//	    while (-1 != ch)
	//	    {
	//	      int ch2 = input2.read();
	//	      if (ch != ch2)
	//	      {
	//	        return false;
	//	      }
	//	      ch = input1.read();
	//	    }
	//
	//	    int ch2 = input2.read();
	//	    return (ch2 == -1);
	//	  }
	//	
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
