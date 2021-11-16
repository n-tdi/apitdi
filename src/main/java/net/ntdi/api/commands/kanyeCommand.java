package net.ntdi.api.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class kanyeCommand extends SubCommand {

    private static HttpURLConnection connection;

    @Override
    public String getName() {
        return "kanye";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Get a random Kanye Quote";
    }

    @Override
    public String getSyntax() {
        return "/apitdi kanye";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        try {

            URL url = new URL("https://api.kanye.rest/");

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

                //Get the required object from the above created object
//                JSONObject obj = (JSONObject) data_obj.get("Global");
//
//                //Get the required data using its key
//                System.out.println(obj.get("TotalRecovered"));
//
//                JSONArray arr = (JSONArray) data_obj.get("Countries");
//
//                for (int i = 0; i < arr.size(); i++) {
//
//                    JSONObject new_obj = (JSONObject) arr.get(i);
//
//                    if (new_obj.get("Slug").equals("albania")) {
//                        System.out.println("Total Recovered: " + new_obj.get("TotalRecovered"));
//                        break;
//                    }
//                }

                String obj = (String) data_obj.get("quote");

                commandSender.sendMessage("<Kanye> " + obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }

    public String GetJSONValue(String JSONString, String Field)
    {
        return JSONString.substring(JSONString.indexOf(Field), JSONString.indexOf("\n", JSONString.indexOf(Field))).replace(Field+"\": \"", "").replace("\"", "").replace(",","");
    }
}
