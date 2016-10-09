import domain.City;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

/**
 * this class writes a list of java objects to a csv file;
 * the name of the file consists of initial query (i.e. Berlin => berlin.csv)
 * Created by olgasyrova on 09.10.16.
 */
public class CsvWriter {
    public static final String DELIMITER = ",";
    public static final String END_OF_LINE = "\r\n";
    // csv header fields
    public static final String ID = "_id";
    public static final String CITY_NAME = "name";
    public static final String TYPE = "type";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

    public static void writeToCsvFile(String fileName, List<City> cities){
        try {
            File file = new File(fileName + ".csv");
            FileWriter writer = new FileWriter(file);
            writer.append(constructHeader());
            cities.forEach(new Consumer<City>() {
                @Override
                public void accept(City city) {
                    try {
                        writer.append(constructLine(city));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // adjust to csv file format conventions
    // https://www.csvreader.com/csv_format.php
    public static String adaptToCsvConventions(String value){
        String result = value;
        if (result.contains("\"")) {
            result = result.replaceAll("\"", "\"\"");
        }
        if (result.contains(",")){
            result = result.replaceAll(",", "\\,");
        }

        return result;
    }


    public static String constructHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append(ID).append(DELIMITER)
                .append(CITY_NAME).append(DELIMITER)
                .append(TYPE).append(DELIMITER)
                .append(LATITUDE).append(DELIMITER)
                .append(LONGITUDE).append(END_OF_LINE);
        return sb.toString();
    }

    public static String constructLine(City city){
        StringBuilder sb = new StringBuilder();
        sb.append(city.get_id()).append(DELIMITER);
        sb.append(city.getName()).append(DELIMITER);
        sb.append(city.getType()).append(DELIMITER);
        sb.append(city.getGeo_position().getLatitude()).append(DELIMITER);
        sb.append(city.getGeo_position().getLongitude()).append(END_OF_LINE);
        return sb.toString();
    }


}
