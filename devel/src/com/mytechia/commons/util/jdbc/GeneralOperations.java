/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela, Alejandro Paz
 *   Victor Sonora
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


package com.mytechia.commons.util.jdbc;

import com.mytechia.commons.framework.exception.InternalErrorException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;


public final class GeneralOperations {

    private GeneralOperations() {}

    /**
     * It closes a <code>ResultSet</code> if not <code>null</code>.
     */
    public static void closeResultSet(ResultSet resultSet)
        throws InternalErrorException {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new InternalErrorException(e);
            }
        }

    }

    /**
     * It closes a <code>Statement</code> if not <code>null</code>.
     */
    public static void closeStatement(Statement statement)
        throws InternalErrorException {

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new InternalErrorException(e);
            }
        }

    }

    /**
     * It closes a <code>Connection</code> if not <code>null</code>.
     */
    public static void closeConnection(Connection connection)
        throws InternalErrorException {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new InternalErrorException(e);
            }
        }

    }

}
