package co.uk.zopa.challenge.services;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.interfaces.ValidationService;
import co.uk.zopa.challenge.model.Loan;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public @Data
class LoanValidationService implements ValidationService {

    @Value("${loan.amount.multiplier}")
    private int loanAmtMult;

    @Value("${loan.amount.minimum}")
    private int loanAmtMin;

    @Value("${loan.amount.maximum}")
    private int loanAmtMax;


    public void validateLoanAmount(Loan loan) throws InvalidLoanAmount {

        if (!((getLoanAmtMin() <= loan.getAmount()) && (loan.getAmount() <= getLoanAmtMax()) && ((loan.getAmount() % getLoanAmtMult()) == 0)))
            throw new InvalidLoanAmount(String.format("Please provide a valid loan amount between %s and %s inclusive. The amount provided must be a multiple of %s", getLoanAmtMin(), getLoanAmtMax(), getLoanAmtMult()));

    }
}