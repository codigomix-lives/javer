package br.com.codigomix.javer.model;

import br.com.codigomix.javer.error.JaverException;
import br.com.codigomix.javer.util.enums.JavaVersionEnum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class JaverComponent {

	/**
	 * This is a common method to get the major version of a .class file.
	 * @param path Path object to check the version
	 */
	public JavaVersionEnum getMajorVersion(Path path) throws JaverException {
		
		try {
			byte[] readBytes;
			readBytes = Files.readAllBytes(path);
			int major = readBytes[6] + readBytes[7];
			return JavaVersionEnum.getJavaVersionEnum(major);
		} catch (IOException e) {
			throw new JaverException("Can't read the file", e);
		}

	}

	/**
	 * This is a default method for files, to be used by subclasses.
	 * @param path Path object to check the version
	 */
	public void printVersion(Path path) throws JaverException {

		JavaVersionEnum version = getMajorVersion(path);

		System.out.println(path.getFileName().toString());
		System.out.println(" Compiled version: " + version.getDescription());

	}

	public abstract void printVersion() throws JaverException;

}
