package com.example.paperproject.JDBC;

import java.sql.*;
public class MySQLconnector {
    public static Connection getMySQLDBConnection()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/javaProject","root","Devansh@2006");
        }
        catch(Exception exp)
        {
            System.out.println(exp.toString());
        }
        return con;

    }
}

//package org.example.javafx2025.jdbcc;
//import java.sql.*;
//public class MysqlDBConnection
//{
//    public static Connection getMySQLDBConnection()
//    {
//        Connection con=null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://localhost/javaDB","root","Devansh@2006");
//        }
//        catch(Exception exp)
//        {
//            System.out.println(exp.toString());
//        }
//        return con;
//
//    }
//}
