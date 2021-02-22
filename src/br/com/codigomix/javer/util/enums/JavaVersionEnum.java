package br.com.codigomix.javer.util.enums;

import java.util.HashMap;
import java.util.Map;

public enum JavaVersionEnum {

    JAVA_SE_1_1(45, "JDK 1.1"),
    JAVA_SE_1_2(46, "JDK 1.2"),
    JAVA_SE_1_3(47, "JDK 1.3"),
    JAVA_SE_1_4(48, "JDK 1.4"),
    JAVA_SE_5_0(49, "JDK 5.0"),
    JAVA_SE_6_0(50, "JDK 6.0"),
    JAVA_SE_7(51, "Java SE 7"),
    JAVA_SE_8(52, "Java SE 8"),
    JAVA_SE_9(53, "Java SE 9"),
    JAVA_SE_10(54, "Java SE 10"),
    JAVA_SE_11(55, "Java SE 11"),
    JAVA_SE_12(56, "Java SE 12"),
    JAVA_SE_13(57, "Java SE 13"),
    JAVA_SE_14(58, "Java SE 14"),
    JAVA_SE_15(59, "Java SE 15"),
    JAVA_SE_16(60, "Java SE 16"),
    JAVA_SE_17(61, "Java SE 17");

    private String description;
    private int majorVersion;
    private static Map<Integer, JavaVersionEnum> versions = new HashMap<>();

    static {
        for (JavaVersionEnum e : values()) {
            versions.put(e.majorVersion, e);
        }
    }

    JavaVersionEnum(Integer majorVersion, String description){
        this.majorVersion = majorVersion;
        this.description = description;
    }

    public static JavaVersionEnum getJavaVersion(Integer majorVersion){
        return versions.get(majorVersion);
    }

    public String getJavaVersion(JavaVersionEnum javaVersionEnum){
        return javaVersionEnum.description;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public String getDescription() {
        return description;
    }
}

