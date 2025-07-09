package generalstore;

import java.sql.*;
import java.util.Scanner;

public class customerDAO {
    static Scanner sc = new Scanner(System.in);

    public static void addCustomer() throws SQLException {
        System.out.print("ग्राहकाचे नाव टाका: ");
        String name = sc.nextLine();

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO customer (name) VALUES (?) ON CONFLICT (name) DO NOTHING"
        		//i used this query becoz it ignores if name already exists (avoids duplicate error)..
        );
        ps.setString(1, name);
        ps.executeUpdate();
        System.out.println("ग्राहक नोंदवला गेला.");
        con.close();
    }
  //this funtion used here for return customer id from database using name,if customer not exits it return -1 
    public static int getCustomerIdByName(String name) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT id FROM customer WHERE name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        int id = -1;
        if (rs.next()) id = rs.getInt("id");
        con.close();
        return id;
    }
}