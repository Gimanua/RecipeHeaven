package nu.te4.recipeheaven;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import nu.te4.recipeheaven.beans.PropertyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to get a connection with the database used by the project.
 *
 * @author Adrian Klasson
 */
public final class ConnectionFactory {

    /**
     * Logs information.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

    /**
     * The string used to connect to the database.
     */
    private static final String CONNECTION_STRING;

    /**
     * An empty constructor. This is private so no one mistakingly makes an
     * instance of this class given it only has static methods.
     */
    private ConnectionFactory() {
    }

    static {
        try {
            LOGGER.trace("Initializing JDBC Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            LOGGER.trace("Successfully initialized JDBC Driver.");
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Failed to initialize JDBC Driver. {}", ex.getMessage());
        }

        String username = "";
        String password = "";
        String host = "";
        String databaseName = "";
        try {
            username = PropertyBean.getProperty(PropertyBean.DatabaseProperty.USERNAME);
            LOGGER.debug("Username used for connection: {}", username);

            password = PropertyBean.getProperty(PropertyBean.DatabaseProperty.PASSWORD);
            LOGGER.debug("Password used for connection: {}", password);

            host = PropertyBean.getProperty(PropertyBean.DatabaseProperty.HOST);
            LOGGER.debug("Host used for connection: {}", host);

            databaseName = PropertyBean.getProperty(PropertyBean.DatabaseProperty.DATABASE_NAME);
            LOGGER.debug("Database Name user for connection: {}", databaseName);
        } catch (IOException ex) {
            LOGGER.error("Failed to load properties used for connection. {}", ex.getMessage());
        }
        
        CONNECTION_STRING = String.format("jdbc:mysql://%s/%s?noAccessToProcedureBodies=true&user=%s&password=%s", host, databaseName, username, password);
        LOGGER.debug("Connection string set to: {}", CONNECTION_STRING);
    }

    /**
     * Gets a connection to the database used by this project.
     *
     * @return A connection to the database.
     * @throws SQLException If the connection fails.
     */
    public static Connection getConnection() throws SQLException {
        LOGGER.trace("Retrieving connection");
        return DriverManager.getConnection(CONNECTION_STRING);
    }

}
