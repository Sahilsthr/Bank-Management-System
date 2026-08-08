
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        bank.loadAccounts();

        while (true) {
            System.out.println("===Bank  Managemet System===");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Search Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Delete Account");
            System.out.println("7. Check Balance");
            System.out.println("8. Transfer Money");

            System.out.println("9. Exit");
            int choice = 0;
            try {
                System.out.print("Enter Choice: ");
                choice = sc.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input!! Please enter a number");
                sc.nextLine();
                continue;

            }
            switch (choice) {
                case 1:
                    int accountNo = 0;
                    String accountName = "";
                    String accType = "";
                    double balance = 0;

                    try {

                        System.out.print("Enter Accont Number: ");
                        accountNo = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Accont Holder Name: ");
                        accountName = sc.nextLine();
                        System.out.print("Enter Accont Type: ");
                        accType = sc.nextLine();

                        System.out.print("Enter Balance: ₹");
                        balance = sc.nextDouble();
                        sc.nextLine();

                        Account account = new Account(accountNo, accountName, accType, balance);

                        bank.createAccount(account);
                        bank.saveAccounts();

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please check your input.");
                        sc.nextLine();
                    }
                    break;
                case 2:
                    bank.viewAccount();
                    break;
                case 3:

                    try {
                        System.out.print("Enter AccountNo: ");
                        int accoNo = sc.nextInt();
                        bank.searchAccount(accoNo);

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");
                    }

                    break;

                case 4:
                    try {
                        System.out.print("Enter Account Number: ");
                        int num = sc.nextInt();

                        System.out.print("Enter Deposit amount: ");
                        double amount = sc.nextDouble();
                        bank.depositMoney(num, amount);

                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid account number and deposit amount.");
                    }

                    break;

                case 5:
                    try {
                        System.out.print("Enter Account Number: ");
                        int withAcc = sc.nextInt();

                        System.out.print("Enter Withdrawn amount: ₹");
                        double amountWithdraw = sc.nextDouble();
                        bank.withdrawMoney(withAcc, amountWithdraw);

                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid account number and withdrawal amount.");

                    }
                    break;
                case 6:
                    try {
                        System.out.print("Enter Account Number: ");
                        int delAcc = sc.nextInt();

                        bank.deleteAccount(delAcc);

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");

                    }

                    break;
                case 7:
                    try {
                        System.out.print("Enter Account Number: ");
                        int checkAcc = sc.nextInt();

                        bank.checkBalance(checkAcc);

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");

                    }
                    break;

                case 8:
                    try {
                        System.out.print("Enter sender account number: ");
                        int senderAcc = sc.nextInt();

                        System.out.print("Enter receiver account number: ");
                        int receiverAcc = sc.nextInt();

                        System.out.print("Amount: ₹");
                        double amount = sc.nextDouble();

                        bank.transferMoney(senderAcc, receiverAcc, amount);

                    } catch (InputMismatchException e) {
                        System.out.println("Enter valid input!");

                    }
                    break;
                case 9:
                    System.out.println("===Thanks for using Bank Management System===");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");

            }

        }

    }
}
