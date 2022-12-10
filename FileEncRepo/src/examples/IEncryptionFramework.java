package examples;

/**
 * Framework interface
 * Provides basic needed methods to all inherited classes
 * @author Mark Fish
 *
 */
public interface IEncryptionFramework {

	long runEncrypt(String key, String inputFile, String encryptedFile);
	
	long runDecrypt(String key, String encryptedFile, String decryptedFile);
	
	String getAlgorithmDescription();
	
	String getAlgorithmType();

	boolean decryptSuccess (String inputFile, String decryptedFile);
	
	long fileSize(String inputFile);

}
