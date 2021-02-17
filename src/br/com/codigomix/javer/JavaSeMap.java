package br.com.codigomix.javer;

import java.util.HashMap;
import java.util.Map;

public class JavaSeMap {
	public static final Map<Integer, String> javaSeVersions = new HashMap<>();

    static {
        javaSeVersions.put(50, "Java SE 6.0");
        javaSeVersions.put(49, "Java SE 5.0");
        javaSeVersions.put(48, "JDK 1.4");
        javaSeVersions.put(47, "JDK 1.3");
        javaSeVersions.put(46, "JDK 1.2");
        javaSeVersions.put(45, "JDK 1.1");
    }
}
