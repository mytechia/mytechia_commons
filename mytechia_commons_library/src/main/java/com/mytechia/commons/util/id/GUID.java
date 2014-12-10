/*******************************************************************************
 *   
 *   Copyright 2007 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alejandro Paz
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

package com.mytechia.commons.util.id;

import com.mytechia.commons.util.net.IPUtil;
import java.net.InetAddress;
import java.security.SecureRandom;



/** This class generates global unique identifiers.
 * The identifier is an hexadecimal value constituted by the CPU time, local IP, 
 * the hash code of the object instance and a random number of 8 digits.
 * The identifier has 40 chars long.
 */
public final class GUID implements java.io.Serializable 
{
    
    /**
     * Atributo de clase que almacena la direcci�n IP en hexadecimal de la maquina local.
     */
    private static String _direccionIP;
    /** 
     * Atributo de clase necesario para generar los numeros aleatorios usados
     * en la generaci�n del identificador �nico. 
     */
    private static SecureRandom _oAleatorioSeguro;
    /** 
     * Atributo que almacena el valor del identificador para la instancia. 
     * Tiene un m�ximo de 40 caracteres.
     */
    private String identificador;
    /** 
     * Valor del c�digo hash que identifica cada instancia de esta clase.
     */
    private String identityHashCode;
    
    

    
    /**
     * Sets class attributes one time
     */
    static {
        //establecerDireccionIP((InetAddress.getLocalHost()).getHostAddress());
        InetAddress ip = IPUtil.getLocalIP();
        if (ip != null) {
            establecerDireccionIP(ip.getHostAddress());
        } else {
            establecerDireccionIP("127.0.0.1");
        }
        _oAleatorioSeguro = new SecureRandom();
        _oAleatorioSeguro.setSeed(_oAleatorioSeguro.generateSeed(8));
    }
    
    
    
    
    /**
     * Constructor.
     * Generar un identificador y establecer el atributo identificador.
     */
    public GUID() {
        this.identityHashCode = Integer.toHexString(System.identityHashCode(this));
        establecerIdentificador(generarIdentificador());
    }
    
    
    /**
     * @return El valor del atributo direccionIP.
     */
    public String obtenerDireccionIP() {
        return _direccionIP;
    }
    
    
    /**
     *
     * @return Identificador �nico.
     */
    public String obtenerIdentificador() {
        return this.identificador;
    }
 
        
    
    /**
     * Este metodo genera una nueva cadena de identificador Unico.
     * La cadena devuelta tiene un maximo de 40 caracteres.
     *
     * @return un identificador unico
     */
    public String generarIdentificador( ) {
        String UUID = Long.toHexString(System.currentTimeMillis());
        UUID = UUID.concat(obtenerDireccionIP());
        UUID = UUID.concat(identityHashCode);
        UUID = UUID.concat(Integer.toHexString(_oAleatorioSeguro.nextInt()));
        return UUID;
    }
    
    

    
    /**
     * Igualdad entre dos objetos IdentificadorUnico verificada a trav�s del identificador
     * generado pra cada uno de ellos.
     *
     * @param another 
     * @return 
     */
    public boolean equals(Object another) {
        boolean resultado = false;
        try {
            GUID otro = (GUID) another;
            resultado = this.identificador.equals(otro.obtenerIdentificador());
        } catch (Exception e) {
            resultado = false;
        }
        return resultado;
    }
    
    
    
    /**
     * 
     * @return 
     */
    public int hashCode() {
        return this.identificador.hashCode();
    }
    
    
    
    /**
     * 
     * @return 
     */
    public String toString() {
        return this.identificador;
    }
    
    
    
    
    /*--------------------------- M�TODOS PRIVADOS --------------------------*/
    
    
    
   
    /**
     * Quita los puntos a la direccion IP que se pasa como par�metro, se convierte
     * a hexadecimal y se establece el atributo direccionIP.
     * 
     * @param direccion Direcci�n IP con formato "XXX.XXX.XXX.XXX".
     */
    private static void establecerDireccionIP(final String direccion) {
        String aux = new String();
        String ip = new String();
        
        for (int i=0; i<direccion.length(); i++) {
            if (direccion.charAt(i) != '.') {
                aux = aux.concat(String.valueOf(direccion.charAt(i)));
            } else {
                ip = ip.concat(Long.toHexString((new Long(aux)).longValue()));
                aux = new String();
            }
        }
        ip = ip.concat(Long.toHexString((new Long(aux)).longValue()));
        
        _direccionIP = ip;
    }

    
    /**
     *
     * @param identificador nuevo valor del atributo identificador.
     */
    private void establecerIdentificador(final String identificador ) {
        this.identificador = identificador;
    }
        
    
}
