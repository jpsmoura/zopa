package co.uk.zopa.challenge.interfaces;

import co.uk.zopa.challenge.model.Loan;
import co.uk.zopa.challenge.model.LoanQuote;

public interface LoanQuoteCalculationService {

    public LoanQuote processQuote(Loan loanRequest);

    }
