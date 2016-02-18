

package com.mytechia.commons.util.version;

/**
 * <p></p>
 *
 * <p><b>Creation date:</b>04-feb-2015</p>
 *
 * @author Julio Alberto Gomez Fernandez
 * @version 1 
 */

public class Version implements Comparable<Version>{
    
    private final byte mayor;
    private final byte minor;
    private final byte micro;

    
    public Version(String version){
        
        boolean isValid = version.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}(-SNAPSHOT)?");

        if (!isValid) {
            throw new IllegalVersion("The version=%s is no valid.", version);
        }

        String[] splitedVersion = version.split("-SNAPSHOT");
        String versionWithoutSnapshot = splitedVersion[0];

        String[] valores = versionWithoutSnapshot.split("\\.");

        this.mayor = Byte.valueOf(valores[0]);
        checkNumberVersion(this.mayor);

        this.minor = Byte.valueOf(valores[1]);
        checkNumberVersion(this.minor);

        this.micro = Byte.valueOf(valores[2]);
        checkNumberVersion(this.micro);
        
    }
    
    public Version(byte mayor, byte minor, byte micro) {
        
        checkNumberVersion(mayor);
        this.mayor = mayor;
        
        checkNumberVersion(minor);
        this.minor = minor;
        
        checkNumberVersion(micro);
        this.micro = micro;
    }
    

    
    private void checkNumberVersion(byte numberVersion){
        if(numberVersion<0){
            throw new IllegalVersion("The version number can not be a negative number. The value %s is illegal.", numberVersion);
        }
        
        if(numberVersion>99){
                throw new IllegalVersion("The version number cannot be greater than 99. The value %s is illegal.", numberVersion);
       }
    }

    public byte getMayor() {
        return mayor;
    }

    public byte getMinor() {
        return minor;
    }

    public byte getMicro() {
        return micro;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.mayor;
        hash = 83 * hash + this.minor;
        hash = 83 * hash + this.micro;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Version other = (Version) obj;
        if (this.mayor != other.mayor) {
            return false;
        }
        if (this.minor != other.minor) {
            return false;
        }
        if (this.micro != other.micro) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        StringBuilder strBuilder= new StringBuilder();
        
        return strBuilder.append(this.mayor).append(".")
                  .append(this.minor).append(".")
                  .append(this.micro).toString();
    }

    @Override
    public int compareTo(Version otherVersion) {

        if (otherVersion == null) {
            return 1;
        }

        if (this.mayor > otherVersion.mayor) {
            return 1;
        } else {
            if (this.mayor < otherVersion.mayor) {
                return -1;
            }
        }

        if (this.minor > otherVersion.minor) {
            return 1;
        } else {
            if (this.minor < otherVersion.minor) {
                return -1;
            }
        }

        if (this.micro > otherVersion.micro) {
            return 1;
        } else {
            if (this.micro < otherVersion.micro) {
                return -1;
            }
        }

        return 0;

    }
    

}
