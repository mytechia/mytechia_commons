package com.mytechia.commons.util.version;



import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 * </p>
 *
 * <p>
 * <b>Creation date:</b>05-feb-2015</p>
 *
 * @author Julio Alberto Gomez Fernandez
 * @version 1
 */
public class ProjectVersion {

    private static final String VERSION_PROPERTY = "version";

    private static final String VERSION_PROPERTIES = "/version.properties";
    
    
    public static String projectVersion(InputStream versionStream) throws ErrorRetrievingVersion {

        Properties properties = new Properties();

        try {
            properties.load(versionStream);
        } catch (Exception ex) {
            throw new ProjectVersion.ErrorRetrievingVersion("Error reading version", ex);
        }

        return properties.getProperty(VERSION_PROPERTY);

    }
    

    public static String projectVersion() throws ErrorRetrievingVersion {

        Properties properties = new Properties();

        try {
            properties.load(ProjectVersion.class.getResourceAsStream(VERSION_PROPERTIES));
        } catch (Exception ex) {
            throw new ProjectVersion.ErrorRetrievingVersion("Error reading version", ex);
        }

        return properties.getProperty(VERSION_PROPERTY);

    }

    @SuppressWarnings("serial")
	public static class ErrorRetrievingVersion extends Exception {

        public ErrorRetrievingVersion() {
        }

        public ErrorRetrievingVersion(String msg, Throwable th) {
            super(msg, th);
        }

    }

}
