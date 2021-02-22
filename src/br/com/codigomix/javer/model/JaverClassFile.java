package br.com.codigomix.javer.model;

import br.com.codigomix.javer.error.JaverException;
import br.com.codigomix.javer.util.JaverComponents;

import java.nio.file.Path;

public class JaverClassFile extends JaverComponent {
	
	private Path path;

	public static JaverClassFile of(Path p) {
		if(!JaverComponents.isJaverClassFile(p))
			throw new IllegalArgumentException("Illegal attempt to create a JaverClassFile object");
		return new JaverClassFile(p);
	}

	private JaverClassFile(Path path) {
		this.path = path;
	}

	public void printVersion() throws JaverException {
		super.printVersion(path);
	}
}
