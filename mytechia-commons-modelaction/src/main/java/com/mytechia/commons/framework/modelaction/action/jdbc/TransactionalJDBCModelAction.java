/*******************************************************************************
 *   
 *   Copyright 2010 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.framework.modelaction.action.jdbc;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.modelaction.action.ModelAction;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;



/** This class should be extended by all classes that implement model operations.
 *  
 *
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: ModelAction.java
 * Date: 27 de noviembre de 2007
 * Changelog:
 *
 *      27 de noviembre de 2007  --  Initial version
 */
public abstract class TransactionalJDBCModelAction extends ModelAction
{
    
    private DataSource ds;
    private Connection connection;
    
    /**
     * @param name The name may be used by GUI elements to display the action performed.
     */
    public TransactionalJDBCModelAction(String name, DataSource ds) throws InternalErrorException
    {
        super(name);
        this.ds = ds;

        try {
            setConnection(ds.getConnection());
        }
        catch(SQLException ex) {
            throw new InternalErrorException(ex);
        }
    }


    public TransactionalJDBCModelAction(DataSource ds) throws InternalErrorException
    {
        this("", ds);
    }   


    private void setConnection(Connection conn)
    {
        this.connection = conn;
    }


    protected Connection getConnection()
    {
        return this.connection;
    }
    
    
    public Object execute() throws Exception
    {

        boolean rollback = false;

        try {

            /*
             * Get a connection with isolation level to
             * "TRANSACTION_SERIALIZABLE" and autocommit to "false".
             *
             * IMPORTANT: Some JDBC drivers require "setTransactionIsolation"
             * to be called before "setAutoCommit".
             */
            connection.setTransactionIsolation(
                Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            /* Execute action. */
            Object result = executeTransactionalAction(connection);

            /* Return "result". */
            return result;

        } catch(SQLException e) {
            rollback = true;
            throw new InternalErrorException(e);
        } catch(InternalErrorException e) {
            rollback = true;
            throw e;
        } catch(RuntimeException e) {
            rollback = true;
            throw e;
        } catch(Error e) {
            rollback = true;
            throw e;
        } finally {
            try {
                /* Commit or rollback, and finally, close connection. */
                if (connection != null) {
                    if (rollback) {
                        connection.rollback();
                    } else {
                        connection.commit();
                    }
                    connection.close();
                }
            } catch (SQLException e) {
                throw new InternalErrorException(e);
            }
        }

    }


    public abstract Object executeTransactionalAction(Connection conn) throws Exception;

    
}
