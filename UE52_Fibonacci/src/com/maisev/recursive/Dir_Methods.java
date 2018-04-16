package com.maisev.recursive;

import java.io.File;
import java.util.ArrayList;

public class Dir_Methods {
    private ArrayList<String> directories = new ArrayList<>();
    int layer = 0;

    public ArrayList<String> getDirectories(File root) {
        File[] list = root.listFiles();
        StringBuilder tabs = new StringBuilder();

        if (list == null) return null;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                layer++;
                for (int i = 0; i < layer; i++) {
                    tabs.append("-");
                }
                getDirectories(new File(f.getAbsolutePath()));
                directories.add(tabs + " Dir:  " + f.getAbsoluteFile() );
            }
            else {
                for (int i = 0; i < layer; i++) {
                    tabs.append("-");
                }
                directories.add(tabs + ">File:  " + f.getAbsoluteFile() );
            }
        }

        layer--;
        return directories;
    }
}
