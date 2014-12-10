/*******************************************************************************
 *   
 *   Copyright 2009 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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
package com.mytechia.commons.util.zip;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * <p><b>Description:</b>
 *
 *
 * </p>
 *
 * <p><b>Creation date:</b> 15-jun-2009</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>1 - 15-jun-2009 Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 */
public class Zips
{


    private static final void copyInputStream(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();
    }


    public static final void unzip(ZipFile zipFile, File outputDir) throws IOException
    {

        Enumeration entries = zipFile.entries();

        if (outputDir.canWrite()) {

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File f = new File(outputDir, entry.getName());
                if (entry.isDirectory()) {
                    f.mkdirs();
                }
                else {
                    if (!f.getParentFile().exists()) {
                        f.getParentFile().mkdirs();
                    }
                    copyInputStream(
                            zipFile.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(f)));
                }
            }

        }
        else {
            throw new IOException("Unable to write in '" + outputDir + "'.");
        }

        zipFile.close();

    }


    public static final void zip(String parentName, File input, ZipOutputStream output) throws FileNotFoundException, IOException
    {
            
         byte[] buffer = new byte[2048];
         int bytesRead = 0;

         ZipEntry ze;
         
         if (input.isDirectory()) {


             String inputName = getZipFileName(parentName, input);

             if (input.listFiles().length == 0) {
                 //is empty
                 ze = new ZipEntry(inputName);
                 output.putNextEntry(ze);
             }

            for(File i : input.listFiles()) {

                if (i.isDirectory()) {
                    zip(inputName, i, output);
                }
                else {
                    FileInputStream fis = new FileInputStream(i);
                    ze = new ZipEntry(getZipFileName(inputName, i));
                    output.putNextEntry(ze);
                    while((bytesRead = fis.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    fis.close();
                }

            }
        }
            
    }


    private static String getZipFileName(String parent, File input)
    {

        String zipFileName = input.getName();
        if (input.isDirectory()) {
            if (!zipFileName.endsWith("/")) {
                 zipFileName = zipFileName.concat("/");
            }
        }

        String parentName = "";
        if (parent != null) {            
            if (!parent.endsWith("/")) {
                parentName = parent.concat("/");
            }
            else {
                parentName = parent;
            }
        }

        return parentName.concat(zipFileName);

    }


    public static final void zip(File input, File output) throws FileNotFoundException, IOException
    {

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(output));
        zip(null, input, zos);
        zos.close();

    }


}
