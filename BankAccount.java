package com.yl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.module.InvalidModuleDescriptorException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        // int min = 100;
        // int max = 999;
        // int a = (int) (Math.random() * (max - min + 1) + min);
        // int b = (int) (Math.random() * (max - min + 1) + min);
        // int c = (int) (Math.random() * (max - min + 1) + min);
        // this.accNo = String.valueOf(a) + "-" + String.valueOf(b) + "-" + String.valueOf(c);
        // LocalDateTime todayDate = LocalDateTime.now();
        this.accNo = generateRandom();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDate = todayDate.format(myFormatObj);
        this.accCreateDate = formatDate;
        transactions = new ArrayList<>();
    }

    public BankAccount(String accHolder, float accBalance) {
        this.accHolder = accHolder;
        this.accBalance = accBalance;
        // int min = 100;
        // int max = 999;
        // int a = (int) (Math.random() * (max - min + 1) + min);
        // int b = (int) (Math.random() * (max - min + 1) + min);
        // int c = (int) (Math.random() * (max - min + 1) + min);
        // this.accNo = String.valueOf(a) + "-" + String.valueOf(b) + "-" + String.valueOf(c);
        this.accNo = generateRandom();
        LocalDateTime todayDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDate = todayDate.format(myFormatObj);
        this.accCreateDate = formatDate;
        transactions = new ArrayList<>();
    }

    // create function to generate a random number
    // random number range from 1 to 999999999
    // if is not 9 char then pad it with 0
    // add - at every third char

    public String generateRandom() {
        Random random = new Random();
        int x = random.nextInt(999999999) + 1; // 0 - 999999999
        String y = String.valueOf(x);
        if (y.length() > 9) {
            throw new InvalidParameterException();
        }
        String paddedVal = (String.format("%09d", x));
        String output = String.format("%s-%s-%s", paddedVal.substring(0, 3), paddedVal.substring(3, 6), paddedVal.substring(6));

        return output; // "123-456-789"

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

        if (isClosed) {
            System.out.println("Account has been closed on " + formatDate);
        }
    }

    public void saveTransaction(String fileName) throws IOException{
        
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);

        int listIndex = 0;
        bw.write("Transaction History:\n");
        while(transactions.size() > listIndex){
            bw.write(transactions.get(listIndex));
            bw.newLine();
            listIndex++;
        }
        bw.flush();
        bw.close();

    }

    public void clearFile(String fileName)throws IOException{
        System.out.println("File Cleared");
        new FileWriter(fileName, false).close();
    }

    public static void main(String[] args) throws IOException {
        
        String fileName = args[0];
        BankAccount fixedDeposit = new BankAccount("Yenleng", 1000);
        fixedDeposit.withdraw(1000);
        fixedDeposit.deposit(509);
        fixedDeposit.saveTransaction(fileName);
    
        
        //filereader save to file
        
    }
}
