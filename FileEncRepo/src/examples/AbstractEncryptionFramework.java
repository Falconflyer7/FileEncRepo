package examples;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

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

}
