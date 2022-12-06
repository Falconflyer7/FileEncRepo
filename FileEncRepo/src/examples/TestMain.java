package examples;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestMain {

	public static void main(String[] args) {


		TestMain main = new TestMain();
		main.example2();


	}

	private void example2() {

		try {
			String key = "mypassword"; // needs to be at least 8 characters for DES

			FileInputStream fis = new FileInputStream("original.txt");
			FileOutputStream fos = new FileOutputStream("encrypted.txt");
			CipherFileExample2.encrypt(key, fis, fos);

			FileInputStream fis2 = new FileInputStream("encrypted.txt");
			FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
			CipherFileExample2.decrypt(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}	

}
