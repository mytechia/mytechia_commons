/*******************************************************************************
 * Copyright (C) 2016 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 * Copyright (C) 2016 Victor Sonora Pombo <victor.pombo@mytechia.com>
 * <p>
 * This file is part of Mytechia Commons.
 * <p>
 * Mytechia Commons is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Mytechia Commons is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with Mytechia Commons.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.mytechia.commons.util.net;

import java.io.Serializable;

/**
 * Created by victorsonorapombo on 11/1/16.
 */
public class CommunicationsInitializationException extends Exception implements Serializable {

    public CommunicationsInitializationException(String message) {
        super(message);
    }

    public CommunicationsInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

}
