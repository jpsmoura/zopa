package co.uk.zopa.challenge;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.QuoteCalculationService;
import co.uk.zopa.challenge.interfaces.ValidationService;
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
    private QuoteCalculationService quoteCalculationService;

    @Autowired
    private ValidationService validationService;

    @Value("${loan.amount}")
    private int loanAmt;

    public static void main(String[] args) {
        SpringApplication.run(LoanQuoteConsoleApp.class, args);

    }

    @Override
    public void run(String... args) {

        try {
            Loan loan = new Loan(loanAmt);
            LoanQuote quote = quoteCalculationService.processQuote(loan);
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