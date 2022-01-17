package at.froehlich.demo.data;

import at.froehlich.demo.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCConnection {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:testdb";

    private static final String USER = "sa";
    private static final String PASS = "password";

    public static List<Person> getPersonsWithLastName(String name){
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver

            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT per_id, per_firstname, per_lastname, per_sex FROM person WHERE per_lastname like '" + name+"';";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            List<Person> lst = new ArrayList<>();
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("per_id");
                String first = rs.getString("per_firstname");
                String last = rs.getString("per_lastname");
                String sex = rs.getString("per_sex");
                lst.add(new Person(id, first, last, sex));
            }
            // STEP 5: Clean-up environment
            rs.close();
            return lst;
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        return null;
    }
}

