package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration of window titles opening during session. Titles are fetched from titles.properties file.
 */
public class TitlesConfiguration {
    private static Properties properties = null;

    public static String getProperty(String key) throws CompanyException {
        if(properties == null){
            try {
                properties = new Properties();
                InputStream inputStream = AppFX.class.getResourceAsStream("/titles.properties");
                properties.load(inputStream);
                inputStream.close();

            } catch (Exception e) {
                throw new CompanyException(e.getMessage(), e);
            }
        }
        return properties.getProperty(key);
    }

}
