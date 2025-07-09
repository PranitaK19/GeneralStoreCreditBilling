package generalstore;

import java.sql.*;
import java.util.Scanner;

public class ReportService {

    // this function shows all customers who have pending dues (credit - paid > 0)
    public static void showPendingDues() throws SQLException {
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();

        // used to get all customers from the database
        ResultSet customers = stmt.executeQuery("SELECT id, name FROM customer");

        System.out.println("\n---- बाकी उधारी ----");
        while (customers.next()) {
            int id = customers.getInt("id");
            String name = customers.getString("name");
            
            // used to get total credit amount for the customer
            Statement st1 = con.createStatement();
            ResultSet cr = st1.executeQuery("SELECT SUM(amount) FROM credit_entry WHERE customer_id = " + id);
            double credit = 0;
            if (cr.next()) credit = cr.getDouble(1);

            // Get total paid amount by the customer
            Statement st2 = con.createStatement();
            ResultSet pr = st2.executeQuery("SELECT SUM(paid_amount) FROM payment_entry WHERE customer_id = " + id);
            double paid = 0;
            if (pr.next()) paid = pr.getDouble(1);

            double due = credit - paid;

            //it  displayyy customer name and due amount if dues are pending
            if (due > 0) {
                System.out.println(name + " : ₹" + due);
            }
        }

        con.close();
    }

    // this function used to displays the full transaction history (credits and payments) of a customer
    public static void showCustomerHistory() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("ग्राहकाचे नाव: ");
        String name = sc.nextLine();

        // used to get customer ID using name; returns -1 if customer not found
        int id = customerDAO.getCustomerIdByName(name);
        if (id == -1) {
            System.out.println("ग्राहक सापडला नाही.");
            return;
        }

        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        // Show credit (item and amount taken) history...
        System.out.println("\n--- घेतलेला माल ---");
        ResultSet cr = st.executeQuery("SELECT item, amount, date FROM credit_entry WHERE customer_id = " + id);
        while (cr.next()) {
            System.out.println(
                cr.getString("item") + " ₹" +
                cr.getDouble("amount") + " on " +
                cr.getTimestamp("date")
            );
        }

        //it is used here to show payment history
        System.out.println("\n--- फेडलेले पैसे ---");
        ResultSet pr = st.executeQuery("SELECT paid_amount, date FROM payment_entry WHERE customer_id = " + id);
        while (pr.next()) {
            System.out.println("Paid ₹" + pr.getDouble("paid_amount") + " on " + pr.getTimestamp("date"));
        }

        con.close();
    }
}
