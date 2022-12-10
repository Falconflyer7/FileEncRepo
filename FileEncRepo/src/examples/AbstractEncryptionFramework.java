package examples;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Abstract parent class
 * Framework for some basic universal methods needed across multiple subclasses
 * @author Mark Fish
 *
 */
public abstract class AbstractEncryptionFramework implements IEncryptionInterface, IEncryptionFramework {


	protected long filesCompareByByte(Path path1, Path path2) throws IOException {
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

	protected void doCopy(InputStream is, OutputStream os) throws IOException {
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
	public long fileSize(String inputFile) {
		Path path = Paths.get(inputFile);

		try {

			// size of a file (in bytes)
			long bytes = Files.size(path);
			return bytes;

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public boolean decryptSuccess(String inputFile, String decryptedFile) {
		try {

			Path path1 = Paths.get(inputFile);
			Path path2 = Paths.get(decryptedFile);
			long result = filesCompareByByte(path1,path2);

			return result == -1;

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
