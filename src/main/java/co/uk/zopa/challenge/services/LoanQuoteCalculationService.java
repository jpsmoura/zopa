package co.uk.zopa.challenge.services;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.QuoteCalculationService;
import co.uk.zopa.challenge.interfaces.ValidationService;
import co.uk.zopa.challenge.model.Lender;
import co.uk.zopa.challenge.model.Loan;
import co.uk.zopa.challenge.model.LoanQuote;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public @Data
class LoanQuoteCalculationService implements QuoteCalculationService {

    @Value("${loan.payments.year}")
    private int numPaymentsYear;

    @Value("${loan.duration}")
    private int loanDuration;

    @Autowired
    private MarketCSVService marketCSVService;

    @Autowired
    private ValidationService validationService;


    public LoanQuote processQuote(Loan loanRequest) throws InvalidLoanAmount, MarketInsufficientFunds {
        validationService.validateLoanAmount(loanRequest);
        marketCSVService.validateMarketSufficientFunds(loanRequest);
        return computeQuote(loanRequest);
    }

    public LoanQuote computeQuote(Loan loan) {
        LoanQuote quote = new LoanQuote();
        quote.setAmount(loan.getAmount());
        quote.setRate(calculateWeightedAverageRate(quote));
        quote.setRepayment(calculateRepayment(quote));
        quote.setTotalRepayment(calculateTotalRepayment(quote));
        return quote;
    }

    public double calculateWeightedAverageRate(LoanQuote loan) {
        int currentAmt = 0;
        double rate = 0;

        for (Lender l : getMarketCSVService().getLenders()) {
            if (currentAmt + l.getAmount() > loan.getAmount()) {
                double delta = loan.getAmount() - currentAmt;
                rate += (delta / loan.getAmount()) * l.getRate();
                break;
            } else {
                currentAmt += l.getAmount();
                rate += ((double) l.getAmount() / loan.getAmount()) * l.getRate();
            }
        }
        return rate;
    }
    public double calculateRepayment(LoanQuote loan) {

        return loan.getRate() * loan.getAmount() / getNumPaymentsYear() / (1 - Math.pow((loan.getRate() / getNumPaymentsYear() + 1), (-getNumPaymentsYear() * getLoanDuration())));
    }

    public double calculateTotalRepayment(LoanQuote loan) {

        return loan.getRepayment() * getLoanDuration() * getNumPaymentsYear();
    }

}