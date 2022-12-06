package examples;

public class CaesarCipher {
	public static void main(String...s){
		CaesarCipher main = new CaesarCipher();
		
		String message = "generic message";
		int key = 3;
		
		main.encrypt(message, key);
		main.decrypt(message, key);
	}
	
	private void encrypt(String message, int key) {
		String encryptedMessage = "";
		char ch;
		
		for(int i = 0; i < message.length(); ++i){
			ch = message.charAt(i);
			if(ch >= 'a' && ch <= 'z'){
				ch = (char)(ch + key);

				if(ch > 'z'){
					ch = (char)(ch - 'z' + 'a' - 1);
				}

				encryptedMessage += ch;
			}
			else if(ch >= 'A' && ch <= 'Z'){
				ch = (char)(ch + key);

				if(ch > 'Z'){
					ch = (char)(ch - 'Z' + 'A' - 1);
				}

				encryptedMessage += ch;
			}
			else {
				encryptedMessage += ch;
			}
		}
		
		System.out.println("Encrypted Message = " + encryptedMessage);
	}
	
	private void decrypt(String message, int key) {
		String decryptedMessage = "";
		char ch;

		for(int i = 0; i < message.length(); ++i){
			ch = message.charAt(i);
			if(ch >= 'a' && ch <= 'z'){
				ch = (char)(ch - key);

				if(ch < 'a'){
					ch = (char)(ch + 'z' - 'a' + 1);
				}

				decryptedMessage += ch;
			}
			else if(ch >= 'A' && ch <= 'Z'){
				ch = (char)(ch - key);

				if(ch < 'A'){
					ch = (char)(ch + 'Z' - 'A' + 1);
				}

				decryptedMessage += ch;
			}
			else {
				decryptedMessage += ch;
			}
		}

		System.out.println("Decrypted Message = " + decryptedMessage);
	}
}