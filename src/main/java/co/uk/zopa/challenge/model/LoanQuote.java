package co.uk.zopa.challenge.model;

import lombok.Data;

public @Data
class LoanQuote {

    private int amount;

    private double rate;

    private double repayment;

    private double totalRepayment;

    public String prettyPrint() {
        String newline = System.lineSeparator();
        String print = "";
        print += String.format("Requested amount: %d£", getAmount()) + newline;
        print += String.format("Rate: %.1f%%", getRate() * 100) + newline;
        print += String.format("Monthly repayment: %.2f£", getRepayment()) + newline;
        print += String.format("Total repayment: %.2f£", getTotalRepayment());

        return print;
    }
}
