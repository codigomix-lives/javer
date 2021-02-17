package br.com.codigomix.javer;

import java.io.File;
import java.io.FileFilter;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Favor informar a classe, biblioteca ou diretorio");
            System.exit(1);
        }

        String path = args[0];

        File file = new File(path);

        JaverService.printJavaVersion(file);

    }
}
