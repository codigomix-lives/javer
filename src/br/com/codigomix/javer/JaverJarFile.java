package br.com.codigomix.javer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JaverJarFile extends JaverComponent {

	private Path path;

	public static JaverJarFile of(Path p) {
		if (!JaverComponents.isJaverJarFile(p))
			throw new IllegalArgumentException("Illegal attempt to create a JaverClassFile object");
		return new JaverJarFile(p);
	}

	public static JaverJarFile of(String s) {
		Path p = Paths.get(s);
		return of(p);
	}

	private JaverJarFile(Path path) {
		this.path = path;
	}

	@Override
	public void printVersion() {

		try {
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(path.toFile()));

			ZipEntry zipEntry = zipInputStream.getNextEntry();

			while (zipEntry != null) {

				if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".class")) {

					File tmpOutput = File.createTempFile("aFile", ".class");
					tmpOutput.deleteOnExit();

					FileOutputStream fos = new FileOutputStream(tmpOutput);

					byte[] buffer = new byte[1024];
					int len;
					while ((len = zipInputStream.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}

					fos.close();

					printVersion(tmpOutput.toPath());

				}

				zipEntry = zipInputStream.getNextEntry();
			}

			zipInputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
