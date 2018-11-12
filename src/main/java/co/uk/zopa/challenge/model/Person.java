package co.uk.zopa.challenge.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

public @Data
class Person {

    @CsvBindByPosition(position = 0)
    String forename;

}
