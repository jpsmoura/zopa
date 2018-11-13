package co.uk.zopa.challenge.services;

import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.interfaces.MarketService;
import co.uk.zopa.challenge.model.Lender;
import co.uk.zopa.challenge.model.Loan;
import co.uk.zopa.challenge.utilities.CSVUtility;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public @Data
class MarketCSVService implements MarketService {

    private List<Lender> lenders;

    private long availableAmount;

    @Value("${csv.filename}")
    private String filename;

    @PostConstruct
    public void init() throws FileNotFoundException {
        setLenders(CSVUtility.loadCSVFile(filename, Lender.class));
        sortLendersByLowestRate();
        setAvailableAmount(lenders.stream().collect(Collectors.summingInt(Lender::getAmount)));
    }

    public void sortLendersByLowestRate() {
        lenders.sort(Comparator.comparing(Lender::getRate));
    }

    public void validateMarketSufficientFunds(Loan loan) throws MarketInsufficientFunds {

        if (loan.getAmount() > getAvailableAmount())
            throw new MarketInsufficientFunds(String.format("Market can't fulfill loan request: loanAmount (%s) > marketAvailable(%s)",loan.getAmount(), getAvailableAmount()));

    }

}
