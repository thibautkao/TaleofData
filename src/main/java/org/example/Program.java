package org.example;

import org.example.bean.Data;
import org.example.bean.GenericData;
import org.example.service.DataService;

import java.util.function.Function;

public class Program {

    private static final String INPUT_FILE_PATH = "src/main/resources/in/data.csv";
    private static final String OUTPUT_FILE_PATH = "src/main/resources/out/data.csv";

    public static void main(String[] args) {

        var dataService = new DataService<Data>();

        // Read CSV file
        var dataList = dataService.createDataFromCSV(INPUT_FILE_PATH, Data.class);

        Function<Data, Data> transformationFunction = data -> {
            Data resultData = new Data();
            resultData.setName(data.getName().toUpperCase());
            Double roundedValue = Double.valueOf(String.valueOf(Math.round(data.getValue())));
            resultData.setValue(roundedValue);
            return resultData;
        };

        // Transform CSV file
        var dataListToSave = dataService.transformData(dataList, transformationFunction);

        dataListToSave.forEach(System.out::println);

        // Save CVS file
        dataService.saveDataToCSV(OUTPUT_FILE_PATH, dataListToSave);
    }
}
