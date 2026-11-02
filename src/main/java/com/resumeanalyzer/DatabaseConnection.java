package com.resumeanalyzer;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/resume_analyzer";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "anshi@26";

    public static Connection getConnection()
            throws Exception {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
    public static void main(String[] args){
        try{
        Connection con= getConnection();
            System.out.println("Database Connected Successfully");
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        }
    }
