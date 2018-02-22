package ru.moneta.pft.mantis.Tests;

import org.testng.annotations.Test;
import ru.moneta.pft.mantis.model.UserData;
import ru.moneta.pft.mantis.model.Users;

import java.sql.*;

public class DBConnectionTests {

    @Test
    public void testDbConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email, access_level, enabled from mantis_user_table");
            Users users =  new Users();
            while (rs.next()){
                users.add(new UserData().withId(rs.getInt("id")).withUserName(rs.getString("username"))
                        .withEmail(rs.getString("email"))
                        .withAccessLevel(rs.getInt("access_level")).withEnabledStatus(rs.getInt("enabled")));
           }
            rs.close();
            st.close();
            conn.close();

            System.out.println(users);
            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
