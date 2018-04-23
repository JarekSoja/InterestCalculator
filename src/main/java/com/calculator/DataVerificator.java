package com.calculator;

import java.time.LocalDate;

public class DataVerificator {

    public static boolean isYearlyInterestValid(double interestRate) {
        return (interestRate >= 0);
    }

    public static boolean isNumberOfInstallmentsValid(int installments) {
        return (installments < 61 && installments > 2);
    }

    public static boolean isValueOfGoodsValid(double valueOfGoods) {
        return (valueOfGoods > 0);
    }

    public static boolean isDownPaymentValid(double downPayment, double goodsValue) {
        return (downPayment > 0 && downPayment < goodsValue / 2);
    }

    public static boolean isDateOfFIrstPaymentValid(LocalDate date) {
        return date.isAfter(LocalDate.now()) && date.getDayOfMonth() < 29;
    }

}
