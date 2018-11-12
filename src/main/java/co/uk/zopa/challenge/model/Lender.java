package co.uk.zopa.challenge.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

public @Data
class Lender extends Person {

    @CsvBindByPosition(position = 1)
    private double rate;
    @CsvBindByPosition(position = 2)
    private int amount;

    @Override
    public String toString() {
        return "Lender{" +
                "rate=" + rate +
                ", amount=" + amount +
                ", forename='" + forename + '\'' +
                '}';
    }
}