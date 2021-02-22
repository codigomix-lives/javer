package br.com.codigomix.javer.model;

import br.com.codigomix.javer.error.JaverException;
import br.com.codigomix.javer.util.JaverComponents;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class JaverDirectory extends JaverComponent {

	private Path path;

	public static JaverDirectory of(Path path) {
		if (!JaverComponents.isJaverDirectory(path))
			throw new IllegalArgumentException("Illegal attempt to create a JaverDirectory object");

		return new JaverDirectory(path);
	}

	private JaverDirectory(Path path) {
		this.path = path;
	}

	@Override
	public void printVersion() throws JaverException {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path p : stream) {
				if (JaverComponents.isJaverClassFile(p)) {
					JaverClassFile.of(p).printVersion();
				} else if (JaverComponents.isJaverJarFile(p)){
					JaverJarFile.of(p).printVersion();
				}
				// TODO else is just ignoring the file type. A filter in query would be nice!
				// @see ExtensionFilter from old implementation
			}
		} catch (IOException e) {
			throw new JaverException("Error reading directory", e);
		}
	}
}
