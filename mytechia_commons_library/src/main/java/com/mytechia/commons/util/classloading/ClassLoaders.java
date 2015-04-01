/**
 * *****************************************************************************
 *
 * Copyright 2012 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Victor
 * Sonora
 *
 * This file is part of Mytechia Commons.
 *
 * Mytechia Commons is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Mytechia Commons is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Mytechia Commons. If not, see <http://www.gnu.org/licenses/>.
 *
 *****************************************************************************
 */
package com.mytechia.commons.util.classloading;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Utility functions to load classes dynamically
 *
 * @author victor
 */
public class ClassLoaders
{

    /**
     * Adds and returns all classes contained in the given package (and its
     * sub-packages).
     *
     * @param pkg
     * @param classForClassLoader
     * @return
     * @throws IOException
     */
    public static List<Class<?>> getClassesForPackage(Package pkg, Class classForClassLoader) throws IOException
    {
        return getClassesForPackage(pkg.getName(), classForClassLoader);
    }

    public static List<Class<?>> getClassesForPackage(String pkgname, Class classForClassLoader) throws IOException
    {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String relPath = pkgname.replace('.', '/');
        System.out.println("ClassDiscovery: Package: " + pkgname + " becomes Path:" + relPath);
        Enumeration<URL> resources = classForClassLoader.getClassLoader().getResources(relPath);
        while (resources.hasMoreElements())
        {
            classes.addAll(getClassesForResource(resources.nextElement(), pkgname, relPath));
        }
        return classes;
    }

    private static final Class<?>[] PARAMS = new Class[]
    {
        URL.class
    };
    
    public static List<Class<?>> loadAndGetClassFromJar(String path) throws IOException, ClassNotFoundException, URISyntaxException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        JarFile jarFile = new JarFile(path);

        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class sysclass = URLClassLoader.class;
        Method method = sysclass.getDeclaredMethod("addURL", PARAMS);
        method.setAccessible(true);
        method.invoke(sysloader, new Object[]
        {
            new URI("file://" + path).toURL()
        });
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements())
        {
            JarEntry entry = entries.nextElement();

            String entryName = entry.getName();
            if (entryName.endsWith(".class"))
            {
                String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
                classes.add(Class.forName(className, true, sysloader));
            }
        }

        return classes;
    }

    private static List<Class<?>> getClassesForResource(URL resource, String pkgname, String relPath)
    {
        // Get a File object for the package
        File directory = null;
        String fullPath;
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (resource == null)
        {
            throw new RuntimeException("No resource for " + relPath);
        }
        fullPath = resource.getFile();

        try
        {
            directory = new File(resource.toURI());
        } catch (URISyntaxException e)
        {
            throw new RuntimeException(pkgname
                    + " (" + resource + ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...", e);
        } catch (IllegalArgumentException e)
        {
            directory = null;
        }

        if (directory != null && directory.exists())
        {
            return getClassesContainedInPackageDirectory(directory, pkgname);
        } else
        {
            try
            {
                String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
                JarFile jarFile = new JarFile(jarPath);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements())
                {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    if (entryName.startsWith(relPath)
                            && entryName.endsWith(".class")
                            && entryName.length() > (relPath.length() + "/".length()))
                    {
                        String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
                        try
                        {
                            classes.add(Class.forName(className));
                        } catch (ClassNotFoundException e)
                        {
                            throw new RuntimeException("ClassNotFoundException loading " + className);
                        }
                    }
                }
            } catch (IOException e)
            {
                throw new RuntimeException(pkgname + " (" + directory + ") does not appear to be a valid package", e);
            }
        }
        return classes;
    }

    private static List<Class<?>> getClassesContainedInPackageDirectory(File packageDirectory, String nameOfPackage)
    {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        // Get the list of the files contained in the package
        String[] files = packageDirectory.list();
        if (null == files)
        {
            return classes;
        }
        for (String file : files)
        {
            // we are only interested in .class files
            if (file.endsWith(".class"))
            {
                // removes the .class extension
                String className = nameOfPackage + '.' + file.substring(0, file.length() - 6);
                try
                {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e)
                {
                    throw new RuntimeException("ClassNotFoundException loading " + className);
                }
            } else
            {
                if (!file.contains("."))
                {
                    classes.addAll(getClassesContainedInPackageDirectory(new File(packageDirectory.getPath() + File.separator + file), nameOfPackage + "." + file));
                }
            }
        }
        return classes;
    }
}
