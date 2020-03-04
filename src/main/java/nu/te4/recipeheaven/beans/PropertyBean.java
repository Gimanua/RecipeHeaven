/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adrian Klasson
 */
public final class PropertyBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyBean.class);
    
    private PropertyBean() {
    }
    
    public enum DatabaseProperty {
        HOST("host"),
        DATABASE_NAME("db_name"),
        USERNAME("username"),
        PASSWORD("password");
        
        private final String key;
        
        private DatabaseProperty(String key){
            this.key = key;
        }
    }
    
    public enum GitHubOAuthProperty{
        CLIENT_ID("client_id"),
        CLIENT_SECRET("client_secret");
        
        private final String key;
        
        private GitHubOAuthProperty(String key){
            this.key = key;
        }
    }
    
    public static String getProperty(DatabaseProperty property) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("database.properties");
            
            Properties properties = new Properties();
            properties.load(stream);
            
            String propertyValue = properties.getProperty(property.key);
            
            return propertyValue;
        } catch (IOException ex) {
            LOGGER.error("Failed to get property: {}", ex.getMessage());
            return null;
        }
    }
    
    public static String getProperty(GitHubOAuthProperty property) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("github-oauth.properties");
            
            Properties properties = new Properties();
            properties.load(stream);
            
            String propertyValue = properties.getProperty(property.key);
            
            return propertyValue;
        } catch (IOException ex) {
            LOGGER.error("Failed to get property: {}", ex.getMessage());
            return null;
        }
    }

}
