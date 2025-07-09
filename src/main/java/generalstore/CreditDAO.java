package generalstore;

import java.sql.*;
import java.util.Scanner;

public class CreditDAO {
    static Scanner sc = new Scanner(System.in);

    public static void addCreditEntry() throws SQLException {
        System.out.print("ग्राहकाचे नाव: ");
        String name = sc.nextLine();
        int id = customerDAO.getCustomerIdByName(name);//this funtion used here for return customer id from database using name,if customer not exits it return -1 
      
        if (id == -1) {
            System.out.println("ग्राहक सापडला नाही.");
            return;
        }

        System.out.print("घेतलेला माल (Item): ");
        String item = sc.nextLine();
        System.out.print("रक्कम: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO credit_entry (customer_id, item, amount) VALUES (?, ?, ?)"
        );
        ps.setInt(1, id);
        ps.setString(2, item);
        ps.setDouble(3, amount);
        ps.executeUpdate();
        con.close();
        System.out.println("उधारी नोंदवली गेली.");
    }
}