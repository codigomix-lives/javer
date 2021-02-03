package br.com.codigomix.javer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class JaverService {

    private static Map<Integer, String> javaSeVersions = new HashMap<>();

    static {
        javaSeVersions.put(61, "Java SE 17");
        javaSeVersions.put(60, "Java SE 16");
        javaSeVersions.put(59, "Java SE 15");
        javaSeVersions.put(58, "Java SE 14");
        javaSeVersions.put(57, "Java SE 13");
        javaSeVersions.put(56, "Java SE 12");
        javaSeVersions.put(55, "Java SE 11");
        javaSeVersions.put(54, "Java SE 10");
        javaSeVersions.put(53, "Java SE 9");
        javaSeVersions.put(52, "Java SE 8");
        javaSeVersions.put(51, "Java SE 7");
        javaSeVersions.put(50, "Java SE 6.0");
        javaSeVersions.put(49, "Java SE 5.0");
        javaSeVersions.put(48, "JDK 1.4");
        javaSeVersions.put(47, "JDK 1.3");
        javaSeVersions.put(46, "JDK 1.2");
        javaSeVersions.put(45, "JDK 1.1");
    }


    private JaverService(){

    }

    public static void printJavaVersion(String pathOfTheClass) {
        printJavaVersion(pathOfTheClass, null);
    }

    public static void printJavaVersion(String pathOfTheClass, String title) {
        try {
            Path path = Path.of(pathOfTheClass);
            if (title == null){
                int separator = pathOfTheClass.lastIndexOf(File.separator);
                title = pathOfTheClass.substring(separator + 1);
            }
            System.out.println(title);
            System.out.print("Compiled version: ");

            if (path.toFile().exists()){
                byte[] readBytes = Files.readAllBytes(path);
                int major = readBytes[6] + readBytes[7];
                System.out.println( javaSeVersions.getOrDefault(major, guessJavaSeVersion(major)) );
            } else {
                System.out.println( "File not found!" );
            }

        } catch (IOException e) {
            System.err.println( "Error trying to find the version" + e.getMessage() );
        }
    }

    private static String guessJavaSeVersion(int major) {
        return "Java SE " + (major - 44) + " (guessed)";
    }
}
