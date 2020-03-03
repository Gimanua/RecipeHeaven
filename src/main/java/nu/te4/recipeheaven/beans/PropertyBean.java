/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import nu.te4.recipeheaven.exceptions.InvalidDataException;

/**
 *
 * @author Adrian Klasson
 */
public final class PropertyBean {

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
    
    public static String getProperty(DatabaseProperty property) throws IOException, InvalidDataException{
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("database.properties");
        
        Properties properties = new Properties();
        properties.load(stream);
        
        String propertyValue = properties.getProperty(property.key);
        if(propertyValue == null){
            throw new InvalidDataException("The property does not exist");
        }
        
        return propertyValue;
    }

}
