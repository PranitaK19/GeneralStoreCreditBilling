package generalstore;

import java.sql.*;
import java.util.Scanner;

public class PaymentDAO {
    static Scanner sc = new Scanner(System.in);

    public static void addPaymentEntry() throws SQLException {
        System.out.print("ग्राहकाचे नाव: ");
        String name = sc.nextLine();
        int id = customerDAO.getCustomerIdByName(name);
        if (id == -1) {
            System.out.println("ग्राहक सापडला नाही.");
            return;
        }

        System.out.print("फेडलेली रक्कम: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO payment_entry (customer_id, paid_amount) VALUES (?, ?)"
        );
        ps.setInt(1, id);
        ps.setDouble(2, amt);
        ps.executeUpdate();
        con.close();
        System.out.println("पैसे फेडल्याची नोंद झाली.");
    }
}