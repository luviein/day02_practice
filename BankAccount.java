package com.yl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class BankAccount {

    private final String accHolder;
    private final String accNo;
    private float accBalance;
    private List<String> transactions;
    private boolean isClosed;
    private final String accCreateDate;
    private Date accCloseDate;

    public BankAccount(String accHolder) {
        this.accHolder = accHolder;
        accBalance = 0.0f;
        int min = 100;
        int max = 999;
        int a = (int) (Math.random() * (max - min + 1) + min);
        int b = (int) (Math.random() * (max - min + 1) + min);
        int c = (int) (Math.random() * (max - min + 1) + min);
        this.accNo = String.valueOf(a) + "-" + String.valueOf(b) + "-" + String.valueOf(c);
        LocalDateTime todayDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDate = todayDate.format(myFormatObj);
        this.accCreateDate = formatDate;
        transactions = new ArrayList<>();
    }

    public BankAccount(String accHolder, float accBalance) {
        this.accHolder = accHolder;
        this.accBalance = accBalance;
        int min = 100;
        int max = 999;
        int a = (int) (Math.random() * (max - min + 1) + min);
        int b = (int) (Math.random() * (max - min + 1) + min);
        int c = (int) (Math.random() * (max - min + 1) + min);
        this.accNo = String.valueOf(a) + "-" + String.valueOf(b) + "-" + String.valueOf(c);
        LocalDateTime todayDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDate = todayDate.format(myFormatObj);
        this.accCreateDate = formatDate;
        transactions = new ArrayList<>();
    }

    public String getAccHolder() {
        return accHolder;
    }

    public String getAccNo() {
        return accNo;
    }

    public float getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(float accBalance) {
        this.accBalance = accBalance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Date getAccCloseDate() {
        return accCloseDate;
    }

    public void setAccCloseDate(Date accCloseDate) {
        this.accCloseDate = accCloseDate;
    }

    @Override
    public String toString() {
        return "BankAccount [accHolder=" + accHolder + ", accNo=" + accNo + ", accBalance=" + accBalance
                + ", isClosed=" + isClosed + ", accCreateDate=" + accCreateDate + ", accCloseDate="
                + accCloseDate + "]";
    }

    LocalDateTime todayDate = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formatDate = todayDate.format(myFormatObj);

    public void deposit(float amount) {
        if (isClosed) {
            System.out.println("Account is closed");
            throw new IllegalArgumentException();
        }

        if (amount < 0) {
            throw new IllegalArgumentException();
        } else {
            accBalance += amount;
            System.out.println("You have successfully deposited " + amount + " at " + formatDate.toString());
            transactions.add("You have successfully deposit " + amount + " at " + formatDate.toString());
        }
    }

    public void withdraw(float amount) {
        if (isClosed) {
            System.out.println("Account is closed");
            throw new IllegalArgumentException();

        }
        if (amount < 0) {
            throw new IllegalArgumentException();
        } else if (accBalance < amount) {
            System.out.println("You do not have enough funds to withdraw. Bye.");
            System.exit(0);
        } else {
            accBalance -= amount;
            System.out.println("You have successfully withdrawn " + amount + " at " + formatDate.toString());
            transactions.add("You have successfully withdrawn " + amount + " at " + formatDate.toString());
        }

    }

    public void transactionHistory() {
        System.out.println("Your Transaction History:\n");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("Total Balance: " + accBalance);

        if(isClosed){
            System.out.println("Account has been closed on " + formatDate);
        }
    }

    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("Yenleng", 10000);
        myAccount.withdraw(500);
        myAccount.deposit(999);
        System.out.println(myAccount);

        myAccount.transactionHistory();
    }
}
