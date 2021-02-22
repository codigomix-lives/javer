package br.com.codigomix.javer.model;

import br.com.codigomix.javer.util.enums.JavaVersionEnum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class JaverComponent {
	
	/**
	 * This is a default method for files, to be used by subclasses.
	 * @param path Path object to check the version
	 */
	public void printVersion(Path path) {
		
		System.out.println(path.getFileName().toString());
		System.out.print("\n Compiled version: ");

		try {
			byte[] readBytes;
			readBytes = Files.readAllBytes(path);
			int major = readBytes[6] + readBytes[7];
			System.out.println(JavaVersionEnum.getJavaVersion(major).getDescription());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void printVersion();

}
