/**
 * *****************************************************************************
 *
 * Copyright 2013 Mytech Ingenieria Aplicada <http://www.mytechia.com>
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
package com.mytechia.commons.patterns.observer;

/**
 * Interface that must be implemented by the objects that can be observed
 *
 * @author Alejandro Paz
 *
 */
public interface Subject {

    /**
     * Registers an observer
     */
    void add(Observer observer);

    /**
     * Unregisters an observer
     */
    void remove(Observer observer);

    /**
     * Notifes changes in this subject to observer objects
     */
    void notify(Object object);

}
