package co.uk.zopa.challenge.services;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.LoanQuoteValidationService;
import co.uk.zopa.challenge.model.Loan;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public @Data
class LoanQuoteValidationServiceImpl implements LoanQuoteValidationService {

    @Value("${loan.amount.multiplier}")
    private int loanAmtMult;

    @Value("${loan.amount.minimum}")
    private int loanAmtMin;

    @Value("${loan.amount.maximum}")
    private int loanAmtMax;

    @Autowired
    private MarketService marketService;

    public void validateLoanAmount(Loan loan) throws InvalidLoanAmount {

        if (!((getLoanAmtMin() <= loan.getAmount()) && (loan.getAmount() <= getLoanAmtMax()) && ((loan.getAmount() % getLoanAmtMult()) == 0)))
            throw new InvalidLoanAmount(String.format("Please provide a valid loan amount between %s and %s inclusive. The amount provided must be a multiple of %s", getLoanAmtMin(), getLoanAmtMax(), getLoanAmtMult()));

    }
    public void validateMarketSufficientFunds(Loan loan) throws MarketInsufficientFunds {

        if (loan.getAmount() > this.getMarketService().getAvailableAmount())
            throw new MarketInsufficientFunds(String.format("MarketService can't fulfill loan request: loanAmount (%s) > marketAvailable(%s)",loan.getAmount(), this.getMarketService().getAvailableAmount()));

    }
    public void validateLoanRequest(Loan loanRequest) throws InvalidLoanAmount, MarketInsufficientFunds {
        validateLoanAmount(loanRequest);
        validateMarketSufficientFunds(loanRequest);
    }


}