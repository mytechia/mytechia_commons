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

package com.mytechia.commons.util.jdbc.hsqldb;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.modelaction.exception.InstanceNotFoundException;
import com.mytechia.commons.util.jdbc.GeneralOperations;
import com.mytechia.commons.util.jdbc.StatementOperations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * <p><b>Description:</b>
 *
 * </p>
 *
 * <p><b>Creation date:</b> 09-dic-2010</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>1 - 09-dic-2010 Initial release.</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela
 * @version 1
 */
public class HSQLDBDAOHelper
{

    public void removeEntity(Connection dbConn, String table, String id_field, Long id, Class c) throws InstanceNotFoundException, InternalErrorException
    {

        String queryString = "DELETE FROM "+table+" WHERE "+id_field+" = ?";
        removeEntity(dbConn, queryString, id, c);

    }


    public void removeEntity(Connection dbConn, String query, Long id, Class c) throws InstanceNotFoundException, InternalErrorException
    {

        PreparedStatement preparedStatement = null;

        try {

            String queryString = query;
            preparedStatement = dbConn.prepareStatement(queryString);
            StatementOperations so = new StatementOperations(preparedStatement);

            int i = 1;
            so.setLong(i++, id);

            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0)
                throw new InstanceNotFoundException(
                       id.toString(),
                       c.getName());

        }
        catch (SQLException e) {
            throw new InternalErrorException(e);
        }
        finally {
            GeneralOperations.closeStatement(preparedStatement);
        }

    }

}
