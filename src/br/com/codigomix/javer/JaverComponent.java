package br.com.codigomix.javer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class JaverComponent {
	
	/**
	 * This is a default method for files, to be used by subclasses.
	 * @param path
	 */
	public void printVersion(Path path) {
		
		System.out.println(path.getFileName().toString());
		System.out.print("\n Compiled version: ");

		try {
			byte[] readBytes;
			readBytes = Files.readAllBytes(path);
			int major = readBytes[6] + readBytes[7];
			System.out.println(JavaSeMap.javaSeVersions.getOrDefault(major, inferJavaSeVersion(major)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String inferJavaSeVersion(int major) {
		return "Java SE " + (major - 44);
	}
	
	public abstract void printVersion();

}
