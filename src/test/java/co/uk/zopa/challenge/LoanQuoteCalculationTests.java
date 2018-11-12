package co.uk.zopa.challenge;

import co.uk.zopa.challenge.exceptions.InvalidLoanAmount;
import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.LoanQuoteCalculationService;
import co.uk.zopa.challenge.model.Lender;
import co.uk.zopa.challenge.model.Loan;
import co.uk.zopa.challenge.model.LoanQuote;
import co.uk.zopa.challenge.services.LoanQuoteCalculationServiceImpl;
import co.uk.zopa.challenge.utilities.CSVUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanQuoteCalculationTests {

	@Autowired
	private LoanQuoteCalculationServiceImpl loanQuoteCalculation;

	@Test
	public void testExampleProvided() {

		Loan loan = new Loan(1000);
		LoanQuote quote = loanQuoteCalculation.processQuote(loan);
		assertEquals(String.format("%.2f",quote.getRepayment()),"30.88");
		assertEquals(String.format("%.2f",quote.getTotalRepayment()),"1111.64");
		assertEquals(String.format("%.1f",quote.getRate()*100),"7.0");
	}

	@Test
	public void testCalulateRate() {

		LoanQuote loan = new LoanQuote();
		loan.setAmount(1000);
		assertEquals(String.format("%.1f",loanQuoteCalculation.calculateRate(loan) * 100),"7.0");

	}

	@Test
	public void testCalculateRepayment() {

		LoanQuote loan = new LoanQuote();
		loan.setAmount(1000);
		loan.setRate(loanQuoteCalculation.calculateRate(loan));
		assertEquals(String.format("%.2f",loanQuoteCalculation.calculateRepayment(loan)),"30.88");

	}

	@Test
	public void testCalculateTotalRepayment() {

		LoanQuote loan = new LoanQuote();
		loan.setAmount(1000);
		loan.setRate(loanQuoteCalculation.calculateRate(loan));
		loan.setRepayment(loanQuoteCalculation.calculateRepayment(loan));
		assertEquals(String.format("%.2f",loanQuoteCalculation.calculateTotalRepayment(loan)),"1111.64");
	}





}
