/*******************************************************************************
 *   
 *   Copyright 2013 Mytech Ingenieria Aplicada <http://www.mytechia.com>
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

package com.mytechia.commons.framework.simplemessageprotocol.udp;

import com.mytechia.commons.framework.simplemessageprotocol.Command;
import com.mytechia.commons.framework.simplemessageprotocol.channel.IAddress;
import com.mytechia.commons.framework.simplemessageprotocol.channel.ReceiveResult;
import com.mytechia.commons.framework.simplemessageprotocol.exception.CommunicationException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Sonora
 */
public class UDPCommunicationChannelImplementation implements IUDPCommunicationChannel
{

    private DatagramSocket udpSocket;

    private int port;

    private ArrayList<UDPAddress> broadcastAddressList;

    private InetAddress defaultAddr;
    
    
    
    public UDPCommunicationChannelImplementation(int port) throws UnknownHostException, SocketException
    {
        this.port = port;
        this.udpSocket = new DatagramSocket(null);
        this.udpSocket.setReuseAddress(true);
        this.udpSocket.bind(new InetSocketAddress(port));
        if (!this.udpSocket.getReuseAddress()) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Unable to configure an UDP socket to be reusable. You will not be able to launch more than one HI3 gateway on this host.");
        }
        setBroadcastAdresses();
    }
    
    
    private void setBroadcastAdresses() throws SocketException
    {
        this.broadcastAddressList = new ArrayList<UDPAddress>(1);
        Enumeration<NetworkInterface> nicList = NetworkInterface.getNetworkInterfaces();
        while(nicList.hasMoreElements()) {
            NetworkInterface nic = nicList.nextElement();
            if (!nic.isLoopback() && !nic.isPointToPoint() && nic.isUp()) {
                for(InterfaceAddress nicAddr : nic.getInterfaceAddresses()) {
                    if (defaultAddr == null)
                        defaultAddr = nicAddr.getAddress();
                    InetAddress bcastAddr = nicAddr.getBroadcast();
                    if (bcastAddr != null)
                        this.broadcastAddressList.add(new UDPAddress(bcastAddr, this.port));
                }
            }
        }
    }
    
    @Override
    public int getPort()
    {
        return udpSocket.getLocalPort();
    }

    
    @Override
    public void send(IAddress dev, byte[] data, int offset, int count) throws CommunicationException
    {

        if (dev instanceof UDPAddress) {
            UDPAddress udpDev = (UDPAddress) dev;

            try {
                DatagramPacket dp =
                        new DatagramPacket(data, offset, count, udpDev.getAddress(), udpDev.getPort());
                this.udpSocket.send(dp);
            }
            catch(UnknownHostException ex) {
                throw new CommunicationException(ex);
            }
            catch(IOException ex) {
                throw new CommunicationException(ex);
            }

        }

    }


    @Override
    public void send(IAddress dev, byte[] data) throws CommunicationException
    {
        send(dev, data, 0, data.length);
    }


    @Override
    public void broadcast(byte[] data, int offset, int count) throws CommunicationException
    {
        for(UDPAddress udpDev : this.broadcastAddressList) {
            this.send(udpDev, data, offset, count);
        }
    }


    @Override
    public void broadcast(byte[] data) throws CommunicationException
    {
        for(UDPAddress udpDev : this.broadcastAddressList) {
            this.send(udpDev, data);
        }
    }


    @Override
    public ReceiveResult receive(byte[] data, int offset, int count, long timeout) throws CommunicationException
    {
        try {
            DatagramPacket packet = new DatagramPacket(data, offset, count);
            this.udpSocket.receive(packet);
            UDPAddress origin = new UDPAddress(packet.getAddress(), packet.getPort());
            return new ReceiveResult(packet.getLength(), origin, null);
        }
        catch (IOException ex) {
            throw new CommunicationException(ex);
        }

    }


    @Override
    public ReceiveResult receive(byte[] data, int offset, int count) throws CommunicationException
    {
        return receive(data, offset, count, -1);
    }


    @Override
    public ReceiveResult receive(byte[] data) throws CommunicationException
    {
        return receive(data, 0, data.length);
    }


    @Override
    public ReceiveResult receive() throws CommunicationException
    {
        byte[] data = new byte[Command.MAX_MESSAGE_SIZE];
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length);
            this.udpSocket.receive(packet);
            UDPAddress origin = new UDPAddress(packet.getAddress(), packet.getPort());
            return new ReceiveResult(packet.getLength(), origin, data);
        }
        catch(IOException ex) {
            throw new CommunicationException(ex);
        }
    }


    @Override
    public String getIPAddress()
    {
        return this.defaultAddr.getHostAddress();
    }
    
    
}
