package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Configuration of window titles opening during session.
 */
public class TitlesConfiguration {
    private Properties properties = null;

    public String getProperty(String key) throws CompanyException {
        try {
            if (properties == null) {
                properties = new Properties();
                FileInputStream inputStream = new FileInputStream("titles.properties");
                properties.load(inputStream);
                inputStream.close();
            }
            return properties.getProperty(key);
        } catch (Exception e) {
            throw new CompanyException(e.getMessage(), e);
        }
    }

}
