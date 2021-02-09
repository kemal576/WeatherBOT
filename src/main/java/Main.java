import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;

public class Main {
    public static String prefix = "$";
    public static void main(String[] args) throws LoginException {
        JDABuilder jda = JDABuilder.createDefault("YOUR DİSCORD BOT TOKEN(https://discord.com/developers/applications)");
        jda.setActivity(Activity.watching("Sky"));
        jda.addEventListeners(new Komutlar());
        jda.build();
    }
}
