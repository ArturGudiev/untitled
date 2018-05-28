package mine;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherStatusResponse;
import org.json.JSONException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Weather {
    public static void main2(String[] args) throws IOException, JSONException {
        OwmClient owm = new OwmClient ();
        WeatherStatusResponse currentWeather = owm.currentWeatherAtCity ("{cityName: Tokyo}");
        if (currentWeather.hasWeatherStatus ()) {
            WeatherData weather = currentWeather.getWeatherStatus ().get (0);
            if (weather.getPrecipitation () == Integer.MIN_VALUE) {
                WeatherData.WeatherCondition weatherCondition = weather.getWeatherConditions ().get (0);
                String description = weatherCondition.getDescription ();
                if (description.contains ("rain") || description.contains ("shower"))
                    System.out.println ("No rain measures in Tokyo but reports of " + description);
                else
                    System.out.println ("No rain measures in Tokyo: " + description);
            } else
                System.out.println ("It's raining in Tokyo: " + weather.getPrecipitation () + " mm/h");
        }
    }

    public static void main(String[] args) throws JAXBException, IOException {
        YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2502265", DegreeUnit.CELSIUS);
        System.out.println(channel.getDescription());
    }
}
