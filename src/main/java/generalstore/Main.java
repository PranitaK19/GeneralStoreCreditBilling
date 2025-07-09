package generalstore;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("\n----: जनरल स्टोअर उधारी बिलिंग :----");
            System.out.println("1. नवीन ग्राहक नोंदवा");
            System.out.println("2. ग्राहकासाठी उधारी बिल टाका");
            System.out.println("3. पैसे फेडल्याची नोंद करा");
            System.out.println("4. सर्व उधारी असलेल्या ग्राहकांची यादी");
            System.out.println("5. ग्राहकाचा व्यवहार बघा");
            System.out.println("6. Exit");
            System.out.print("तुमचा पर्याय निवडा (१-६): ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    customerDAO.addCustomer();
                    break;
                case 2:
                    CreditDAO.addCreditEntry();
                    break;
                case 3:
                    PaymentDAO.addPaymentEntry();
                    break;
                case 4:
                    ReportService.showPendingDues();
                    break;
                case 5:
                    ReportService.showCustomerHistory();
                    break;
                case 6:
                    System.exit(0);
                    System.out.println("Exiting......");
                    break;
                default:
                    System.out.println("\"अवैध पर्याय निवडला आहे. कृपया १ ते ६ यापैकी एक पर्याय निवडा...\"");
            }
        }
    }
}