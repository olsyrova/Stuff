import domain.City;
import domain.GeoPosition;
import junit.framework.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olgasyrova on 09.10.16.
 */
public class CsvWriterTest {

    @Test
    public void writeToCsvFileTest(){
        String query = "London";
        City city1 = new City();
        City city2 = new City();
        city1.set_id(1);
        city1.setName("London");
        city1.setType("location");
        GeoPosition geoPosition1 = new GeoPosition();
        geoPosition1.setLatitude(1.22f);
        geoPosition1.setLongitude(4.55f);
        city1.setGeo_position(geoPosition1);
        city2.set_id(2);
        city2.setName("Paris");
        city2.setType("location");
        GeoPosition geoPosition2 = new GeoPosition();
        geoPosition2.setLatitude(1.65f);
        geoPosition2.setLongitude(10.333f);
        city2.setGeo_position(geoPosition1);
        List<City> cityList = new ArrayList<>();
        cityList.add(city1);
        cityList.add(city2);
        CsvWriter.writeToCsvFile(query, cityList);
        File f = new File(query + ".csv");
        Assert.assertTrue(f.exists());

    }

}
