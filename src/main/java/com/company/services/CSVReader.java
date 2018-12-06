package com.company.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class CSVReader {
    private static String CSV_FILE_PATH;
    private List<String> list = new ArrayList<>();

    public static void setCsvFilePath(String path){
        CSV_FILE_PATH = path;
    }

    public static String getCsvFilePath(){
        return CSV_FILE_PATH;
    }

    public List getList() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("URL")
                        .withIgnoreHeaderCase()
                        .withTrim())
        ) {
            for (CSVRecord csvRecord : csvParser) {
                list.add(csvRecord.get("URL"));
            }
        }
        Collections.shuffle(list);
        return list;
    }

}