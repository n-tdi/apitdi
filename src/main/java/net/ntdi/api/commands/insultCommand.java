package net.ntdi.api.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class insultCommand extends SubCommand {
    @Override
    public String getName() {
        return "insult";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Generate a insult";
    }

    @Override
    public String getSyntax() {
        return "/apitdi insult";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        try {

            URL url = new URL("https://evilinsult.com/generate_insult.php?lang=en&type=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                String obj = (String) data_obj.get("insult");

                commandSender.sendMessage("<Dr. Evil> " + obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}
