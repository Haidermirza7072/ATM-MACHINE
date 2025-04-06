import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Account {
    String name;
    int accountNumber;
    double balance;
    ArrayList<String> transactions = new ArrayList<>();

    public Account(String name, int accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        transactions.add("Account created with balance: " + balance);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: " + amount);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactions;
    }
}

public class BankManagementSystem {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static int accountCounter = 1001;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bank Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        JButton createAccountBtn = new JButton("Create Account");
        JButton depositBtn = new JButton("Deposit Money");
        JButton withdrawBtn = new JButton("Withdraw Money");
        JButton balanceBtn = new JButton("Check Balance");
        JButton historyBtn = new JButton("Transaction History");

        createAccountBtn.addActionListener(e -> createAccount());
        depositBtn.addActionListener(e -> depositMoney());
        withdrawBtn.addActionListener(e -> withdrawMoney());
        balanceBtn.addActionListener(e -> checkBalance());
        historyBtn.addActionListener(e -> transactionHistory());

        frame.add(createAccountBtn);
        frame.add(depositBtn);
        frame.add(withdrawBtn);
        frame.add(balanceBtn);
        frame.add(historyBtn);

        frame.setVisible(true);
    }

    private static void createAccount() {
        String name = JOptionPane.showInputDialog("Enter your name:");
        String initialDepositStr = JOptionPane.showInputDialog("Enter initial deposit:");
        double initialDeposit = Double.parseDouble(initialDepositStr);
        Account newAccount = new Account(name, accountCounter++, initialDeposit);
        accounts.add(newAccount);
        JOptionPane.showMessageDialog(null, "Account created! Account Number: " + newAccount.accountNumber);
    }

    private static void depositMoney() {
        String accNumStr = JOptionPane.showInputDialog("Enter Account Number:");
        int accNum = Integer.parseInt(accNumStr);
        String amountStr = JOptionPane.showInputDialog("Enter Deposit Amount:");
        double amount = Double.parseDouble(amountStr);

        for (Account acc : accounts) {
            if (acc.accountNumber == accNum) {
                acc.deposit(amount);
                JOptionPane.showMessageDialog(null, "Deposit Successful! New Balance: " + acc.getBalance());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Account not found!");
    }

    private static void withdrawMoney() {
        String accNumStr = JOptionPane.showInputDialog("Enter Account Number:");
        int accNum = Integer.parseInt(accNumStr);
        String amountStr = JOptionPane.showInputDialog("Enter Withdraw Amount:");
        double amount = Double.parseDouble(amountStr);

        for (Account acc : accounts) {
            if (acc.accountNumber == accNum) {
                if (acc.withdraw(amount)) {
                    JOptionPane.showMessageDialog(null, "Withdrawal Successful! New Balance: " + acc.getBalance());
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance!");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Account not found!");
    }

    private static void checkBalance() {
        String accNumStr = JOptionPane.showInputDialog("Enter Account Number:");
        int accNum = Integer.parseInt(accNumStr);

        for (Account acc : accounts) {
            if (acc.accountNumber == accNum) {
                JOptionPane.showMessageDialog(null, "Your Balance: " + acc.getBalance());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Account not found!");
    }

    private static void transactionHistory() {
        String accNumStr = JOptionPane.showInputDialog("Enter Account Number:");
        int accNum = Integer.parseInt(accNumStr);

        for (Account acc : accounts) {
            if (acc.accountNumber == accNum) {
                String history = String.join("\n", acc.getTransactionHistory());
                JOptionPane.showMessageDialog(null, "Transaction History:\n" + history);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Account not found!");
    }
}
