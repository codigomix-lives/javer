package br.com.codigomix.javer.model;

import br.com.codigomix.javer.error.JaverException;
import br.com.codigomix.javer.util.JaverComponents;
import br.com.codigomix.javer.util.enums.JavaVersionEnum;

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
	public void printVersion() throws JaverException {

		try {
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(path.toFile()));

			ZipEntry zipEntry = zipInputStream.getNextEntry();

			while (zipEntry != null) {

				if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".class")) {

					File tmpOutput = File.createTempFile("aFile", ".class");
					tmpOutput.deleteOnExit();

					FileOutputStream fos = new FileOutputStream(tmpOutput);

					byte[] buffer = new byte[8];
					int len = zipInputStream.read(buffer);
					if (len > 0){
						fos.write(buffer, 0, len);
					}

					fos.close();

					JavaVersionEnum version = super.getMajorVersion(tmpOutput.toPath());
					System.out.println(path.getFileName().toString());
					System.out.println(" Compiled version: " + version.getDescription());

					// TODO: deep check. We can check all files instead of just the first .class
					break;

				}

				zipEntry = zipInputStream.getNextEntry();
			}

			zipInputStream.close();

		} catch (IOException e) {
			throw new JaverException("Can't read the directory", e);
		}

	}

}
