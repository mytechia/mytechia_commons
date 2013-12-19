/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alejandro Paz
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


package com.mytechia.commons.framework.exception;

import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * Root class for all internal exceptions.
 * 
 * @author Alejandro Paz
 */
public class InternalErrorException extends Exception 
implements java.io.Serializable {

    private Exception encapsulatedException = null;

    
    public InternalErrorException(Exception exception) {
        super((String) null);
        encapsulatedException = exception;
    }


    public InternalErrorException(String message) {
        super(message);
        encapsulatedException = null;
    }
    
    
    public InternalErrorException(Exception exception, String message) {
        super(message);
        encapsulatedException = exception;
    }
    
    
    @Override
    public String getMessage() {
        if (super.getMessage() != null) {
            return super.getMessage();
        } else {
            if (encapsulatedException != null) {
                return encapsulatedException.getMessage();
            } else {
                return null;
            }
        }
    }


    public Exception getEncapsulatedException() {
        return encapsulatedException;
    }


    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    
    @Override
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (encapsulatedException != null) {
            printStream.println("***Information about encapsulated exception***");
            encapsulatedException.printStackTrace(printStream);
        }
    }


    @Override
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (encapsulatedException != null) {
            printWriter.println("***Information about encapsulated exception***");
            encapsulatedException.printStackTrace(printWriter);
        }
    }

    
 
    
}
