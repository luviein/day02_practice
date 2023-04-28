package com.yl;

public class FixedDeposit extends BankAccount {

    private float interest = 3.0f;
    private int durationMonth = 6;
    private boolean isSetInterest = false;
    private boolean isSetDuration = false;

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        if (isSetInterest = false) {
            this.interest = interest;
        } else {
            System.out.println("Interest can only be set once.");
            throw new IllegalArgumentException();
        }

    }

    public int getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(int durationMonth) {
        if (isSetDuration = false) {
            this.durationMonth = durationMonth;
        } else {
            System.out.println("Duration can only be set once.");
            throw new IllegalArgumentException();
        }

    }


    // @Override
    // public String toString() {
    //     return "FixedDeposit [interest=" + interest + ", durationMonth=" + durationMonth + ", isSetInterest="
    //             + isSetInterest + ", isSetDuration=" + isSetDuration + "]";
    // }

    @Override
    public void deposit(float amount){
        System.out.println("No Deposit Allowed");
    }

    @Override
    public void withdraw(float amount){
        System.out.println("No Withdrawals Allowed");
    }

    public FixedDeposit(String accHolder, float interest, int durationMonth) {
        super(accHolder);
        this.interest = interest;
        this.durationMonth = durationMonth;
    }

    public FixedDeposit(String accHolder, float accBalance, float interest, int durationMonth) {
        super(accHolder, accBalance);
        this.interest = interest;
        this.durationMonth = durationMonth;
    }

    public void getBalance() {
        System.out.println(getAccBalance() + (getAccBalance() * (interest/100))) ;
    }





}
