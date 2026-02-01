package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {

    Connection connection;
    Statement statement;

    public conn() {
        try {
            // Corrected driver name (dot instead of comma)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Make sure MySQL server is running and database exists
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeemanagement",
                    "root",
                    "tanmay@majumdar123"
            );

            statement = connection.createStatement();
            System.out.println("✅ Database Connected Successfully!");

        } catch (Exception e) {
            System.err.println("❌ Database Connection Failed!");
            e.printStackTrace();
        }
    }
}
