import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by olgasyrova on 09.10.16.
 */
public class JsonRestProviderTest {

    @Test
    public void getJSONFromUrlTest(){
        String jsonBerlin = JsonRestProvider.getJSONFromUrl("Berlin");
        String badRequestQuery = JsonRestProvider.getJSONFromUrl("");
        String badRequestQuery2 = JsonRestProvider.getJSONFromUrl("?");
        String emptyJson = JsonRestProvider.getJSONFromUrl("CITY_NAME");
        Assert.assertNotNull(jsonBerlin);
        Assert.assertNull(badRequestQuery);
        Assert.assertNull(badRequestQuery2);
        Assert.assertEquals("[]",emptyJson);

    }
}
