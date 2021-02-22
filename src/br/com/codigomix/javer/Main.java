package br.com.codigomix.javer;

import br.com.codigomix.javer.model.JaverClassFile;
import br.com.codigomix.javer.model.JaverComponent;
import br.com.codigomix.javer.model.JaverJarFile;
import br.com.codigomix.javer.util.JaverComponents;

import java.nio.file.Path;

public class Main {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Favor informar a classe, biblioteca ou diretorio");
			System.exit(1);
		}

		Path cmdLinePath = Path.of(args[0]);

		JaverComponent jc = null;

		if (JaverComponents.isJaverClassFile(cmdLinePath)) {
			jc = JaverClassFile.of(cmdLinePath);

		} else if (JaverComponents.isJaverDirectory(cmdLinePath)) {
			jc = JaverJarFile.of(cmdLinePath);

		} else if (JaverComponents.isJaverJarFile(cmdLinePath)) {
			jc = JaverJarFile.of(cmdLinePath);

		} else {
			System.out.println("Invalid path");
			System.exit(1);
		}

		jc.printVersion();

	}
}
