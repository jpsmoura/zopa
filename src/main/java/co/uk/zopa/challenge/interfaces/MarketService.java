package co.uk.zopa.challenge.interfaces;

import co.uk.zopa.challenge.exceptions.MarketInsufficientFunds;
import co.uk.zopa.challenge.model.Lender;
import co.uk.zopa.challenge.model.Loan;

import java.util.List;

public interface MarketService {

    public List<Lender> getLenders();

    public void sortLendersByLowestRate();

    public long getAvailableAmount();

    public void validateMarketSufficientFunds(Loan loan) throws MarketInsufficientFunds;

    }
