package com.calculator;

import java.math.BigDecimal;

class FlatRateCalculation implements CalculationPattern {

    private CalculatorDto calculatorDto = CalculatorDto.getCalculatorDtoInstance();
    private double interestRevision = 0;

    public void runCalculations() {
        calculatorDto.setQConstant();
        calculateDateOfLastInstallment();
        calculateRate();
        createSchedule();
        calculateCreditCost();
        calculateTotalValueOfGoods();
    }

    public void calculateDateOfLastInstallment() {
        calculatorDto.setDateOfLastPayment(calculatorDto.getDateOfFirstPayment().plusMonths(calculatorDto.getNumberOfInstallments() - 1));
    }

    private void calculateRate() {
        double singleRate = round2(calculatorDto.getLoanValue() * (calculatorDto.getMonthlyInterestRate()
                * (Math.pow(calculatorDto.getQConstant(), calculatorDto.getNumberOfInstallments()))) / (Math.pow(calculatorDto.getQConstant(), calculatorDto.getNumberOfInstallments()) - 1));
        calculatorDto.setSingleInstallment(singleRate);
    }

    public void createSchedule() {
        for (int i = 0; i < calculatorDto.getNumberOfInstallments(); i++) {
            SingleRate rate = new SingleRate();
            double rateValue = calculatorDto.getSingleInstallment();
            calculatorDto.getCreditSchedule().add(rate);
            rate.setSingleInstallment(rateValue);
            switch (i) {
                case 0:
                    rate.setDayOfPayment(calculatorDto.getDateOfFirstPayment());
                    rate.setInterestPayment(round2(calculatorDto.getLoanValue() * (calculatorDto.getInterestRate() / 12)));
                    rate.setCapitalPayment(round2(rateValue - rate.getInterestPayment()));
                    calculatorDto.setAmountDueToPay(round2(calculatorDto.getLoanValue() - rate.getCapitalPayment()));
                    rate.setLoanLeftToToPay(round2(calculatorDto.getAmountDueToPay()));
                    break;
                default:
                    rate.setDayOfPayment(calculatorDto.getDateOfFirstPayment().plusMonths(i));
                    rate.setInterestPayment(round2(calculatorDto.getAmountDueToPay() * (calculatorDto.getInterestRate() / 12)));
                    if (i != calculatorDto.getNumberOfInstallments() - 1) {
                        rate.setCapitalPayment(round2(rateValue - rate.getInterestPayment()));
                        calculatorDto.setAmountDueToPay(round2(calculatorDto.getAmountDueToPay() - rate.getCapitalPayment()));
                        rate.setLoanLeftToToPay(round2(calculatorDto.getAmountDueToPay()));
                    } else {
                        rate.setCapitalPayment(round2(rateValue - rate.getInterestPayment()));
                        calculatorDto.setAmountDueToPay(round2(calculatorDto.getAmountDueToPay() - rate.getCapitalPayment()));
                        interestRevision = calculatorDto.getAmountDueToPay();
                        rate.setCapitalPayment(round2(rate.getCapitalPayment() + rate.getLoanLeftToPay()));
                        calculatorDto.setAmountDueToPay(0);
                        rate.setLoanLeftToToPay(round2(calculatorDto.getAmountDueToPay()));
                    }
            }
        }
    }

    public void calculateCreditCost() {
        double loan = 0;
        for (SingleRate rate : calculatorDto.getCreditSchedule()) {
            loan += rate.getSingleInstallment();
        }
        calculatorDto.setLoanCost(round2((loan - calculatorDto.getLoanValue()) + interestRevision));
    }

    public void calculateTotalValueOfGoods() {
        calculatorDto.setValueOfGoodsWithLoanCost(calculatorDto.getLoanValue() + calculatorDto.getLoanCost() + calculatorDto.getDownPayment());
    }

    public double round2(double value) {
        return BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
