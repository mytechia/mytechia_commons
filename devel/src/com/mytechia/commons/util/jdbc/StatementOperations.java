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


package com.mytechia.commons.util.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * <p><b>
 * </b>
 *
 * </p>
 *
 * <p><b>Creation date:</b> 24-02-2010</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>1 - 24-02-2010 Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela
 * @version 1
 */
public class StatementOperations
{


    private PreparedStatement st;



    public StatementOperations(PreparedStatement st)
    {
        this.st = st;
    }


    public void setString(int i, String str) throws SQLException
    {
        if (str == null)
            this.st.setNull(i, java.sql.Types.VARCHAR);
        else
            this.st.setString(i, str);
    }


    public void setInt(int i, Integer n) throws SQLException
    {
        if ((n==null) || (n == 0))
            this.st.setNull(i, java.sql.Types.INTEGER);
        else
            this.st.setInt(i, n);
    }


    public void setLong(int i, Long n) throws SQLException
    {
        if ((n== null) || (n == 0))
            this.st.setNull(i, java.sql.Types.BIGINT);
        else
            this.st.setLong(i, n);
    }


}
