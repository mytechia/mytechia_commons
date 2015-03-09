/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela, Victor Sonora,
 *   Alejandro Paz
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

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * <p><b>
 * </b>
 *
 *
 *
 * <p><b>Creation date:</b> 24-02-2010</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 24-02-2010 Initial release</li>
 * </ul>
 *
 *
 * @author Gervasio Varela
 * @version 1
 */
public class ResultSetOperations
{


    private ResultSet rs;



    public ResultSetOperations(ResultSet rs)
    {
        this.rs = rs;
    }


    public String getString(int i) throws SQLException
    {
        return rs.getString(i);
    }


    public Integer getInt(int i) throws SQLException
    {
        Integer r = rs.getInt(i);
        if (r == 0)
            r = null;
        return r;
    }


    public Long getLong(int i) throws SQLException
    {
        Long r = rs.getLong(i);
        if (r == 0)
            r = null;
        return r;
    }


}
