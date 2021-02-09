import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.io.IOException;

public class Kontrol {
    public static Boolean KontrolEt(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith(Main.prefix+"weather")) {
            if (event.getMessage().getContentRaw().split(" ",2).length == 1) {
                event.getChannel().sendMessage("Yetersiz parametre girdiniz! Lutfen bosluk birakip sehir ismini giriniz..").queue();
                return false;
            }
            else if (event.getMessage().getContentRaw().split(" ",-1).length == 2) {
                String cityName = event.getMessage().getContentRaw().split(" ", -1)[1];
                try {
                    if (Weather.returnCoords(cityName,"lat") == 404f)
                    {event.getChannel().sendMessage("Yanlıs sehir adı girildi!!!").queue();
                    return false;}
                    else
                    { return true; }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                event.getChannel().sendMessage("HATALI GIRIS!").queue();
                return false;
            }
        }
        return false;
    }
    public static EmbedBuilder buildEmbed(Weather wh,String cityName) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Weather Information of "+cityName.toUpperCase()+" // "+Kontrol.returnDate(wh.dt.get(0))+" "+Kontrol.returnDay(wh.dt.get(0)));
        eb.addField("Temperature (D/N)",wh.temp_day.get(0).toString()+"C / "+wh.temp_night.get(0).toString()+"C",true);
        eb.addField("Feels Like (D/N)",wh.feels_day.get(0).toString()+"C / "+wh.feels_night.get(0).toString()+"C",true);
        eb.addField("Humidity","%"+wh.humidity.get(0).toString(),true);
        eb.addField("Clouds","%"+wh.clouds.get(0).toString(),true);
        eb.addField("Wind Speed",wh.wind_speed.get(0).toString()+" m/s",true);
        eb.addField("Description",wh.description.get(0).toUpperCase(),true);
        eb.setFooter("(D/N) :  Day/Night ,  C : Celcius ,  m/s :  meter/second\nDate Format: dd//MM//yyyy");
        eb.setColor(Color.orange);
        eb.setThumbnail(Weather.returnIconUrl(wh.icon_code.get(0)));
        return eb;
    }
    public static String returnDate(Integer dt) {
        long unix_seconds = dt;
        Date date = new Date(unix_seconds*1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return jdf.format(date);
    }
    public static String returnDay(Integer dt) {
        long unix_seconds = dt;
        Date date = new Date(unix_seconds*1000L);
        int dayCode = date.getDay();
        return switch (dayCode) {
            case 0 -> "SUNDAY";
            case 1 -> "MONDAY";
            case 2 -> "TUESDAY";
            case 3 -> "WEDNESDAY";
            case 4 -> "THURSDAY";
            case 5 -> "FRIDAY";
            case 6 -> "SATURDAY";
            default -> "MISSING";
        };
    }
}
