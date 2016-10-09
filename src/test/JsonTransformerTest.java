import domain.City;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by olgasyrova on 09.10.16.
 */
public class JsonTransformerTest {

    @Test
    public void parseJsonTest(){

        try {
            String json = new String(Files.readAllBytes(Paths.get("src/berlin_test.json")));
            List<City> cityList = JsonTransformer.parseJson(json);
            Assert.assertEquals(8, cityList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNull(JsonTransformer.parseJson("[]"));
    }
}
