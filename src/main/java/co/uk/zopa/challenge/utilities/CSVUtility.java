package co.uk.zopa.challenge.utilities;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@Slf4j
public class CSVUtility {


    public static <T> List<T> loadCSVFile(String filepath, Class<T> clazz) throws FileNotFoundException {

        FileReader fileReader = new FileReader(ResourceUtils.getFile("classpath:" + filepath));

        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();

        ms.setType(clazz);

        CsvToBean cb = new CsvToBeanBuilder(fileReader)
                .withType(clazz)
                .withMappingStrategy(ms)
                .withSkipLines(1)
                .build();

        return cb.parse();
    }

}
