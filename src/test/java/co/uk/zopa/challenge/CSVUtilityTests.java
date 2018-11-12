package co.uk.zopa.challenge;


import co.uk.zopa.challenge.model.Lender;
import co.uk.zopa.challenge.utilities.CSVUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.io.FileNotFoundException;


@Slf4j
public class CSVUtilityTests {

	@Test(expected = FileNotFoundException.class)
	public void testMissingCSVFile() throws FileNotFoundException {
		CSVUtility.loadCSVFile("nofile.csv", Lender.class);
	}

	@Test
	public void testFoundCSVFile() throws FileNotFoundException {
		CSVUtility.loadCSVFile("Market Data for Exercise - csv.csv", Lender.class);
	}




}
