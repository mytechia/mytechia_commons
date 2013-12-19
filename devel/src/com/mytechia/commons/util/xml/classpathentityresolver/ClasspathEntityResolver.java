/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.util.xml.classpathentityresolver;

import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/** EntityResolver that can be used to retrieve DTD's, schemas, etc. from the classpath.
 * It needs to be associated to a parser in order that the parser can load DTD's from
 * the classpath.
 * 
 * Example using JDOM:
 *   SAXBuilder builder = new SAXBuilder(true);
 *   builder.setEntityResolver(new ClasspathResolver());
 *   builder.build(xmlfile);
 * 
 * @author Gervasio Varela Fernández
 * @version 1 
 * @date 10/01/2008
 * 
 * Changelog:
 *
 *      10-01-2008  --  Initial version
 */
public class ClasspathEntityResolver implements EntityResolver {


    private ClassLoader classLoader = null;


    public ClasspathEntityResolver()
    {
    }

    public ClasspathEntityResolver(ClassLoader classLoader)
    {
        this.classLoader = classLoader;
    }


    
    public InputSource resolveEntity(String publicId, String systemId)
        throws IOException {

        if ((systemId != null) && (systemId.startsWith("classpath:"))) {
            String id = systemId.substring(10); //substract 'classpath:' from the systemId
            
            InputStream stream = null;

            if (this.classLoader != null) {
                if (id.startsWith("/"))
                    id = id.substring(1); //getResourceAsStream behaves differently on ClassLoader than Clas, in the second it eliminates the first /, in the first don't
                stream = this.classLoader.getResourceAsStream(id);
            }
            else
                stream = getClass().getResourceAsStream(id);
            
            if (stream == null) {
                return null;
            } else {
                return new InputSource(stream);
            }
        } else {
            return null;
        }
    }
    
    
}