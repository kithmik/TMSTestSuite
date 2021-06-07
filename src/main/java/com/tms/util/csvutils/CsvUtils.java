package com.tms.util.csvutils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Kithmi Kalpana
 * Read the test data from the CSV file
 */

public class CsvUtils {
    //Provide test data CSV file path.
    static String CSV_PATH = "src/main/resources/config/db_config.csv";


    @Test
    public static String[] ReadCSV() throws IOException, CsvValidationException {
        //Create an object of CSVReader
        CSVReader csvReader = new CSVReader(new FileReader(CSV_PATH));
        String[] csvCell = csvReader.readNext();

        int csvCellLen =csvCell.length;

        if (csvCellLen !=0){
            String dbUrl = csvCell[0];
            String username = "";
            String password = "";

            if (csvCellLen > 1){
                username = csvCell[1];
            }
            if (csvCellLen > 2){
                password = csvCell[2];
            }
            return new String[]{dbUrl,username,password};

        }
        throw new CsvValidationException("CSV file is empty");

    }
}
