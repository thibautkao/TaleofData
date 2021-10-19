package org.example.service;

import org.example.bean.Data;
import org.example.bean.GenericData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    private static final String INPUT_FILE_PATH = "src/test/resources/in/data.csv";
    private static final String OUTPUT_FILE_PATH = "src/test/resources/out/data.csv";

    private DataService underTest;
    private Function<Data, Data> transformationFunction;

    @BeforeEach
    void setUp() {
        underTest = new DataService();
        transformationFunction = (data) -> {
            Data resultData = new Data();
            resultData.setName(data.getName().toUpperCase());
            Double roundedValue = Double.valueOf(String.valueOf(Math.round(data.getValue())));
            resultData.setValue(roundedValue);
            return resultData;
        };
    }

    @Test
    void givenCSVFile_whenReadingFile_thenReturnsListOfData() throws IOException {

        List<Data> dataList = underTest.createDataFromCSV(INPUT_FILE_PATH, Data.class);

        Assertions.assertAll(
                () -> assertFalse(dataList.isEmpty()),
                () -> assertEquals(5, dataList.size()),
                () -> assertEquals("thibaut", dataList.get(0).getName())
        );
    }

    @Test
    void givenDataList_whenSavingData_thenSaveListOfData() throws IOException {

        var transformedData = createListOfTransformedData();

        boolean isDataSaved = underTest.saveDataToCSV(OUTPUT_FILE_PATH, transformedData);

        assertTrue(isDataSaved);
    }

    private List<Data> createListOfTransformedData() throws IOException {
        var dataList = underTest.createDataFromCSV(INPUT_FILE_PATH, Data.class);
        var transformedDataList = underTest.transformData(dataList, transformationFunction);

        return transformedDataList;
    }
}