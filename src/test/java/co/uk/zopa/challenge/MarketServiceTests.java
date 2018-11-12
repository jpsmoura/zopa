package co.uk.zopa.challenge;

import co.uk.zopa.challenge.model.Lender;
import co.uk.zopa.challenge.services.MarketService;
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
public class MarketServiceTests {

	@Autowired
	private MarketService marketService;

	@Test
	public void testLendersCollectionIsOrdered() {
		double rate = 0;
		for (Lender l : marketService.getLenders()) {
			assertTrue(rate <= l.getRate());
			rate = l.getRate();
		}
		assertTrue(marketService.getLenders().get(0).getForename().equals("Jane"));

	}

	@Test
	public void testCSVparseRecords() {
		assertTrue(marketService.getLenders().size() == 7);
	}

	@Test
	public void testMarketAvailableAmount() {
		assertTrue(marketService.getAvailableAmount() == 2330);
	}
}
