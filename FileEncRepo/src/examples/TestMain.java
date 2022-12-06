package examples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class TestMain {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {

		String key = "KeyStringWithLea";


		TestMain main = new TestMain();
		
		List <IEncryptionFramework> ciphers = new LinkedList<>();
		ciphers.add(new FileEnc2());
		ciphers.add(new FileEnc5DES());
		ciphers.add(new FileEnc4AES());
		
		for (IEncryptionFramework cipher : ciphers) {
			cipher.run(key, "original.txt", "encrypted.txt", "decrypted.txt");
		}
		
		
	}
}




