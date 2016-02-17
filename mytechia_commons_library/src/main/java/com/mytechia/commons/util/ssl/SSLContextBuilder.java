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
package com.mytechia.commons.util.ssl;

import com.mytechia.commons.util.net.CommunicationsInitializationException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 *  Utility class that knows how to build a SSLContext with keystore and truststore.
 *
 * Created by victorsonorapombo on 11/1/16.
 */
public class SSLContextBuilder {

    private String keystoreType;

    private String securityProtocol;


    public SSLContextBuilder() {
        this("JKS", "TLS");
    }

    public SSLContextBuilder(String keystoreType, String securityProtocol) {
        this.keystoreType = keystoreType;
        this.securityProtocol = securityProtocol;
    }


    /**
     *   The paths and passwords for both keystore and truststore are provided.
     *  And used to build an SSLContext.
     *  @param keystoreFilePath Absolute path for the keystore.
     *  @param keystorePassword Password for the keystore.
     *  @param truststoreFilePath Absolute path for the truststore.
     *  @param truststorePassword Password for the truststore.
     */
    public SSLContext build(String keystoreFilePath,
                            String keystorePassword,
                            String truststoreFilePath,
                            String truststorePassword)
            throws CommunicationsInitializationException {

        // Passwords are needed as char arrays
        char[] ckeystorePassword = keystorePassword.toCharArray();
        char[] ctruststorePassword = truststorePassword.toCharArray();

        // Load Keystore
        FileInputStream fIn = null;
        KeyStore keystore = null;
        try {
            fIn = new FileInputStream(keystoreFilePath);
            keystore = KeyStore.getInstance(this.keystoreType);
            keystore.load(fIn, ckeystorePassword);
        } catch (CertificateException e) {
            throw new CommunicationsInitializationException("Cannot initialize keystore from: " + keystoreFilePath, e);
        } catch (NoSuchAlgorithmException e) {
            throw new CommunicationsInitializationException("Cannot initialize keystore from: " + keystoreFilePath, e);
        } catch (KeyStoreException e) {
            throw new CommunicationsInitializationException("Cannot initialize keystore from: " + keystoreFilePath, e);
        } catch (IOException e) {
            throw new CommunicationsInitializationException("Cannot initialize keystore from: " + keystoreFilePath, e);
        }

        // Load Truststore
        KeyStore truststore = null;
        try {
            fIn = new FileInputStream(truststoreFilePath);
            truststore = KeyStore.getInstance(this.keystoreType);
            truststore.load(fIn, ctruststorePassword);
        } catch (IOException e) {
            throw new CommunicationsInitializationException("Cannot initialize truststore from: " + truststoreFilePath, e);
        } catch (KeyStoreException e) {
            throw new CommunicationsInitializationException("Cannot initialize truststore from: " + truststoreFilePath, e);
        } catch (NoSuchAlgorithmException e) {
            throw new CommunicationsInitializationException("Cannot initialize truststore from: " + truststoreFilePath, e);
        } catch (CertificateException e) {
            throw new CommunicationsInitializationException("Cannot initialize truststore from: " + truststoreFilePath, e);
        }

        // Initialize Keystore and KeyManagerFactory
        KeyManagerFactory kmf = null;
        try {
            kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keystore, ckeystorePassword);
        } catch (NoSuchAlgorithmException e) {
            throw new CommunicationsInitializationException("Cannot initialize KeyManagerFactory", e);
        } catch (UnrecoverableKeyException e) {
            throw new CommunicationsInitializationException("Cannot initialize KeyManagerFactory", e);
        } catch (KeyStoreException e) {
            throw new CommunicationsInitializationException("Cannot initialize KeyManagerFactory", e);
        }

        // Initialize Truststore and TrustManagerFactory
        TrustManagerFactory trustManagerFactory = null;
        try {
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(truststore);
        } catch (NoSuchAlgorithmException e) {
            throw new CommunicationsInitializationException("Cannot initialize TrustManagerFactory", e);
        } catch (KeyStoreException e) {
            throw new CommunicationsInitializationException("Cannot initialize TrustManagerFactory", e);
        }

        // Build SSLContext
        SSLContext clientContext = null;
        try {
            clientContext = SSLContext.getInstance(this.securityProtocol);
            clientContext.init(kmf.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        } catch (NoSuchAlgorithmException e) {
            throw new CommunicationsInitializationException("Cannot initialize SSLContext", e);
        } catch (KeyManagementException e) {
            throw new CommunicationsInitializationException("Cannot initialize SSLContext", e);
        }

        return clientContext;
    }

}

