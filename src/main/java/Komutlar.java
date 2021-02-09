import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;

public class Komutlar extends ListenerAdapter {
    Weather wh = new Weather();
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
    {
        if (Kontrol.KontrolEt(event) && !event.getMember().getEffectiveName().equalsIgnoreCase("Weather.BOT")) {
            String cityName,lat = null,lon = null;
            cityName = event.getMessage().getContentRaw().split(" ",-1)[1];
            try {
                lat = Weather.returnCoords(cityName,"lat").toString();
                lon = Weather.returnCoords(cityName,"lon").toString();
            } catch (IOException e) { e.printStackTrace(); }
            try {
                wh.extractWeatherdata(Weather.returnWeatherData(lat,lon));
            } catch (IOException e) {
                e.printStackTrace();
            }
            EmbedBuilder eb = Kontrol.buildEmbed(wh,cityName);
            event.getChannel().sendMessage(eb.build()).queue();
        }


        else if (event.getMessage().getContentRaw().startsWith("$weather") && !Kontrol.KontrolEt(event) && !event.getMember().getEffectiveName().equalsIgnoreCase("Selami"))
            event.getChannel().sendMessage("INVALID CITY NAME").queue();
    }
}
