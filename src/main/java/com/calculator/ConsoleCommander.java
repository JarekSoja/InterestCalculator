package com.calculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class ConsoleCommander extends Commander {

    private Scanner reader = new Scanner(System.in);
    private CalculatorDto calculatorDto;
    private CalculationPattern calculations;
    private DataPrinter printer;

    ConsoleCommander() {
        calculatorDto = CalculatorDto.getCalculatorDtoInstance();
        calculations = new FlatRateCalculation();
        printer = new ConsoleDataPrinter();
    }

    void runApp() {
        setDateOfFirstPayment();
        setYearlyInterestRate();
        setNumberOfInstallments();
        setValueOfGoods();
        setDownPayment();
        reader.close();
        calculations.runCalculations();
        printer.returnSummary();
    }

    void setYearlyInterestRate() {
        while (true) {
            System.out.println("Please enter yearly interest rate: ");
            String temp = reader.nextLine();
            double result = Double.parseDouble(temp);
            if (DataVerificator.isYearlyInterestValid(result)) {
                calculatorDto.setInterestRate(result);
                break;
            } else {
                yearlyInterestRateError();
            }
        }
    }

    void setNumberOfInstallments() {
        while (true) {
            System.out.println("Please enter number of installments: ");
            int result = Integer.parseInt(reader.nextLine());
            if (DataVerificator.isNumberOfInstallmentsValid(result)) {
                calculatorDto.setNumberOfInstallments(result);
                break;
            } else
                numberOfInstallmentsError();
        }
    }

    void setValueOfGoods() {
        while (true) {
            System.out.println("Please enter total value of goods: ");
            String temp = reader.nextLine();
            double result = Double.parseDouble(temp);
            if (DataVerificator.isValueOfGoodsValid(result)) {
                calculatorDto.setValueOfGoods(result);
                break;
            } else
                valueOfGoodsError();
        }
    }

    void setDownPayment() {
        while (true) {
            System.out.println("Please enter your down payment: ");
            String temp = reader.nextLine();
            double result = Double.parseDouble(temp);
            if (DataVerificator.isDownPaymentValid(result, calculatorDto.getValueOfGoods())) {
                calculatorDto.setDownPayment(result);
                break;
            } else
                downPaymentError();
        }
    }

    void setDateOfFirstPayment() {
        while (true) {
            System.out.println("Please enter payment date of first rate as YYYY-MM-DD: ");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            String temp = reader.nextLine();
            LocalDate firstPaymentDate = LocalDate.parse(temp, formatter);
            if (DataVerificator.isDateOfFIrstPaymentValid(firstPaymentDate)) {
                calculatorDto.setDateOfFirstPayment(firstPaymentDate);
                break;
            } else
                dateOfFirstPaymentError();
        }
    }

    private static void downPaymentError() {
        System.out.println("Declared down payment must be higher than zero and be no higher that 50% of declared value of goods.");
    }

    private static void dateOfFirstPaymentError() {
        System.out.println("Payment date of first rate can't be in the past and can't be set to any day after 28th day of month.");
    }

    private static void yearlyInterestRateError() {
        System.out.println("Interest rate has to be positive number.");
    }

    private static void numberOfInstallmentsError() {
        System.out.println("Number of installments has to be between 3 and 60 months.");
    }

    private static void valueOfGoodsError() {
        System.out.println("Value of goods has to be bigger than zero.");
    }

}
