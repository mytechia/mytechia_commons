/*******************************************************************************
 *   
 *   Copyright 2012 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Victor Sonora
 * 
 *   This file is part of Mytechia Commons.
 *
 *   Mytechia Commons is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Mytechia Commons is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with Mytechia Commons.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ******************************************************************************/

package com.mytechia.commons.util.classloading;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *  Utility functions to load classes dynamically
 * 
 * @author Victor Sonora
 */
public class ClassLoaders {
    
    /**
     *  Adds and returns all classes contained in the given package
     * (and its sub-packages).
     * @param pkg
     * @return
     * @throws IOException 
     */
    public static List<Class<?>> getClassesForPackage(Package pkg, Class classForClassLoader) throws IOException {        
        return getClassesForPackage(pkg.getName(), classForClassLoader);
    }
    
    public static List<Class<?>> getClassesForPackage(String pkgname, Class classForClassLoader) throws IOException {         
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String relPath = pkgname.replace('.', '/');
        System.out.println("ClassDiscovery: Package: " + pkgname + " becomes Path:" + relPath);
        //Enumeration<URL> resources = ObjectOWLSTranslator.class.getClassLoader().getResources(relPath);
        Enumeration<URL> resources = classForClassLoader.getClassLoader().getResources(relPath);
        while (resources.hasMoreElements()) {
            classes.addAll(getClassesForResource(resources.nextElement(), pkgname, relPath));
        }
        return classes;
    }
    
    private static List<Class<?>> getClassesForResource(URL resource, String pkgname, String relPath) {
        // Get a File object for the package
        File directory = null;
        String fullPath;
        System.out.println("ClassDiscovery: Resource = " + resource);
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (resource == null) {
            throw new RuntimeException("No resource for " + relPath);
        }
        fullPath = resource.getFile();
        System.out.println("ClassDiscovery: FullPath = " + resource);

        try {
            directory = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(pkgname 
                    + " (" + resource + ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...", e);
        } catch (IllegalArgumentException e) {
            directory = null;
        }
        System.out.println("ClassDiscovery: Directory = " + directory);

        if (directory != null && directory.exists()) {
            classes = getClassesContainedInPackageDirectory(directory, pkgname);
        } else {
            try {
                String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
                JarFile jarFile = new JarFile(jarPath);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    if (entryName.startsWith(relPath)
                            && entryName.endsWith(".class")
                            && entryName.length() > (relPath.length() + "/".length())) {
                        System.out.println("ClassDiscovery: JarEntry: " + entryName);
                        String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
                        System.out.println("ClassDiscovery: className = " + className);
                        try {
                            classes.add(Class.forName(className));
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException("ClassNotFoundException loading " + className);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(pkgname + " (" + directory + ") does not appear to be a valid package", e);
            }
        }
        return classes;
    }

    private static List<Class<?>> getClassesContainedInPackageDirectory(File packageDirectory, String nameOfPackage) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        // Get the list of the files contained in the package
        String[] files = packageDirectory.list();
        if (null == files) {
            return classes;
        }
        for (int i = 0; i < files.length; i++) {
            // we are only interested in .class files
            if (files[i].endsWith(".class")) {
                // removes the .class extension
                String className = nameOfPackage + '.' + files[i].substring(0, files[i].length() - 6);
                System.out.println("ClassDiscovery: className = " + className);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("ClassNotFoundException loading " + className);
                }
            } else {
                if (!files[i].contains(".")) {
                    classes.addAll(
                            getClassesContainedInPackageDirectory(
                            new File(packageDirectory.getPath() + File.separator + files[i]),
                            nameOfPackage + "." + files[i]));
                }
            }
        }
        return classes;
    }
}
