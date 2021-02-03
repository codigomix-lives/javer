package br.com.codigomix.javer;

import java.io.File;
import java.io.FileFilter;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Favor informar a classe, biblioteca ou diretorio");
            System.exit(1);
        }

        String pathOfTheClass = args[0];

        //String pathOfTheClass = "D:\\Desenv\\CodigoMix\\lives\\code\\javer\\out\\production\\javer\\br\\com\\codigomix\\javer\\";

        boolean isClassFile = pathOfTheClass.endsWith(".class");
        boolean isJarFile = pathOfTheClass.endsWith(".jar");

        File file = new File(pathOfTheClass);

        if (isClassFile){
            JaverService.printJavaVersion(pathOfTheClass);
        } else if (isJarFile) {
            /* TODO - verificar o arquivo compactado.
                    - Encontrar na lista de entries um arquivo .class
                    - extrair somente um .class
                    - testa este unico arquivo
            JaverService.printJavaVersion(pathOfTheClass, "nome do jar");
             */
        } else if (file.isDirectory()) {

            /*
            TODO - verificar .jar
            - Melhorar filter
             */

            FileFilter filter = new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".class") || pathname.getName().endsWith(".jar");
                }
            };

            String files[] = file.list();
            for (String s : files){
                if (s.endsWith(".class")){
                    JaverService.printJavaVersion(file.getPath() + File.separator + s);
                } else {
                    System.out.println(s + " not implemented");
                }
            }
        }
    }
}
