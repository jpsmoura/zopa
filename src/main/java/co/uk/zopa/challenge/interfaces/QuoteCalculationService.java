package co.uk.zopa.challenge.interfaces;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.model.Loan;
import co.uk.zopa.challenge.model.LoanQuote;

public interface QuoteCalculationService {

    public LoanQuote processQuote(Loan loanRequest) throws InvalidLoanAmount, MarketInsufficientFunds;

    public double calculateWeightedAverageRate(LoanQuote loan);

    public double calculateRepayment(LoanQuote loan);

    public double calculateTotalRepayment(LoanQuote loan);

}
