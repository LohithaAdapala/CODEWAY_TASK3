import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double checkBalance() {
        return this.balance;
    }
}

class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int option) {
        switch (option) {
            case 1:
                System.out.print("Enter withdrawal amount: ");
                double withdrawAmount = scanner.nextDouble();
                if (userAccount.withdraw(withdrawAmount)) {
                    System.out.printf("Withdrawal successful. Remaining balance: $%.2f%n", userAccount.checkBalance());
                } else {
                    System.out.println("Invalid withdrawal amount or insufficient funds.");
                }
                break;
            case 2:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                if (userAccount.deposit(depositAmount)) {
                    System.out.printf("Deposit successful. New balance: $%.2f%n", userAccount.checkBalance());
                } else {
                    System.out.println("Invalid deposit amount.");
                }
                break;
            case 3:
                System.out.printf("Current balance: $%.2f%n", userAccount.checkBalance());
                break;
            case 4:
                System.out.println("Exiting. Thank you!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    public void run() {
        while (true) {
            displayOptions();
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            performTransaction(option);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
