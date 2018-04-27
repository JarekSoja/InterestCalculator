package com.calculator;

public class ConsoleDataPrinter implements DataPrinter {

    private CalculatorDto calculatorDto = CalculatorDto.getCalculatorDtoInstance();

    public void returnSummary() {
        System.out.println("Thank you!\nAccording to data you've provided:\n" +
                "- date of last installment is: " + calculatorDto.getDateOfLastPayment());
        System.out.println("- loan amount equals: " + calculatorDto.getLoanValue());
        System.out.println("- total credit cost: " + calculatorDto.getLoanCost());
        System.out.println("- total value of goods you want to buy: " + calculatorDto.getValueOfGoodsWithLoanCost());
        System.out.println();
        System.out.println(schedule());
    }

    private String schedule() {
        final String PIPE = "|";
        final String SEPARATOR = "|-------------|-------------|-------------|-------------|-------------|-------------|\n";
        final String NEW_LINE = "\n";
        StringBuilder schedule = new StringBuilder();
        for (int i = -1; i < calculatorDto.getNumberOfInstallments(); i++) {
            switch (i) {
                case -1:
                    schedule.append(SEPARATOR)
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
                            .append(NEW_LINE)
                            .append(SEPARATOR);
                    break;
                default:
                    SingleRate rate = calculatorDto.getCreditSchedule().get(i);
                    System.out.println(rate);
                    int rateNumber = i + 1;
                    if (rateNumber < 10) {
                        schedule.append("      ")
                                .append(rateNumber)
                                .append("         ");
                    } else {
                        schedule.append("     ")
                                .append(rateNumber)
                                .append("         ");
                    }
                    schedule.append(" ")
                            .append(rate.getDayOfPayment())
                            .append("   ")
                            .append("    ")
                    .append(rate.getSingleInstallment())
                    .append("     ")
                            .append("     ")
                            .append(rate.getCapitalPayment())
                            .append("      ")
                            .append("     ")
                            .append(rate.getInterestPayment())
                            .append("    ")
                            .append("   ")
                            .append(rate.getLoanLeftToPay())
                            .append("   ")
                            .append(NEW_LINE);
            }
        }
        schedule.append(SEPARATOR);
        return schedule.toString();
    }
}












