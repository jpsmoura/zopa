package co.uk.zopa.challenge;

import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.MarketService;
import co.uk.zopa.challenge.model.Lender;
import co.uk.zopa.challenge.model.Loan;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketCSVServiceTests {

	@Autowired
	private MarketService marketCSVService;

	@Test
	public void testLendersCollectionIsOrdered() {
		double rate = 0;
		for (Lender l : marketCSVService.getLenders()) {
			assertTrue(rate <= l.getRate());
			rate = l.getRate();
		}
		assertTrue(marketCSVService.getLenders().get(0).getForename().equals("Jane"));

	}

	@Test(expected = MarketInsufficientFunds.class)
	public void testMarketInsufficientFunds() throws MarketInsufficientFunds {
		Loan loan = new Loan(3000);
		marketCSVService.validateMarketSufficientFunds(loan);
	}

	@Test
	public void testCSVparseRecords() {
		assertTrue(marketCSVService.getLenders().size() == 7);
	}

	@Test
	public void testMarketAvailableAmount() {
		assertTrue(marketCSVService.getAvailableAmount() == 2330);
	}
}
