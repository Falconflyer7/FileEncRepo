package examples;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Encryption Interface
 * Standardizes encryption calls across all implemented algorithms
 * @author Mark Fish
 *
 */
public interface IEncryptionInterface {

	void encrypt(String key, InputStream is, OutputStream os) throws Throwable;
	void decrypt(String key, InputStream is, OutputStream os) throws Throwable;

	void decryptedFile(String secretKey, String fileInputPath, String fileOutPath) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException;
	void encryptedFile(String secretKey, String fileInputPath, String fileOutPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException; 
}
