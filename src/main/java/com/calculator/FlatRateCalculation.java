package com.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class FlatRateCalculation implements CalculationPattern {

    private CalculatorDto calculatorDto = com.calculator.CalculatorDto.getCalculatorDtoInstance();

    public void runCalculations() {
        calculatorDto.setQConstant();
        calculateRate();
        createSchedule();
        calculateCreditCost();
        calculateTotalValueOfGoods();
        calculateDateOfLastInstallment();
    }

    private void calculateRate() {
        double singleRate = round2(calculatorDto.getLoanValue() * (calculatorDto.getMonthlyInterestRate()
                * (Math.pow(calculatorDto.getQConstant(), calculatorDto.getNumberOfInstallments()))) / (Math.pow(calculatorDto.getQConstant(), calculatorDto.getNumberOfInstallments()) - 1));
        System.out.println(singleRate);
        calculatorDto.setSingleInstallment(singleRate);
    }

    public void calculateCreditCost() {
        calculatorDto.setLoanCost(round2((calculatorDto.getSingleInstallment() * calculatorDto.getNumberOfInstallments()) - calculatorDto.getLoanValue()));
    }

    public void calculateTotalValueOfGoods() {
        calculatorDto.setValueOfGoodsWithLoanCost(calculatorDto.getLoanValue() + calculatorDto.getLoanCost());

    }

    public void createSchedule() {
        double rateValue = calculatorDto.getSingleInstallment();
        List<SingleRate> creditSchedule = new ArrayList<>();

        for (int i = 0; i < calculatorDto.getNumberOfInstallments(); i++) {
            switch (i) {
                case 0:
                    calculatorDto.setAmountDueToPay(calculatorDto.getLoanValue() * calculatorDto.getQConstant() - rateValue);
                    SingleRate firstRate = new SingleRate();
                    calculatorDto.getCreditSchedule().add(firstRate);
                    firstRate.setSinglePayment(rateValue);
                    firstRate.setDayOfPayment(calculatorDto.getDateOfFirstPayment().plusMonths(1));
                    firstRate.setLoanLeftToToPay(calculatorDto.getAmountDueToPay());
                    firstRate.setCapitalPayment(round2(calculatorDto.getLoanValue() - firstRate.getLoanLeftToPay()));
                    firstRate.setInterestPayment(round2(rateValue - firstRate.getCapitalPayment()));
                    firstRate.setCapitalPayment(round2(rateValue - firstRate.getInterestPayment()));
                    break;
                default:
                    SingleRate rate = new SingleRate();
                    calculatorDto.getCreditSchedule().add(rate);
                    rate.setSinglePayment(rateValue);
                    rate.setDayOfPayment(calculatorDto.getDateOfFirstPayment().plusMonths(i + 1));
                    rate.setLoanLeftToToPay(round2(calculatorDto.getCreditSchedule().get(i - 1).getLoanLeftToPay() * calculatorDto.getQConstant() - rateValue));
                    rate.setCapitalPayment(round2(calculatorDto.getCreditSchedule().get(i - 1).getLoanLeftToPay() - rate.getLoanLeftToPay()));
                    rate.setInterestPayment(round2(rateValue - rate.getCapitalPayment()));
                    rate.setCapitalPayment(round2(rateValue - rate.getInterestPayment()));
            }
            calculatorDto.setCreditSchedule(creditSchedule);
        }
    }

    public void calculateDateOfLastInstallment() {
        calculatorDto.setDateOfLastPayment(calculatorDto.getDateOfFirstPayment().plusMonths(calculatorDto.getNumberOfInstallments() - 1));
    }

    public double round2(double value) {
        return BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
