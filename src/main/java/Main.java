import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.*;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by syrovo01 on 04.10.2016.
 */
public class Main {

	public static final String LOCATION_ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en/";

	public static void main(String[] args){
		// check if the number of arguments is right
		if (args.length != 1){
			System.out.println("the program accepts exactly 1 argument");
			return;
		}

		String query = LOCATION_ENDPOINT + args[0];
		try {
			URL url = new URL(query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream()), "UTF-8"));

			String jsonString;
			StringBuilder sb = new StringBuilder();
			System.out.println("Output from Server .... \n");
			while ((jsonString = br.readLine()) != null) {
				sb.append(jsonString);
				System.out.println(jsonString);
			}
			System.out.println("stop");

			JSONObject output;
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				List<City> myObjects = Arrays.asList(mapper.readValue(sb.toString(), City[].class));

				// create mapper and schema
				CsvSchema csvSchema = CsvSchema.builder()
						.addColumn("_id")
						.addColumn("name")
						.addColumn("type")
						.addColumn("latitude", CsvSchema.ColumnType.NUMBER)
						.addColumn("longitude", CsvSchema.ColumnType.NUMBER)
						.setUseHeader(true)
						.build()
						.withLineSeparator("\r");

				CsvMapper csvMapper = new CsvMapper();
				// output writer
				ObjectWriter myObjectWriter = csvMapper.writer(csvSchema);
				File tempFile = new File("users.csv");
				FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
				OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
				myObjectWriter.writeValue(writerOutputStream, myObjects);


				/*CsvSchema csvSchema = CsvSchema.builder()
						.addColumn("_id")
						.addColumn("name")
						.addColumn("type")
						.addColumn("latitude", CsvSchema.ColumnType.NUMBER)
						.addColumn("longitude", CsvSchema.ColumnType.NUMBER)
						.setUseHeader(true)
						.build()
						.withLineSeparator("\r");

				CsvMapper csvMapper = new CsvMapper();
				CsvFactory factory = mapper.getTypeFactory();
				CsvGenerator gen = factory.createGenerator(
						new File("test_csv.csv"),
						CsvEncoding.UTF8
				);
				gen.useDefaultPrettyPrinter();
				ObjectWriter objectWriter = csvMapper.writer(csvSchema);
				objectWriter.writeValue(gen, myObjects);*/

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
