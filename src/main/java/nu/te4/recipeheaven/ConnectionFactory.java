/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Adrian Klasson
 */
public final class ConnectionFactory {
    
    private ConnectionFactory(){}
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/recipe_heaven?user=recipe_heaven_backend&password=taB6LiQaxjKM9esz&noAccessToProcedureBodies=true");
    }
    
}