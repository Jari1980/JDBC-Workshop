package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.db.MySQLConnection.getConnection;

public class Main {
    public static void main(String[] args) {


        //Testing connection
        String sql = "SELECT * FROM COUNTRY";
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ){
            while(resultSet.next()){
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("Continent: " + resultSet.getString("Continent"));
                System.out.println("Population: " + resultSet.getString("Population"));
            }
        }
        catch(SQLException e){
            e.getStackTrace();
        }
    }
}