package com.calculator;

import java.time.LocalDate;

public class SingleRate {

    private LocalDate dayOfPayment;
    private double singlePayment;
    private double interestPart;
    private double capitalPart;
    private double loanLeftToPay;

    LocalDate getDayOfPayment() {
        return dayOfPayment;
    }

    void setDayOfPayment(LocalDate dayOfPayment) {
        this.dayOfPayment = dayOfPayment;
    }

    void setSinglePayment(double totalPayment) {
        this.singlePayment = totalPayment;
    }

    double getInterestPayment() {
        return interestPart;
    }

    void setInterestPayment(double interestPayment) {
        this.interestPart = interestPayment;
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
        this.loanLeftToPay = loanLeftToPay;
    }

    @Override
    public String toString() {
        return "Date of payment: " + dayOfPayment + "\n To pay: " + singlePayment + "\n " +
                "Including interest: " + interestPart + " \n and capital part: " + capitalPart +
                "\n Left to repay, after adding interests and subtracting current installment: \n " + loanLeftToPay;
    }

}
