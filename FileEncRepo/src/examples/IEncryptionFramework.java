package examples;

public interface IEncryptionFramework {

	long runEnc(String key, String inputFile, String encryptedFile);
	long runDec(String key, String encryptedFile, String decryptedFile);
	
	
	String encryptionType();
	
	String getAlgorithmType();

	boolean decryptSuccess (String inputFile, String decryptedFile);
	long fileSize(String inputFile);

}
