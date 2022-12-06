package examples;

public interface IEncryptionFramework {

	long run(String key, String inputFile, String encryptedFile, String decryptedFile);
	long runEnc(String key, String inputFile, String encryptedFile, String decryptedFile);
	long runDec(String key, String inputFile, String encryptedFile, String decryptedFile);
	
	
	String encryptionType();


}
