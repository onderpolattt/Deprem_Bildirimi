package pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.*;

public class DepremGoster {

	public static void Gunlukkontrol(Date tarih) {
		
		// günlük deprem konrolü buradan yapılacak 
		// zaman parametre olarak gelmediği içöin kodların doğruluğunu tespit edemedim.
		// Başlangıç tarihinden gün ay yıl olarak karşılaştırma yaparak o gün deprem
		// olup olmadığını kontol edeceğim.
		
		// Date today = ....... ;
		// Date depremday=tarih ;
		// if (today.compareTo(d2) != 0){
		// System.out.println("Bugün depren olmadı"); }

	}

	public static void main(String[] args) {

		try {

			String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2022-01-01&endtime=2022-01-02";
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			ArrayList<Values> values = new ArrayList<Values>();

			JSONObject myrepo = new JSONObject(response.toString());

			List<Object> repo = new ArrayList<>();
			{
				int index = myrepo.getJSONArray("features").length();

				for (int i = 0; i < index; i++) {
					Values value = new Values();
					value.setCountry(myrepo.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.get("place").toString());
					String zaman = (myrepo.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.get("time").toString());
					value.setDate(zaman); // eğer gelen 13 haneli sayıyı zamana çevire bilseydim bu satır yerine altta ki 3 satır olacaktı.
					
					//LocalDate date = LocalDate.parse(zaman, DateTimeFormatter.BASIC_ISO_DATE);
					//value.setDate(date.toString());
					//Gunlukkontrol(date);

					value.setMag(myrepo.getJSONArray("features").getJSONObject(i).getJSONObject("properties").get("mag")
							.toString());
					value.setMagType(myrepo.getJSONArray("features").getJSONObject(i).getJSONObject("properties")
							.get("magType").toString());
					value.setCoordinates(myrepo.optJSONArray("features").getJSONObject(i).getJSONObject("geometry")
							.get("coordinates").toString());

					values.add(value);

					System.out.println(value.toString());

				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
