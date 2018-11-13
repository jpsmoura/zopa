package co.uk.zopa.challenge;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.ValidationService;
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
	private ValidationService validationService;


	@Test
	public void testValidAmount() throws InvalidLoanAmount {
		Loan loan = new Loan(1100);
		validationService.validateLoanAmount(loan);
	}

	@Test(expected = InvalidLoanAmount.class)
	public void testLowerBoundInvalidAmount() throws InvalidLoanAmount {
		Loan loan = new Loan(900);
		validationService.validateLoanAmount(loan);

	}


	@Test(expected = InvalidLoanAmount.class)
	public void testUpperBoundInvalidAmount() throws InvalidLoanAmount {
		Loan loan = new Loan(16000);
		validationService.validateLoanAmount(loan);

	}

	@Test(expected = InvalidLoanAmount.class)
	public void testMultiplierInvalidAmount() throws InvalidLoanAmount {
		Loan loan = new Loan(2250);
		validationService.validateLoanAmount(loan);

	}



}
