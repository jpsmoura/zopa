package co.uk.zopa.challenge;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.LoanQuoteCalculationService;
import co.uk.zopa.challenge.interfaces.LoanQuoteValidationService;
import co.uk.zopa.challenge.model.Loan;
import co.uk.zopa.challenge.model.LoanQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LoanQuoteConsoleApp implements CommandLineRunner {

    @Autowired
    private LoanQuoteCalculationService loanCalculationService;

    @Autowired
    private LoanQuoteValidationService loanValidationService;

    @Value("${loan.amount}")
    private int loanAmt;

    public static void main(String[] args) {
        SpringApplication.run(LoanQuoteConsoleApp.class, args);

    }

    @Override
    public void run(String... args) {

        try {
            Loan loan = new Loan(loanAmt);
            loanValidationService.validateLoanRequest(loan);
            LoanQuote quote = loanCalculationService.processQuote(loan);
            System.out.println(quote.prettyPrint());
        } catch (InvalidLoanAmount invalidLoanAmount) {
            //invalidLoanAmount.printStackTrace();
            System.out.println(invalidLoanAmount.getMessage());
        } catch (MarketInsufficientFunds marketInsufficientFunds) {
            //marketInsufficientFunds.printStackTrace();
            System.out.println(marketInsufficientFunds.getMessage());
        }


    }


}