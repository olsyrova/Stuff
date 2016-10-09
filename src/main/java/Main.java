import domain.City;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by syrovo01 on 04.10.2016.
 */
public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args){
		// check if the number of arguments is right
		if (args.length != 1){
			logger.error("please provide an argument with city name");
			return;
		}

        // get response json
        String jsonString = JsonRestProvider.getJSONFromUrl(args[0]);
        if ("".equalsIgnoreCase(jsonString) || jsonString == null){
            logger.error("empty json is returned => no csv file will be created");
            return;
        }
        // map received json to java objects
        List<City> cities = JsonTransformer.parseJson(jsonString);
        // write objects to csv file
        CsvWriter.writeToCsvFile(args[0], cities);
        logger.info(args[0] + ".csv file is created successfully in the current directory");

	}

}
