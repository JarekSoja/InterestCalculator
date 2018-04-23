package com.calculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalculatorDto {

    private static CalculatorDto calculatorDto = null;

    private double interestRate;
    private double qConstant;
    private double valueOfGoods;
    private double downPayment;
    private double loanCost;
    private double valueOfGoodsWithLoanCost;
    private double amountDueToPay;
    private double singleInstallment;
    private int numberOfInstallments;
    private LocalDate dateOfFirstPayment;
    private LocalDate dateOfLastPayment;
    private List<SingleRate> creditSchedule;

    private CalculatorDto() {
        this.creditSchedule = new ArrayList<>();
    }


    static CalculatorDto getCalculatorDtoInstance() {
        if (calculatorDto == null) {
            calculatorDto = new CalculatorDto();
        }
        return calculatorDto;
    }

    void setInterestRate(double interestRate) {
        this.interestRate = interestRate / 100;
    }

    int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    double getValueOfGoods() {
        return valueOfGoods;
    }

    void setValueOfGoods(double setValueOfGoods) {
        this.valueOfGoods = setValueOfGoods;
    }

    void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    LocalDate getDateOfFirstPayment() {
        return dateOfFirstPayment;
    }

    void setDateOfFirstPayment(LocalDate dateOfFirstPayment) {
        this.dateOfFirstPayment = dateOfFirstPayment;
    }

    double getLoanValue() {
        return valueOfGoods - downPayment;
    }

    double getLoanCost() {
        return loanCost;
    }

    void setLoanCost(double loanCost) {
        this.loanCost = loanCost;
    }

    double getValueOfGoodsWithLoanCost() {
        return valueOfGoodsWithLoanCost;
    }

    void setValueOfGoodsWithLoanCost(double valueOfGoodsWithLoanCost) {
        this.valueOfGoodsWithLoanCost = valueOfGoodsWithLoanCost;
    }

    List<SingleRate> getCreditSchedule() {
        return creditSchedule;
    }

    void setCreditSchedule(List<SingleRate> creditSchedule) {
        this.creditSchedule = creditSchedule;
    }

    double getAmountDueToPay() {
        return amountDueToPay;
    }

    void setAmountDueToPay(double amountDueToPay) {
        this.amountDueToPay = amountDueToPay;
    }

    double getQConstant() {
        return qConstant;
    }

    public double getSingleInstallment() {
        return singleInstallment;
    }

    public void setSingleInstallment(double singleInstallment) {
        this.singleInstallment = singleInstallment;
    }

    public LocalDate getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    public void setDateOfLastPayment(LocalDate dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public double getMonthlyInterestRate() {
        return interestRate / 12;
    }

    public void setQConstant() {
         qConstant = 1 + (interestRate / 12);
    }

}
