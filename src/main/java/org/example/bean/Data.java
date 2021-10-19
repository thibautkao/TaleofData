package org.example.bean;

import com.opencsv.bean.CsvBindByName;

public class Data extends GenericData {

    @CsvBindByName
    private String name;

    @CsvBindByName
    private Double value;

    public Data() {
    }

    public Data(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
