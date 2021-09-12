package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;

public class ExampleResource {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String username = "root";
        String pawword = "Qwertypoloasd321!";
        String connectionUrl = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectionUrl, username, pawword);
            Statement statement = connection.createStatement()) {
            //System.out.println("Connected");
            statement.executeUpdate("drop table Books");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, PRIMARY KEY(id));");
            statement.executeUpdate("insert into Books (name) values('Inferno')");
            statement.executeUpdate("insert into Books set name='Solomon key'");
            ResultSet resultSet = statement.executeQuery("select * from Books");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println("-----------------");
            }
            System.out.println("_____________________");
            ResultSet b = statement.executeQuery("select name from Books where id = 1");
            while (b.next()){
                System.out.println(b.getString(1));
            }
        }
    }
}