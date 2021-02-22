package br.com.codigomix.javer.model;

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
	public void printVersion() {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path p : stream) {
				if (JaverComponents.isJaverClassFile(p)) {
					JaverClassFile.of(p).printVersion();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
