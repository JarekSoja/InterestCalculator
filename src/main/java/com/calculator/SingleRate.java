package com.calculator;

import java.time.LocalDate;

public class SingleRate {

    private LocalDate dayOfPayment;
    private double singleInstallment;
    private double interestPart;
    private double capitalPart;
    private double loanLeftToPay;

    LocalDate getDayOfPayment() {
        return dayOfPayment;
    }

    void setDayOfPayment(LocalDate dayOfPayment) {
        this.dayOfPayment = dayOfPayment;
    }

    void setSingleInstallment(double totalPayment) {
        this.singleInstallment = totalPayment;
    }

    double getInterestPayment() {
        return interestPart;
    }

    void setInterestPayment(double interestPayment) {
        this.interestPart = interestPayment;
    }

    public double getSingleInstallment() {
        return singleInstallment;
    }

    double getCapitalPayment() {
        return capitalPart;
    }

    void setCapitalPayment(double capitalPayment) {
        this.capitalPart = capitalPayment;
    }

    double getLoanLeftToPay() {
        return loanLeftToPay;
    }

    void setLoanLeftToToPay(double loanLeftToPay) {
        if (loanLeftToPay >= 0) {
            this.loanLeftToPay = loanLeftToPay;
        } else {
            loanLeftToPay = 0;
        }
    }

    @Override
    public String toString() {
        return "Date of payment: " + dayOfPayment + "\n To pay: " + singleInstallment + "\n " +
                "Including interest: " + interestPart + " \n and capital part: " + capitalPart +
                "\n Left to pay: \n " + loanLeftToPay;
    }

}
