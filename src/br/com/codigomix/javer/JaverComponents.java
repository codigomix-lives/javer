package br.com.codigomix.javer;

import java.nio.file.Files;
import java.nio.file.Path;

public class JaverComponents {
	public static boolean isJaverClassFile(Path p) {
		return Files.exists(p) && Files.isRegularFile(p) && p.toString().endsWith(".class");
	}
	
	public static boolean isJaverJarFile(Path p) {
		return Files.exists(p) && Files.isRegularFile(p) && p.toString().endsWith(".jar");
	}
	
	public static boolean isJaverDirectory(Path p) {
		return Files.exists(p) && Files.isDirectory(p);
	}
}
