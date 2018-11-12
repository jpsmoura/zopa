package co.uk.zopa.challenge.interfaces;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.model.Loan;

public interface LoanQuoteValidationService {

    public void validateLoanRequest(Loan loanRequest) throws InvalidLoanAmount, MarketInsufficientFunds;
}
