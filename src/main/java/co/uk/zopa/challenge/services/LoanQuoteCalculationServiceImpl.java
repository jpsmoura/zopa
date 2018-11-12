package co.uk.zopa.challenge.services;

import co.uk.zopa.challenge.interfaces.LoanQuoteCalculationService;
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
class LoanQuoteCalculationServiceImpl implements LoanQuoteCalculationService {

    @Value("${loan.amount.multiplier}")
    private int loanAmtMult;

    @Value("${loan.amount.minimum}")
    private int loanAmtMin;

    @Value("${loan.amount.maximum}")
    private int loanAmtMax;

    @Value("${loan.payments.year}")
    private int numPaymentsYear;

    @Value("${loan.duration}")
    private int loanDuration;

    @Autowired
    private MarketService marketService;


    public LoanQuote processQuote(Loan loanRequest) {
        return computeQuote(loanRequest);
    }

    public LoanQuote computeQuote(Loan loan) {
        LoanQuote quote = new LoanQuote();
        quote.setAmount(loan.getAmount());
        quote.setRate(calculateRate(quote));
        quote.setRepayment(calculateRepayment(quote));
        quote.setTotalRepayment(calculateTotalRepayment(quote));
        return quote;
    }

    public double calculateRate(LoanQuote loan) {
        int currentAmt = 0;
        double rate = 0;

        for (Lender l : getMarketService().getLenders()) {
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