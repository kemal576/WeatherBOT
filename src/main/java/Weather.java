import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Weather {
    ArrayList<Integer> dt = new ArrayList<>();
    ArrayList<Integer> temp_day = new ArrayList<>();
    ArrayList<Integer> temp_night = new ArrayList<>();
    ArrayList<Integer> feels_day = new ArrayList<>();
    ArrayList<Integer> feels_night = new ArrayList<>();
    ArrayList<Integer> humidity = new ArrayList<>();
    ArrayList<Integer> wind_speed = new ArrayList<>();
    ArrayList<Integer> clouds = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String> main = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> icon_code = new ArrayList<>();

    public static String returnIconUrl(String iconCode) {
        return "http://openweathermap.org/img/wn/"+iconCode+"@2x.png"; }
    public void extractWeatherdata(JSONObject weatherData) {
        dt.clear(); temp_night.clear(); temp_day.clear(); feels_night.clear(); feels_day.clear(); humidity.clear();
        wind_speed.clear(); clouds.clear(); id.clear(); main.clear(); description.clear(); icon_code.clear();

        JSONArray daily = new JSONArray(weatherData.getJSONArray("daily").toString());
        JSONObject[] day = new JSONObject[5];
        for (int i=0;i<5;i++) {
            day[i] = daily.getJSONObject(i);
            JSONObject temp = new JSONObject(day[i].getJSONObject("temp").toString());
            JSONObject feels_like = new JSONObject(day[i].getJSONObject("feels_like").toString());
            JSONArray wth_detail = new JSONArray(day[i].getJSONArray("weather").toString());
            JSONObject wth = new JSONObject(wth_detail.getJSONObject(0).toString());

            dt.add(day[i].getInt("dt"));
            temp_day.add(temp.getInt("day"));
            temp_night.add(temp.getInt("night"));
            feels_day.add(feels_like.getInt("day"));
            feels_night.add(feels_like.getInt("night"));
            humidity.add(day[i].getInt("humidity"));
            wind_speed.add(day[i].getInt("wind_speed"));
            clouds.add(day[i].getInt("clouds"));
            id.add(wth.getInt("id"));
            main.add(wth.getString("main"));
            description.add(wth.getString("description"));
            icon_code.add(wth.getString("icon"));

        }



    }
    public static JSONObject returnWeatherData(String lat,String lon) throws IOException {
        String urll = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&exclude=minutely,hourly,alerts&appid=f567b5c611539e00b3d69f868cf02eee&units=metric&lang=en";
        URL url = new URL(urll);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
        in.close();

        JSONObject weatherData = new JSONObject(response.toString());
        return weatherData;
    }
    public static Float returnCoords(String cityName,String coordType) throws IOException {
        String key = "YOUR OpenWeatherMap API KEY";
        String urll = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + key + "&units=metric&mode=json&lang=en";
        URL url = new URL(urll);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
        in.close();

        JSONObject veriler = new JSONObject(response.toString());
        if (veriler.getFloat("cod") == 200) {
            JSONObject coords = new JSONObject(veriler.getJSONObject("coord").toString());
            return coords.getFloat(coordType);  }
        else
            return 404f;

    }
}





