//package examples;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStreamReader;
//import java.security.NoSuchAlgorithmException;
//
//import javax.crypto.Cipher;
//import javax.crypto.CipherInputStream;
//import javax.crypto.CipherOutputStream;
//import javax.crypto.KeyGenerator;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//
//public class CipherFileExample3AES {
//
//	
//	private SecretKey secretKey;
//	private Cipher cipher;
//	
//	CipherFileExample3AES(SecretKey secretKey, String transformation) {
//		this.secretKey = secretKey;
//		try {
//			this.cipher = Cipher.getInstance(transformation);
//		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void whenEncryptingIntoFile_andDecryptingFileAgain_thenOriginalStringIsReturned() {
//		String originalContent = "foobar";
//		SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
//
//		CipherFileExample3AES fileEncrypterDecrypter
//		= new CipherFileExample3AES(secretKey, "AES/CBC/PKCS5Padding");
//		fileEncrypterDecrypter.encrypt(originalContent, "baz.enc");
//
//		String decryptedContent = fileEncrypterDecrypter.decrypt("baz.enc");
////		assertThat(decryptedContent, is(originalContent));
//
//		new File("baz.enc").delete(); // cleanup
//	}
//
//
//
//	void encrypt(String content, String fileName) {
//		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//		byte[] iv = cipher.getIV();
//
//		try (FileOutputStream fileOut = new FileOutputStream(fileName);
//				CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
//			fileOut.write(iv);
//			cipherOut.write(content.getBytes());
//		}
//	}
//
//
//	String decrypt(String fileName) {
//		String content;
//
//		try (FileInputStream fileIn = new FileInputStream(fileName)) {
//			byte[] fileIv = new byte[16];
//			fileIn.read(fileIv);
//			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));
//
//			try (
//					CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
//					InputStreamReader inputReader = new InputStreamReader(cipherIn);
//					BufferedReader reader = new BufferedReader(inputReader)
//					) {
//
//				StringBuilder sb = new StringBuilder();
//				String line;
//				while ((line = reader.readLine()) != null) {
//					sb.append(line);
//				}
//				content = sb.toString();
//			}
//
//		}
//		return content;
//	}
//
//}
