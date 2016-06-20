package com.mytechia.commons.util.ssl;

public class SSLContextStores {
	
	private String keystoreFilePath;
	private String keystorePassword;
    private String truststoreFilePath;
    private String truststorePassword;
    
    
    /**
     *   The paths and passwords for both keystore and truststore are provided.
     *  And used to build an SSLContext.
     *  @param keystoreFilePath Absolute path for the keystore.
     *  @param keystorePassword Password for the keystore.
     *  @param truststoreFilePath Absolute path for the truststore.
     *  @param truststorePassword Password for the truststore.
     */
    public SSLContextStores(String keystoreFilePath, String keystorePassword, 
    						String truststoreFilePath,String truststorePassword) {
    	super();
    	
    	if(keystoreFilePath==null){
    		throw new NullPointerException("The parameter keystoreFilePath is required");
    	}
    	
    	if(truststoreFilePath==null){
    		throw new NullPointerException("The parameter truststoreFilePath is required");
    	}
    	
    	this.keystoreFilePath = keystoreFilePath;
    	this.keystorePassword = keystorePassword;
    	this.truststoreFilePath = truststoreFilePath;
    	this.truststorePassword = truststorePassword;
    }

	public String getKeystoreFilePath() {
		return keystoreFilePath;
	}

	public String getKeystorePassword() {
		return keystorePassword;
	}

	public String getTruststoreFilePath() {
		return truststoreFilePath;
	}

	public String getTruststorePassword() {
		return truststorePassword;
	}
    
    
    

}
