package com.resumeanalyzer;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            if (connection != null) {

                System.out.println(
                        "Database Connected Successfully!"
                );

                connection.close();

            }

        } catch (Exception e) {

            System.out.println(
                    "Database Connection Failed!"
            );

            e.printStackTrace();
        }
    }
}