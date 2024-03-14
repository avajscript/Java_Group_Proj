package org.example.grouppro.classes.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection = null;
    private static String db = null;
    private static String name = null;
    private static String user = null;
    private static String pass = null;
    private static String host = null;
    private static String port = null;

    private DBConnection() {
    }

    public static void loadProperties() {
        Properties props = new Properties();
        try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(in);
        } catch (IOException e) {
            System.out.println("Failed to load database properties");
            e.printStackTrace();
        }
        db = props.getProperty("db");
        name = props.getProperty("name");
        host = props.getProperty("host");
        pass = props.getProperty("pass");
        port = props.getProperty("port");
        user = props.getProperty("user");

    }

    /**
     * Returns a Connection
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                if (connection != null) {
                    connection.close();
                }

                // load the database.properties file to set db instance variables
                // if the db one is null
                if (db == null) {
                    loadProperties();
                }
                connection = DriverManager.getConnection(("jdbc:" + db + "://" + host + ":" + port + "/" + name),
                        user, pass);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}
