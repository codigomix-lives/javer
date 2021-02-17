package br.com.codigomix.javer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaFile {
	private Path path;

	private JavaFile(Path p) {
		path = p;
	}

	public static JavaFile of(Path p) {
		return new JavaFile(p);
	}

	public static JavaFile of(String s) {
		Path p = Paths.get(s);
		return new JavaFile(p);
	}

	private String name() {
		return path.getFileName().toString();
	}

	private String guessJavaSeVersion(int major) {
		return "Java SE " + (major - 44) + " (guessed)";
	}

	public void printVersion() {
		System.out.println(name());
		System.out.print("Compiled version: ");
		
		// default behavior is to follow symbolic links
		if (Files.notExists(path)) {
			System.out.println("Either the file don't exists or you don't have permissions to access it");
			return;
		}
		
		try {
			byte[] readBytes;
			readBytes = Files.readAllBytes(path);
			int major = readBytes[6] + readBytes[7];
			System.out.println(guessJavaSeVersion(major));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
