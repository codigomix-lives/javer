package br.com.codigomix.javer.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;

public class ExtensionFilter implements FileFilter {

    private String[] extensions;

    public ExtensionFilter(String[] extensions){
        Objects.requireNonNull(extensions, "extensions can't be null");
        if (extensions.length == 0){
            throw new IllegalArgumentException("extensions can't be empty");
        }
        this.extensions = extensions;
    }

    @Override
    public boolean accept(File pathname) {

        for (String ext : extensions){
            if (pathname.getName().endsWith("." + ext))
                return true;
        }
        return false;

    }
}
