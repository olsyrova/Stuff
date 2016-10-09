import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by olgasyrova on 09.10.16.
 */
public class JsonRestProvider {

    final static Logger logger = Logger.getLogger(Main.class);

    private static final String LOCATION_ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en/";

    public static String getJSONFromUrl(String query) {
        String result = "";
        // validate user input string
        if (isValidUserQuery(query)) {
            // build query url
            String queryURL = LOCATION_ENDPOINT + query;
            try {
                URL url = new URL(queryURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream()), "UTF-8"));
                result = readJSONString(br);

                // close the connection
                conn.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * checks if input is a valid city
     * TO DO : need to clarify what would be an invalid input
     * @param input
     * @return
     */

    private static boolean isValidUserQuery(String input){
        return !"".equalsIgnoreCase(input) && !"?".equalsIgnoreCase(input);
    }

    public static String readJSONString(BufferedReader br){
        StringBuilder sb = new StringBuilder();
        String jsonString = "";
        try {
            while ((jsonString = br.readLine()) != null) {
                sb.append(jsonString);
            }
            if (isValidJson(sb.toString())){
                return sb.toString();
            } else {
                logger.error("invalid json returned from url");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isValidJson(String json){
        return !"".equalsIgnoreCase(json) && json != null;
    }


}
