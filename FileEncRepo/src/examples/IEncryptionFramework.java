package examples;

public interface IEncryptionFramework {

	long run(String key, String inputFile, String encryptedFile, String decryptedFile);
	long runEnc(String key, String inputFile, String encryptedFile);
	long runDec(String key, String encryptedFile, String decryptedFile);
	
	
	String encryptionType();

	boolean decryptSuccess (String inputFile, String decryptedFile);
	long fileSize(String inputFile);

}
