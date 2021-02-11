# WeatherBOT
 A discord bot that gives users weather information.

### What is Weather.BOT?

My purpose of building this bot was to learn java language better, to understand the logic of using APIs and to present it as my java homework.

### While developing the project, I used:

🌤 For weather information: [OpenWeatherMap API](https://openweathermap.org/api)

👨‍💻 To develop a Discord bot: [JDA (Java Discord API)](https://github.com/DV8FromTheWorld/JDA)

📚 To extract the weather information sent as JSON: [org.json](https://mvnrepository.com/artifact/org.json/json)

### To make it work:
Install the IntelliJ Idea compiler. Right click on the project folder and select "Open folder as IntelliJ Idea Project". Before running the project:
* Enter your own discord bot token in the specified place in the main method in main.java. [Roadmap](https://www.writebots.com/discord-bot-token/) for integrating this project into your own bot, generating token and more.
```
...
JDABuilder jda = JDABuilder.createDefault("YOUR DİSCORD BOT TOKEN");
...
```

* Enter your own OpenWeatherMap API Key in the specified place in the returnCoords method in weather.java. Click [here](https://openweathermap.org/appid) to get OpenWeatherMAP API key and more.
```
public static Float returnCoords(String cityName,String coordType) throws IOException {
        String key = "YOUR OpenWeatherMap API KEY";
...
```
### That's all you have to do.
After that, you just need to invite the bot to your server (the [roadmap](https://www.writebots.com/discord-bot-token/) I gave above explains how to do it) and then run the project!

### To request weather information, first type $weather, leave a space and enter a city, state, etc. name. :)

![exampleImg](https://github.com/kemal576/WeatherBOT/blob/main/src/exampleImg/1.png?raw=true)
-
![exampleImg](https://github.com/kemal576/WeatherBOT/blob/main/src/exampleImg/2.png?raw=true)

Thank you for your time. 😊
