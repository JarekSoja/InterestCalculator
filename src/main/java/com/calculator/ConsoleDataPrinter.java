package com.calculator;

import sun.management.jmxremote.SingleEntryRegistry;

public class ConsoleDataPrinter implements DataPrinter {

    private final String PIPE = "|";
    private final String SEPARATOR = "|-------------|-------------|-------------|-------------|-------------|-------------|\n";
    private final String LINE_WITH_SPACES = "|             |             |             |             |             |             |\n";
    private final String SPACE = "   ";
    private final String NEW_LINE = "\n";
    private String schedule = "";
    private CalculatorDto calculatorDto = CalculatorDto.getCalculatorDtoInstance();

    public void returnSummary() {
        System.out.println("Thank you!\nAccording to data you've provided:\n" +
                "- date of last installment is: " + calculatorDto.getDateOfLastPayment());
        System.out.println("- loan amount equals: " + calculatorDto.getLoanValue());
        System.out.println("- total credit cost: " + calculatorDto.getLoanCost());
        System.out.println("- total value of goods you want to buy: " + calculatorDto.getValueOfGoodsWithLoanCost());

        System.out.println(schedule());
    }

    private String schedule() {
        StringBuilder schedule = new StringBuilder();
        for (int i = -1; i < calculatorDto.getNumberOfInstallments(); i++) {
            switch (i) {
                case -1:
                    schedule.append(SEPARATOR)
                            .append(LINE_WITH_SPACES)
                            .append(PIPE)
                            .append(" RATE NUMBER ")
                            .append(PIPE)
                            .append("   DATE DUE  ")
                            .append(PIPE)
                            .append("    TO PAY   ")
                            .append(PIPE)
                            .append(" INC CAPITAL ")
                            .append(PIPE)
                            .append(" INC INTEREST")
                            .append(PIPE)
                            .append(" LEFT TO PAY ")
                            .append(PIPE)
                            .append(SEPARATOR);

                    break;
                default:
                    SingleRate rate = calculatorDto.getCreditSchedule().get(i);
                    schedule.append(PIPE)
                            .append(i+1)
                            .append(PIPE)
                            .append(rate.getDayOfPayment())
                            .append(PIPE)
                            .append(calculatorDto.getSingleInstallment())
                            .append(PIPE)
                            .append(rate.getCapitalPayment())
                            .append(PIPE)
                            .append(rate.getInterestPayment())
                            .append(PIPE)
                            .append(rate.getLoanLeftToPay())
                            .append(PIPE);
            }
        }
        schedule.append(SEPARATOR);
        return schedule.toString();
    }
}












