package co.uk.zopa.challenge.services;

import co.uk.zopa.challenge.model.Lender;
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
class MarketService {

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

}
