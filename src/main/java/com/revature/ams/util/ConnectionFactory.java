package com.revature.ams.util;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
    Factory Design
        - Creational, used to abstract the creation & instantiation of our class away from the user
        - churn out instances of Connections to all of the other objects in our API
    Singleton Design Pattern
        - Creational Design, RESTRICTS that there is only a SINGLE instance of the class that can be made
 */
public class ConnectionFactory {
    private static ConnectionFactory connectionFactory = new ConnectionFactory(); // Eagerly instantiating the object
    private Properties properties = new Properties();

    // Singleton Design via privatizing the Constructor to only be executable int he class itself
    private ConnectionFactory(){
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Static block to CHECK that you have your intended driver available
    static {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    public static ConnectionFactory getConnectionFactory(){
//        // Lazy instantiation the object, if it doesn't exist create it & return, otherwise just return the object
//       if(connectionFactory == null){
//           connectionFactory = new ConnectionFactory();
//           return connectionFactory;
//       }
       return connectionFactory;
    }

    public Connection getConnection(){
        try {
            // WE NEED TO OBFUSCATE this info
            // ENSURE you set up your db.properties in our resources directory
            return DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
