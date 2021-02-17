package br.com.codigomix.javer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class JaverService {

    private static Map<Integer, String> javaSeVersions = new HashMap<>();

    static {
        javaSeVersions.put(50, "Java SE 6.0");
        javaSeVersions.put(49, "Java SE 5.0");
        javaSeVersions.put(48, "JDK 1.4");
        javaSeVersions.put(47, "JDK 1.3");
        javaSeVersions.put(46, "JDK 1.2");
        javaSeVersions.put(45, "JDK 1.1");
    }

    private JaverService(){
    }

    public static void printJavaVersion(File file) {

        if (file.isDirectory()) {

            ExtensionFilter filter = new ExtensionFilter(new String[]{"jar", "class"});

            File[] files = file.listFiles(filter);

            if (null == files || files.length == 0) {
                System.out.println("No .class or .jar files found!");
                System.exit(0);
            }

            for (File f : files){
                printJarOrClassJavaVersion(f);
            }
        } else {
            printJarOrClassJavaVersion(file);
        }

    }

    private static void printJarOrClassJavaVersion(File f) {
        if (f.getName().endsWith(".class")){
            JaverService.printJavaVersion(f, null);
        } else if (f.getName().endsWith(".jar")){
            JaverService.printJarJavaVersion(f);
        } else {
            System.out.println(f + " not implemented");
            System.exit(1);
        }
    }

    private static void printJarJavaVersion(File f) {

        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(f));

            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null){
                if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".class")){
                    File tmpOutput = File.createTempFile("class", ".class");
                    tmpOutput.deleteOnExit();
                    FileOutputStream fos = new FileOutputStream(tmpOutput);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    printJavaVersion(tmpOutput, f.getName());
                    break;
                }
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static void printJavaVersion(File file, String title) {
        try {
            Path pathObj = file.toPath();
            title = title == null ? file.getName() : title;

            System.out.println(title);
            System.out.print("Compiled version: ");

            if (pathObj.toFile().exists()){
                byte[] readBytes = Files.readAllBytes(pathObj);
                int major = readBytes[6] + readBytes[7];
                System.out.println( javaSeVersions.getOrDefault(major, guessJavaSeVersion(major)) );
            } else {
                System.out.println( "File not found!" );
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println( "Error trying to find the version" + e.getMessage() );
            System.exit(1);
        }
    }

    private static String guessJavaSeVersion(int major) {
        return "Java SE " + (major - 44);
    }
}
