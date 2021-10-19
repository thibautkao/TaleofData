package org.example.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.example.bean.GenericData;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DataService<T extends GenericData> {

    public DataService() {
    }

    public List<T> createDataFromCSV(String filePath, Class<T> type) {

        try {
            HeaderColumnNameMappingStrategy<T> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
            mappingStrategy.setType(type);

            Path path = Paths.get(filePath);
            Reader reader = Files.newBufferedReader(path);
            CsvToBean<T> csvToBean= new CsvToBeanBuilder(reader)
                    .withMappingStrategy(mappingStrategy)
                    .withIgnoreLeadingWhiteSpace(Boolean.TRUE)
                    .build();

            List<T> dataResult = csvToBean.parse();
            reader.close();

            Logger.getLogger(DataService.class.getName()).log(
                    Level.INFO, String.format("Data created from file %s", filePath));

            return dataResult;
        } catch(IOException ex) {
            Logger.getLogger(DataService.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }

    public List<T> transformData(List<T> dataList, Function<T, T> function) {

        var transformedData = dataList.stream()
                .map(function)
                .collect(Collectors.toList());

        Logger.getLogger(DataService.class.getName()).log(Level.INFO, "Data transformed");

        return transformedData;
    }

    public boolean saveDataToCSV(String filePath, List<T> dataList) {

        Path path = Paths.get(filePath);

        try (var writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(dataList);

            Logger.getLogger(DataService.class.getName()).log(
                    Level.INFO, String.format("Data created from file %s", filePath));

            return Boolean.TRUE;
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
            Logger.getLogger(DataService.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
            return Boolean.FALSE;
        }
    }
}
