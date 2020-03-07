package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
	// write your code here
        connectWithDB();
    }

    private static void connectWithDB() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:databaseTest.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String course = rs.getString("course");
                int age = rs.getInt("age");

                System.out.println("id = " + id + "\nname = " + name + "\ncourse = " + course + "\nage = " + age + "\n\n");
            }

            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.print(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Imported data from database successfully!");
    }
}
