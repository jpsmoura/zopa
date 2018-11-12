package co.uk.zopa.challenge;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.LoanQuoteValidationService;
import co.uk.zopa.challenge.model.Loan;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanQuoteValidationTests {

	@Autowired
	private LoanQuoteValidationService loanValidationService;


	@Test
	public void testValidAmount() throws InvalidLoanAmount, MarketInsufficientFunds {
		Loan loan = new Loan(1100);
		loanValidationService.validateLoanRequest(loan);
	}

	@Test(expected = InvalidLoanAmount.class)
	public void testLowerBoundInvalidAmount() throws InvalidLoanAmount, MarketInsufficientFunds {
		Loan loan = new Loan(900);
		loanValidationService.validateLoanRequest(loan);

	}

	@Test(expected = MarketInsufficientFunds.class)
	public void testMarketInsufficientFunds() throws InvalidLoanAmount, MarketInsufficientFunds {
		Loan loan = new Loan(3000);
		loanValidationService.validateLoanRequest(loan);
	}

	@Test(expected = InvalidLoanAmount.class)
	public void testUpperBoundInvalidAmount() throws InvalidLoanAmount, MarketInsufficientFunds {
		Loan loan = new Loan(16000);
		loanValidationService.validateLoanRequest(loan);

	}

	@Test(expected = InvalidLoanAmount.class)
	public void testMultiplierInvalidAmount() throws InvalidLoanAmount, MarketInsufficientFunds {
		Loan loan = new Loan(2250);
		loanValidationService.validateLoanRequest(loan);

	}



}
